package alquileres.rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import alquileres.modelo.Usuario;
import alquileres.servicio.IServicioAlquileres;
import io.jsonwebtoken.Claims;
import repositorio.AlquilerActivoException;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.ReservaActivaException;
import repositorio.SinReservaActivaException;
import repositorio.SuperaTiempoException;
import repositorio.UsuarioBloqueadoException;
import servicio.FactoriaServicios;

@Path("alquileres")
public class AlquileresControladorRest {

	private IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private UriInfo uriInfo;

	// curl -X GET http://localhost:8080/api/alquileres/prueba/123
	@GET
	@Path("/prueba/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public Response prueba(@PathParam("id") String idUsuario) {
		System.out.println("Usuario ID: " + idUsuario);
		return Response.status(Response.Status.OK)
				.entity("{\"message\": \"Prueba exitosa\", \"userId\": \"" + idUsuario + "\"}").build();
	}

	// curl -X GET http://localhost:8080/api/alquileres/664cb6a9367e786845a20439 -H
	// "Authorization: Bearer <token>"
	@GET
	@Path("/{id}")
	@RolesAllowed({ "usuario", "gestor" })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHistorialUsuario(@PathParam("id") String idUsuario)
			throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = servicio.historialUsuario(idUsuario);
		return Response.status(Response.Status.OK).entity(usuario).build();
	}

	// curl -X PATCH http://localhost:8080/api/alquileres/664cb6a9367e786845a20439
	// -H "Authorization: Bearer <token>"
	@PATCH
	@Path("/{id}")
	@RolesAllowed("gestor")
	public Response liberarUsuario(@PathParam("id") String idUsuario) throws RepositorioException, EntidadNoEncontrada {

		if (this.servletRequest.getAttribute("claims") != null) {
			Claims claims = (Claims) this.servletRequest.getAttribute("claims");
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

		servicio.liberarBloqueo(idUsuario);
		return Response.status(Response.Status.OK).entity("Usuario desbloqueado").build();

	}

	// curl -X PUT http://localhost:8080/api/alquileres/664cb6a9367e786845a20439/alquilar/bici123 -H "Authorization: Bearer <token>"
	@PUT
	@Path("/{id}/alquilar/{idBici}")
	@RolesAllowed({ "usuario", "gestor" })
	public Response alquilar(@PathParam("id") String idUsuario, @PathParam("idBici") String idBici)
			throws ReservaActivaException, AlquilerActivoException, UsuarioBloqueadoException, SuperaTiempoException, RepositorioException, EntidadNoEncontrada {
			servicio.alquilar(idUsuario, idBici);
			return Response.status(Response.Status.CREATED).entity("Bicibleta alquilada").build();
	}

	// curl -X POST http://localhost:8080/api/alquileres/664cb6a9367e786845a20439/reservar/bici123 -H "Authorization: Bearer <token>"
	@POST
	@Path("/{id}/reservar/{idBici}")
	@RolesAllowed({ "usuario", "gestor" })
	public Response reservar(@PathParam("id") String idUsuario, @PathParam("idBici") String idBici)
			throws ReservaActivaException, AlquilerActivoException, UsuarioBloqueadoException, SuperaTiempoException,
			RepositorioException, EntidadNoEncontrada {
		servicio.reservar(idUsuario, idBici);
		return Response.status(Response.Status.CREATED).entity("Bicicleta con id: " + idBici + " reservada").build();

	}

	// curl -X POST http://localhost:8080/api/alquileres/664cb6a9367e786845a20439 -H "Authorization: Bearer <token>"
	@POST
	@Path("/{id}")
	@RolesAllowed({ "usuario", "gestor" })
	public Response confirmarReserva(@PathParam("id") String idUsuario)
			throws SinReservaActivaException, RepositorioException, EntidadNoEncontrada {
		servicio.confirmarReserva(idUsuario);
		return Response.status(Response.Status.CREATED).entity("Se ha confirmado una reserva").build();
	}

	/*
	 * // curl -X POST --data
	 * http://localhost:8080/api/alquileres/664cb6a9367e786845a20439/estaciones/
	 * estacion1 -H "Authorization: Bearer <token>"
	 * 
	 * @POST
	 * 
	 * @Path("/{id}/estaciones/{isEstacion}")
	 * 
	 * @Produces({ MediaType.APPLICATION_JSON })
	 * 
	 * @RolesAllowed("usuario") public Response
	 * estacionarBicicleta(@PathParam("idUsuario") String
	 * idUsuario, @PathParam("idEstacion") String idEstacion) { try {
	 * servicio.dejarBicicleta(idUsuario, idEstacion); return
	 * Response.status(Response.Status.OK).entity("Se ha estacionado la bicicleta").
	 * build(); } catch (Exception e) { return
	 * Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
	 * .build(); } }
	 */
}
