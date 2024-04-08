package alquileres.servicio;

public interface IServicioEstaciones {

	public boolean hayHuecoDisponible(String idEstacion);
	
	public boolean dejarBicicleta(String idUsuario, String idEstacion);
}
