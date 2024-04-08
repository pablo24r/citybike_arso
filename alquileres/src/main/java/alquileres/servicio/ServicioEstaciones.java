package alquileres.servicio;

public class ServicioEstaciones implements IServicioEstaciones{
	public boolean hayHuecoDisponible(String idEstacion) {
		return true;
	}
	
	public boolean dejarBicicleta(String idUsuario, String idEstacion) {
		return true;
	}

}
