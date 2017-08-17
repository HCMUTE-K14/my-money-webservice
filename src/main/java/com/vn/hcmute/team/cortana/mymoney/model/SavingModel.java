package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class SavingModel {
	DataRepository dataRepository;
	
	@Autowired
	public SavingModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	public void getSaving(String userid, String token, CallBack<List<Saving>> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Saving> list=dataRepository.getSaving(userid);
			callBack.onSuccess(list);
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Saving!"));
		}
	}
	public void createSaving(String userid, String token,Saving saving,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail create Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.createSaving(saving);
			callBack.onSuccess("Create saving success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail create Saving!"));
		}
	}
	public void updateSaving(String userid, String token,Saving saving,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail update Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.updateSaving(saving);
			callBack.onSuccess("Update saving success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail update Saving!"));
		}
	}
	public void deleteSaving(String userid,String token,String idSaving ,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail delete Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.deleteSaving(idSaving);
			callBack.onSuccess("Delete saving success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail delete Saving!"));
		}
	}
	public void takeIn(String userid,String token,String idWallet,String idSaving,String money, CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail take in Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.takeIn(idWallet, idSaving, money);
			callBack.onSuccess("Take in saving success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail take in Saving!"));
		}
	}
	public void takeOut(String userid,String token,String idWallet,String idSaving,String money, CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail take out Saving!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.takeOut(idWallet, idSaving, money);
			callBack.onSuccess("Take out saving success!");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail take out Saving!"));
		}
	}
}
