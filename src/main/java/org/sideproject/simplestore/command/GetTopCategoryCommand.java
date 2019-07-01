package org.sideproject.simplestore.command;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.sideproject.simplestore.service.CategoryService;
import org.sideproject.simplestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("GET_TOP_CATEGORY")
public class GetTopCategoryCommand extends Command{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	private String commandName = "GET_TOP_CATEGORY";
	private String commandUsage = "GET_TOP_CATEGORY <username>";
	
	public GetTopCategoryCommand() {
		super();
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}
	
	@Override
	public String getCommandUsage() {
		return this.commandUsage;
	}
	
	@Override
	public ResponseObject doAction() {
		Optional<User> users = userService.findByUserNameIgnoreCase(getCommands().get(1));
		
		if(!users.isPresent()) {
			return new ResponseObject(ResponseObject.Status.GET_TOP_CATEGORY_UNKNOWN_USER);
		}
		
		List<Category> category = categoryService.findTopCategoryByUserNameQuery(getCommands().get(1));
				
		if(category.isEmpty()) {
			return new ResponseObject(ResponseObject.Status.GET_TOP_CATEGORY_NO_LIST_EXISTING);
		}
		
		return new ResponseObject(ResponseObject.Status.GET_TOP_CATEGORY_SUCCESS, category.get(0).getCategory());
	}
	
	@Override
	public void validateCommand() throws UnsupportCommandException {
		List<String> commands = getCommands();
		
		try {
			//Check command	
			if(!commands.get(0).equalsIgnoreCase(commandName)) {
				throw new UnsupportCommandException(commands.get(0));
			}
			
		} catch (Exception e) {
			throw new UnsupportCommandException(e, commands.get(0));
		}
	}
	
	@Override
	public void beforeAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAction() {
		// TODO Auto-generated method stub
		
	}
}
