package org.sideproject.simplestore.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sideproject.simplestore.Application;
import org.springframework.stereotype.Service;

@Service
public class CommandManager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	public String execute(List<String> commands) {
		if(commands.isEmpty()) {
			return "Unsuppport opreation";
		}

		String serviceName = getCommandClassName(commands.get(0));
		
		if(serviceName.isEmpty()) {
			return "Unsuppport opreation";
		}
		
		Operation operation = (Operation) Application.applicationContext.getBean(serviceName);
		
        if (operation == null) {
        	logger.info("Command [{}] does NOT support", commands.get(0));
        	return "Command " + commands.get(0) + " does NOT support";
        }
        
        operation.setArgs(commands);
        
        String message = operation.execute();
        
        return message;
	}

	private String getCommandClassName(String command) {
		UserOP[] ops = UserOP.values();
		
		for(UserOP op : ops) {
			if(op.getOpkeyword().equalsIgnoreCase(command)) {
				return op.getClassName();
			}
		}
		
		return "";
	}
}
