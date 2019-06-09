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

@Service
public class DeleteListingCommand extends Operation{
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public DeleteListingCommand() {
		super();
	}

	@Override
	void doAction() {
		Optional<Listing> lists = listingRepository.findById(Integer.parseInt(getArgs().get(2)));
		
		if(!lists.isPresent()) {
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
	}
}
