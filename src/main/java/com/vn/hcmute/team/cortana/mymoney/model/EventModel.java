package com.vn.hcmute.team.cortana.mymoney.model;

import static org.hamcrest.CoreMatchers.either;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Currencies;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;

@Component
public class EventModel {
DataRepository dataRepository;
	
	@Autowired
	SecurityUtil securityUtil;
	@Autowired
	public EventModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	public void getEvent(String userid, String token, CallBack<List<Event>> callBack){
		if(userid.equals("")||token.equals("")) {
			 callBack.onFailure(new Throwable("Fail get Event!"));
		}else {
			String apiKeyDB=securityUtil.getApiKey(userid);
			//gena
			String apiKey=SecurityUtil.generateApiKey(token);
			
			if(apiKeyDB.equals(apiKey)) {
				List<Event> list=dataRepository.getEvent(userid);
				callBack.onSuccess(list);
				
			}else {
				callBack.onFailure(new Throwable("Fail get Event!"));
			}
			
		}
	}
	public void createEvent(Event event, CallBack<String> callBack) {
		if(event.getUserid().equals("")||event.getToken().equals("")) {
			 callBack.onFailure(new Throwable("Fail create Event!"));
		}else {
			String apiKeyDB=securityUtil.getApiKey(event.getUserid());
			//gena
			String apiKey=SecurityUtil.generateApiKey(event.getToken());
			
			if(apiKeyDB.equals(apiKey)) {
				event.setId(SecurityUtil.generateUUID());
				dataRepository.createEvent(event);
				callBack.onSuccess("Success create Event!");
				
			}else {
				callBack.onFailure(new Throwable("Fail create Event!"));
			}
			
		}
	}
	public void updateEvent(Event event, CallBack<String> callBack) {
		if(event.getUserid().equals("")||event.getToken().equals("")) {
			 callBack.onFailure(new Throwable("Fail update Event!"));
		}else {
			String apiKeyDB=securityUtil.getApiKey(event.getUserid());
			//gena
			String apiKey=SecurityUtil.generateApiKey(event.getToken());
			
			if(apiKeyDB.equals(apiKey)) {
				dataRepository.updateEvent(event);
				callBack.onSuccess("Success update Event!");
				
			}else {
				callBack.onFailure(new Throwable("Fail update Event!"));
			}
			
		}
	}
	public void deleteEvent(String userid, String token, String idEvent, CallBack<String> callBack) {
		if(userid.equals("")||token.equals("")) {
			 callBack.onFailure(new Throwable("Fail delete Event!"));
		}else {
			String apiKeyDB=securityUtil.getApiKey(userid);
			//gena
			String apiKey=SecurityUtil.generateApiKey(token);
			
			if(apiKeyDB.equals(apiKey)) {
				dataRepository.deleteEvent(userid, idEvent);
				callBack.onSuccess("Success delete Event!");
				
			}else {
				callBack.onFailure(new Throwable("Fail delete Event!"));
			}
			
		}
	}
}
