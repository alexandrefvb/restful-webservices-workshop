package br.com.tqi.enquete.resource;

import java.util.List;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.resource.Link;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.ResourceList;

public class EnquetesResource extends ResourceList<EnqueteResource, Enquete> {

    public EnquetesResource(List<Enquete> modelList) {
	super(modelList);
	add(new Link(EnqueteResource.URI));
	add(new Link("nova-enquete", EnqueteResource.URI, Method.POST));
    }

    public List<EnqueteResource> getEnquetes() {
	return resourceList();
    }

}
