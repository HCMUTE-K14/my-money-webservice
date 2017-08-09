package com.vn.hcmute.team.cortana.mymoney;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class HelloController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public HelloModel greeting(@QueryParam("name") @DefaultValue("world") String name) {
		HelloModel helloModel = new HelloModel();

		helloModel.id = counter.incrementAndGet();
		helloModel.content = String.format(template, name);
		System.out.println(helloModel.content);

		return helloModel;
	}
}