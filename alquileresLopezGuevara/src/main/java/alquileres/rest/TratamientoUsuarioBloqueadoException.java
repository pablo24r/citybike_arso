package alquileres.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import repositorio.UsuarioBloqueadoException;

public class TratamientoUsuarioBloqueadoException implements ExceptionMapper<UsuarioBloqueadoException> {
	@Override
	public Response toResponse(UsuarioBloqueadoException arg0) {
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(arg0.getMessage()).build();
	}
}
