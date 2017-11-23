package com.vn.hcmute.team.cortana.mymoney.data.service.person;

import com.vn.hcmute.team.cortana.mymoney.bean.Person;
import java.util.List;

public interface PersonService {
    
    List<Person> getPersons(String userid);
    
    void addPerson(Person person);
    
    void updatePerson(Person person);
    
    void removePerson(String personid);
    
    void syncPerson(List<Person> persons);
}
