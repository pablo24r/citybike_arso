package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import repositorio.AlquilerActivoException;

@Provider
public class TratamientoAlquilerActivoException implements ExceptionMapper<AlquilerActivoException> {
	@Override
	public Response toResponse(AlquilerActivoException arg0) {
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(arg0.getMessage()).build();
	}
}
