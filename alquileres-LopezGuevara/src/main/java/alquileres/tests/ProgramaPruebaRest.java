package alquileres.tests;

import javax.xml.ws.Endpoint;

import alquileres.servicio.ServicioAlquileres;

public class ProgramaPruebaRest {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8080/ws/alquileres", new ServicioAlquileres());

		System.out.println("Servicio funcionando");
	}

}
