package com.vn.hcmute.team.cortana.mymoney.data.service.person;

import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Person;

public interface PersonService {
	List<Person> getPersons(String userid);
	void addPerson(Person person);
	void removePerson(String personid);
	
}
