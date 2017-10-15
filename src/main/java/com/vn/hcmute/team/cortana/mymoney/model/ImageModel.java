package com.vn.hcmute.team.cortana.mymoney.model;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Image;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.ImageException;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
@Component
public class ImageModel {

	private DataRepository mDataRepository;
	
	@Autowired
	public ImageModel(DataRepository mDataRepository){
		this.mDataRepository=mDataRepository;
	}
	
	public void getAllImage(String userid,String token,CallBack<List<Image>> callback){
		try{
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Image> result = mDataRepository.getAllImage(userid);
			if (result != null) {
				callback.onSuccess(result);
				return;
			}
			callback.onFailure(new ImageException("cannot get images"));
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	
	public void upload(String userid,String token,String detail,InputStream input,CallBack<String> callback){
		try{
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			mDataRepository.uploadImage(userid, token, detail, input);
			callback.onSuccess("Upload image successful");
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	public void upload(String userid,String token,String detail,InputStream[] inputStreams,CallBack<List<Image>> callback){
		try{
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			List<Image> images = mDataRepository.uploadImage(userid, token, detail, inputStreams);
			callback.onSuccess(images);
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	
	public void remove(String userid,String token,String imageId,CallBack<String> callback){
		try{
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			mDataRepository.removeImage(userid, imageId);
			callback.onSuccess("Remove image successful");
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	
	public void get(String userid,String token,String imageId,CallBack<Image> callback){
		try{
			if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
				callback.onFailure(new UserException("User id, token is empty"));
				return;
			}
			if (!mDataRepository.isApiKey(userid, token)) {
				callback.onFailure(new UserException("Wrong api key!"));
				return;
			}
			Image img=mDataRepository.getImage(userid, imageId);
			callback.onSuccess(img);
		}catch(Exception e){
			callback.onFailure(e);
		}
	}
	
}
