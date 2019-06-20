package org.sideproject.simplestore;


import java.util.List;
import java.util.Scanner;

import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.service.CommandManager;
import org.sideproject.simplestore.util.CommandPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Application 
{	
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
//        	String meassge = commandManager.execute(inputArgs);
        	
//        	System.out.println(meassge);
        	
        	commandManager.execute(inputArgs);
        	
        	System.out.print("# ");
        }
        userInput.close();
        
        System.out.println("Thank you!!");
        
    }
    
    private static void init() {
    	applicationContext = new AnnotationConfigApplicationContext();
    	applicationContext.register(Application.class);
    	applicationContext.scan("org.sideproject.simplestore", "org.sideproject.simplestore.config", "org.sideproject.simplestore.service");
    	applicationContext.refresh();  
 	}
}
