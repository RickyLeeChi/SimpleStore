package org.test.simplestore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sideproject.simplestore.config.DBConfig;
import org.sideproject.simplestore.service.RegisterUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class, RegisterUserCommand.class})
public class SimpleStoreTest {
	
	@Autowired 
	RegisterUserCommand registerUserCommand;
	
	@Before
	public void init() {	
	}
	
	@Test
	public void testRegisterUser() {
		List<String> arg = new ArrayList<String>();
		arg.add("REGISTER");
		arg.add("ricky1");
		
		registerUserCommand.setArgs(arg);
		
		String ret = registerUserCommand.execute();
		
		System.out.println(ret);
		assertEquals("Success",ret);
	}
}
