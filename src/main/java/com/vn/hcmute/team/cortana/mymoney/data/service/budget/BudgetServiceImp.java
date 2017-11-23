package com.vn.hcmute.team.cortana.mymoney.data.service.budget;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
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
public class BudgetServiceImp implements BudgetService {
    
    @Autowired
    MongoTemplate mMongoTemplate;
    
    @Override
    public List<Budget> getBudget(String userid) {
        try {
            Query searchQuery = new Query(Criteria.where("user_id").is(userid));
            List<Budget> list = mMongoTemplate
                      .find(searchQuery, Budget.class, DbConstraint.TABLE_BUDGET);
            return list;
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void createBudget(Budget budget) {
        try {
            mMongoTemplate.save(budget, DbConstraint.TABLE_BUDGET);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
    @Override
    public void updateBudget(Budget budget) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("budget_id").is(budget.getBudget_id()).and("user_id")
                                .is(budget.getUser_id()));
            
            Budget budget2 = mMongoTemplate.findOne(query, Budget.class, DbConstraint.TABLE_BUDGET);
            
            if (budget2 == null) {
                throw new RuntimeException("Null event!");
            }
            
            Update update = new Update();
            update.set("category", budget.getCategory());
            update.set("wallet", budget.getWallet());
            update.set("range_date", budget.getRange_date());
            update.set("money_goal", budget.getMoney_goal());
            update.set("money_expense", budget.getMoney_expense());
            update.set("status", budget.getStatus());
            
            mMongoTemplate.updateFirst(query, update, Budget.class, DbConstraint.TABLE_BUDGET);
            
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void removeBudget(String budgetId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("budget_id").is(budgetId));
            
            Budget budget = mMongoTemplate.findOne(query, Budget.class, DbConstraint.TABLE_BUDGET);
            if (budget == null) {
                throw new RuntimeException("Fail delete Budget!");
            }
            
            mMongoTemplate.remove(query, Budget.class, DbConstraint.TABLE_BUDGET);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
    @Override
    public synchronized void syncBudget(List<Budget> list) {
    
        if (list == null) {
            throw new RuntimeException("Null list budget!");
        }
        
        List<Budget> listBudgetServer = getBudget(list.get(0).getUser_id());
        for (int i = 0; i < listBudgetServer.size(); i++) {
            if (!list.contains(listBudgetServer.get(i))) {
                removeBudget(listBudgetServer.get(i).getBudget_id());
            }
        }
        
        for (Budget budget : list) {
            sync(budget);
        }
        
    }
    
    public void sync(Budget budget) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("budget_id").is(budget.getBudget_id()).and("user_id")
                                .is(budget.getUser_id()));
            
            Budget budget2 = mMongoTemplate.findOne(query, Budget.class, DbConstraint.TABLE_BUDGET);
            
            if (budget2 == null) {
                
                createBudget(budget);
                
                return;
            }
            
            Update update = new Update();
            update.set("category", budget.getCategory());
            update.set("wallet", budget.getWallet());
            update.set("range_date", budget.getRange_date());
            update.set("money_goal", budget.getMoney_goal());
            update.set("money_expense", budget.getMoney_expense());
            update.set("status", budget.getStatus());
            
            mMongoTemplate.updateFirst(query, update, Budget.class, DbConstraint.TABLE_BUDGET);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
}
