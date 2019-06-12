package org.sideproject.simplestore.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sideproject.simplestore.Application;
import org.springframework.stereotype.Service;

@Service
public class CommandManager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	Map<String, Operation> commandMap = new HashMap<String, Operation>();
	
	public void register(UserOP userOp, Operation op) {		
		this.commandMap.put(userOp.getOpkeyword(), op);
	}
	
	public void execute(List<String> commands) {
		if(commands.isEmpty()) {
			return;
		}

		String className = getCommandClassName(commands.get(0));
		
		//
//		Operation op = null;
//		
//		try {
//			Class<Operation> clazz = (Class<Operation>) Class.forName(className);
//			
//			Method method = clazz.getMethod("getInstance");
//			op = (Operation) method.invoke(clazz);
//			
//		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//
		
//		Operation op = commandMap.get(commands.get(0));
		
//		Operation op = (Operation) Application.applicationContext.getBean(RegisterUserCommand.class);
		Operation op = (Operation) Application.applicationContext.getBean(className);
		
        if (op == null) {
        	logger.info("Command [{}] does NOT support", commands.get(0));
        	return;
        }
        
        op.setArgs(commands);
        
        op.execute();
	}

	private String getCommandClassName(String command) {
		UserOP2[] ops = UserOP2.values();
		
		for(UserOP2 op : ops) {
			if(op.getOpkeyword().equalsIgnoreCase(command)) {
				return op.getClassName();
			}
		}
		
		return "";
	}
}
