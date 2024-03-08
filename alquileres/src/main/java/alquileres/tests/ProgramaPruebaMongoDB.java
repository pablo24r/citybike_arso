package alquileres.tests;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;
import alquileres.repositorio.RepositorioUsuariosMongoDB;
import alquileres.servicio.IServicioAlquileres;
import servicio.FactoriaServicios;

public class ProgramaPruebaMongoDB {
	public static void main(String[] args) {
		String connectionString = "mongodb+srv://pabloraullopezmartinez:ARSO2024@clusterarso.w0erjqo.mongodb.net/?retryWrites=true&w=majority&appName=ClusterARSO";
		String databaseName = "ClusterARSO";
		String collectionName = "usuarios";

		RepositorioUsuariosMongoDB repositorio = new RepositorioUsuariosMongoDB(connectionString, databaseName,
				collectionName);
		//IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);


		try {

			Usuario user1 = new Usuario();
			//servicio.alquilar(user1.getId(), "bici123");
			
			Alquiler alquiler = new Alquiler("bici123",LocalDateTime.now(), LocalDateTime.now().plusDays(1));
			List<Alquiler> alquileres = new LinkedList<Alquiler>();
			alquileres.add(alquiler);
			user1.setAlquileres(alquileres);

			Reserva reserva = new Reserva("bici123",LocalDateTime.now(), LocalDateTime.now().plusDays(1));
			List<Reserva> reservas = new LinkedList<Reserva>();
			reservas.add(reserva);
			user1.setReservas(reservas);
			
			
			String id = repositorio.add(user1); // Agregar usuario al repositorio y obtener el ID
			user1.setId(id); // Asignar el ID al usuario

			// user1.getAlquileres().add(alquiler);
			System.out.println("usuario local: " + user1.toString());

			// Obtener el usuario del repositorio usando el ID
			Usuario usuarioRecuperado = repositorio.getById(id);
			System.out.println("usuario recuperado: " + usuarioRecuperado.toString()); // Imprimir el usuario recuperado


			System.out.println("Usuarios en el repositorio ("+ repositorio.getAll().size() + "): \n" + repositorio.getAll());

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
