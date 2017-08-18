package com.vn.hcmute.team.cortana.mymoney.data.service.image;

import java.io.InputStream;
import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Image;

public interface ImageService {
	
	List<Image> getAllImage(String userid);
	
	void upload(String userid,String token,String detail,InputStream input);
	
	void remove(String userid,String imageId);
	
	Image get(String userid,String imageId);
	
}
