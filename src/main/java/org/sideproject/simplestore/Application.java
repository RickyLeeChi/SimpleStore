package org.sideproject.simplestore;


import java.util.List;
import java.util.Scanner;

import org.sideproject.simplestore.command.Command;
import org.sideproject.simplestore.command.CommandManager;
import org.sideproject.simplestore.command.ResponseObject;
import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.util.CommandPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Application 
{	
	/*
	 * Service business logic
	 * Build.sh run.sh
	 * 
	 * Generate Command manager get command usage
	 * 
	 */
	
	@Autowired
	CommandManager commandManager;
	
	@Autowired
	public static AnnotationConfigApplicationContext applicationContext;
	
    public static void main( String[] args ) throws UnsupportCommandException, CommandParseFailException {
    	init(); 	

    	Application m = applicationContext.getBean(Application.class);
    	
    	m.run();
    }

    public void run() throws UnsupportCommandException, CommandParseFailException {    	
    	Scanner userInput = new Scanner(System.in);
    	
    	System.out.println("Welcome to simple store!!");
    	System.out.println("Please enter your operation : ");
    	
    	System.out.print("# ");
    	
        boolean running = true;
        
        while(running){
        	String input = userInput.nextLine();
        	List<String> inputArgs = CommandPaser.parse(input);
        	
        	ResponseObject response = handleCommand(inputArgs);
        	
        	System.out.println(response.getMessage());
        	
        	if(response.getStatus() == ResponseObject.Status.CLOSE_APPLICATION) {
                userInput.close();
                System.out.println("Bye ~ Bye!!");
        		System.exit(0);
        	}
        	
        	System.out.print("# ");
        }
    }
    
    public ResponseObject handleCommand(List<String> inputArgs) {
    	ResponseObject retobj;
    	
    	try {
    		Command command = commandManager.getCommand(inputArgs.get(0));
    		command.setCommands(inputArgs);
    		
    		retobj = command.execute();
		} catch (UnsupportCommandException e) {
			retobj = new ResponseObject(ResponseObject.Status.EXCEPTION_OCCUR, e.getMessage());
		}
    	
    	return retobj;
    }
    
    private static void init() {
    	applicationContext = new AnnotationConfigApplicationContext();
    	applicationContext.register(Application.class);
    	applicationContext.scan("org.sideproject.simplestore", "org.sideproject.simplestore.config", "org.sideproject.simplestore.service");
    	applicationContext.refresh();  
 	}
}
