package alquileres.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import alquileres.servicio.*;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Path("alquileres")
public class AlquileresControladorRest {

	private IServicioAlquileres servicio = FactoriaServicios.getServicio(IServicioAlquileres.class);

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/alquileres/1

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHistorialUsuario(@PathParam("id") String idUsuario) {
        try {
            // URL de acceso: http://localhost:8080/api/alquileres/{idUsuario}
            // Ejemplo de uso con curl: curl -i http://localhost:8080/api/alquileres/{idUsuario}
            String historial = servicio.historialUsuario(idUsuario);
            return Response.status(Response.Status.OK).entity(historial).build();
        } catch (RepositorioException | EntidadNoEncontrada e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
