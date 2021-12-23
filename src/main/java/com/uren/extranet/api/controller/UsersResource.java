package com.uren.extranet.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.uren.extranet.api.model.Users;
import com.uren.extranet.api.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UsersResource {

	private Logger logger = LoggerFactory.getLogger(LanguageController.class);

	private Gson gson;
    private UserRepository userRepository;

    @Autowired
    public UsersResource(UserRepository userRepository, Gson gson) {
        this.userRepository = userRepository;
        this.gson = gson;
    }

    @RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> getAll() {
    	return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<?> addUser(@RequestParam("id") int id, 
    								       @RequestParam("name") String name,
                                           @RequestParam("teamName") String teamName,
                                           @RequestParam("salary") String salary) {
    	
    	logger.info("::addUser id:{}, name:{}, teamName:{}, salary:{}", 
    			id, name, teamName, salary);
    	
    	Users users = new Users(id, name, teamName, Long.parseLong(salary));
    	
    	Users addedUser = userRepository.save(users);
    	
    	return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getUser", method=RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<?> getUser(@RequestParam("id") int id) {
    	
    	Optional<Users> addedUser = userRepository.findById(id);
    	
    	if(addedUser.isPresent()) {
    		Users user = addedUser.get();
    		return new ResponseEntity<>(user, HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  
}