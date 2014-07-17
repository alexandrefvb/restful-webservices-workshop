package br.com.tqi.enquete.provider;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class ObjectMapperContexResolver implements
	ContextResolver<ObjectMapper> {

    private ObjectMapper objectMapper;

    public ObjectMapperContexResolver() {
	this.objectMapper = new ObjectMapper();
	this.objectMapper.configure(
		SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	this.objectMapper.setDateFormat(new SimpleDateFormat(
		"dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public ObjectMapper getContext(Class<?> objectType) {
	return this.objectMapper;
    }

}
