package estaciones.test;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import estaciones.EstacionesApplication;
import estaciones.servicio.IServicioEstaciones;

public class ProgramaPruebaServicioEstaciones {
    public static void main(String[] args) {

        ConfigurableApplicationContext contexto = SpringApplication.run(EstacionesApplication.class, args);

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
        System.out.println(servicio.getListadoBicicletasDTO(idEstacion, null));

        // Obtener listado de todas las estaciones
        System.out.println("Listado de todas las estaciones:");
        System.out.println(servicio.getListadoEstacionesDTO(null));


        // Obtener listado de bicicletas disponibles de una estación
        System.out.println("Listado de bicicletas disponibles de la estación:");
        System.out.println(servicio.getBicisDisponibles(idEstacion, null));

        servicio.estacionarBicicleta(idEstacion, idBicicleta);
		System.out.println("Bicicleta estacionada correctamente en la estación.");

        contexto.close();
    }
}
