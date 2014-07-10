package br.com.tqi.enquete.provider;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.tqi.enquete.exception.EnqueteAtivaException;
import br.com.tqi.enquete.exception.EnqueteInativaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.EnqueteNaoEncontradaException;
import br.com.tqi.enquete.exception.OpcaoNaoEncontradaException;

@Provider
public class EnqueteExceptionMapper implements ExceptionMapper<Exception> {

	private Map<Class<? extends Exception>, Status> statusMap = new HashMap<Class<? extends Exception>, Status>();

	public EnqueteExceptionMapper() {
		statusMap.put(EnqueteAtivaException.class, Status.CONFLICT);
		statusMap.put(EnqueteInativaException.class, Status.CONFLICT);

		statusMap.put(EnqueteNaoEncontradaException.class, Status.NOT_FOUND);

		statusMap.put(EnqueteInvalidaException.class, Status.BAD_REQUEST);
		statusMap.put(OpcaoNaoEncontradaException.class, Status.BAD_REQUEST);
	}

	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Exception exception) {
		return Response.status(statusFor(exception))
				.entity(exception.getMessage()).build();
	}

	private Status statusFor(Exception exception) {
		Status status = statusMap.get(exception.getClass());
		return (status != null) ? status : Status.INTERNAL_SERVER_ERROR;
	}

}
