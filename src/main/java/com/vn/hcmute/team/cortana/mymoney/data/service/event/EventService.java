package com.vn.hcmute.team.cortana.mymoney.data.service.event;

import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import java.util.List;

public interface EventService {
    
    List<Event> getEvent(String userid);
    
    void createEvent(Event event);
    
    void updateEvent(Event event);
    
    void deleteEvent(String userid, String idEvent);
    
    void syncEvent(List<Event> list);
}
