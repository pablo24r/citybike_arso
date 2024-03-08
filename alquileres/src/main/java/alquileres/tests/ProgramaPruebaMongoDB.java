package alquileres.tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;

public class ProgramaPruebaMongoDB {
	public static void main(String[] args) {
		Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

		try {
			Reserva reserva = new Reserva("bici1", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
			Alquiler alquiler = new Alquiler("bici1", LocalDateTime.now(), LocalDateTime.now().plusDays(1));

			List<Reserva> reservas = new ArrayList<Reserva>();
			reservas.add(reserva);

			List<Alquiler> alquileres = new ArrayList<Alquiler>();
			alquileres.add(alquiler);

			// System.out.println(reserva.toString());
			// System.out.println(alquiler.toString());

			Usuario user1 = new Usuario(reservas, alquileres);

			String id = repositorio.add(user1); // Agregar usuario al repositorio y obtener el ID
			user1.setId(id); // Asignar el ID al usuario

			// user1.getAlquileres().add(alquiler);
			System.out.println("usuario local: " + user1.toString());

			// Obtener el usuario del repositorio usando el ID
			Usuario usuarioRecuperado = repositorio.getById(id);
			System.out.println("usuario recuperado: " + usuarioRecuperado.toString()); // Imprimir el usuario recuperado

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
