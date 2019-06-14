package org.sideproject.simplestore.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sideproject.simplestore.Application;
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
	
	public String execute(List<String> commands) {
		if(commands.isEmpty()) {
			return "Unsuppport opreation";
		}
		
		String serviceName = commands.get(0);
		
		if(serviceName.isEmpty()) {
			return "Unsuppport opreation";
		}
		
		//
		//Refelection getsubtypeof
		//Need get object every time? 
		Operation operation = (Operation) applicationContext.getBean(serviceName);
		
        if (operation == null) {
        	logger.info("Command [{}] does NOT support", commands.get(0));
        	return "Command " + commands.get(0) + " does NOT support";
        }
        
        operation.setArgs(commands);
        
        String message = operation.execute();
        
        return message;
	}
}
