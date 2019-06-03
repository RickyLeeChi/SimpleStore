package org.sideproject.simplestore;


import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.entity.Category.CategoryBuilder;
import org.sideproject.simplestore.repository.UserRepository;
import org.sideproject.simplestore.service.CommandManager;
import org.sideproject.simplestore.service.CreateListingCommand;
import org.sideproject.simplestore.service.ListingOperation;
import org.sideproject.simplestore.service.RegisterUserCommand;
import org.sideproject.simplestore.service.UserOP;
import org.sideproject.simplestore.service.UserOperation;
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
	private UserOperation userop;
	
	@Autowired
	private ListingOperation listingop;
	
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
        	
//        	if(input.contains("REGISTER")) {
//        		String userName = input.split(" ")[1];
//        			
//        		User user = new UserBuilder()
//        						.setUserName(userName)
//        						.build();
//        		
//        		userop.setUser(user);
//        		userop.setOp(UserOP.CREATEUSER);
//        		
//        		userop.execute();
//        	}
//        	
//        	if(input.contains("CREATE_LISTING")) {
//        		String listing[] = input.split(" ");
//
//        		Category category = new CategoryBuilder()
//        								.setCategory(listing[5])
//        								.build();
//        		
//        		
//        		
//        		Listing list = new ListingBuilder()
//        						   .setUserName(listing[1])
//        						   .setTitle(listing[2])
//        						   .setDescription(listing[3])
//        						   .setPrice(Double.parseDouble(listing[4]))
//        						   .setCategory(category)
//        						   .build();
//        						   
//        					   
//        		listingop.setListing(list);
//        		listingop.setOp(UserOP.CREATELIST);
//        		
//        		listingop.execute();
//        	}
//        	
//        	if(input.contains("TEST")) {
//        		System.out.println("TEST");
//        	}
//        	
//        	if(input.contains("Exit")) {
//        		running = false;
//        	}
        	
        	System.out.print("# ");
        }
        command.close();
        
        System.out.println("Thank you!!");
        
    }

	private void register() {
		commandManager = new CommandManager();
		
		commandManager.register(UserOP.CREATEUSER, new RegisterUserCommand());
		commandManager.register(UserOP.CREATELIST, new CreateListingCommand());
	}
}
