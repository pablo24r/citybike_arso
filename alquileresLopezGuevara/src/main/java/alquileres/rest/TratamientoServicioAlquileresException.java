package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import alquileres.servicio.ServicioAlquileresException;

@Provider
public class TratamientoServicioAlquileresException implements ExceptionMapper<ServicioAlquileresException> {
	@Override
	public Response toResponse(ServicioAlquileresException arg0) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(arg0.getMessage()).build();
	}
}
