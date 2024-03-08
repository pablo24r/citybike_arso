package alquileres.tests;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.bson.Document;

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

			System.out.println(reserva.toString());
			System.out.println(alquiler.toString());

			Usuario user1 = new Usuario();
			user1.getReservas().add(reserva);
			user1.getAlquileres().add(alquiler);
			
			String id = repositorio.add(user1); // Agregar usuario al repositorio y obtener el ID
			user1.setId(id); // Asignar el ID al usuario

			// Obtener el usuario del repositorio usando el ID
			Usuario usuarioRecuperado = repositorio.getById(id);
			System.out.println(usuarioRecuperado.toString()); // Imprimir el usuario recuperado

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
