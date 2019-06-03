package org.sideproject.simplestore.service;

import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingOperation implements Command{

	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Listing listing;
	private UserOP op;
	
	public UserOP getOp() {
		return op;
	}

	public ListingRepository getListingRepository() {
		return listingRepository;
	}

	public void setListingRepository(ListingRepository listingRepository) {
		this.listingRepository = listingRepository;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public void setOp(UserOP op) {
		this.op = op;
	}

	@Override
	public void execute() {
		if(op.getOpkeyword().equals("CREATE_LISTING")) {
			
			Iterable<User> users = userRepository.findAll();
			
			boolean hasUserExist = false;
			
			for(User user : users) {
				if(user.getUserName().equalsIgnoreCase(this.listing.getUserName())){
					hasUserExist = true;
					break;
				}
			}
			
			if(!hasUserExist) {
				return;
			}
			
			listingRepository.save(this.listing);
		}
	}
}
