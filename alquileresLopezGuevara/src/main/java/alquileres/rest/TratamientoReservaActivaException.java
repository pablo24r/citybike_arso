package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import repositorio.ReservaActivaException;

@Provider
public class TratamientoReservaActivaException implements ExceptionMapper<ReservaActivaException> {
	@Override
	public Response toResponse(ReservaActivaException arg0) {
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(arg0.getMessage()).build();
	}
}
