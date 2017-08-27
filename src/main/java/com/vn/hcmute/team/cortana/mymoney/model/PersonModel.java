package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Person;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class PersonModel {
	@Autowired
	DataRepository dataRepository;
	
	public void getPersons(String userid,String token,CallBack<List<Person>> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Person> list=dataRepository.getPersons(userid);
			callBack.onSuccess(list);
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Saving!"));
		}
	}
	public void addPerson(String userid,String token,Person person,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail create Person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.addPerson(person);
			callBack.onSuccess("Create person success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail create Person!"));
		}
	}
	
	public void updatePerson(String userid,String token,Person person,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail update Person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.updatePerson(person);
			callBack.onSuccess("Update person success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail create Person!"));
		}
	}
	
	public void synchPerson(String userid,String token,List<Person> persons,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail update Person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.synchPerson(persons);
			callBack.onSuccess("Create person success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail create Person!"));
		}
	}
	
	public void removePerson(String userid, String token,String personid,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail delete person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.removePerson(personid);
			callBack.onSuccess("Delete person success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail person Saving!"));
		}
	}
	
}
