package alquileres.servicio;

import java.time.LocalDateTime;


import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioAlquileres implements IServicioAlquileres {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);
	private IServicioEstaciones servicioEstaciones;
	
	@Override
	public void reservar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);
		System.out.println("User:" + usuario.toString());
		if (usuario.reservaActiva() != null || usuario.alquiler()!=null|| usuario.bloqueado()
				|| usuario.superaTiempo())
			throw new IllegalArgumentException("No se puede realizar la reserva.");
		else {
			Reserva reserva = new Reserva(idBicicleta, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
			usuario.getReservas().add(reserva);
			System.out.println("Reserva creada.");
		}

	}

	@Override
	public void confirmarReserva(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);

		if (usuario.reservaActiva() == null)
			throw new IllegalArgumentException("El usuario no tiene ninguna reserva activa.");
		else {
			Reserva reservaActiva = usuario.reservaActiva();
			Alquiler alquiler = new Alquiler(reservaActiva.getIdBicicleta(), LocalDateTime.now(), null);
			usuario.getAlquileres().add(alquiler);
			usuario.getReservas().remove(reservaActiva);
			System.out.println("Alquiler creado.");
			System.out.println("Reserva eliminada");
		}
	}

	@Override
	public void alquilar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada {		
		Usuario usuario = repositorio.getById(idUsuario);
		if (usuario.reservaActiva() != null || usuario.alquiler()!=null || usuario.bloqueado()
				|| usuario.superaTiempo())
			throw new IllegalArgumentException("No se puede realizar el alquiler.");
		else {
			Alquiler alquiler = new Alquiler(idBicicleta, LocalDateTime.now(), null);
			usuario.getAlquileres().add(alquiler);
			System.out.println("Alquiler creado.");
		}

	}

	@Override
	public String historialUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);

		String historial = "HISTORIAL DEL USUARIO: " + idUsuario;
		historial += "\n ALQUILERES: " + usuario.getAlquileres().toString();
		historial += "\n RESERVAS: " + usuario.getReservas().toString();
		historial += "\n ESTADO DEL SERVICIO: ";
		if (usuario.bloqueado())
			historial += " Usuario bloqueado";
		else {
			historial += "\n\t Tiempo de uso hoy: " + usuario.tiempoUsoHoy();
			historial += "\n\t Tiempo de uso semanal: " + usuario.tiempoUsoSemana();
		}
		return historial;
	}

	@Override
	public void dejarBicicleta(String idUsuario, String idEstacion) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);

		if (usuario.alquiler()!=null || !servicioEstaciones.hayHuecoDisponible())
			throw new IllegalArgumentException("No se puede dejar la bicicleta.");
		else {
			usuario.alquiler().setFin(LocalDateTime.now());
			servicioEstaciones.dejarBicicleta();
			System.out.println("Bicicleta dejada en la estación.");
		}

	}

	@Override
	public void liberarBloqueo(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);

		for (Reserva r : usuario.getReservas()) {
			if (r.isCaducada())
				usuario.getReservas().remove(r);
		}

	}

}
