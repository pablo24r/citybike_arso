package alquileres.tests;

import javax.jws.WebService;

@WebService(endpointInterface = "alquileres.tests.Saludo", targetNamespace = "http://um.es/arso")
public class SaludoImpl implements Saludo {

	@Override
	public String getSaludo(String nombre) {
		return "Hola " + nombre;
	}
}