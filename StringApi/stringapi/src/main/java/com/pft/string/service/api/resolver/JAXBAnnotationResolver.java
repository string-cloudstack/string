package com.pft.string.service.api.resolver;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JAXBAnnotationResolver implements ContextResolver<ObjectMapper> {

	private ObjectMapper _objectMapper = null;
	
	public JAXBAnnotationResolver() throws Exception {
		this._objectMapper = new ObjectMapper()
							.configure(SerializationConfig.Feature.USE_ANNOTATIONS, true)
							.configure(DeserializationConfig.Feature.USE_ANNOTATIONS, true)
							.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);
        this._objectMapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
        this._objectMapper.getSerializationConfig().setAnnotationIntrospector(introspector);
	}
	
	public ObjectMapper getContext(Class<?> arg0) {
		return this._objectMapper;
	}
}
