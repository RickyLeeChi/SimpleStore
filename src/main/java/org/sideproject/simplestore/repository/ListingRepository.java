package org.sideproject.simplestore.repository;

import java.util.Optional;

import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ListingRepository extends CrudRepository<Listing, Integer>{
	
	@Transactional
	void deleteByIdAndUserName(Integer id, String userName);
	
	Optional<Listing> findByIdAndUserName(Integer id, String userName);
}
