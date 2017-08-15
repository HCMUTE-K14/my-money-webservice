package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;

@Component
public class WalletModel {
	
	DataRepository dataRepository;
	@Autowired
	SecurityUtil securityUtil;
	
	@Autowired
	public WalletModel(DataRepository dataRepository) {
		this.dataRepository=dataRepository;
	}
	//Dung chung 1 cai callback la ok roi ko can tao moi dau sao lai la list vay wallet user nhieu vi
	//Thi phai truyen vao user id chu trong cai wallet co userid ruif ko y la luc may post len day thi thi minh post theo query param chu ko phai wall et
	public void getInfoWallet(String userid,String token,CallBack<List<Wallet>> resultWallet){
		try {
			 if(userid.equals("")||token.equals("")) {
				 resultWallet.onFailure(new Throwable("Fail get Wallets!"));
			 }else {
				String apiKeyDB=securityUtil.getApiKey(userid);
					//gena
				String apiKey=SecurityUtil.generateApiKey(token);
				if(apiKey.equals(apiKeyDB)) {
					 List<Wallet> list =dataRepository.getInfoWallet(userid);
					 resultWallet.onSuccess(list);
				}else {
					resultWallet.onFailure(new Throwable("Fail get Wallets!"));
				}
			 }
		}catch(Exception e) {
			resultWallet.onFailure(e);
		}

	}
	//rui ok de tao viet lai/ con luc muon tao thi minh dua ntokentrugyen uvao wallet luon 
	//co phai y may la dua cai userid voi totoken udungroi lay theo 2 cai do dc roi ko can phai truyen json wallet len ok hiurservire dau 
	public void createWallet(Wallet wallet,CallBack<String> callBack) {
		try {
			String apiKeyDB=securityUtil.getApiKey(wallet.getUserid());
			//gena
			String apiKey=SecurityUtil.generateApiKey(wallet.getToken());
			if(apiKeyDB.equals(apiKey)) {
				wallet.setId(SecurityUtil.generateUUID());
				dataRepository.createWallet(wallet);
				callBack.onSuccess("Create wallet success!");
			}else {
				callBack.onFailure(new Throwable("Create fail!"));
			}
				
		}catch (Exception e) {
			// TODO: handle exception
			callBack.onFailure(e);
		}
	}
	public void deleteWallet(String userid,String token,String idwallet,CallBack<String> callBack) {
		try {
			if(userid.equals("")||token.equals("")||idwallet.equals("")) {
				callBack.onFailure(new Throwable("Fail delete wallet!"));
			}else {
				String apiKeyDB=securityUtil.getApiKey(userid);
				//gena
				String apiKey=SecurityUtil.generateApiKey(token);
				if(apiKey.equals(apiKeyDB)) {
					dataRepository.deleteWallet(userid, idwallet);
					callBack.onSuccess("Delete wallet userId: "+userid+", idWallet: "+idwallet+" success!");
					
				}else {
					callBack.onFailure(new Throwable("Fail delete wallet!"));
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			callBack.onFailure(e);
		}

	}

	public void updateWallet(Wallet wallet,CallBack<String> callBack) {
		try {
			String apiKeyDB=securityUtil.getApiKey(wallet.getUserid());
			//gena
			String apiKey=SecurityUtil.generateApiKey(wallet.getToken());
			if(apiKeyDB.equals(apiKey)) {
				dataRepository.updateWallet(wallet);
				callBack.onSuccess("Update wallet success!");
			}else {
				callBack.onFailure(new Throwable("Fail update wallet!"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			callBack.onFailure(e);
		}
	}
	public void moveMoneyWallet(String userid,String token, String idWalletFrom, String idWalletTo, String money,CallBack<String> callBack) {
		try {
			if(userid.equals("")||token.equals("")||idWalletFrom.equals("")||idWalletTo.equals("")||money.equals("")) {
				callBack.onFailure(new Throwable("Fail move money wallet!"));
			}else {
				String apiKeyDB=securityUtil.getApiKey(userid);
				//gena
				String apiKey=SecurityUtil.generateApiKey(token);
				if(apiKeyDB.equals(apiKey)) {
					dataRepository.moveMoneyWallet(userid, idWalletFrom, idWalletTo, money);
					callBack.onSuccess("Move money sucess!");
				}else {
					callBack.onFailure(new Throwable("Fail move money wallet!"));
				}
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			callBack.onFailure(e);
		}
	}
}
