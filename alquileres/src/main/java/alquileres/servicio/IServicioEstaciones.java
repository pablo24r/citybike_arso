package alquileres.servicio;

public interface IServicioEstaciones {

	default boolean hayHuecoDisponible() {
		return true;
	}
	
	default boolean dejarBicicleta() {
		return true;
	}
}
