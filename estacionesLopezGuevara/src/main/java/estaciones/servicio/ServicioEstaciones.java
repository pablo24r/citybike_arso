package estaciones.servicio;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import estaciones.modelo.Bicicleta;
import estaciones.modelo.Estacion;
import estaciones.repositorio.RepositorioBicicletas;
import estaciones.repositorio.RepositorioEstaciones;
import repositorio.EntidadNoEncontrada;

@Service
public class ServicioEstaciones implements IServicioEstaciones{
	
	private RepositorioEstaciones repoEstaciones;
	private RepositorioBicicletas repoBicis;
	
	@Autowired
	public ServicioEstaciones(RepositorioEstaciones repoEstaciones, RepositorioBicicletas repoBicis) {
		this.repoEstaciones=repoEstaciones;
		this.repoBicis=repoBicis;
	}
	
	public boolean hayHuecoDisponible(String idEstacion) {
		return true;
	}
	
	public boolean dejarBicicleta(String idUsuario, String idEstacion) {
		return true;
	}


	@Override
	public String darAltaEstacion(String nombre, int numPuestos, String dirPostal, String coordenadas) {
		Estacion estacion = new Estacion(nombre, numPuestos, dirPostal, coordenadas);
		estacion.setFechaAlta(LocalDateTime.now());
		String id = repoEstaciones.save(estacion).getId();
		return id;
	}



	@Override
	public String darAltaBicicleta(String modelo, String idEstacion) {
		
		Estacion estacion = repoEstaciones.findById(idEstacion).get();
		if(estacion.getListadoBicicletas().size()<estacion.getNumPuestos()) {
			Bicicleta bici = new Bicicleta(modelo);
			bici.setFechaAlta(LocalDateTime.now());
			estacion.getListadoBicicletas().add(bici);
			bici.setDisponible(true);
			bici.setIdEstacion(idEstacion);
			bici.setId(repoBicis.save(bici).getId());
			estacion.setNumPuestos(estacion.getNumPuestos()-1);
			repoEstaciones.save(estacion).getId(); 
			return bici.getId();
		}
		else
			return null;
		
	}



	@Override
	public void darBajaBicicleta(String idBici, String motivo) {
	    Optional<Bicicleta> optionalBici = repoBicis.findById(idBici);
	    if (!optionalBici.isPresent()) {
	        throw new IllegalArgumentException("Bicicleta no encontrada: " + idBici);
	    }
	    
	    Bicicleta bici = optionalBici.get();
	    
	    Optional<Estacion> optionalEstacion = repoEstaciones.findById(bici.getIdEstacion());
	    if (!optionalEstacion.isPresent()) {
	        throw new IllegalArgumentException("Estación no encontrada: " + bici.getIdEstacion());
	    }
	    
	    Estacion estacion = optionalEstacion.get();
	    
	    bici.setFechaBaja(LocalDateTime.now());
	    bici.setIdEstacion(null);
	    bici.setDisponible(false);

	    // Actualizar la bicicleta en la base de datos primero
	    repoBicis.save(bici);

	    // Eliminar la bicicleta de la lista de bicicletas de la estación
	    estacion.getListadoBicicletas().removeIf(b -> b.getId().equals(bici.getId()));
	    estacion.setNumPuestos(estacion.getNumPuestos()+1);

	    // Guardar la estación actualizada
	    repoEstaciones.save(estacion);
	}




	@Override
	public List<Bicicleta> getListadoBicicletas(String idEstacion) {
		Estacion estacion = repoEstaciones.findById(idEstacion).get();
		return estacion.getListadoBicicletas();
	}



	@Override
	public Page<Estacion> getListadoEstaciones(Pageable pageable) {
		return repoEstaciones.findAll(pageable);
	}



	@Override
	public ResponseEntity<Object>  getInfoEstacion(String idEstacion) throws EntidadNoEncontrada {
		if (idEstacion == null || idEstacion.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Optional<Estacion> estacion = repoEstaciones.findById(idEstacion);
		
		if(! estacion.isPresent())
			throw new EntidadNoEncontrada("No existe la estación:" + idEstacion);
		else
		{
			// Crear un objeto personalizado para devolver como JSON
			LinkedHashMap<String, Object> response = new LinkedHashMap<>();
	        response.put("nombre", estacion.get().getNombre());
	        response.put("id", estacion.get().getId());
	        response.put("direccionPostal", estacion.get().getDireccionPostal());
	        response.put("coordenadas", estacion.get().getCoordenadas());
	        response.put("fecha de alta", estacion.get().getFechaAlta());
	        response.put("huecos disponibles", estacion.get().getNumPuestos());
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}



	@Override
	public List<Bicicleta> getBicisDisponibles(String idEstacion) {
		Estacion estacion = repoEstaciones.findById(idEstacion).get();
		List<Bicicleta> bicis = new LinkedList<Bicicleta>();
		for (Bicicleta b: estacion.getListadoBicicletas()){
			if(b.isDisponible())
				bicis.add(b);
		}
		return bicis;
	}



	@Override
	public void estacionarBicicleta(String idEstacion, String idBici) {
		Estacion estacion = repoEstaciones.findById(idEstacion).get();
		Bicicleta bici = repoBicis.findById(idBici).get();
		if(estacion.getListadoBicicletas().size()<estacion.getNumPuestos()) {
			estacion.getListadoBicicletas().add(bici);
			bici.setIdEstacion(idEstacion);
			bici.setFechaBaja(null);
			bici.setDisponible(true);
			repoBicis.save(bici);
			estacion.setNumPuestos(estacion.getNumPuestos()-1);
			repoEstaciones.save(estacion);
		}
	}

	@Override
	public Estacion getEstacion(String idEstacion) throws EntidadNoEncontrada {
			if (idEstacion == null || idEstacion.isEmpty())
				throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

			Optional<Estacion> estacion= repoEstaciones.findById(idEstacion);
			
			if(! estacion.isPresent())
				throw new EntidadNoEncontrada("No existe la estación:" + idEstacion);
			else
				return estacion.get();
	}

}
