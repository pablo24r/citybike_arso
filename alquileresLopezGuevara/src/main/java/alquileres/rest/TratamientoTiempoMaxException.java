package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import repositorio.SuperaTiempoException;

@Provider
public class TratamientoTiempoMaxException implements ExceptionMapper<SuperaTiempoException> {
	@Override
	public Response toResponse(SuperaTiempoException arg0) {
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(arg0.getMessage()).build();
	}
}
