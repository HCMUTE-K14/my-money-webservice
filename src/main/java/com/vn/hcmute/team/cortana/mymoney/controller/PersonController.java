package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.Person;
import com.vn.hcmute.team.cortana.mymoney.model.PersonModel;

@SuppressWarnings("unchecked")
@Component
@Path("person")
public class PersonController {
	@Autowired
	PersonModel personModel;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("person");
		
		return response.toString();
	}
	
	@GET
	@Path("getPerson/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getInfo(@PathParam("userid") String userid,@PathParam("token") String token) {
		Class<List<Person>> clazz = (Class<List<Person>>) (Object) List.class;
		JsonResponse<List<Person>> response=new JsonResponse<List<Person>>(clazz);
		CallBack<List<Person>> callBack=new CallBack<List<Person>>() {
			
			@Override
			public void onSuccess(List<Person> result) {
				
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		personModel.getPersons(userid, token, callBack);
		
		return response.toString();
	}
	
	@POST
	@Path("add/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(Person person,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("Add Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		personModel.addPerson(userid, token, person, callBack);
		return response.toString();
	}
	
	@POST
	@Path("update/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(Person person,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				response.setStatus("success");
				response.setMessage("update Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(e.getMessage());
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		
		personModel.updatePerson(userid, token, person, callBack);
		return response.toString();
	}
	
	@POST
	@Path("sync/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String syncPerson(List<Person> persons,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				response.setStatus("success");
				response.setMessage("Synchronized Done");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(e.getMessage());
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		
		personModel.synchPerson(userid, token, persons, callBack);
		return response.toString();
	}
	
	@GET
	@Path("remove/{userid}/{token}/{personid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("personid") String personid) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				
				
				response.setStatus("success");
				response.setMessage("remove Successful");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				
				
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		personModel.removePerson(userid, token, personid, callBack);
		return response.toString();
	}
}
