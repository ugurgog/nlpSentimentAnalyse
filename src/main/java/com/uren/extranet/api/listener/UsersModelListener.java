package com.uren.extranet.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.uren.extranet.api.model.Users;
import com.uren.extranet.api.service.NextSequenceService;

public class UsersModelListener extends AbstractMongoEventListener<Users>{

	@Autowired
	private NextSequenceService nextSequenceService;
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<Users> event) {
	    if (event.getSource().getId() < 1) {
	        event.getSource().setId((int)nextSequenceService.generateSequence(Users.SEQUENCE_NAME));
	    }
	}
}
