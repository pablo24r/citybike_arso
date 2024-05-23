package estaciones.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import estaciones.modelo.Bicicleta;
import estaciones.modelo.Estacion;
import estaciones.servicio.IServicioEstaciones;
import repositorio.EntidadNoEncontrada;

@RestController
@RequestMapping("/estaciones")
public class EstacionesControladorRest {

	private IServicioEstaciones servicio;

	@Autowired
	public EstacionesControladorRest(IServicioEstaciones servicio) {
		this.servicio = servicio;
	}

	// curl -X GET "http://localhost:8080/estaciones/{id}"
	@GetMapping("/{id}")
	public ResponseEntity<Object> getInfoEstacion(@PathVariable String id) throws EntidadNoEncontrada {
		return servicio.getInfoEstacion(id);
	}

	// curl -X GET "http://localhost:8080/estaciones?page=0&size=5"
    @GetMapping
    public PagedModel<EntityModel<Estacion>> getEstaciones (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) throws EntidadNoEncontrada {
        Pageable pageable = PageRequest.of(page, size);
        Page<Estacion> estacionesPage = servicio.getListadoEstaciones(pageable);

        List<EntityModel<Estacion>> estacionesModel = estacionesPage.stream()
                .map(estacion -> {
                    EntityModel<Estacion> estacionModel = EntityModel.of(estacion);

                    WebMvcLinkBuilder linkToInformacionEstacion = null;
					try {
						linkToInformacionEstacion = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstacionesControladorRest.class)
						        .getInfoEstacion(estacion.getId()));
					} catch (EntidadNoEncontrada e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                    WebMvcLinkBuilder linkToBicicletasDisponibles = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstacionesControladorRest.class)
                            .getBicicletasDisponiblesEstacion(estacion.getId()));

                    estacionModel.add(linkToInformacionEstacion.withRel("Más información sobre esta estación:"));
                    estacionModel.add(linkToBicicletasDisponibles.withRel("Bicicletas disponibles en esta estación:"));

                    return estacionModel;
                })
                .collect(Collectors.toList());

        return PagedModel.of(estacionesModel, new PagedModel.PageMetadata(size, page, estacionesPage.getTotalElements()));
    }

	// curl -X GET "http://localhost:8080/estaciones/{idEstacion}/bicicletasDisponibles"
	@GetMapping("/{idEstacion}/bicicletasDisponibles")
	public List<Bicicleta> getBicicletasDisponiblesEstacion(@PathVariable String idEstacion) {
		return servicio.getBicisDisponibles(idEstacion);
	}

	// curl -X GET "http://localhost:8080/estaciones/{idEstacion}/bicicletas"
	@GetMapping("/{idEstacion}/bicicletas")
	public List<EntityModel<Bicicleta>> getBicicletasByEstacion(@PathVariable String idEstacion) {
	    List<Bicicleta> bicicletas = servicio.getListadoBicicletas(idEstacion);
	    List<EntityModel<Bicicleta>> bicicletasModel = new ArrayList<>();
	    
	    for (Bicicleta bicicleta : bicicletas) {
	        EntityModel<Bicicleta> bicicletaModel = EntityModel.of(bicicleta);
	        String bicicletaId = bicicleta.getId();
	        
	        // Construir el enlace para dar de baja la bicicleta
	        WebMvcLinkBuilder linkToBajaBicicleta = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstacionesControladorRest.class)
	                .darBajaBicicleta(bicicletaId, "enlace-dar-de-baja"));
	        
	        // Agregar el enlace al modelo de la bicicleta
	        bicicletaModel.add(linkToBajaBicicleta.withRel("darBaja").withType("POST"));
	        bicicletasModel.add(bicicletaModel);
	    }
	    
	    return bicicletasModel;
	}



	// curl -X POST "http://localhost:8080/estaciones/664f45fea1e68a23f78be3a0/nuevaBicicleta?modeloBici=ModeloDePrueba"
	@PostMapping("/{idEstacion}/nuevaBicicleta")
	public ResponseEntity<Object> nuevaBicicleta(@PathVariable String idEstacion, @RequestParam String modeloBici) {
		String id = servicio.darAltaBicicleta(modeloBici, idEstacion);
		return new ResponseEntity<>("Bicicleta con id: " + id + " añadida a la estación: " + idEstacion,
				HttpStatus.CREATED);
	}

	// curl -X POST "http://localhost:8080/estaciones/nuevaEstacion" -d "nombre=NombreDePrueba" -d "numPuestos=10" -d "direccion=DireccionDePrueba" -d "coordenadas=CoordenadasDePrueba"
	@PostMapping("/nuevaEstacion")
	public ResponseEntity<Object> nuevaEstacion(@RequestParam String nombre, @RequestParam int numPuestos,
			@RequestParam String direccion, @RequestParam String coordenadas) {
		String id = servicio.darAltaEstacion(nombre, numPuestos, direccion, coordenadas);
		return new ResponseEntity<>("Estación creada con id: " + id, HttpStatus.CREATED);
	}
	
	// curl -X POST "http://localhost:8080/estaciones/baja/{idBicicleta}?motivo=RuedaPinchada"
	@PostMapping("/baja/{idBicicleta}")
	public ResponseEntity<Object> darBajaBicicleta(@PathVariable String idBicicleta, @RequestParam String motivo) {
		servicio.darBajaBicicleta(idBicicleta, motivo);
		return new ResponseEntity<>("Bicicleta con id: " + idBicicleta + " dada de baja debido a: " + motivo,
				HttpStatus.OK);
	}

	// curl -X POST "http://localhost:8080/estaciones/{idEstacion}/estacionar/{idBicicleta}"
	@PostMapping("/{idEstacion}/estacionar/{idBicicleta}")
	public ResponseEntity<Object> estacionarBicicleta(@PathVariable String idEstacion,
			@PathVariable String idBicicleta) {
		servicio.estacionarBicicleta(idEstacion, idBicicleta);
		return new ResponseEntity<>("Bicicleta con id: " + idBicicleta + " estacionada en la estación: " + idEstacion,
				HttpStatus.OK);
	}
	
}

/*
 * <dependency> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-security</artifactId> </dependency>
 */