package com.examplenative.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplenative.model.User;
import com.examplenative.repository.UserRepository;

@Service
public class UserBusiness {
	
	private UserRepository repository;

	public User findByUser(String user) {
		return repository.findByUserName(user);
	}
	
	@Autowired
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

}
