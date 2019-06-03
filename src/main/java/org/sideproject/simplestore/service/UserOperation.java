package org.sideproject.simplestore.service;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserOperation implements Command{

	@Autowired
	private UserRepository userRepository;
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private User user;
	private UserOP op;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserOP getOp() {
		return op;
	}

	public void setOp(UserOP op) {
		this.op = op;
	}

	@Override
	public void execute() {
		if(op.getOpkeyword().equals("REGISTER")) {
			Iterable<User> users = userRepository.findAll();
			
			boolean dupname = false;
			
			for(User user : users) {
				if(user.getUserName().equalsIgnoreCase(this.user.getUserName())){
					dupname = true;
					break;
				}
			}
			
			if(dupname) {
				return;
			}
			
			userRepository.save(this.user);
		}
	}
}
