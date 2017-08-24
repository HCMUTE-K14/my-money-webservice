package com.vn.hcmute.team.cortana.mymoney.data.service.event;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Event;

public interface EventService {
	
	List<Event> getEvent(String userid);
	
	void createEvent(Event event);
	
	void updateEvent(Event event);
	
	void deleteEvent(String userid, String idEvent);
}
