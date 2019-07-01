package org.test.simplestore;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sideproject.simplestore.command.CreateListingCommand;
import org.sideproject.simplestore.command.DeleteListingCommand;
import org.sideproject.simplestore.command.GetCategoryCommand;
import org.sideproject.simplestore.command.GetListingCommand;
import org.sideproject.simplestore.command.GetTopCategoryCommand;
import org.sideproject.simplestore.command.RegisterUserCommand;
import org.sideproject.simplestore.command.ResponseObject;
import org.sideproject.simplestore.config.DBConfig;
import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.service.CategoryServiceImpl;
import org.sideproject.simplestore.service.ListingServiceImpl;
import org.sideproject.simplestore.service.UserServiceImpl;
import org.sideproject.simplestore.util.CommandPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBConfig.class, 
		RegisterUserCommand.class,
		CreateListingCommand.class,
		GetListingCommand.class,
		DeleteListingCommand.class,
		GetCategoryCommand.class,
		GetTopCategoryCommand.class,
		UserServiceImpl.class,
		ListingServiceImpl.class,
		CategoryServiceImpl.class}
)
@TestMethodOrder(OrderAnnotation.class)
public class SimpleStoreTest {
	
	@Autowired 
	RegisterUserCommand registerUserCommand;
	@Autowired 
	CreateListingCommand createListingCommand;
	@Autowired 
	GetListingCommand getListingCommand;
	@Autowired 
	DeleteListingCommand deleteListingCommand;
	@Autowired 
	GetCategoryCommand getCategoryCommand;
	@Autowired 
	GetTopCategoryCommand getTopCategoryCommand;
	
	//Enhance test data
	
	//User
	String registerCommand = "REGISTER ricky1";
	String registerCommand2 = "REGISTER ricky2";
	
	//Listing
	String clistCommand = "CREATE_LISTING ricky1 'Phone model 8' 'Black color, brand new' 4000 'Electronics'";
	String clistCommand2 = "CREATE_LISTING ricky1 'Phone model 9' 'White color, brand new' 1000 'Electronics'";
	String clistCommand3 = "CREATE_LISTING ricky1 'Phone model 8' 'Black color, brand new' 4000 'Electronicsss'";
	String clistCommand4 = "CREATE_LISTING ricky1 'Phone model 9' 'White color, brand new' 1000 'Electronicsss'";
	String clistCommand5 = "CREATE_LISTING ricky2 'Phone model 8' 'Black color, brand new' 4000 'Electronics'";
	String clistCommand6 = "CREATE_LISTING ricky1 'Phone model 10' 'Black color, brand new' 4000 'Electronicsss'";
	
	//Get list
	String glistCommand = "GET_LISTING ricky1 3";
	
	//Delete list
	String dlistCommand = "DELETE_LISTING ricky1 3";
	
	//Get category 
	String gCategoryCommand = "GET_CATEGORY ricky1 Electronics sort_price asc";
	
	//Get TopCategory
	String gTopCategoryCommand = "GET_TOP_CATEGORY ricky1";
	
	@Test
	@Order(1)
	public void testRegisterUser1() throws UnsupportCommandException, CommandParseFailException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(registerCommand);

		registerUserCommand.setCommands(arg);
		
		ResponseObject ret = registerUserCommand.execute();
		
		System.out.println(ret.getMessage());
		assertEquals("Success",ret.getMessage());
	}
	
	@Test
	@Order(2)
	public void testRegisterUser2() throws UnsupportCommandException, CommandParseFailException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(registerCommand2);

		registerUserCommand.setCommands(arg);
		
		ResponseObject ret = registerUserCommand.execute();

		System.out.println(ret.getMessage());
		assertEquals("Success",ret.getMessage());
	}
	
	@Test
	@Order(3)
	public void testCreateListing1() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(4)
	public void testCreateListing2() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand2);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(5)
	public void testCreateListing3() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand3);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(6)
	public void testCreateListing4() throws CommandParseFailException, UnsupportCommandException{
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand4);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(7)
	public void testCreateListing5() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand5);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(8)
	public void testCreateListing6() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		arg = CommandPaser.parse(clistCommand6);
		
		
		createListingCommand.setCommands(arg);
		
		ResponseObject ret = createListingCommand.execute();
		
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(9)
	public void testGetCategory() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		
		arg = CommandPaser.parse(gCategoryCommand);
		
		getCategoryCommand.setCommands(arg);
		
		ResponseObject ret = getCategoryCommand.execute();
			
		System.out.println(ret.getMessage());
	}
	
	@Test
	@Order(10)
	public void testGetTopCategory() throws CommandParseFailException, UnsupportCommandException {
		List<String> arg = new ArrayList<String>();
		
		arg = CommandPaser.parse(gTopCategoryCommand);
		
		getTopCategoryCommand.setCommands(arg);
		
		ResponseObject ret = getTopCategoryCommand.execute();	
		
		System.out.println(ret.getMessage());
	}
}
