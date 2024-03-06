package alquileres.repositorio;

import java.util.LinkedList;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;
import repositorio.RepositorioMongoDB;

public class RepositorioUsuariosMongoDB extends RepositorioMongoDB<Usuario>  {

	public RepositorioUsuariosMongoDB () {
		super();
		try {
			LinkedList<Reserva> reservas = new LinkedList<Reserva>();
			LinkedList<Alquiler> alquileres = new LinkedList<Alquiler>();
			
			Usuario user1 = new Usuario("user123",reservas , alquileres);
			this.add(user1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
