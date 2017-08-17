package com.vn.hcmute.team.cortana.mymoney.model;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.exception.ValidateUserException;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;

@Component
public class UserModel {
	private DataRepository mDataRepository;

	private Pattern mPatternUsername;
	private Pattern mPatternPassword;
	/**
	 * Username Pattern & Password Pattern Lenght: 4-15 char Contrainst: a-z,
	 * A-Z,0-9
	 */
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,15}$";
	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{4,15}$";

	@Autowired
	public UserModel(DataRepository dataRepository) {
		mPatternUsername = Pattern.compile(USERNAME_PATTERN);
		mPatternPassword = Pattern.compile(PASSWORD_PATTERN);

		this.mDataRepository = dataRepository;
	}

	public void register(User user, CallBack<String> callback) {
		
		if (validateUser(user)==false) {
			callback.onFailure(new ValidateUserException("Username,password and email must be at least 4 character"));
			return;
		}
		
		try {
			user.setUserid(SecurityUtil.generateUUID());

			user.setPassword(SecurityUtil.generateMD5(user.getPassword()));
			user.setToken(SecurityUtil.generateUUID());
			user.setApikey(SecurityUtil.generateApiKey(user.getToken()));
			user.setActive(true);

			this.mDataRepository.register(user);
			this.mDataRepository.initDefaultCategory(user.getUserid());

			callback.onSuccess("ok");
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	public long getNumberOfActiveUser() {
		return this.mDataRepository.getNumberOfActiveUser();
	}

	public void login(UserCredential userCredential, CallBack<User> callback) {
		try {
			User user = this.mDataRepository.login(userCredential);
			callback.onSuccess(user);
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}
	public void forgetPassword(String email,CallBack<String> callback){
		try{
			if(TextUtil.isEmpty(email)){
				callback.onFailure(new UserException("Email cannot empty"));
				return;
			}
			
			mDataRepository.forgetPassword(email);
			callback.onSuccess("");
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	public void changePassword(String userid,String token,String oldpassword,String newpassword,CallBack<String> callBack){
		try{
			if(TextUtil.isEmpty(userid)||TextUtil.isEmpty(token)||TextUtil.isEmpty(oldpassword)||TextUtil.isEmpty(newpassword)){
				callBack.onFailure(new UserException("Userid,token,oldpassword or newpassword is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			mDataRepository.changePassword(userid, oldpassword, newpassword);
			callBack.onSuccess("Change password successful");
		}catch(Exception e){
			callBack.onFailure(e);
		}
	}
	public boolean isApiKey(String userid,String token){
		return mDataRepository.isApiKey(userid, token);
	}
	public void changeProfile(String userid,String token,User user,CallBack<String> callBack){
		try{
			if(TextUtil.isEmpty(userid)||TextUtil.isEmpty(token)){
				callBack.onFailure(new UserException("Userid,token,oldpassword or newpassword is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callBack.onFailure(new UserException("Wrong api key!"));
				return;
			}
			mDataRepository.changeProfile(user);
			callBack.onSuccess("Change password successful");
		}catch(Exception e){
			callBack.onFailure(e);
		}
	}

	private boolean validateUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String email =user.getEmail();
	

		if (username == null || password == null || email == null) {
			return false;
		}

		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			return false;
		}

		if (!mPatternUsername.matcher(user.getUsername()).matches()) {
			return false;
		}
		if (!mPatternPassword.matcher(user.getPassword()).matches()) {
			return false;
		}
		return true;
	}
}
