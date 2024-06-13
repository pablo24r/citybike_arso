package estaciones.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import estaciones.modelo.BicicletaDTO;
import estaciones.modelo.Estacion;
import estaciones.modelo.EstacionDTO;
import estaciones.servicio.IServicioEstaciones;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import repositorio.EntidadNoEncontrada;

@RestController
@RequestMapping("/estaciones")
@Tag(name = "Estaciones", description = "Aplicación de estaciones")
public class EstacionesControladorRest {

	private IServicioEstaciones servicio;

	@Autowired
	public EstacionesControladorRest(IServicioEstaciones servicio) {
		this.servicio = servicio;
	}

	@Autowired
	private PagedResourcesAssembler<EstacionDTO> pagedEstacionDTOAssembler;

	@Autowired
	private PagedResourcesAssembler<BicicletaDTO> pagedBicicletaDTOAssembler;

	// http:localhost:8080/estaciones{id}
	@Operation(summary = "Obtener información de una estación", description = "Obtiene la información detallada de una estación por su ID")
	@GetMapping("/{id}")
	public ResponseEntity<EstacionDTO> getInfoEstacion(@PathVariable String id) throws EntidadNoEncontrada {
		EstacionDTO estacion = Estacion.toDTO(servicio.getEstacion(id));
		return ResponseEntity.ok(estacion); 
	}

	// http:localhost:8080/estaciones
	@Operation(summary = "Obtener estaciones", description = "Obtiene un listado paginado de todas las estaciones")
	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<EstacionDTO>>> getEstaciones(@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) throws EntidadNoEncontrada {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<EstacionDTO> estacionesPage = servicio.getListadoEstacionesDTO(pageable);

	    PagedModel<EntityModel<EstacionDTO>> pagedModel = pagedEstacionDTOAssembler.toModel(estacionesPage, estacionDTO -> {
	        EntityModel<EstacionDTO> estacionDTOModel = EntityModel.of(estacionDTO);

	        WebMvcLinkBuilder linkToBicicletasDisponibles = WebMvcLinkBuilder
	                .linkTo(WebMvcLinkBuilder.methodOn(EstacionesControladorRest.class)
	                        .getBicicletasDisponiblesEstacion(estacionDTO.getId(), page, size));

	        estacionDTOModel.add(linkToBicicletasDisponibles.withRel("bicicletasDisponibles:"));

	        return estacionDTOModel;
	    });

	    return ResponseEntity.ok(pagedModel); // 200 OK
	}


	// http:localhost:8080/estaciones/{idEstacion}/bicicletasDisponibles
	@Operation(summary = "Obtener bicicletas disponibles en una estación", description = "Obtiene el listado de bicicletas disponibles en una estación específica")
	@GetMapping("/{idEstacion}/bicicletasDisponibles")
	public ResponseEntity<Page<BicicletaDTO>> getBicicletasDisponiblesEstacion(@PathVariable String idEstacion,
	        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<BicicletaDTO> bicicletasPage = servicio.getBicisDisponibles(idEstacion, pageable);
	    
	    return ResponseEntity.ok(bicicletasPage);
	}


	// http:localhost:8080/estaciones/{idEstacion}/bicicletas
	@Operation(summary = "Obtener todas las bicicletas de una estación", description = "Obtiene el listado completo de bicicletas en una estación específica")
	@GetMapping("/{idEstacion}/bicicletas")
	public ResponseEntity<PagedModel<EntityModel<BicicletaDTO>>> getBicicletasByEstacion(@PathVariable String idEstacion,
	        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<BicicletaDTO> bicicletasPage = servicio.getListadoBicicletasDTO(idEstacion, pageable);

	    PagedModel<EntityModel<BicicletaDTO>> pagedModel = pagedBicicletaDTOAssembler.toModel(bicicletasPage, bicicletaDTO -> {
	        EntityModel<BicicletaDTO> bicicletaDTOModel = EntityModel.of(bicicletaDTO);

	        // Construir el enlace para dar de baja la bicicleta
	        WebMvcLinkBuilder linkToBajaBicicleta = WebMvcLinkBuilder
	                .linkTo(WebMvcLinkBuilder.methodOn(EstacionesControladorRest.class)
	                        .darBajaBicicleta(bicicletaDTO.getId(), "enlace-dar-de-baja"));

	        // Agregar el enlace al modelo de la bicicleta
	        bicicletaDTOModel.add(linkToBajaBicicleta.withRel("darBaja").withType("PUT"));

	        return bicicletaDTOModel;
	    });

	    return ResponseEntity.ok(pagedModel);
	}


	// curl -X POST http://localhost:8080/estaciones/662f6c7c8b431333486f662f/nuevaBicicleta?modeloBici=model1999
	@Operation(summary = "Agregar una nueva bicicleta a una estación", description = "Agrega una nueva bicicleta a una estación específica utilizando el modelo de bicicleta proporcionado")
	@PostMapping("/{idEstacion}/bicicletas")
	public ResponseEntity<Map<String, String>> nuevaBicicleta(@PathVariable String idEstacion, @RequestParam String modelo) {
	    String id = servicio.darAltaBicicleta(modelo, idEstacion);
	    Map<String, String> responseBody = new HashMap<>();
	    responseBody.put("mensaje", "Bicicleta con id: " + id + " añadida a la estación: " + idEstacion);
	    return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
	}


// curl -X POST http://localhost:8080/estaciones?nombre=Estacion%20Lorca&numPuestos=33&direccion=Av%20JuanCarlos%20l&coordenadas=N%20100%2C%20W%20200
	@Operation(summary = "Crear una nueva estación", description = "Crea una nueva estación con los detalles proporcionados")
	@PostMapping
	public ResponseEntity<Object> nuevaEstacion(@RequestParam String nombre, @RequestParam int numPuestos,
	        @RequestParam String direccion, @RequestParam String coordenadas) {
	    String id = servicio.darAltaEstacion(nombre, numPuestos, direccion, coordenadas);
	    String location = ServletUriComponentsBuilder.fromCurrentRequest()
	                                                 .path("/{id}")
	                                                 .buildAndExpand(id)
	                                                 .toUriString();
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Location", location);
	    return ResponseEntity.status(HttpStatus.CREATED)
	                         .headers(headers)
	                         .body("Estación creada con id: " + id);
	}

	// curl -X PUT http://localhost:8080/estaciones/baja/{idBicicleta}
	@Operation(summary = "Dar de baja una bicicleta", description = "Da de baja una bicicleta específica utilizando su ID y el motivo proporcionado")
	@PutMapping("/baja/{idBicicleta}")
	public ResponseEntity<Object> darBajaBicicleta(@PathVariable String idBicicleta, @RequestParam String motivo) {
		servicio.darBajaBicicleta(idBicicleta, motivo);
		return new ResponseEntity<>("Bicicleta con id: " + idBicicleta + " dada de baja debido a: " + motivo,
				HttpStatus.OK);
	}

	
	// curl -X POST http://localhost:8080/estaciones/{idEstacion}/estacionar/{idBicicleta} 
	@Operation(summary = "Estacionar una bicicleta", description = "Estaciona una bicicleta en una estación específica utilizando sus IDs")
	@PostMapping("/{idEstacion}/estacionar/{idBicicleta}")
	public ResponseEntity<EstacionDTO> estacionarBicicleta(@PathVariable String idEstacion, @PathVariable String idBicicleta)
	        throws EntidadNoEncontrada {
	    servicio.estacionarBicicleta(idEstacion, idBicicleta);
	    EstacionDTO estacionDTO = Estacion.toDTO(servicio.getEstacion(idEstacion));
	    return ResponseEntity.ok().body(estacionDTO);
	}

}