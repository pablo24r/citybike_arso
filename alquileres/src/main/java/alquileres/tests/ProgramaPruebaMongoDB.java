package alquileres.tests;

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
			Usuario usuarioPRUEBA = repositorio.getById("user123");
			System.out.println(usuarioPRUEBA.getId());
			
			servicio.alquilar(usuarioPRUEBA.getId(), "bicicleta123");
			System.out.println(servicio.historialUsuario(usuarioPRUEBA.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
