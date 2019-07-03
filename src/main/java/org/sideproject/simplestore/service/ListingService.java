package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.User;

public interface ListingService {
	public Optional<Listing> findById(Integer id);
	public Listing save(Listing list);
	public Optional<Listing> findByIdAndUserName(Integer id, String userName);
	public Listing createLisitng(String title, String description, double price, Category category, User user);
	public Listing getListingById(Integer id);
	public Listing getListingByIdAndUserName(Integer id, String userName);
	public void deleteListing(Listing list);
}
