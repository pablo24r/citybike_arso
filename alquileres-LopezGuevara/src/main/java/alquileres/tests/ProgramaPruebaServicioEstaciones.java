package alquileres.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import alquileres.AlquileresApp;
import alquileres.servicio.IServicioEstaciones;

public class ProgramaPruebaServicioEstaciones {
	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = SpringApplication.run(AlquileresApp.class, args);

		IServicioEstaciones servicio = contexto.getBean(IServicioEstaciones.class);
 
		String idEstacion = servicio.darAltaEstacion("EstacionPrueba1", 100, "30800", "N 20 W 233");
		servicio.darAltaBicicleta("Modelo MNT BIKE", idEstacion);
		System.out.println(servicio.getInfoEstacion(idEstacion));
		
		contexto.close();
	}
}
