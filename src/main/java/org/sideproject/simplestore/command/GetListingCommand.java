package org.sideproject.simplestore.command;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Category.CategoryBuilder;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.sideproject.simplestore.service.ListingService;
import org.sideproject.simplestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("GET_LISTING")
public class GetListingCommand extends Command{
	
	@Autowired
	private ListingService listingService;
	
	@Autowired
	private UserService userService;
	
	private String commandName = "GET_LISTING";
	private String commandUsage = "GET_LISTING <username> <listing_id>";
	
	public GetListingCommand() {
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
			return new ResponseObject(ResponseObject.Status.GET_LISTING_UNKNOWN_USER);
		}
		
		Optional<Listing> lists = listingService.findByIdAndUserName(Integer.parseInt(getCommands().get(2)), getCommands().get(1));
		
		
		if(!lists.isPresent()) {
			return new ResponseObject(ResponseObject.Status.GET_LISTING_NOT_FOUND);
		}
		
		Listing l = lists.get();
		
		return new ResponseObject(ResponseObject.Status.GET_LISTING_SUCCESS, l.toString());
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
