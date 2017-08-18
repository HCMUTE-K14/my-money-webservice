package com.vn.hcmute.team.cortana.mymoney.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.Constraint;
import com.vn.hcmute.team.cortana.mymoney.model.UserModel;

@Component
@Path("resource")
public class ResourceController {

	@Autowired
	UserModel mUserModel;
	
	public ResourceController() {

	}

	@GET
	@Path("img")
	@Produces({ "image/png", "image/jpg" })
	public Response getImage(@QueryParam("uid")String userid,@QueryParam("token")String token,@QueryParam("id")String imageId){
		
		if(!mUserModel.isApiKey(userid, token)){
			return null;
		}
		
		BufferedImage image=null;
		
		String path=Constraint.LOCATION_STORE_IMAGE+userid+"/"+imageId;
		try {
			File file=new File(path+".png");
			if(!file.exists()){
				file=new File(path+".jpg");
			}
			image = ImageIO.read(file);
		} catch (IOException e) {
			
		}
		
		return Response.ok(image).build();
	}
}
