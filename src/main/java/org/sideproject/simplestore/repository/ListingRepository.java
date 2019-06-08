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
	
//	@Query("Select a "
//			+ "From Listing a")
//	@Query("Select a "
//			+ "From Listing a "
//			+ "Left Join ( "
//			+ "Select category, id "
//			+ "From Category where category = :category "
//			+ ") b on a.Category_id = b.id "
//			+ "Where a.userName = :userName ")
	
//	@Query(value = "Select a "
//			+ "From Listing a "
//			+ "where a.Category_id = "
//			+ "( "
//			+ "Select id from Category "
//			+ "Where category = :category "
//			+ ") "
//			+ "And a.userName = :userName")
//	Optional<Listing> findAllListingByUserNameAndCategoryQuery(@Param("userName") String userName, @Param("category") String category);
}
