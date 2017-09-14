package com.sap.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ApplicationPath("/Traderepo")
@ComponentScan("com.sap.*")
public class JerseyConfig extends ResourceConfig {

	@Autowired
	public JerseyConfig(ObjectMapper objectMapper) {
		// register endpoints
		packages("com.sap.controllers");
		// register jackson for json
		register(new ObjectMapperContextResolver(objectMapper));
	}

	@Provider
	public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
		private final ObjectMapper mapper;
		public ObjectMapperContextResolver(ObjectMapper mapper) {
			this.mapper = mapper;
		}

		@Override
		public ObjectMapper getContext(Class<?> type) {
			return mapper;
		}
	}
}
