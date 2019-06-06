package org.sideproject.simplestore.repository;

import java.util.Optional;

import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ListingRepository extends JpaRepository<Listing, Integer>{
	
	@Transactional
	void deleteByIdAndUserName(Integer id, String userName);
	
	Optional<Listing> findByIdAndUserName(Integer id, String userName);
	
	@Query("Select * from A a  left join B b on a.id=b.id")
	public Optional<Listing> findAllWithDescriptionQuery(@Param("userName") String userName, @Param("category") String category);
}
