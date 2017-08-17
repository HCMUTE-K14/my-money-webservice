package com.vn.hcmute.team.cortana.mymoney;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;


import com.vn.hcmute.team.cortana.mymoney.controller.CurrenciesController;
import com.vn.hcmute.team.cortana.mymoney.controller.EventController;
import com.vn.hcmute.team.cortana.mymoney.controller.SavingController;
import com.vn.hcmute.team.cortana.mymoney.controller.CategoryController;

import com.vn.hcmute.team.cortana.mymoney.controller.UserController;
import com.vn.hcmute.team.cortana.mymoney.controller.WalletController;

@Component
@ApplicationPath("/") //Remote to root
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
        register(RequestContextFilter.class);
        
        //Add class Controller here
        register(UserController.class);

        register(WalletController.class);
        register(CurrenciesController.class);
        register(EventController.class);

        register(CategoryController.class);
        
        register(SavingController.class);
    }
}
