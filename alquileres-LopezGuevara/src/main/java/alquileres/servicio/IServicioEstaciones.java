package alquileres.servicio;
import java.util.List;

import alquileres.modelo.Bicicleta;
import alquileres.modelo.Estacion;
import repositorio.EntidadNoEncontrada;

public interface IServicioEstaciones {
	// Gestor
	public String darAltaEstacion(String nombre, int numPuestos, String dirPostal, String coordenadas);

	public String darAltaBicicleta(String modelo, String idEstacion);
	
	public void darBajaBicicleta(String idBici, String motivo);
	
	public List<Bicicleta> getListadoBicicletas(String idEstacion);
	
	// Todos los usuarios
	public List<Estacion> getListadoEstaciones();
	
	public String getInfoEstacion(String idEstacion);
	
	public List<Bicicleta> getBicisDisponibles(String idEstacion);
	
	public void estacionarBicicleta(String idEstacion, String idBicicleta);
	
	public Estacion getEstacion(String idEstacion) throws EntidadNoEncontrada;
	
	public boolean hayHuecoDisponible(String idEstacion);
	
	public boolean dejarBicicleta(String idUsuario, String idEstacion);
	
	public void limpiar();
}
