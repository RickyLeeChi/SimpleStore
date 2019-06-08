package org.sideproject.simplestore.repository;

import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByCategory(String category);
	
	@Query(value = "Select list "
			+ "From Category c "
			+ "INNER JOIN c.listings list "
			+ "where list.userName = :userName "
			+ "And c.category = :category ")
	Optional<Listing> findAllListingByUserNameAndCategoryQuery(@Param("userName") String userName, @Param("category") String category);
}
