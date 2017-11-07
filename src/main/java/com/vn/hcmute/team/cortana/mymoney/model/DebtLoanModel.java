package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.DebtLoan;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
@Component
public class DebtLoanModel {
	
	private DataRepository dataRepository;
	@Autowired
	public DebtLoanModel(DataRepository dataRepository) {
		// TODO Auto-generated constructor stub
		this.dataRepository=dataRepository;
	}
	
	
	public void getListDebtLoan(String userid, String token, String wallet_id, CallBack<List<DebtLoan>> callBack){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<DebtLoan> list=dataRepository.getDebtLoan(wallet_id);
			callBack.onSuccess(list);
		}catch(Exception e){
			callBack.onFailure(new Throwable(e.getMessage()));
		}
	}
	
	public void addDebtLoan(String userid,String token, DebtLoan debtLoan, CallBack<String> callBack){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.addDebtLoan(debtLoan);
			callBack.onSuccess("Insert Successful");
		}catch(Exception e){
			callBack.onFailure(new Throwable(e.getMessage()));
		}
	}
	
	public void updateDebtLoan(String userid,String token,String wallet_id, DebtLoan debtLoan, CallBack<String> callBack){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.updateDebtLoan(debtLoan, wallet_id);
			callBack.onSuccess("Update Successful");
		}catch(Exception e){
			callBack.onFailure(new Throwable(e.getMessage()));
		}
	}
	
	public void deleteDebtLoan(String userid,String token, DebtLoan debtLoan, CallBack<String> callBack){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.deleteDebtLoan(debtLoan);
			callBack.onSuccess("Remove Successful");
		}catch(Exception e){
			callBack.onFailure(new Throwable(e.getMessage()));
		}
	}
	public void getDebtLoanByType(String userid, String token, String wallet_id, String type, CallBack<List<DebtLoan>> callBack){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get person!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<DebtLoan> list=dataRepository.getDebtLoanByType(wallet_id, type);
			callBack.onSuccess(list);
		}catch(Exception e){
			callBack.onFailure(new Throwable(e.getMessage()));
		}
	}
}
