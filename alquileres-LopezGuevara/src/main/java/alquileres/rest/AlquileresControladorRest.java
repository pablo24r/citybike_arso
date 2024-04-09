package alquileres.rest;

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
import alquileres.servicio.*;
import io.jsonwebtoken.Claims;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Path("alquileres")
public class AlquileresControladorRest {

	private IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/alquileres/

	// curl -X GET http://localhost:8080/api/alquileres/65ee2c7b5d973f40258d0dac -H "Authorization: Bearer <token>"

	@GET
	@Path("/{id}")
	@RolesAllowed({ "gestor", "usuario" })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHistorialUsuario(@PathParam("id") String idUsuario) {
		try {
			Usuario usuario = servicio.historialUsuario(idUsuario);
			return Response.status(Response.Status.OK).entity(usuario.toString()).build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// curl -X PATCH http://localhost:8080/api/alquileres/65ee2c7b5d973f40258d0dac -H "Authorization: Bearer <token>"

	@PATCH
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("gestor")
	public Response liberarUsuario(@PathParam("id") String idUsuario) {

		if (this.servletRequest.getAttribute("claims") != null) {
			Claims claims = (Claims) this.servletRequest.getAttribute("claims");
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

		try {
			servicio.liberarBloqueo(idUsuario);
			return Response.status(Response.Status.OK).entity("usuario desbloqueado").build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	// curl -i -X PUT --data "idBici=bici1" http://localhost:8080/api/alquileres/65edecfd15b7177732ac20cd/alquiler/ -H "Authorization: Bearer <token>"

	@PUT
	@Path("/{id}/alquiler")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("usuario")
	public Response alquilar(@PathParam("id") String idUsuario, @PathParam("idBici") String idBici) {
		try {
			servicio.alquilar(idUsuario, idBici);
			return Response.status(Response.Status.CREATED).entity("Bicibleta alquilada").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	// curl -i -X POST --data "idBici=bici1" http://localhost:8080/api/alquileres/65edecfd15b7177732ac20cd/reserva/ -H "Authorization: Bearer <token>"
	@POST
	@Path("/{id}/reserva")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("usuario")
	public Response reservar(@PathParam("id") String idUsuario, @PathParam("idBici") String idBici) {
		try {
			servicio.reservar(idUsuario, idBici);
			return Response.status(Response.Status.CREATED).entity("Bicicleta con id: " + idBici + " reservada")
					.build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// curl -X POST http://localhost:8080/api/alquileres/65ee2c7b5d973f40258d0dac -H "Authorization: Bearer <token>"
	@POST
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("usuario")
	public Response confirmarReserva(@PathParam("id") String idUsuario) {
		
		try {
			servicio.confirmarReserva(idUsuario);
			return Response.status(Response.Status.CREATED).entity("Se ha confirmado una reserva").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	// curl -X POST --data http://localhost:8080/api/alquileres/65ee2c7b5d973f40258d0dac/estaciones/estacion1 -H "Authorization: Bearer <token>"
	@POST
	@Path("/{id}/estaciones/{isEstacion}")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("usuario")
	public Response estacionarBicicleta(@PathParam("idUsuario") String idUsuario, @PathParam("idEstacion") String idEstacion) {
		try {
			servicio.dejarBicicleta(idUsuario, idEstacion);
			return Response.status(Response.Status.OK).entity("Se ha estacionado la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
