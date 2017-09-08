package com.vn.hcmute.team.cortana.mymoney.data.service.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Person;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
@Component
public class PersonServiceImp implements  PersonService{
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Person> getPersons(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("userid").is(userid));
			List<Person> list=mongoTemplate.find(searchQuery, Person.class,DbConstraint.TABLE_PERSON);
			return list;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void addPerson(Person person) {
		try {
			mongoTemplate.save(person, DbConstraint.TABLE_PERSON);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

	@Override
	public void removePerson(String personid) {
		try {
			
			Query query = new Query();
			query.addCriteria(Criteria.where("personid").is(personid));
			Person person=mongoTemplate.findOne(query, Person.class,DbConstraint.TABLE_PERSON);
			if(person==null)
				throw new RuntimeException("Null Person!");
			mongoTemplate.remove(query, Person.class, DbConstraint.TABLE_PERSON);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void updatePerson(Person person) {
		try{
			Query query=new Query();
			query.addCriteria(Criteria.where("personid").is(person.getPersonid()).and("userid").is(person.getUserid()));
			
			Person _person=mongoTemplate.findOne(query, Person.class,DbConstraint.TABLE_PERSON);
			if(_person == null ){
				throw new RuntimeException("Null Person!");
			}
			
			Update update=new Update();
			update.set("name", person.getName());
			update.set("describe", person.getDescribe());
		
			mongoTemplate.updateFirst(query, update,Person.class,DbConstraint.TABLE_PERSON);
		}catch(MongoException e){
			throw new DatabaseException("Something wrong ! Please try later");
		}
		
	}

	@Override
	public synchronized void syncPerson(List<Person> persons) {
		try{
			if(persons == null || persons.isEmpty()){
				throw new RuntimeException("List of person is empty");
			}
			List<Person> listPersonRemote=getPersons(persons.get(0).getUserid());
			
			for(int i=0; i<listPersonRemote.size();i++){
				if(!persons.contains(listPersonRemote.get(i))){
					removePerson(listPersonRemote.get(i).getPersonid());
				}
			}

			Query query=new Query();
			for(int i=0;i<persons.size();i++){
				query.addCriteria(Criteria
						.where("personid")
						.is(persons.get(i).getPersonid())
						.and("userid")
						.is(persons.get(i).getUserid()));
				
				Person _person=mongoTemplate.findOne(query, Person.class,DbConstraint.TABLE_PERSON);
				if(_person == null){
					mongoTemplate.save(persons.get(i),DbConstraint.TABLE_PERSON);
					continue;
				}
				
				Update update=new Update();
				update.set("name", _person.getName());
				update.set("describe", _person.getDescribe());
			
				mongoTemplate.updateFirst(query, update,Person.class,DbConstraint.TABLE_PERSON);
			}
		
		}catch(MongoException e){
			throw new DatabaseException("Something wrong ! Please try later");
		}		
	}
}
