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
			Query searchQuery = new Query(Criteria.where("user_id").is(userid));
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
			query.addCriteria(Criteria.where("event_id").is(event.getEvent_id()).and("user_id").is(event.getUser_id()));
			
			Event event1 = mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
			
			if(event1==null) {
				throw new RuntimeException("Null event!");
			}
			
			Update update=new Update();
			update.set("name", event.getName());
			update.set("money", event.getMoney());
			update.set("wallet_id", event.getWallet_id());
			update.set("status", event.getStatus());
			update.set("date", event.getDate());
			update.set("currencies", event.getCurrencies());
			update.set("icon",event.getIcon());
			mongoTemplate.updateFirst(query, update, Event.class,DbConstraint.TABLE_EVENT);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
		
	}

	@Override
	public void deleteEvent(String userid, String idEvent) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("event_id").is(idEvent).and("user_id").is(userid));
			Event event=mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
			if(event==null)
				throw new RuntimeException("Null Event!");
			mongoTemplate.remove(query,Event.class,DbConstraint.TABLE_EVENT);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public synchronized void syncEvent(List<Event> list) {
		
		if(list==null) {
			throw new RuntimeException("Null list");
		}
		
		List<Event> listEventServer=getEvent(list.get(0).getUser_id());
		for(int i=0;i<listEventServer.size();i++) {
			if(!list.contains(listEventServer.get(i))) {
				deleteEvent(listEventServer.get(i).getUser_id(), listEventServer.get(i).getEvent_id());
			}
		}
		
		for (Event event : list) {
			sync(event);
		}
		
	}
	public void sync(Event event) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("event_id").is(event.getEvent_id()).and("user_id").is(event.getUser_id()));
			
			Event event1 = mongoTemplate.findOne(query, Event.class,DbConstraint.TABLE_EVENT);
			
			if(event1==null) {
				createEvent(event);
				return;
			}
			
			Update update=new Update();
			update.set("name", event.getName());
			update.set("money", event.getMoney());
			update.set("wallet_id", event.getWallet_id());
			update.set("status", event.getStatus());
			update.set("date", event.getDate());
			update.set("currencies", event.getCurrencies());
			update.set("icon", event.getIcon());
			mongoTemplate.updateFirst(query, update, Event.class,DbConstraint.TABLE_EVENT);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
}
