package org.sideproject.simplestore.command;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Category.CategoryBuilder;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.service.CategoryService;
import org.sideproject.simplestore.service.ListingService;
import org.sideproject.simplestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CREATE_LISTING")
public class CreateListingCommand extends Command{
	
	@Autowired
	private ListingService listingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	private String commandName = "CREATE_LISTING";
	private String commandUsage = "CREATE_LISTING <username> <title> <description> <price> <category>";
	
	public CreateListingCommand() {
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
		User user = userService.getUserByName(getCommands().get(1));
		
		if(user == null) {
			return new ResponseObject(ResponseObject.Status.CREATE_LIST_UNKNOWN_USER);
		}
		
		Category category = categoryService.getCategoryByName(getCommands().get(5));
		
		if(category == null) {
			category = categoryService.createCategory(getCommands().get(5));
		}
		
		Listing list = listingService.createListing(getCommands().get(2),
										getCommands().get(3),
										Double.parseDouble(getCommands().get(4)),
										category,
										user);
		
		return new ResponseObject(ResponseObject.Status.CREATE_LIST_SUCCESS, String.valueOf(list.getId()));
	}
	
	//TODO : Command validation 
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
