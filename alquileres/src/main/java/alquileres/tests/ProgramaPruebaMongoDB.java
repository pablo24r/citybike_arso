package alquileres.tests;

import java.util.LinkedList;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;
import alquileres.servicio.IServicioAlquileres;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import servicio.FactoriaServicios;

public class ProgramaPruebaMongoDB {
	public static void main(String[] args) {
		Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);
		IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

		try {
			LinkedList<Reserva> reservas = new LinkedList<Reserva>();
			LinkedList<Alquiler> alquileres = new LinkedList<Alquiler>();

			Usuario user1 = new Usuario(reservas, alquileres);
			String id = repositorio.add(user1);
			user1.setId(id);

			repositorio.getById(user1.getId());
			System.out.println(user1.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
