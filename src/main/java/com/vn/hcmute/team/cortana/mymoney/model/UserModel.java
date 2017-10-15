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
	private Pattern mPatternEmail;

	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_@]{4,100}$";
	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_@]{4,100}$";
	private static final String EMAIL_PATTERN="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";


	@Autowired
	public UserModel(DataRepository dataRepository) {
		mPatternUsername = Pattern.compile(USERNAME_PATTERN);
		mPatternPassword = Pattern.compile(PASSWORD_PATTERN);
		mPatternEmail=Pattern.compile(EMAIL_PATTERN);
		
		this.mDataRepository = dataRepository;
	}

	public void register(User user, CallBack<String> callback) {
		try {
			validateUser(user);
			
			user.setUser_id(SecurityUtil.generateUUID());

			user.setPassword(SecurityUtil.generateMD5(user.getPassword()));
			user.setToken(SecurityUtil.generateUUID());
			user.setApikey(SecurityUtil.generateApiKey(user.getToken()));
			user.setActive(true);
            if(!TextUtil.isEmpty(user.getFacebook_id())){
                user.setFacebook_id(SecurityUtil.generateMD5(user.getFacebook_id()));
            }

			this.mDataRepository.register(user);
			this.mDataRepository.initDefaultCategory(user.getUser_id());

			callback.onSuccess("ok");
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}
    public void isExistFacebookAccount(String email,CallBack<String> callBack){
        try{
            if(TextUtil.isEmpty(email)){
                callBack.onFailure(new UserException("Email can't be empty"));
                return;
            }
            boolean result = mDataRepository.isExistFacebookAccount(email);
            if(result){
                callBack.onSuccess("Email is available");
            }else{
                callBack.onFailure(new UserException("Email is unavailable"));
            }

        }catch(Exception e){
            callBack.onFailure(e);
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
	public void loginWithFacebook(User userCredential,CallBack<User> callback){
		try{
			User user = this.mDataRepository.loginWithFacebook(userCredential);
			callback.onSuccess(user);
		}catch(Exception e){
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

	private void validateUser(User user) throws ValidateUserException{
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();

		if (username == null || password == null || email == null) {
			throw new ValidateUserException("Userid,token,oldpassword or newpassword is empty");
		}

		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			throw new ValidateUserException("Userid,token,oldpassword or newpassword is empty");
		}
		
		if(!mPatternEmail.matcher(email).matches()){
			throw new ValidateUserException("Wrong mail format");
		}

		if (!mPatternUsername.matcher(user.getUsername()).matches()) {
            System.out.println("USERNAMEE");
			throw new ValidateUserException("Username,password must be at least 4 character");
		}
		if (!mPatternPassword.matcher(user.getPassword()).matches()) {
            System.out.println("PASSWORD");
			throw new ValidateUserException("Username,password must be at least 4 character");
		}
	}
}
