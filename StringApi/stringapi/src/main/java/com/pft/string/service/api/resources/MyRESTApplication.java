package com.pft.string.service.api.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.pft.string.service.api.resolver.JAXBAnnotationResolver;

@ApplicationPath("resources")
public class MyRESTApplication  extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public MyRESTApplication(){
		empty.add(StringAPIServices.class);
		empty.add(JAXBAnnotationResolver.class);
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
