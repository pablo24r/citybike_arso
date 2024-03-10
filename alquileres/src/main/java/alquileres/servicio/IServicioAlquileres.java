package alquileres.servicio;

import java.util.List;

import javax.jws.WebService;

import alquileres.modelo.Alquiler;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@WebService(targetNamespace = "http://um.es/arso")
public interface IServicioAlquileres {

	/**
	 * reservar(idUsuario, idBicicleta). Esta operación está a cargo de crear una
	 * reserva para una bicicleta. Los identificadores son cadena de texto. La fecha
	 * de creación corresponde al momento de la solicitud y la fecha de caducidad es
	 * 30 minutos más tarde. Requisitos:
	 * 
	 * No está permitida la reserva si el usuario tiene una reservaActiva. No está
	 * permitida la reserva si el usuario tiene un alquiler activo. No está
	 * permitida la reserva si el usuario está bloqueado o superaTiempo.
	 * 
	 */
	void reservar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * confirmarReserva(idUsuario). Esta operación confirma la reservaActiva del
	 * usuario creando un alquiler. La propiedad inicio del alquiler corresponde al
	 * momento de la confirmación. Un alquiler recién creado no tiene valor para la
	 * propiedad fin. Por último, esta operación elimina la reserva. Requisitos:
	 * 
	 * El usuario tiene una reservaActiva.
	 */
	void confirmarReserva(String idUsuario) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * alquilar(idUsuario, idBicicleta). Crea el alquiler, sin reserva previa, de
	 * una bicicleta por parte de un usuario. La propiedad inicio del alquiler
	 * corresponde al momento de la confirmación. En un alquiler recién creado no
	 * tiene valor la propiedad fin. Requisitos:
	 * 
	 * El usuario no tiene una reservaActiva. El usuario no tiene un alquilerActivo.
	 * No está permitido el alquiler si el usuario está bloqueado o superaTiempo.
	 */
	void alquilar(String idUsuario, String idBicicleta) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * historialUsuario(idUsuario): Usuario. Retorna la información con los
	 * alquileres y reservas del usuario, y el estado del servicio (bloqueado,
	 * tiempo de uso).
	 */
	String historialUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * dejarBicicleta(idUsuario, idEstacion). La operación se encarga de concluir el
	 * alquilerActivo del usuario estableciendo como fecha de fin el instante
	 * actual. Además, sitúa la bicicleta en la estación. Requisitos:
	 * 
	 * El usuario tiene un alquilerActivo. La estación tiene un hueco disponible
	 * para el estacionamiento.
	 */
	void dejarBicicleta(String idUsuario, String idEstacion) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * liberarBloqueo(idUsuario). Esta operación elimina todas las reservas
	 * caducadas de un usuario.
	 */
	void liberarBloqueo(String idUsuario) throws RepositorioException, EntidadNoEncontrada;

	/*
	 * Recupera un alquiler de un usuario utilizando el identificador
	 */

	public List<Alquiler> recuperarAlquileres(String idUsuario) throws RepositorioException, EntidadNoEncontrada;

	/*
	 * Estable el id de un alquiler para que nunca haya dos con el mismo id
	 */

	String obtenerId();
}
