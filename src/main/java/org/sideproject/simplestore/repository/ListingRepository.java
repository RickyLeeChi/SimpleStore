package org.sideproject.simplestore.repository;

import java.util.Optional;

import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ListingRepository extends JpaRepository<Listing, Integer>{
	
	/**
	 * 
	 * @param id
	 * @param userName
	 * @return
	 */
	Optional<Listing> findByIdAndUserName(Integer id, String userName);
}
