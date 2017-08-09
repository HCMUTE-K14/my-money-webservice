package com.vn.hcmute.team.cortana.mymoney;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
        register(RequestContextFilter.class);
        
        //Add class Controller here
        register(HelloController.class);
        
    }
}
