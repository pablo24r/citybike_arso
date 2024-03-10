package alquileres.rest;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response prueba() throws Exception {
		return Response.status(Response.Status.OK).entity(servicio.obtenerId()).build();
	}

	@GET
	@Path("{id}")
	@RolesAllowed("gestor")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHistorialUsuario(@PathParam("id") String idUsuario) {
		try {
			// URL de acceso: http://localhost:8080/api/alquileres/{idUsuario}
			// Ejemplo de uso con curl: curl -i
			// http://localhost:8080/api/alquileres/{idUsuario}
			String historial = servicio.historialUsuario(idUsuario);
			return Response.status(Response.Status.OK).entity(historial).build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PATCH
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@RolesAllowed("gestor")
	public Response liberarUsuario(@PathParam("id") String idUsuario) {

		if (this.servletRequest.getAttribute("claims") != null) {
			Claims claims = (Claims) this.servletRequest.getAttribute("claims");
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

		try {
			// URL de acceso: http://localhost:8080/api/alquileres/{idUsuario}
			// Ejemplo de uso con curl: curl -i
			// http://localhost:8080/api/alquileres/{idUsuario}
			servicio.liberarBloqueo(idUsuario);
			return Response.status(Response.Status.OK).entity("usuario desbloqueado").build();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}
//>>>>>>> branch 'main' of https://github.com/pablo24r/citybike_arso
}
