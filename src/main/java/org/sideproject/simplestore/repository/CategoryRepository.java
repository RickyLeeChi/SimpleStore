package org.sideproject.simplestore.repository;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	/**
	 * 
	 * @param category
	 * @return
	 */
	Optional<Category> findByCategory(String category);
	
	/**
	 * 
	 * @param userName
	 * @param category
	 * @param sort
	 * @return
	 */
	@Query(value = "Select list "
			+ "From Category c "
			+ "INNER JOIN c.listings list "
			+ "where list.userName = :userName "
			+ "And c.category = :category ")
	List<Listing> findAllListingByUserNameAndCategoryQuery(@Param("userName") String userName, @Param("category") String category, Sort sort);
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Query(value = "Select L.id, L.category "
			+ "From (Select c.id as id, c.category as category, list.userName as name, COUNT(list.userName) AS user_Count "
			+ "From Listing list "
			+ "INNER JOIN Category c ON c.id = list.Category_id "
			+ "Where list.userName = :userName "
			+ "GROUP BY c.category, list.userName "
			+ "ORDER BY user_Count DESC) AS L "
			, nativeQuery = true)
	List<Category> findTopCategoryByUserNameQuery(@Param("userName") String userName);
}
