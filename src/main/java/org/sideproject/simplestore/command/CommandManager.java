package org.sideproject.simplestore.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sideproject.simplestore.Application;
import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandManager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	public ApplicationContext applicationContext;
	
	//Cache for Commands
	private Map<String, Command> commandCache= new HashMap<String, Command>();
	
	public void loadAllCommands() {
		//
		//Refelection getsubtypeof
		//Need get object every time? 
		//
		if(!commandCache.isEmpty()) {
			return;
		}

		GenericApplicationContext ctx = new GenericApplicationContext(applicationContext);
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(ctx);
		scanner.scan("org.sideproject.simplestore.command");
		ctx.refresh();
        
		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
        	if(ctx.getBean(beanDefinitionName) instanceof Command) {
            	commandCache.put(beanDefinitionName, (Command)ctx.getBean(beanDefinitionName));
        	}
        }
		//
	}
	
	public Command getCommand(String userInputCommand) throws UnsupportCommandException {	
		loadAllCommands();
		
		if(userInputCommand.isEmpty()) {
			throw new UnsupportCommandException("");
		}
		
		Command cmd = null;
		
		try {
			cmd = commandCache.get(userInputCommand);
		} catch (Exception e) {
			throw new UnsupportCommandException(userInputCommand);
		}
		
		if (cmd == null) {
        	logger.info("Command [{}] does NOT support", userInputCommand);
        	throw new UnsupportCommandException(userInputCommand);
        }
		
		return cmd;
	}
}
