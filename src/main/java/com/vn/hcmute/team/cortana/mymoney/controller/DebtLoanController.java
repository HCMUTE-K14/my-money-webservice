package com.vn.hcmute.team.cortana.mymoney.controller;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.DebtLoan;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.model.DebtLoanModel;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
@Path("debt_loan")
@SuppressWarnings("unchecked")
public class DebtLoanController {
    
    public static final Log LOG = LogFactory.getLog(DebtLoanController.class);
    
    @Autowired
    DebtLoanModel mDebtLoanModel;
    
    public DebtLoanController() {
    
    }
    
    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("uid") String userid, @QueryParam("token") String token,
              @QueryParam("wallet_id") String wallet_id,
              @DefaultValue("all") @QueryParam("type") String type) {
        Class<List<DebtLoan>> clazz = (Class<List<DebtLoan>>) (Object) List.class;
        JsonResponse<List<DebtLoan>> response = new JsonResponse<List<DebtLoan>>(clazz);
        CallBack<List<DebtLoan>> callBack = new CallBack<List<DebtLoan>>() {
            
            @Override
            public void onSuccess(List<DebtLoan> result) {
                
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
        switch (type) {
            case "debt":
            case "loan":
                mDebtLoanModel.getDebtLoanByType(userid, token, wallet_id, type, callBack);
                break;
            default:
                mDebtLoanModel.getListDebtLoan(userid, token, wallet_id, callBack);
                break;
            
        }
        
        return response.toString();
    }
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(@QueryParam("uid") String userId, @QueryParam("token") String token,
              DebtLoan debtLoan) {
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
        mDebtLoanModel.addDebtLoan(userId, token, debtLoan, callback);
        return response.toString();
    }
    
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@QueryParam("uid") String userId, @QueryParam("token") String token,
              @QueryParam("wallet_id") String wallet_id, DebtLoan debtLoan) {
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
        mDebtLoanModel.updateDebtLoan(userId, token, wallet_id, debtLoan, callback);
        return response.toString();
    }
    
    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@QueryParam("uid") String userId, @QueryParam("token") String token,
              DebtLoan debtLoan) {
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
        mDebtLoanModel.deleteDebtLoan(userId, token, debtLoan, callback);
        return response.toString();
    }
}
