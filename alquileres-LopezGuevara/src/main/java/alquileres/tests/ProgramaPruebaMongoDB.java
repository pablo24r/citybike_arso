package alquileres.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import alquileres.AlquileresApp;
import alquileres.modelo.Usuario;
import alquileres.repositorio.RepositorioUsuariosMongoDB;
import alquileres.servicio.IServicioAlquileres;
import alquileres.servicio.ServicioEstaciones;
import servicio.FactoriaServicios;

public class ProgramaPruebaMongoDB {
	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = SpringApplication.run(AlquileresApp.class, args);

		RepositorioUsuariosMongoDB repositorioUsuarios = new RepositorioUsuariosMongoDB();
		IServicioAlquileres servicioAlq = FactoriaServicios.getServicio(IServicioAlquileres.class);

		ServicioEstaciones servicioEst = contexto.getBean(ServicioEstaciones.class);

		try {

			Usuario user1 = new Usuario();
			String id = repositorioUsuarios.add(user1); // Agregar usuario al repositorio y obtener el ID
			user1.setId(id); // Asignar el ID al usuario

			servicioAlq.alquilar(id, "bici1");
			servicioAlq.dejarBicicleta(id, "Estacion1");

			servicioAlq.reservar(id, "bici2");

			// servicioAlq.alquilar("pruebaUsuario123", "bici 123123");

			// Obtener el usuario del repositorio usando el ID
			Usuario usuarioRecuperado = repositorioUsuarios.getById(id);
			System.out.println("usuario recuperado: " + usuarioRecuperado.toString()); // Imprimir el usuario recuperado

			System.out.println("Usuarios en el repositorio (" + repositorioUsuarios.getAll().size() + "): \n"
					+ repositorioUsuarios.getAll());

			String idEst = servicioEst.darAltaEstacion("estacion1", 10, "calle estacion", "0,0,0,0");

			servicioEst.darAltaBicicleta("Domyos", idEst);

			System.out.println(servicioEst.getInfoEstacion(idEst) + "\n" + idEst);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
