package org.sideproject.simplestore;


import java.util.List;
import java.util.Scanner;

import org.sideproject.simplestore.service.CommandManager;
import org.sideproject.simplestore.service.CreateListingCommand;
import org.sideproject.simplestore.service.DeleteListingCommand;
import org.sideproject.simplestore.service.GetCategoryCommand;
import org.sideproject.simplestore.service.GetListingCommand;
import org.sideproject.simplestore.service.RegisterUserCommand;
import org.sideproject.simplestore.service.UserOP;
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
	
	private static AnnotationConfigApplicationContext applicationContext;
	
    public static void main( String[] args )
    {
    	applicationContext = new AnnotationConfigApplicationContext();
    	applicationContext.register(Application.class);
    	applicationContext.scan("org.sideproject.simplestore", "org.sideproject.simplestore.config", "org.sideproject.simplestore.service");
    	applicationContext.refresh();   	

    	Application m = applicationContext.getBean(Application.class);
    	m.run();
    }
    
    public void run() {
    	//register all support command
    	register();
    	
    	Scanner command = new Scanner(System.in);
    	
    	System.out.println("Welcome to simple store!!");
    	System.out.println("Please enter your operation : ");
    	
    	System.out.print("# ");
    	
        boolean running = true;
        
        while(running){
        	String input = command.nextLine();
        	List<String> inputArgs = StringPaser.parse(input);
        	commandManager.execute(inputArgs);
        	
        	System.out.print("# ");
        }
        command.close();
        
        System.out.println("Thank you!!");
        
    }

	private void register() {
		commandManager.register(UserOP.CREATEUSER, applicationContext.getBean(RegisterUserCommand.class));
		commandManager.register(UserOP.CREATELIST, applicationContext.getBean(CreateListingCommand.class));
		commandManager.register(UserOP.DELETELIST, applicationContext.getBean(DeleteListingCommand.class));
		commandManager.register(UserOP.GETLIST, applicationContext.getBean(GetListingCommand.class));
		commandManager.register(UserOP.GETCATEGORY, applicationContext.getBean(GetCategoryCommand.class));
	}
}
