package org.sideproject.simplestore.service;

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

@Service("GET_LISTING")
public class GetListingCommand extends Operation{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public GetListingCommand() {
		super();
	}

	@Override
	void doAction() {
		Optional<User> users = userRepository.findByUserNameIgnoreCase(getArgs().get(1));
		
		if(!users.isPresent()) {
			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Optional<Listing> lists = listingRepository.findByIdAndUserName(Integer.parseInt(getArgs().get(2)), getArgs().get(1));
		
		
		if(!lists.isPresent()) {
			setReturnMeasge("Error - not found");
			return;
		}
		
		Listing l = lists.get();
		
		setReturnMeasge(l.toString());
		
		
		
//		
//		lists.get().getCategory().getListings().remove(l);
		
//		Optional<Listing> lists = listingRepository.findByIdAndUserName(Integer.parseInt(getArgs().get(2)), getArgs().get(1));
//		Listing l = lists.get();
//		
//		listingRepository.delete(l);
		
//		listingRepository.deleteByIdAndUserName(Integer.parseInt(getArgs().get(2)), getArgs().get(1));
	}
}
