package com.vn.hcmute.team.cortana.mymoney.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Image;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.ImageModel;

@SuppressWarnings("unchecked")
@Component
@Path("image")
public class ImageController {
	@Autowired
	ImageModel mImageModel;

	public ImageController() {

	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll(@QueryParam("uid") String userid, @QueryParam("token") String token) {
		Class<List<Image>> clazz = (Class<List<Image>>) (Object) List.class;
		JsonResponse<List<Image>> response = new JsonResponse<List<Image>>(clazz);

		CallBack<List<Image>> callback = new CallBack<List<Image>>() {

			@Override
			public void onSuccess(List<Image> result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mImageModel.getAllImage(userid, token, callback);

		return response.toString();
	}

	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@PathParam("id") String imageId) {

		JsonResponse<Image> response = new JsonResponse<Image>(Image.class);

		CallBack<Image> callback = new CallBack<Image>() {

			@Override
			public void onSuccess(Image result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mImageModel.get(userid, token, imageId, callback);
		return response.toString();
	}

	// @POST
	// @Path("upload")
	// @Consumes(MediaType.MULTIPART_FORM_DATA)
	// @Produces(MediaType.APPLICATION_JSON)
	// public String upload(@FormDataParam("file") InputStream
	// uploadedInputStream,
	// @FormDataParam("file") FormDataContentDisposition fileDetail,
	// @FormDataParam("uid")String userid,
	// @FormDataParam("token")String token,
	// @FormDataParam("detail")String imageDetail){
	// JsonResponse<String> response=new JsonResponse<String>(String.class);
	// CallBack<String> callback=new CallBack<String>(){
	//
	// @Override
	// public void onSuccess(String result) {
	// response.setStatus("success");
	// response.setMessage("ok");
	// response.setData(result);
	// }
	//
	// @Override
	// public void onFailure(Throwable e) {
	// response.setStatus("failure");
	// response.setMessage(e.getMessage());
	// response.setData(null);
	// }
	// };
	// mImageModel.upload(userid, token, imageDetail, uploadedInputStream,
	// callback);
	//
	// return response.toString();
	// }

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String upload(@FormDataParam("file") List<FormDataBodyPart> bodyParts,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("uid") String userid,
			@FormDataParam("token") String token, @FormDataParam("detail") String imageDetail,
			@FormDataParam("local_path") String local_path) {
		Class<List<Image>> clazz = (Class<List<Image>>) (Object) List.class;
		JsonResponse<List<Image>> response = new JsonResponse<List<Image>>(clazz);
		CallBack<List<Image>> callback = new CallBack<List<Image>>() {

			@Override
			public void onSuccess(List<Image> result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		List<InputStream> inputs = new ArrayList<>();
		for (int i = 0; i < bodyParts.size(); i++) {
			BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			inputs.add(bodyPartEntity.getInputStream());
		}

		 mImageModel.upload(userid, token, imageDetail, inputs.toArray(new InputStream[inputs.size()]),callback);
		
		 return response.toString();
	}

	@GET
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	public String remove(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("id") String imageId) {
		JsonResponse<String> response = new JsonResponse<String>(String.class);
		CallBack<String> callback = new CallBack<String>() {

			@Override
			public void onSuccess(String result) {
				response.setStatus("success");
				response.setMessage("ok");
				response.setData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				response.setStatus("failure");
				response.setMessage(e.getMessage());
				response.setData(null);
			}
		};
		mImageModel.remove(userid, token, imageId, callback);

		return response.toString();
	}
}
