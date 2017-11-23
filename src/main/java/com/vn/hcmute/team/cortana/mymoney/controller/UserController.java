package com.vn.hcmute.team.cortana.mymoney.controller;

import com.google.gson.Gson;
import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.model.UserModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("users")
public class UserController {
    
    public static final Log LOG = LogFactory.getLog(UserController.class);
    
    @Autowired
    UserModel mUserModel;
    
    @Autowired
    Gson mGson;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String home() {
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        
        response.setStatus("success");
        response.setMessage("ok");
        response.setData("user_active:" + mUserModel.getNumberOfActiveUser());
        
        return response.toString();
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(UserCredential userCredentials) {
        LOG.info("Login....");
        JsonResponse<User> response = new JsonResponse<User>(User.class);
        CallBack<User> callback = new CallBack<User>() {
            
            @Override
            public void onSuccess(User result) {
                response.setStatus("success");
                response.setMessage("ok");
                response.setData(result);
            }
            
            @Override
            public void onFailure(Throwable e) {
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                response.setData(null);
                LOG.info("Login fail");
            }
        };
        mUserModel.login(userCredentials, callback);
        return response.toString();
    }
    
    @POST
    @Path("login_with_facebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginWithFacebook(User userCredentials) {
        LOG.info("Login....");
        JsonResponse<User> response = new JsonResponse<User>(User.class);
        CallBack<User> callback = new CallBack<User>() {
            
            @Override
            public void onSuccess(User result) {
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
        mUserModel.loginWithFacebook(userCredentials, callback);
        return response.toString();
    }
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String register(User user) {
        LOG.info("Register....");
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        
        CallBack<String> callback = new CallBack<String>() {
            
            @Override
            public void onSuccess(String result) {
                
                response.setStatus("success");
                response.setMessage("Register Successful");
                response.setData(result);
            }
            
            @Override
            public void onFailure(Throwable e) {
                
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                response.setData(null);
                LOG.info("Register is fail");
            }
            
        };
        mUserModel.register(user, callback);
        return response.toString();
    }
    
    @POST
    @Path("forget")
    @Produces(MediaType.APPLICATION_JSON)
    public String forgetpassword(@QueryParam("email") String email) {
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        CallBack<String> callBack = new CallBack<String>() {
            
            @Override
            public void onSuccess(String result) {
                response.setStatus("success");
                response.setMessage("Sent to " + email + " Successful");
                response.setData(result);
                
            }
            
            @Override
            public void onFailure(Throwable e) {
                response.setStatus("failure");
                response.setMessage(e.getMessage());
                response.setData(null);
            }
            
        };
        
        mUserModel.forgetPassword(email, callBack);
        return response.toString();
    }
    
    @POST
    @Path("changepassword")
    @Produces(MediaType.APPLICATION_JSON)
    public String changepassword(@QueryParam("uid") String userid,
              @QueryParam("token") String token,
              @QueryParam("oldpassword") String oldpassword,
              @QueryParam("newpassword") String newpassword) {
        
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        CallBack<String> callBack = new CallBack<String>() {
            
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
        
        mUserModel.changePassword(userid, token, oldpassword, newpassword, callBack);
        return response.toString();
        
    }
    
    @POST
    @Path("changeprofile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String changeprofile(@QueryParam("uid") String userid, @QueryParam("token") String token,
              User user) {
        
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        CallBack<String> callBack = new CallBack<String>() {
            
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
        
        mUserModel.changeProfile(userid, token, user, callBack);
        return response.toString();
        
    }
    
    @POST
    @Path("isExistFacebookAccount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String isExistFacebookAccount(User user) {
        
        JsonResponse<String> response = new JsonResponse<String>(String.class);
        CallBack<String> callBack = new CallBack<String>() {
            
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
        
        String email = user.getEmail();
        mUserModel.isExistFacebookAccount(email, callBack);
        return response.toString();
    }
    
}
