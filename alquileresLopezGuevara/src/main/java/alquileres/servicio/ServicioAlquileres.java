package alquileres.servicio;

import java.time.LocalDateTime;
import java.util.List;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;
import repositorio.AlquilerActivoException;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import repositorio.ReservaActivaException;
import repositorio.SinReservaActivaException;
import repositorio.SuperaTiempoException;
import repositorio.UsuarioBloqueadoException;

public class ServicioAlquileres implements IServicioAlquileres {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	// Método auxiliar para obtener el usuario del repositorio, o crear uno nuevo si
	// no existe
	private Usuario getUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario;
		try {
			usuario = repositorio.getById(idUsuario);
		} catch (EntidadNoEncontrada e) {
			usuario = new Usuario();
			repositorio.add(usuario);
		}
		return usuario;
	}

	@Override
	public void reservar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada,
			ReservaActivaException, AlquilerActivoException, UsuarioBloqueadoException, SuperaTiempoException {
		// Si el usuario no existe, creo uno nuevo
		Usuario usuario = getUsuario(idUsuario);

		if (usuario.reservaActiva() != null) {
			throw new ReservaActivaException("El usuario ya tiene una reserva activa.");
		}
		if (usuario.alquiler() != null) {
			throw new AlquilerActivoException("El usuario ya tiene un alquiler activo.");
		}
		if (usuario.isBloqueado()) {
			throw new UsuarioBloqueadoException("El usuario está bloqueado.");
		}
		if (usuario.superaTiempo()) {
			throw new SuperaTiempoException("El usuario ha superado el tiempo de uso permitido.");
		}

		Reserva reserva = new Reserva(idBicicleta, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
		usuario.getReservas().add(reserva);
		repositorio.update(usuario);
		System.out.println("Reserva creada.");
	}

	@Override
	public void confirmarReserva(String idUsuario)
			throws RepositorioException, EntidadNoEncontrada, SinReservaActivaException {
		try {
			Usuario usuario = repositorio.getById(idUsuario);
			Reserva reservaActiva = usuario.reservaActiva();
			if (reservaActiva == null) {
				throw new SinReservaActivaException("El usuario no tiene ninguna reserva activa.");
			}

			Alquiler alquiler = new Alquiler(reservaActiva.getIdBicicleta(), LocalDateTime.now(), null);
			usuario.getAlquileres().add(alquiler);
			usuario.getReservas().remove(reservaActiva);
			repositorio.update(usuario);
			System.out.println("Alquiler creado.");
			System.out.println("Reserva eliminada");
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alquilar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada,
			ReservaActivaException, AlquilerActivoException, UsuarioBloqueadoException, SuperaTiempoException {
		// Si el usuario no existe, creo uno nuevo
		Usuario usuario = getUsuario(idUsuario);

		if (usuario.reservaActiva() != null) {
			throw new ReservaActivaException("El usuario ya tiene una reserva activa.");
		}
		if (usuario.alquiler() != null) {
			throw new AlquilerActivoException("El usuario ya tiene un alquiler activo.");
		}
		if (usuario.isBloqueado()) {
			throw new UsuarioBloqueadoException("El usuario está bloqueado.");
		}
		if (usuario.superaTiempo()) {
			throw new SuperaTiempoException("El usuario ha superado el tiempo permitido.");
		}

		Alquiler alquiler = new Alquiler(idBicicleta, LocalDateTime.now(), null);
		usuario.getAlquileres().add(alquiler);
		repositorio.update(usuario);
		System.out.println("Alquiler creado.");
	}

	@Override
	public Usuario historialUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);
		String historial = "HISTORIAL DEL USUARIO: " + idUsuario;
		historial += "\n ALQUILERES: " + usuario.getAlquileres().toString();
		historial += "\n RESERVAS: " + usuario.getReservas().toString();
		historial += "\n ESTADO DEL SERVICIO: ";
		if (usuario.isBloqueado())
			historial += " Usuario bloqueado";
		else {
			historial += "\n\t Tiempo de uso hoy: " + usuario.tiempoUsoHoy();
			historial += "\n\t Tiempo de uso semanal: " + usuario.tiempoUsoSemana();
		}
		System.out.println(historial);
		return usuario;

	}

	@Override
	public void liberarBloqueo(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);
		usuario.getReservas().removeIf(Reserva::isCaducada);
		repositorio.update(usuario);
		System.out.println("Reservas caducadas eliminadas.");

	}

	@Override
	public List<Alquiler> recuperarAlquileres(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		try {
			Usuario usuario = repositorio.getById(idUsuario);
			return usuario.getAlquileres();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		return null;

	}

}
