package com.vn.hcmute.team.cortana.mymoney;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.Category;
import com.vn.hcmute.team.cortana.mymoney.controller.CategoryController;
import com.vn.hcmute.team.cortana.mymoney.controller.UserController;

@Component
@ApplicationPath("/") //Remote to root
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
        register(RequestContextFilter.class);
        
        //Add class Controller here
        register(UserController.class);
        register(CategoryController.class);
        
    }
}
