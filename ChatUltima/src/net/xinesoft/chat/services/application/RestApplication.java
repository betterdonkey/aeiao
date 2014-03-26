package net.xinesoft.chat.services.application;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig{
	public RestApplication(){		
		packages("net.xinesoft.chat.services.rest");
	}
}