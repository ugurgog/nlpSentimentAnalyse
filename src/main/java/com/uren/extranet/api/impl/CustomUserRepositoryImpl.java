package com.uren.extranet.api.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.uren.extranet.api.enums.ErrorTypeEnum;
import com.uren.extranet.api.model.BaseResponseModel;
import com.uren.extranet.api.model.Users;

@Component
public class CustomUserRepositoryImpl implements CustomUserRepository{

	private Logger logger = LoggerFactory.getLogger(CustomUserRepositoryImpl.class);
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public BaseResponseModel updateUserSalary(int id, long salary) {
		
		BaseResponseModel response = new BaseResponseModel();
		
		Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("salary", salary);
        
        UpdateResult result = mongoTemplate.updateFirst(query, update, Users.class);
        
        if(result == null) {
        	logger.error("::updateUserSalary No documents updated");
        	response.setErrorCode(ErrorTypeEnum.ERROR.name());
        	response.setErrorMessage("No documents updated");
        }else {
        	response.setSuccess(true);
        	logger.info("::updateUserSalary document(s) updated");
        }
        
        return response;
	}

}
