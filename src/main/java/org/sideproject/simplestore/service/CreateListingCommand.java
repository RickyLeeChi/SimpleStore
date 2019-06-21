package org.sideproject.simplestore.service;

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
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("CREATE_LISTING")
public class CreateListingCommand extends Command{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
	public void doAction() {
		User user = getUserByArgs();
		
		Optional<User> users = userRepository.findByUserNameIgnoreCase(user.getUserName());
		
		if(!users.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.CREATE_LIST_UNKNOWN_USER));
//			setReturnMeasge("Error - unknown user");
			return;
		}
		
		user = users.get();
	
		Category category = getCategoryByArgs();
		
		Optional<Category> categoryInDB=  categoryRepository.findByCategory(category.getCategory());
		
		if(categoryInDB.isPresent()) {
			category = categoryInDB.get();
		}
		else {
			categoryRepository.save(category);
		}
		
		Listing list = getListingByArgs(user, category);
		
		Listing returnList = listingRepository.save(list);
		
		setRetObj(new ResponseObject(ResponseObject.Status.CREATE_LIST_SUCCESS, String.valueOf(returnList.getId())));
//		setReturnMeasge(String.valueOf(returnList.getId()));
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
	
	private User getUserByArgs() {
		List<String> args = getCommands();

		String userName = args.get(1);
		
		User user = new UserBuilder()
				.setUserName(userName)
				.build();
				
		return user;
	}
	
	private Category getCategoryByArgs() {
		List<String> args = getCommands();
		
		Category category = new CategoryBuilder()
							.setCategory(args.get(5))
							.build();
				
		return category;
	}
	
	private Listing getListingByArgs(User user, Category category) {
		List<String> args = getCommands();
		
		Listing list = new ListingBuilder()
				   .setUserName(args.get(1))
				   .setTitle(args.get(2))
				   .setDescription(args.get(3))
				   .setPrice(Double.parseDouble(args.get(4)))
				   .setCategory(category)
				   .setUser(user)
				   .setCreationTime(new Date())
				   .build();
				
		return list;
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
