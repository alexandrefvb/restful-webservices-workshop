package br.com.tqi.enquete.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tqi.enquete.resource.RootResource;

@RestController
@RequestMapping(RootResource.URI)
public class RootEndpoint {

    @RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public RootResource get() {
	return new RootResource();
    }
}
