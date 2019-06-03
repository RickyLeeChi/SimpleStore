package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterUserCommand extends Operation{
	@Autowired
	private UserRepository userRepository;

	private User user;
	
	public RegisterUserCommand() {
		super();
	}
	
	public RegisterUserCommand(User user) {
		super();
		this.user = user;
	}

	@Override
	void doAction() {
		Optional<User> users = userRepository.findByUserName(user.getUserName());
		
		if(users.isPresent()) {
			return;
		}
		
		userRepository.save(this.user);
	}
	
}
