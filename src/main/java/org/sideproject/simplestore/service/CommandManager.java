package org.sideproject.simplestore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandManager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	Map<String, Operation> commandMap = new HashMap<String, Operation>();
	
//	public void register(String commandName, Operation op) {
//		this.commandMap.put(commandName, op);
//	}
	
	public void register(UserOP userOp, Operation op) {		
		this.commandMap.put(userOp.getOpkeyword(), op);
	}
	
	public void execute(List<String> commands) {
		if(commands.isEmpty()) {
			return;
		}
		
		Operation op = commandMap.get(commands.get(0));
		
        if (op == null) {
        	logger.info("Command [{}] does NOT support", commands.get(0));
        }
        
        op.execute();
	}
}
