package alquileres.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import alquileres.AlquileresApp;
import alquileres.servicio.IServicioEstaciones;

public class ProgramaPruebaServicioEstaciones {
    public static void main(String[] args) {

        ConfigurableApplicationContext contexto = SpringApplication.run(AlquileresApp.class, args);

        IServicioEstaciones servicio = contexto.getBean(IServicioEstaciones.class);

        // Alta de una estación
        String idEstacion = servicio.darAltaEstacion("EstacionPrueba1", 100, "30800", "N 20 W 233");
        System.out.println("Estación creada con ID: " + idEstacion);

        // Alta de una bicicleta
        String idBicicleta = servicio.darAltaBicicleta("Modelo MNT BIKE", idEstacion);
        System.out.println("Bicicleta creada con ID: " + idBicicleta);

        // Dar de baja una bicicleta
        servicio.darBajaBicicleta(idBicicleta, "Mantenimiento");

        // Obtener listado de bicicletas de una estación
        System.out.println("Listado de bicicletas de la estación:");
        System.out.println(servicio.getListadoBicicletas(idEstacion));

        // Obtener listado de todas las estaciones
        System.out.println("Listado de todas las estaciones:");
        System.out.println(servicio.getListadoEstaciones());

        System.out.println("Información de la estación:");
		System.out.println(servicio.getInfoEstacion(idEstacion));

        // Obtener listado de bicicletas disponibles de una estación
        System.out.println("Listado de bicicletas disponibles de la estación:");
        System.out.println(servicio.getBicisDisponibles(idEstacion));

        servicio.estacionarBicicleta(idEstacion, idBicicleta);
		System.out.println("Bicicleta estacionada correctamente en la estación.");

        contexto.close();
    }
}
