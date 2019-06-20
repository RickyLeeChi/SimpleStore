package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("REGISTER")
public class RegisterUserCommand extends Command{
	@Autowired
	private UserRepository userRepository;
	
	private String commandName = "REGISTER";
	private String commandUsage = "REGISTER <username>";

	public RegisterUserCommand() {
		super();
	}
	
	@Override
	public String getCommandName() {
		return this.commandName;
	}
	
	@Override
	public String getCommandUsage() {
		return this.commandUsage;
	}
	
	@Override
	public void doAction() {		
		User user = getUserByCommands();
			
		Optional<User> users = userRepository.findByUserNameIgnoreCase(user.getUserName());
		
		if(users.isPresent()) {
//			setReturnMeasge("Error - user already existing");
			return;
		}
		
		userRepository.save(user);
//		setReturnMeasge("Success");		
	}
	
	private User getUserByCommands() {
		List<String> args = getCommands();

		String userName = args.get(1);
		
		User user = new UserBuilder()
				.setUserName(userName)
				.build();
				
		return user;
	}

	@Override
	public void validateCommand() throws UnsupportCommandException {
		List<String> commands = getCommands();
		
		try {
			//Check command	
			if(!commands.get(0).equalsIgnoreCase(commandName)) {
				throw new UnsupportCommandException(commands.get(0));
			}
			
		} catch (Exception e) {
			throw new UnsupportCommandException(e, commands.get(0));
		}
	}

	@Override
	public void beforeAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAction() {
		// TODO Auto-generated method stub
		
	}
}
