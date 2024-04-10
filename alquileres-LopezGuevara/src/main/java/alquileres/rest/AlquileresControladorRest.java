package alquileres.rest;

import java.util.List;

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

import com.google.gson.Gson;

import alquileres.modelo.Bicicleta;
import alquileres.modelo.Estacion;
import alquileres.modelo.Usuario;
import alquileres.servicio.*;
import io.jsonwebtoken.Claims;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Path("alquileres")
public class AlquileresControladorRest {

	private IServicioAlquileres servicioAlq = FactoriaServicios.getServicio(IServicioAlquileres.class);
	private IServicioEstaciones servicioEst = FactoriaServicios.getServicio(IServicioEstaciones.class);

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/alquileres/

	// curl -X GET
	// http://localhost:8080/api/alquileres/usuarios/65ee2c7b5d973f40258d0dac -H
	// "Authorization: Bearer <token>"

	@GET
	@Path("/usuarios/{idUsuario}")
	@RolesAllowed({ "gestor", "usuario" })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHistorialUsuario(@PathParam("idUsuario") String idUsuario) {
		try {
			Usuario usuario = servicioAlq.historialUsuario(idUsuario);
			return Response.status(Response.Status.OK).entity(usuario.toString()).build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// curl -X PATCH
	// http://localhost:8080/api/alquileres/usuarios/65ee2c7b5d973f40258d0dac
	// -H "Authorization: Bearer <token>"

	@PATCH
	@Path("/usuarios/{idUsuario}")
	@RolesAllowed("gestor")
	public Response liberarUsuario(@PathParam("idUsuario") String idUsuario) {

		if (this.servletRequest.getAttribute("claims") != null) {
			Claims claims = (Claims) this.servletRequest.getAttribute("claims");
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

		try {
			servicioAlq.liberarBloqueo(idUsuario);
			return Response.status(Response.Status.OK).entity("usuario desbloqueado").build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	// curl -i -X PUT --data "idBici=bici1"
	// http://localhost:8080/api/alquileres/bicicletas/65edecfd15b7177732ac20cd
	// -H
	// "Authorization: Bearer <token>"

	@POST
	@Path("/bicicletas/{idBici}")
	@RolesAllowed("usuario")
	public Response alquilar(String idUsuario, @PathParam("idBici") String idBici) {
		try {
			servicioAlq.alquilar(idUsuario, idBici);
			return Response.status(Response.Status.CREATED).entity("Bicibleta alquilada").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	// curl -i -X POST --data "idBici=bici1"
	// http://localhost:8080/api/alquileres/usuarios/65edecfd15b7177732ac20cd/reservas
	// -H
	// "Authorization: Bearer <token>"
	@POST
	@Path("/usuarios/{idUsuario}/reservas")
	@RolesAllowed("usuario")
	public Response reservar(@PathParam("idUsuario") String idUsuario, @PathParam("idBici") String idBici) {
		try {
			servicioAlq.reservar(idUsuario, idBici);
			return Response.status(Response.Status.CREATED).entity("Bicicleta con id: " + idBici + " reservada")
					.build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// curl -X POST
	// http://localhost:8080/api/alquileres/usuarios/65ee2c7b5d973f40258d0dac/reservas
	// -H "Authorization: Bearer <token>"
	@PUT
	@Path("/usarios/{idUsuario}/reservas")
	@RolesAllowed("usuario")
	public Response confirmarReserva(@PathParam("idUsuario") String idUsuario) {

		try {
			servicioAlq.confirmarReserva(idUsuario);
			return Response.status(Response.Status.OK).entity("Se ha confirmado una reserva").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// curl -X POST --data
	// http://localhost:8080/api/alquileres/usuarios/65ee2c7b5d973f40258d0dac/estaciones/estacion1
	// -H "Authorization: Bearer <token>"
	@PUT
	@Path("/usuarios/{idUsuario}/estaciones/{idEstacion}")
	@RolesAllowed("usuario")
	public Response dejarBicicleta(@PathParam("idUsuario") String idUsuario,
			@PathParam("idEstacion") String idEstacion) {
		try {
			servicioAlq.dejarBicicleta(idUsuario, idEstacion);
			return Response.status(Response.Status.OK).entity("Se ha estacionado la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/estaciones/{idEstacion}")
	@RolesAllowed("gestor")
	public Response altaEstacion(@PathParam("nombre") String nombre, @PathParam("numPuestos") int numPuestos,
			@PathParam("dirPostal") String dirPostal, @PathParam("coordenadas") String coordenadas) {
		try {
			servicioEst.darAltaEstacion(nombre, numPuestos, dirPostal, coordenadas);
			return Response.status(Response.Status.CREATED).entity("Se ha dado de alta la estación").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/bicicletas")
	@RolesAllowed("gestor")
	public Response altaBici(@PathParam("modelo") String modelo, @PathParam("idEstacion") String idEstacion) {
		try {
			servicioEst.darAltaBicicleta(modelo, idEstacion);
			return Response.status(Response.Status.CREATED).entity("Se ha dado de alta la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/bicicletas/{idBicicleta}")
	@RolesAllowed("gestor")
	public Response bajaBicicleta(@PathParam("idBici") String idBici, @PathParam("motivo") String motivo) {
		try {
			servicioEst.darBajaBicicleta(idBici, motivo);
			return Response.status(Response.Status.OK).entity("Se ha dado de baja la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/estaciones/{idEstacion}/bicicletas")
	@RolesAllowed("gestor")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListadoBicicletas(@PathParam("idEstacion") String idEstacion) {
		try {
			List<Bicicleta> listado = servicioEst.getListadoBicicletas(idEstacion);
			String json = new Gson().toJson(listado);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/estaciones/")
	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListadoEstaciones() {
		try {
			List<Estacion> listado = servicioEst.getListadoEstaciones();
			String json = new Gson().toJson(listado);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/estaciones/{idEstacion}")
	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getInfoEstacion(@PathParam("idEstacion") String idEstacion) {
		try {
			String info = servicioEst.getInfoEstacion(idEstacion);
			String json = new Gson().toJson(info);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/estaciones/{idEstacion}/bicicletas")
	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBicisDisponibles(@PathParam("idEstacion") String idEstacion) {
		try {
			List<Bicicleta> disponibles = servicioEst.getBicisDisponibles(idEstacion);
			String json = new Gson().toJson(disponibles);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/estaciones/{idEstacion}/bicicletas/{idbici}")
	@RolesAllowed("usuario")
	public Response estacionarBicicleta(@PathParam("idEstacion") String idEstacion,
			@PathParam("idBici") String idBici) {
		try {
			servicioEst.estacionarBicicleta(idEstacion, idBici);
			return Response.status(Response.Status.OK).entity("Bicicleta estacionada").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
