package alquileres.tests;

import alquileres.modelo.Usuario;
import alquileres.repositorio.RepositorioUsuariosMongoDB;
import alquileres.servicio.IServicioAlquileres;
import servicio.FactoriaServicios;

public class ProgramaPruebaMongoDB {
	public static void main(String[] args) {


		RepositorioUsuariosMongoDB repositorio = new RepositorioUsuariosMongoDB();
		IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);


		try {

			Usuario user1 = new Usuario();
			String id = repositorio.add(user1); // Agregar usuario al repositorio y obtener el ID
			user1.setId(id); // Asignar el ID al usuario
			
			servicio.alquilar(id, "bici1");
		    servicio.alquilar("mentira", "bici23");
			//servicio.dejarBicicleta(id, "Estacion1");

			servicio.reservar("usernoExiste", "bici2");
			
			//servicio.alquilar("pruebaUsuario123", "bici 123123");
			

			// Obtener el usuario del repositorio usando el ID
			Usuario usuarioRecuperado = repositorio.getById(id);
			System.out.println("usuario recuperado: " + usuarioRecuperado.toString()); // Imprimir el usuario recuperado


			System.out.println("Usuarios en el repositorio ("+ repositorio.getAll().size() + "): \n" + repositorio.getAll());

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
