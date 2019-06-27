package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingServiceImpl implements ListingService{
	@Autowired
	private ListingRepository listingRepository;
	
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
}
