package org.sideproject.simplestore.service;

import java.util.Date;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingServiceImpl implements ListingService{
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Listing save(Listing list) {
		return listingRepository.save(list);
	}
	
	public Optional<Listing> findByIdAndUserName(Integer id, String userName){
		return listingRepository.findByIdAndUserName(id, userName);
	}

	@Override
	public Optional<Listing> findById(Integer id) {
		return listingRepository.findById(id);
	}

	@Override
	public Listing createLisitng(String title, String description, double price, Category category, User user) {
		Listing list = new ListingBuilder()
				   .setUserName(user.getUserName())
				   .setTitle(title)
				   .setDescription(description)
				   .setPrice(price)
				   .setCategory(category)
				   .setUser(user)
				   .setCreationTime(new Date())
				   .build();
		
		return this.save(list);
	}

	@Override
	public Listing getListingById(Integer id) {
		Optional<Listing> listing = this.findById(id);
		
		return listing.isPresent()?listing.get():null;
	}

	@Override
	public Listing getListingByIdAndUserName(Integer id, String userName) {
		Optional<Listing> listing = this.findByIdAndUserName(id, userName);
		
		return listing.isPresent()?listing.get():null;
	}

	@Override
	public void deleteListing(Listing list) {
		if(list == null) {
			return;
		}
		
		Category category = list.getCategory();
		
		boolean hasList = category.getListings().remove(list);
		
		if(hasList && category.getListings().isEmpty()) {
			categoryService.delete(category);
		}
		else {
			categoryService.save(category);
		}
	}
}
