package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DELETE_LISTING")
public class DeleteListingCommand extends Command{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private String commandName = "DELETE_LISTING";
	private String commandUsage = "DELETE_LISTING <username> <listing_id>";
	
	public DeleteListingCommand() {
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
			setRetObj(new ResponseObject(ResponseObject.Status.DELETE_LISTING_UNKNOWN_USER));
//			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Optional<Listing> lists = listingRepository.findById(Integer.parseInt(getCommands().get(2)));
		
		if(!lists.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.DELETE_LISTING_NOT_EXISTING));
//			setReturnMeasge("Error - listing does not exist");
			return;
		}
		
		lists = listingRepository.findByIdAndUserName(Integer.parseInt(getCommands().get(2)), getCommands().get(1));
		
		if(!lists.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.DELETE_LISTING_OWNER_MISMATCH));
//			setReturnMeasge("Error - listing owner mismatch");
			return;
		}
		
		Listing l = lists.get();
		
		Category c = l.getCategory();
		
		c.getListings().remove(l);
		
		if(c.getListings().isEmpty()) {
			categoryRepository.delete(c);
		}		
		else {
			categoryRepository.save(c);
		}
		
		setRetObj(new ResponseObject(ResponseObject.Status.DELETE_LISTING_SUCCESS));
//		setReturnMeasge("Success");
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
