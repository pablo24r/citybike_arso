package alquileres.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import alquileres.servicio.IServicioAlquileres;
import servicio.FactoriaServicios;

@Path("alquileres")
public class AlquileresControladorRest {

	private IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/alquileres/1

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response prueba() throws Exception {
		return Response.status(Response.Status.OK).entity(servicio.obtenerId()).build();
	}

}
