package com.vn.hcmute.team.cortana.mymoney.data.service.user;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.vn.hcmute.team.cortana.mymoney.bean.User;
import com.vn.hcmute.team.cortana.mymoney.bean.UserCredential;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;

@Service
public class UserServiceImp implements UserService {
	public  static final Log LOG=LogFactory.getLog(UserServiceImp.class);
	
	@Autowired
	private MongoTemplate mMongoTemplate;

	public UserServiceImp() {

	}
	
	@Override
	public void register(User user) {
		LOG.info("Check user exists...");
		if(isUserExists(user.getUsername())){
			throw new UserException("User exists");
		}
		LOG.info("Insert document...");
		mMongoTemplate.save(user, DbConstraint.TABLE_USER);
		LOG.info("Insert successful...");
	}

	@Override
	public long getNumberOfActiveUser() {
		LOG.info("Get Number of AcviteUser...");
		return mMongoTemplate.count(query(where("active").is(true)), User.class,DbConstraint.TABLE_USER);
	}

	@Override
	public boolean isUserExists(String username) {
		User user=mMongoTemplate.findOne(query(where("username").is(username)),
				User.class,
				DbConstraint.TABLE_USER);
		
		return user!=null?true:false;
	}

	@Override
	public User login(UserCredential userCredential) {
		String hashPassword=SecurityUtil.generateMD5(userCredential.getPassword());
		LOG.info("Get info user...");
		User user=mMongoTemplate
				.findOne(query(where("username").is(userCredential.getUsername()).and("password").is(hashPassword)),
				User.class,
				DbConstraint.TABLE_USER);
		
		if(user!=null){
			
			user.setPassword(null);
			user.setApikey(null);
			
			return user;
		}else{
			throw new UserException("Wrong username or password");
		}
	}

}
