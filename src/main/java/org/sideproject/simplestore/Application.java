package org.sideproject.simplestore;


import java.util.List;
import java.util.Scanner;

import org.sideproject.simplestore.service.CommandManager;
import org.sideproject.simplestore.util.StringPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Application 
{	
	@Autowired
	CommandManager commandManager;
	
	public static AnnotationConfigApplicationContext applicationContext;
	
    public static void main( String[] args )
    {
    	init(); 	

    	Application m = applicationContext.getBean(Application.class);
    	
    	m.run();
    }

    public void run() {    	
    	Scanner command = new Scanner(System.in);
    	
    	System.out.println("Welcome to simple store!!");
    	System.out.println("Please enter your operation : ");
    	
    	System.out.print("# ");
    	
        boolean running = true;
        
        while(running){
        	String input = command.nextLine();
        	List<String> inputArgs = StringPaser.parse(input);
        	String meassge = commandManager.execute(inputArgs);
        	
        	System.out.println(meassge);
        	
        	System.out.print("# ");
        }
        command.close();
        
        System.out.println("Thank you!!");
        
    }
    
    private static void init() {	   
 	   applicationContext = new AnnotationConfigApplicationContext();
 	   applicationContext.register(Application.class);
 	   applicationContext.scan("org.sideproject.simplestore", "org.sideproject.simplestore.config", "org.sideproject.simplestore.service");
 	   applicationContext.refresh();  
 	}
}
