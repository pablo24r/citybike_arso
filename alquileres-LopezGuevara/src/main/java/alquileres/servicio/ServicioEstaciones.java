package alquileres.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alquileres.modelo.Bicicleta;
import alquileres.modelo.Estacion;
import alquileres.repositorio.RepositorioEstaciones;
import repositorio.EntidadNoEncontrada;

@Service
public class ServicioEstaciones implements IServicioEstaciones{
	
	private RepositorioEstaciones repositorio;
	
	@Autowired
	public ServicioEstaciones(RepositorioEstaciones repositorio) {
		this.repositorio=repositorio;
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
		String id = repositorio.save(estacion).getId();
		return id;
	}



	@Override
	public String darAltaBicicleta(String modelo, String idEstacion) {
		
		Estacion estacion = repositorio.findById(idEstacion).get();
		if(estacion.getListadoBicicletas().size()<estacion.getNumPuestos()) {
			Bicicleta bici = new Bicicleta(modelo);
			bici.setFechaAlta(LocalDateTime.now());
			estacion.getListadoBicicletas().add(bici);
			bici.setDisponible(true);
			repositorio.save(estacion).getId();
			// falta establecer id de la bici al añadir 
			return null;
		}
		else
			return null;
		
	}



	@Override
	public void darBajaBicicleta(String idBici, String motivo) {
		
		
	}



	@Override
	public List<Bicicleta> getListadoBicicletas(String idEstacion) {
		Estacion estacion = repositorio.findById(idEstacion).get();
		return estacion.getListadoBicicletas();
	}



	@Override
	public List<Estacion> getListadoEstaciones() {
		List<Estacion> estaciones = new LinkedList<Estacion>();
		for (Estacion e: repositorio.findAll()) {
			estaciones.add(e);
		}
		return estaciones;
	}



	@Override
	public String getInfoEstacion(String idEstacion) {
		Estacion estacion = repositorio.findById(idEstacion).get();
		String info = "Nombre: " + estacion.getNombre() + "\n";
		info += "Dirección postal: " + estacion.getDireccionPostal() + "\n";
		info += "Coordenadas: " + estacion.getCoordenadas() + "\n";
		info += "Huecos libres de aparcamiento: " + (estacion.getNumPuestos() - estacion.getListadoBicicletas().size()) + "\n";
		return info;
	}



	@Override
	public List<Bicicleta> getBicisDisponibles(String idEstacion) {
		Estacion estacion = repositorio.findById(idEstacion).get();
		List<Bicicleta> bicis = new LinkedList<Bicicleta>();
		for (Bicicleta b: estacion.getListadoBicicletas()){
			if(b.isDisponible())
				bicis.add(b);
		}
		return bicis;
	}



	@Override
	public void estacionarBicicleta(String idEstacion, String idBicicleta) {
		Estacion estacion = repositorio.findById(idEstacion).get();
		if(estacion.getListadoBicicletas().size()<estacion.getNumPuestos()) {
			//falta esto tambien
		}
	}

	@Override
	public Estacion getEstacion(String idEstacion) throws EntidadNoEncontrada {


			if (idEstacion == null || idEstacion.isEmpty())
				throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

			Optional<Estacion> estacion= repositorio.findById(idEstacion);
			
			if(! estacion.isPresent())
				throw new EntidadNoEncontrada("No existe la encuesta:" + idEstacion);
			else
				return estacion.get();
	}

}
