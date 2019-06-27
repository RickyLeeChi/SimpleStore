package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.Listing;

public interface ListingService {
	public Optional<Listing> findById(Integer id);
	public Listing save(Listing list);
	public Optional<Listing> findByIdAndUserName(Integer id, String userName);
}
