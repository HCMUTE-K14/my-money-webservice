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
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.CurrenciesModel;
import com.vn.hcmute.team.cortana.mymoney.model.EventModel;

@Component
@Path("event")
public class EventController {
	private static final String UTF8="; charset=UTF-8";
	@Autowired
	EventModel eventModel;
	 
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String home() {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		
		response.setStatus("success");
		response.setMessage("ok");
		response.setData("event");
		
		return response.toString();
	}
	@GET
	@Path("getEvent/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON+ UTF8)
	@Produces(MediaType.APPLICATION_JSON+ UTF8)
	public String getInfo(@PathParam("userid") String userid,@PathParam("token") String token) {
		Class<List<Event>> classEvent=(Class<List<Event>>)(Object)List.class;
		JsonResponse<List<Event>> response=new JsonResponse<List<Event>>(classEvent);
		CallBack<List<Event>> callBack=new CallBack<List<Event>>() {
			
			@Override
			public void onSuccess(List<Event> result) {
				// TODO Auto-generated method stub
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}
			
			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		eventModel.getEvent(userid, token, callBack);
		
		return response.toString();
	}
	@POST
	@Path("createEvent/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createEvent(Event event,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
				response.setStatus("success");
				response.setMessage("Create Successful");
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
		eventModel.createEvent(event,userid,token, callBack);
		return response.toString();
	}
	@POST
	@Path("updateEvent/{userid}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateEvent(Event event,@PathParam("userid") String userid,@PathParam("token") String token) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
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
		eventModel.updateEvent(event, userid, token, callBack);
		return response.toString();
	}
	@GET
	@Path("deleteEvent/{userid}/{token}/{idEvent}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEvent(@PathParam("userid") String userid,@PathParam("token") String token,
			@PathParam("idEvent") String idEvent) {
		JsonResponse<String> response=new JsonResponse<String>(String.class);
		CallBack<String> callBack=new CallBack<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println("Call back in Controller");
				response.setStatus("success");
				response.setMessage("delete Successful");
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
		eventModel.deleteEvent(userid, token, idEvent, callBack);
		return response.toString();
	}
}
