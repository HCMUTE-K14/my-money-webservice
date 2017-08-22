package com.vn.hcmute.team.cortana.mymoney.data.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
@Component
public class EventServiceImp implements EventService{
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Event> getEvent(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("userid").is(userid));
			List<Event> list=mongoTemplate.find(searchQuery, Event.class,DbConstraint.TABLE_EVENT);
			return list;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	
	}

	@Override
	public void createEvent(Event event) {
		try {
			mongoTemplate.save(event, DbConstraint.TABLE_EVENT);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void updateEvent(Event event) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("eventid").is(event.getEventid()).and("userid").is(event.getUserid()));


			Event event1 = mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
			
			if(event1==null) {
				throw new RuntimeException("Null event!");
			}
			
		
		
			Update update=new Update();
			update.set("name", event.getName());
			update.set("money", event.getMoney());
			update.set("idWallet", event.getIdWallet());
			update.set("status", event.getStatus());
			update.set("date", event.getDate());
			
			mongoTemplate.updateFirst(query, update, Event.class,DbConstraint.TABLE_EVENT);
			
			
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void deleteEvent(String userid, String idEvent) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("eventid").is(idEvent).and("userid").is(userid));
			Event event=mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
			if(event==null)
				throw new RuntimeException("Null Event!");
			mongoTemplate.remove(query,Event.class,DbConstraint.TABLE_EVENT);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}
	
}
