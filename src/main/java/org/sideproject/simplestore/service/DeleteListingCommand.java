package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DeleteListingCommand")
public class DeleteListingCommand extends Operation{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public DeleteListingCommand() {
		super();
	}

	@Override
	void doAction() {
		Optional<User> users = userRepository.findByUserNameIgnoreCase(getArgs().get(1));
		
		if(!users.isPresent()) {
			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Optional<Listing> lists = listingRepository.findById(Integer.parseInt(getArgs().get(2)));
		
		if(!lists.isPresent()) {
			setReturnMeasge("Error - listing does not exist");
			return;
		}
		
		lists = listingRepository.findByIdAndUserName(Integer.parseInt(getArgs().get(2)), getArgs().get(1));
		
		if(!lists.isPresent()) {
			setReturnMeasge("Error - listing owner mismatch");
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
		
		setReturnMeasge("Success");
	}
}
