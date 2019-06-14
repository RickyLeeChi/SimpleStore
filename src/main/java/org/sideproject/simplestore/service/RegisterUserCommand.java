package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("REGISTER")
public class RegisterUserCommand extends Operation{
	@Autowired
	private UserRepository userRepository;

	public RegisterUserCommand() {
		super();
	}
	
	//
	//command usage register
	
	@Override
	void doAction() {		
		User user = getUserByArgs();
			
		Optional<User> users = userRepository.findByUserNameIgnoreCase(user.getUserName());
		
		if(users.isPresent()) {
			setReturnMeasge("Error - user already existing");
			return;
		}
		
		userRepository.save(user);
		setReturnMeasge("Success");		
	}
	
	private User getUserByArgs() {
		List<String> args = getArgs();

		String userName = args.get(1);
		
		User user = new UserBuilder()
				.setUserName(userName)
				.build();
				
		return user;
	}
}
