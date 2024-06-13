package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import repositorio.SinReservaActivaException;

@Provider
public class TratamientoSinReservaException implements ExceptionMapper<SinReservaActivaException> {
	
	@Override
	public Response toResponse(SinReservaActivaException arg0) {
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(arg0.getMessage()).build();
	}
}