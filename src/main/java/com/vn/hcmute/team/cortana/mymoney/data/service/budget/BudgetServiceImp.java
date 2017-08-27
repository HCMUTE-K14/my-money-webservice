package com.vn.hcmute.team.cortana.mymoney.data.service.budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Budget;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;

@Component
public class BudgetServiceImp implements BudgetService{
	@Autowired
	MongoTemplate mMongoTemplate;
	
	@Override
	public List<Budget> getBudget(String userid) {
		try {
			Query searchQuery = new Query(Criteria.where("userid").is(userid));
			List<Budget> list=mMongoTemplate.find(searchQuery, Budget.class,DbConstraint.TABLE_BUDGET);
			return list;
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void createBudget(Budget budget) {
		try {
			mMongoTemplate.save(budget, DbConstraint.TABLE_BUDGET);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

	@Override
	public void updateBudget(Budget budget) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("budgetId").is(budget.getBudgetId()).and("userid").is(budget.getUserid()));

			Budget budget2 = mMongoTemplate.findOne(query, Budget.class,DbConstraint.TABLE_BUDGET);
			
			if(budget2==null) {
				throw new RuntimeException("Null event!");
			}
			
			Update update=new Update();
			update.set("categoryId", budget.getCategoryId());
			update.set("walletid", budget.getWalletid());
			update.set("rangeDate", budget.getRangeDate());
			update.set("moneyGoal", budget.getMoneyGoal());
			update.set("status", budget.getStatus());
			
			mMongoTemplate.updateFirst(query, update, Budget.class,DbConstraint.TABLE_BUDGET);
			
			
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
	}

	@Override
	public void removeBudget(String budgetId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("budgetId").is(budgetId));
			
			Budget budget=mMongoTemplate.findOne(query, Budget.class,DbConstraint.TABLE_BUDGET);
			if(budget==null)
				throw new RuntimeException("Fail delete Budget!");
			
			mMongoTemplate.remove(query,Budget.class,DbConstraint.TABLE_BUDGET);
		}catch (MongoException e) {
			throw new DatabaseException("Something wrong! Please try later");
		}
		
	}

}
