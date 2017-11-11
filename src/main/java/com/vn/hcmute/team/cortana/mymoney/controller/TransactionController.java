package com.vn.hcmute.team.cortana.mymoney.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.model.TransactionModel;

@SuppressWarnings("unchecked")
@Component
@Path("transaction")
public class TransactionController {

	@Autowired
	private TransactionModel mTransactionModel;

	public TransactionController() {

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction home(){
		return new Transaction();
	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("uid") String userid, @QueryParam("token") String token) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getAllTransaction(userid, token, callBack);
		return response.toString();
	}

	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByID(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@PathParam("id") String id) {

		JsonResponse<Transaction> response = new JsonResponse<Transaction>(Transaction.class);

		CallBack<Transaction> callBack = new CallBack<Transaction>() {

			@Override
			public void onSuccess(Transaction result) {
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
		mTransactionModel.getTransactionById(userid, token, id, callBack);
		return response.toString();
	}

	@GET
	@Path("getByType")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByType(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("type") String type) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByType(userid, token, Integer.valueOf(type), callBack);
		return response.toString();
	}
	
	@GET
	@Path("getByType/{walletid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByType_wallet(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("type") String type,@PathParam("walletid")String walletid) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByType(userid, token, Integer.valueOf(type), walletid, callBack);
		return response.toString();
	}
	
	@GET
	@Path("getByTime")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByTime(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("startdate") String startDate,
			@QueryParam("enddate") String endDate
			) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByTime(userid, token, startDate, endDate, callBack);
		return response.toString();
	}
	
	@GET
	@Path("getByTime/{walletid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByTime_wallet(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("startdate") String startDate,
			@QueryParam("enddate") String endDate,@PathParam("walletid") String walletId
			) {
	
		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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

		mTransactionModel.getTransactionByTime(userid, token, startDate, endDate, walletId, callBack);
		return response.toString();
	}

	@GET
	@Path("getByCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByCategory(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("categoryid") String categoryId, @QueryParam("start_date") String startDate,
			@QueryParam("end_date") String endDate) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByCategory(userid, token, categoryId,startDate,endDate, callBack);
		return response.toString();
	}
	@GET
	@Path("getByCategory/{walletid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByCategory_wallet(@QueryParam("uid") String userid, @QueryParam("token") String token,
			@QueryParam("categoryid") String categoryId,@PathParam("walletid") String walletId,
			@QueryParam("start_date") String startDate,
			@QueryParam("end_date") String endDate) {

		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByCategory(userid, token, categoryId, startDate,endDate,walletId, callBack);
		return response.toString();
	}
	@GET
	@Path("getTransactionByEvent/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransactionByEvent(@QueryParam("uid") String userid, @QueryParam("token") String token, @PathParam("eventId") String eventId) {
		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByEvent(userid, token, eventId, callBack);
		return response.toString();
	}
	@GET
	@Path("getTransactionByBudget/{startDate}/{endDate}/{categoryId}/{walletId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransactionByBudget(@QueryParam("uid") String userid, @QueryParam("token") String token, @PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate, @PathParam("categoryId") String categoryId,@PathParam("walletId") String walletId) {
		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionByBudget(userid, token, startDate, endDate, categoryId,walletId, callBack);
		return response.toString();
	}
	@GET
	@Path("getTransactionBySaving/{idSaving}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransactionBySaving(@QueryParam("uid") String userid, @QueryParam("token") String token, @PathParam("idSaving") String idSaving) {
		Class<List<Transaction>> clazz = (Class<List<Transaction>>) (Object) List.class;

		JsonResponse<List<Transaction>> response = new JsonResponse<List<Transaction>>(clazz);

		CallBack<List<Transaction>> callBack = new CallBack<List<Transaction>>() {

			@Override
			public void onSuccess(List<Transaction> result) {
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
		mTransactionModel.getTransactionBySaving(userid, token, idSaving, callBack);
		return response.toString();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(@QueryParam("uid") String userid, @QueryParam("token") String token,Transaction transaction) {
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
		mTransactionModel.addTransaction(userid, token, transaction, callBack);
		return response.toString();
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(@QueryParam("uid") String userid, @QueryParam("token") String token,Transaction transaction) {
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
		mTransactionModel.updateTransaction(userid, token, transaction, callBack);
		return response.toString();
	}
	@POST
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String remove(@QueryParam("uid") String userid, @QueryParam("token") String token,@QueryParam("transactionid")String transactionId) {
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
		mTransactionModel.removeTransaction(userid, token,transactionId, callBack);
		return response.toString();
	}
	
	@POST
	@Path("sync")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sync(@QueryParam("uid") String userid, @QueryParam("token") String token,List<Transaction> transactions) {
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
		mTransactionModel.syncTransaction(userid, token,transactions, callBack);
		return response.toString();
	}
	
}
