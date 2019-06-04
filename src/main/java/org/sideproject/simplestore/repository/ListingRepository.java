package org.sideproject.simplestore.repository;

import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Integer>{
	void deleteByIdAndUserName(Integer id, String userName);
}
