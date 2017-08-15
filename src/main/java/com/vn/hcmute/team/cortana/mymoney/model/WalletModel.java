package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class WalletModel {
	
	DataRepository dataRepository;
	
	@Autowired
	public WalletModel(DataRepository dataRepository) {
		this.dataRepository=dataRepository;
	}

	public void getInfoWallet(String userid,String token,CallBack<List<Wallet>> resultWallet){
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				resultWallet.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				resultWallet.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Wallet> list=dataRepository.getInfoWallet(userid);
			resultWallet.onSuccess(list);
		}catch(Exception e){
			resultWallet.onFailure(new Throwable("Fail get Currencies!"));
		}

	}
	
	public void createWallet(Wallet wallet,String userid, String token,CallBack<String> callBack) {
		try{
			
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.createWallet(wallet);
			callBack.onSuccess("Success create wallet");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Currencies!"));
		}
	}
	public void deleteWallet(String userid,String token,String idwallet,CallBack<String> callBack) {
		try{

			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.deleteWallet(userid, idwallet);
			callBack.onSuccess("Success delete wallet");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Currencies!"));
		}

	}

	public void updateWallet(Wallet wallet,String userid, String token,CallBack<String> callBack) {
		try{
			
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.updateWallet(wallet);
			callBack.onSuccess("Success update wallet");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Currencies!"));
		}
	}
	public void moveMoneyWallet(String userid,String token, String idWalletFrom, String idWalletTo, String money,CallBack<String> callBack) {
		try{
			if(TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)){
				callBack.onFailure(new Throwable("Fail get Currencies!"));
				return;
			}
			if (!dataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			dataRepository.moveMoneyWallet(userid, idWalletFrom, idWalletTo, money);
			callBack.onSuccess("Success update wallet");
		}catch(Exception e){
			callBack.onFailure(new Throwable("Fail get Currencies!"));
		}
	}
}
