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
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CREATE_LISTING")
public class CreateListingCommand extends Operation{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CreateListingCommand() {
		super();
	}

	@Override
	void doAction() {
		User user = getUserByArgs();
		
		Optional<User> users = userRepository.findByUserNameIgnoreCase(user.getUserName());
		
		if(!users.isPresent()) {
			setReturnMeasge("Error - unknown user");
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
		
		setReturnMeasge(String.valueOf(returnList.getId()));
	}
	
	private User getUserByArgs() {
		List<String> args = getArgs();

		String userName = args.get(1);
		
		User user = new UserBuilder()
				.setUserName(userName)
				.build();
				
		return user;
	}
	
	private Category getCategoryByArgs() {
		List<String> args = getArgs();
		
		Category category = new CategoryBuilder()
							.setCategory(args.get(5))
							.build();
				
		return category;
	}
	
	private Listing getListingByArgs(User user, Category category) {
		List<String> args = getArgs();
		
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
}
