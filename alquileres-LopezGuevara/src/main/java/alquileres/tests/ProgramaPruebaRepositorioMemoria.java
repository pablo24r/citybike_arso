package alquileres.tests;

import alquileres.modelo.Usuario;
import alquileres.servicio.IServicioAlquileres;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import servicio.FactoriaServicios;

public class ProgramaPruebaRepositorioMemoria {
	public static void main(String[] args) {
		
		Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);
		IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

		Usuario user1 = new Usuario();
		Usuario user2 = new Usuario();
		try {
			repositorio.add(user1);
			repositorio.add(user2);
			String id1 = user1.getId();

			Usuario usuarioPRUEBA = repositorio.getById(id1);
			System.out.println(usuarioPRUEBA.getId());
			
			servicio.reservar(user1.getId(), "bici1");
			servicio.alquilar(user2.getId(), "bici2");
			
			
			System.out.println(servicio.historialUsuario(usuarioPRUEBA.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
