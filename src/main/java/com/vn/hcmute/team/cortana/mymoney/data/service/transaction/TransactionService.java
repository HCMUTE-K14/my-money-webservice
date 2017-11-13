package com.vn.hcmute.team.cortana.mymoney.data.service.transaction;

import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import java.util.List;

public interface TransactionService {
    
    List<Transaction> getAllTransaction(String userid);
    
    Transaction getTransactionById(String transactionId, String userid);
    
    List<Transaction> getTransactionByType(int type, String userid); // cho vay,
    // chi
    // tieu,
    // thu
    // nhap
    
    List<Transaction> getTransactionByTime(String startDate, String endDate, String userid);
    
    List<Transaction> getTransactionByCategory(String categoryId, String startDate, String endDate,
              String userid);
    
    List<Transaction> getTransactionByType(int type, String userid, String walletId); // cho
    // vay,
    // chi
    // tieu,
    // thu
    // nhap
    
    List<Transaction> getTransactionByTime(String startDate, String endDate, String userid,
              String walletId);
    
    List<Transaction> getTransactionByCategory(String categoryId, String userid, String startDate,
              String endDate,
              String walletId);
    
    List<Transaction> getTransactionByEvent(String userId, String eventId);
    
    List<Transaction> getTransactionByBudget(String userId, String startDate, String endDate,
              String categoryId,
              String walletId);
    
    List<Transaction> getTransactionBySaving(String userId, String savingId);
    
    void addTransaction(Transaction transaction);
    
    void updateTransaction(Transaction transaction);
    
    void removeTransaction(String transactionId, String userid);
    
    void syncTransaction(List<Transaction> transactions);
}
