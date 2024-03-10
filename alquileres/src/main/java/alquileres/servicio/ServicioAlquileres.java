package alquileres.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.jws.WebService;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;
import alquileres.modelo.Usuario;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

@WebService(endpointInterface = "alquileres.servicio.IServicioAlquileres", targetNamespace = "http://um.es/arso")
public class ServicioAlquileres implements IServicioAlquileres, IServicioEstaciones {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);
	private IServicioEstaciones servicioEstaciones = this;

	@Override
	public void reservar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorio.getById(idUsuario);
		if (usuario.reservaActiva() != null || usuario.alquiler() != null || usuario.bloqueado()
				|| usuario.superaTiempo())
			throw new IllegalArgumentException("No se puede realizar la reserva.");
		else {
			Reserva reserva = new Reserva(idBicicleta, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
			usuario.getReservas().add(reserva);
			repositorio.update(usuario);
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
		if (usuario.reservaActiva() != null || usuario.alquiler() != null || usuario.bloqueado()
				|| usuario.superaTiempo())
			throw new IllegalArgumentException("No se puede realizar el alquiler.");
		else {
			Alquiler alquiler = new Alquiler(idBicicleta, LocalDateTime.now(), null);
			usuario.getAlquileres().add(alquiler);
			repositorio.update(usuario);
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

		if (usuario.alquiler() == null || !servicioEstaciones.hayHuecoDisponible())
			throw new IllegalArgumentException("No se puede dejar la bicicleta.");
		else {
			usuario.alquiler().setFin(LocalDateTime.now());
			servicioEstaciones.dejarBicicleta();
			repositorio.update(usuario);
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

	@Override
	public List<Alquiler> recuperarAlquileres(String idUsuario) throws RepositorioException, EntidadNoEncontrada {

		Usuario usuario = repositorio.getById(idUsuario);
		return usuario.getAlquileres();
	}

	@Override
	public String obtenerId() {
		UUID uuid = UUID.randomUUID();
		System.out.println("Se ha generado un nuevo id: " + uuid);
		return uuid.toString();
	}

}
