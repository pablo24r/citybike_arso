package alquileres.repositorio;

import java.util.LinkedList;

import alquileres.modelo.*;
import repositorio.RepositorioMemoria;

public class RepositorioUsuariosMemoria extends RepositorioMemoria<Usuario> {
	
	public RepositorioUsuariosMemoria() {
		try {
			LinkedList<Reserva> reservas = new LinkedList<Reserva>();
			LinkedList<Alquiler> alquileres = new LinkedList<Alquiler>();
			
			Usuario user1 = new Usuario(reservas , alquileres);
			this.add(user1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
