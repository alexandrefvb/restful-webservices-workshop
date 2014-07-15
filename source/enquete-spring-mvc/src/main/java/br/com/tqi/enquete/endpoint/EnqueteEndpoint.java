package br.com.tqi.enquete.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(EnqueteResource.URI)
public class EnqueteEndpoint {

	private EnqueteRepository repository = EnqueteRepository.getInstance();

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnquetesResource findAll() {
		return new EnquetesResource(this.repository.findAll());
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public EnqueteResource create(@RequestBody Enquete enquete)
			throws EnqueteInvalidaException {
		this.repository.create(enquete);
		return new EnqueteResource(enquete);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnqueteResource get(@PathVariable("id") Long id)
			throws EnqueteNaoEncontradaException {
		return new EnqueteResource(this.repository.find(id));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id)
			throws EnqueteNaoEncontradaException, EnqueteAtivaException {
		this.repository.delete(id);
	}

	@RequestMapping(value = "{id}/voto", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vote(@PathVariable("id") Long id, @RequestBody Opcao opcao)
			throws EnqueteNaoEncontradaException, EnqueteInativaException,
			OpcaoNaoEncontradaException {
		this.repository.find(id).findOpcaoToVote(opcao.getTexto()).vote();
	}

	@ExceptionHandler({ EnqueteInvalidaException.class,
			OpcaoNaoEncontradaException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badRequestExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler({ EnqueteNaoEncontradaException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFoundExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler({ EnqueteAtivaException.class,
			EnqueteInativaException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public String conflictExceptionHandler(Exception e) {
		return e.getMessage();
	}

}
