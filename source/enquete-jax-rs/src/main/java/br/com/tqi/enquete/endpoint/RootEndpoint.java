package br.com.tqi.enquete.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.tqi.enquete.resource.RootResource;

@Path(RootResource.URI)
public class RootEndpoint {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RootResource get() {
		return new RootResource();
	}
}
