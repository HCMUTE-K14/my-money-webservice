package com.vn.hcmute.team.cortana.mymoney.data.service.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
			mongoTemplate.remove(query, Person.class, DbConstraint.TABLE_PERSON);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

}
