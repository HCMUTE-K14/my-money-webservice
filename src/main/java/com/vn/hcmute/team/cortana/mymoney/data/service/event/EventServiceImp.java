package com.vn.hcmute.team.cortana.mymoney.data.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;

@Component
public class EventServiceImp implements EventService{
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Event> getEvent(String userid) {
		Query searchQuery = new Query(Criteria.where("userid").is(userid));
		List<Event> list=mongoTemplate.find(searchQuery, Event.class,DbConstraint.TABLE_EVENT);
		return list;
	}

	@Override
	public void createEvent(Event event) {
		mongoTemplate.save(event, DbConstraint.TABLE_EVENT);
		
	}

	@Override
	public void updateEvent(Event event) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(event.getId()).and("userid").is(event.getUserid()));


		Event event1 = mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);

		event.setName(event.getName());
		event1.setMoney(event.getMoney());
		event1.setIdWallet(event.getIdWallet());
		event1.setStatus(event.getStatus());
		event1.setDate(event.getDate());
	
		mongoTemplate.save(event1,DbConstraint.TABLE_EVENT);
		
	}

	@Override
	public void deleteEvent(String userid, String idEvent) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(idEvent).and("userid").is(userid));
		Event event = mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
		mongoTemplate.remove(event,DbConstraint.TABLE_EVENT);
		
	}
	
}
