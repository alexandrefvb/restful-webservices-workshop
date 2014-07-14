package br.com.tqi.enquete.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.enquete.EnqueteRepository;
import br.com.tqi.enquete.Opcao;
import br.com.tqi.enquete.exception.EnqueteAtivaException;
import br.com.tqi.enquete.exception.EnqueteInativaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.EnqueteNaoEncontradaException;
import br.com.tqi.enquete.exception.OpcaoNaoEncontradaException;
import br.com.tqi.enquete.resource.EnqueteResource;
import br.com.tqi.enquete.resource.EnquetesResource;

@Path(EnqueteResource.URI)
public class EnqueteEndpoint {

	private EnqueteRepository repository = EnqueteRepository.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public EnquetesResource findAll() {
		return new EnquetesResource(this.repository.findAll());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Enquete enquete) throws EnqueteInvalidaException {
		this.repository.create(enquete);
		return Response.status(Status.CREATED)
				.entity(new EnqueteResource(enquete)).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EnqueteResource get(@PathParam("id") Long id)
			throws EnqueteNaoEncontradaException {
		return new EnqueteResource(this.repository.find(id));
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id)
			throws EnqueteNaoEncontradaException, EnqueteAtivaException {
		this.repository.delete(id);
	}

	@POST
	@Path("{id}/voto")
	@Consumes(MediaType.APPLICATION_JSON)
	public void vote(@PathParam("id") Long id, Opcao opcao)
			throws EnqueteNaoEncontradaException, EnqueteInativaException,
			OpcaoNaoEncontradaException {
		this.repository.find(id).findOpcaoToVote(opcao.getTexto()).vote();
	}
}
