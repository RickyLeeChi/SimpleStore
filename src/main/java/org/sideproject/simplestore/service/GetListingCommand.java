package org.sideproject.simplestore.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("GET_LISTING")
public class GetListingCommand extends Command{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	public void doAction() {
		Optional<User> users = userRepository.findByUserNameIgnoreCase(getCommands().get(1));
		
		if(!users.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.GET_LISTING_UNKNOWN_USER));
//			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Optional<Listing> lists = listingRepository.findByIdAndUserName(Integer.parseInt(getCommands().get(2)), getCommands().get(1));
		
		
		if(!lists.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.GET_LISTING_NOT_FOUND));
//			setReturnMeasge("Error - not found");
			return;
		}
		
		Listing l = lists.get();
		
		setRetObj(new ResponseObject(ResponseObject.Status.GET_LISTING_SUCCESS, l.toString()));
//		setReturnMeasge(l.toString());
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
