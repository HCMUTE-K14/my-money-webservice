package com.vn.hcmute.team.cortana.mymoney.data.service.debtloan;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.DebtLoan;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class DebtLoanServiceImp implements DebtLoanService {
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    public DebtLoanServiceImp() {
    
    }
    
    @Override
    public void addDebtLoan(DebtLoan debtLoan) {
        try {
            mongoTemplate.save(debtLoan, DbConstraint.TABLE_DEBT_LOAN);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void updateDebtLoan(DebtLoan debtLoan, String wallet_id) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("debt_loan_id").is(debtLoan.getDebt_loan_id()).and("user_id")
                                .is(debtLoan.getUser_id()).and("transaction.wallet.wallet_id")
                                .is(wallet_id));
            
            DebtLoan debt_loan = mongoTemplate
                      .findOne(query, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
            
            if (debt_loan == null) {
                throw new RuntimeException("Null event!");
            }
            
            Update update = new Update();
            
            update.set("transaction", debtLoan.getTransaction());
            update.set("type", debtLoan.getType());
            update.set("status", debtLoan.getStatus());
            
            mongoTemplate.updateFirst(query, update, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
            
            DebtLoan debt_loan_after = mongoTemplate
                      .findOne(query, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
            if (debt_loan_after.getStatus() == 1) {
                query = new Query();
                query.addCriteria(Criteria.where("wallet_id").is(wallet_id).and("user_id")
                          .is(debtLoan.getUser_id()));
                Wallet wallet = mongoTemplate
                          .findOne(query, Wallet.class, DbConstraint.TABLE_WALLET);
                double money;
                switch (debt_loan_after.getType()) {
                    case "loan":
                        money = Double.valueOf(wallet.getMoney())
                                + Double.valueOf(debt_loan_after.getTransaction().getAmount());
                        update.set("money", String.valueOf(money));
                        break;
                    case "debt":
                        money = Double.valueOf(wallet.getMoney())
                                - Double.valueOf(debt_loan_after.getTransaction().getAmount());
                        update.set("money", String.valueOf(money));
                        break;
                    default:
                        break;
                }
                mongoTemplate.updateFirst(query, update, Wallet.class, DbConstraint.TABLE_WALLET);
            }
        } catch (Exception e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void deleteDebtLoan(DebtLoan debtLoan) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("trans_id").is(debtLoan.getTransaction().getTrans_id())
                      .and("user_id")
                      .is(debtLoan.getUser_id()));
            mongoTemplate.findAndRemove(query, Transaction.class, DbConstraint.TABLE_TRANSACTION);
            
            query = new Query();
            query.addCriteria(Criteria.where("debt_loan_id").is(debtLoan.getDebt_loan_id()));
            
            mongoTemplate.findAndRemove(query, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
            
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public List<DebtLoan> getDebtLoan(String wallet_id) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("transaction.wallet.wallet_id").is(wallet_id));
            
            return mongoTemplate.find(query, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
    @Override
    public List<DebtLoan> getDebtLoanByType(String wallet_id, String type) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("transaction.wallet.wallet_id").is(wallet_id).and("type")
                                .is(type));
            
            return mongoTemplate.find(query, DebtLoan.class, DbConstraint.TABLE_DEBT_LOAN);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
}
