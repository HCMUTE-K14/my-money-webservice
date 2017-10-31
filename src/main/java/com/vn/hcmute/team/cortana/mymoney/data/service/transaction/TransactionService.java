package com.vn.hcmute.team.cortana.mymoney.data.service.transaction;


import java.util.List;

import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;

public interface TransactionService {
	List<Transaction> getAllTransaction(String userid);
	
	Transaction  getTransactionById(String transactionId,String userid);
	
	List<Transaction> getTransactionByType(int type,String userid); //cho vay, chi tieu, thu nhap
	
	List<Transaction> getTransactionByTime(String startDate,String endDate,String userid);

	List<Transaction> getTransactionByCategory(String categoryId,String userid);
	
	List<Transaction> getTransactionByType(int type,String userid,String walletId); //cho vay, chi tieu, thu nhap
	
	List<Transaction> getTransactionByTime(String startDate,String endDate,String userid,String walletId);

	List<Transaction> getTransactionByCategory(String categoryId,String userid,String walletId);
	
	List<Transaction> getTransactionByEvent(String userId, String eventId);
	List<Transaction> getTransactionByBudget(String userId, String startDate, String endDate, String categoryId,String walletId);
	
	void addTransaction(Transaction transaction);
	
	void updateTransaction(Transaction transaction);
	
	void removeTransaction(String transactionId,String userid);
	
	void syncTransaction(List<Transaction> transactions);
}
