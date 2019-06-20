package org.sideproject.simplestore.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sideproject.simplestore.Application;
import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandManager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	public ApplicationContext applicationContext;
	
	public void execute(List<String> commands) throws UnsupportCommandException {
		if(commands.isEmpty()) {
			throw new UnsupportCommandException("");
		}
		
		String serviceName = commands.get(0);
		
		if(serviceName.isEmpty()) {
			throw new UnsupportCommandException("");
		}
		
		//
		//Refelection getsubtypeof
		//Need get object every time? 
		Command cmd = (Command) applicationContext.getBean(serviceName);
		
        if (cmd == null) {
        	logger.info("Command [{}] does NOT support", commands.get(0));
        	throw new UnsupportCommandException(commands.get(0));
        }
        
        cmd.setCommands(commands);
        
        cmd.execute();
	}
}
