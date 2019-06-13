package org.test.simplestore;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sideproject.simplestore.config.DBConfig;
import org.sideproject.simplestore.service.CreateListingCommand;
import org.sideproject.simplestore.service.DeleteListingCommand;
import org.sideproject.simplestore.service.GetCategoryCommand;
import org.sideproject.simplestore.service.GetListingCommand;
import org.sideproject.simplestore.service.GetTopCategoryCommand;
import org.sideproject.simplestore.service.RegisterUserCommand;
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
		GetTopCategoryCommand.class}
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
	
	//User
	String registerCommand = "REGISTER";
	String user1 = "ricky1";
	String user2 = "ricky2";
	
	//Listing
	String clistCommand = "CREATE_LISTING";
	String title = "Phone model 8";
	String description = "Black color, brand new";
	String price = "4000";
	String category = "Electronics";
	
	String title2 = "Phone model 9";
	String description2 = "Black color, brand new";
	String price2 = "1000";
	String category2 = "Electronics";
	
	String title3 = "T-shirt";
	String description3 = "White color";
	String price3 = "2000";
	String category3 = "Sports";
	
	String title4 = "Short";
	String description4 = "White color";
	String price4 = "1500";
	String category4 = "Sports";
	
	String title5 = "Phone model 10";
	String description5 = "Black color, brand new";
	String price5 = "3400";
	String category5 = "Electronics";
	
	String title6 = "Hat";
	String description6 = "White color";
	String price6 = "800";
	String category6 = "Sports";
	
	//Get list
	String glistCommand = "GET_LISTING";
	
	//Delete list
	String dlistCommand = "DELETE_LISTING";
	
	//Get category 
	String gCategoryCommand = "GET_CATEGORY";
	String sort = "sort_price";
	String method = "asc";
	
	//Get TopCategory
	String gTopCategoryCommand = "GET_TOP_CATEGORY";
	
	@Test
	@Order(1)
	public void testRegisterUser1() {
		List<String> arg = new ArrayList<String>();
		arg.add(registerCommand);
		arg.add(user1);
		
		registerUserCommand.setArgs(arg);
		
		String ret = registerUserCommand.execute();
		
		System.out.println(ret);
		assertEquals("Success",ret);
	}
	
	@Test
	@Order(2)
	public void testRegisterUser2() {
		List<String> arg = new ArrayList<String>();
		arg.add(registerCommand);
		arg.add(user2);
		
		registerUserCommand.setArgs(arg);
		
		String ret = registerUserCommand.execute();
		
		System.out.println(ret);
		assertEquals("Success",ret);
	}
	
	@Test
	@Order(3)
	public void testCreateListing1() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user1);
		arg.add(title);
		arg.add(description);
		arg.add(price);
		arg.add(category);
		
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(4)
	public void testCreateListing2() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user1);
		arg.add(title2);
		arg.add(description2);
		arg.add(price2);
		arg.add(category2);
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(5)
	public void testCreateListing3() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user1);
		arg.add(title3);
		arg.add(description3);
		arg.add(price3);
		arg.add(category3);
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(6)
	public void testCreateListing4() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user1);
		arg.add(title4);
		arg.add(description4);
		arg.add(price4);
		arg.add(category4);
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(7)
	public void testCreateListing5() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user2);
		arg.add(title5);
		arg.add(description5);
		arg.add(price5);
		arg.add(category5);
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(8)
	public void testCreateListing6() {
		List<String> arg = new ArrayList<String>();
		arg.add(clistCommand);
		arg.add(user1);
		arg.add(title6);
		arg.add(description6);
		arg.add(price6);
		arg.add(category6);
		
		createListingCommand.setArgs(arg);
		
		String ret = createListingCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(9)
	public void testGetCategory() {
		List<String> arg = new ArrayList<String>();
		arg.add(gCategoryCommand);
		arg.add(user1);
		arg.add(category);
		arg.add(sort);
		arg.add(method);
		
		getCategoryCommand.setArgs(arg);
		
		String ret = getCategoryCommand.execute();
		
		System.out.println(ret);
	}
	
	@Test
	@Order(10)
	public void testGetTopCategory() {
		List<String> arg = new ArrayList<String>();
		arg.add(gTopCategoryCommand);
		arg.add(user1);
		
		getTopCategoryCommand.setArgs(arg);
		
		String ret = getTopCategoryCommand.execute();
		
		System.out.println(ret);
	}
}
