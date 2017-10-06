package com.vn.hcmute.team.cortana.mymoney.data.service.image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.Constraint;
import com.vn.hcmute.team.cortana.mymoney.bean.Image;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import com.vn.hcmute.team.cortana.mymoney.exception.ImageException;
import com.vn.hcmute.team.cortana.mymoney.utils.ImageUtil;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class ImageServiceImp implements ImageService{
	
	@Autowired
	MongoTemplate mMongoTemplate;

	@Override
	public List<Image> getAllImage(String userid) {
		try{
			List<Image> list= mMongoTemplate.find(query(where("user_id").is(userid)), Image.class,DbConstraint.TABLE_IMAGE);
			return list;
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void upload(String userid,String token,String detail,InputStream input) {
		try{
			Image image=new Image();
			String imageId=SecurityUtil.generateUUID();
			image.setImage_id(imageId);
			image.setUser_id(userid);
			image.setDetail(detail);
			
			image.setUrl(String.format(Constraint.PATH_GET_IMAGE,imageId,userid,token));
			if(!ImageUtil.uploadImage_Local(imageId, userid, input)){
				throw new ImageException("Cannot upload");
			}
			mMongoTemplate.save(image, DbConstraint.TABLE_IMAGE);
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void remove(String userid, String imageId) {
		try{
			if(!ImageUtil.deleteImage(imageId, userid)){
				throw new ImageException("Cannot remove image");
			}
			mMongoTemplate.findAndRemove(query(where("image_id").is(imageId).and("user_id").is(userid)), Image.class,DbConstraint.TABLE_IMAGE);

		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public Image get(String userid, String imageId) {
		try{
			Image image=mMongoTemplate.findOne(query(where("image_id").is(imageId).and("user_id").is(userid)), Image.class,DbConstraint.TABLE_IMAGE);
			if(image == null){
				throw new ImageException("Cannot found image");
			}
			return image;
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public List<Image> upload(String userid, String token, String detail, InputStream[] input) {
		try{
			List<Image> images = new ArrayList<>();
			for(int i=0;i<input.length;i++){
				Image image=new Image();
				String imageId=SecurityUtil.generateUUID();
				image.setImage_id(imageId);
				image.setUser_id(userid);
				image.setDetail(detail);
				
				image.setUrl(String.format(Constraint.PATH_GET_IMAGE,imageId,userid,token));
				if(!ImageUtil.uploadImage_Local(imageId, userid, input[i])){
					throw new ImageException("Cannot upload");
				}
				images.add(image);
				//mMongoTemplate.save(image, DbConstraint.TABLE_IMAGE);
			}
			
			return images;
		}catch(MongoException e){
			throw new DatabaseException("Something wrong! Please try later");
		}
	}
}
