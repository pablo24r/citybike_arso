package saludo.soap;

import es.um.arso.IServicioAlquileres;
import es.um.arso.ServicioAlquileresService;

public class Programa {

	public static void main(String[] args) {

		ServicioAlquileresService servicioAlq = new ServicioAlquileresService();
		IServicioAlquileres puertoAlq = servicioAlq.getServicioAlquileresPort();
		System.out.println(puertoAlq.obtenerId());

//		SaludoImplService servicio = new SaludoImplService();
//		Saludo puerto = servicio.getSaludoImplPort();
//		System.out.println(puerto.getSaludo("Pepe"));
	}
}
