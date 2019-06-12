package org.sideproject.simplestore.repository;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByCategory(String category);
	
//	@Query(value = "Select list "
//			+ "From Category c "
//			+ "INNER JOIN c.listings list "
//			+ "where list.userName = :userName "
//			+ "And c.category = :category "
//			+ "ORDER BY list.price ASC")
	
	@Query(value = "Select list "
			+ "From Category c "
			+ "INNER JOIN c.listings list "
			+ "where list.userName = :userName "
			+ "And c.category = :category ")
	List<Listing> findAllListingByUserNameAndCategoryQuery(@Param("userName") String userName, @Param("category") String category, Sort sort);
	
//	@Query(value = "Select c "
//			+ "From Category c "
//			+ "INNER JOIN ( "
//			+ "Select category_id, MAX(category_count) "
//			+ "From ( "
//			+ "		Select list.Category_id as category_id, count(list.Category_id) as category_count "
//			+ "		From c.listings list "
//			+ "		Where list.userName = :userName "
//			+ "		GROUP by list.Category_id "
//			+ "		) "
//			+ ") "
//			+ "Where c.id = category_id ")
	
//	@Query(value = "Select c "
//			+ "From Category c "
//			+ "INNER JOIN c.listings list"
//			+ "Where c.id = ( "
//			+ "		Select userName "
//			+ "		From list "
//			+ "		Where userName = :userName "
//			+ "		GROUP by userName "
//			+ "		ORDER BY COUNT(userName) DESC "
//			+ "				) ")
	
//	@Query(value = "Select c "
//			+ "From Category c "
//			+ "Where c.listings.userName = :userName "
//			+ "GROUP by c.listings.userName "
//			+ "ORDER BY COUNT(c.listings.userName) DESC ")
	
//	@Query(value = "Select catgory, userName, MAX(userName_count) "
//			+ "From ( "
//			+ "Select c.category, c.listings.userName, count(c.listings.userName) as userName_count "
//			+ "From Category c "
//			+ "Where c.listings.userName = :userName "
//			+ "GROUP by c.category, c.listings.userName "
//			+ "Order by c.category, userName_count DESC "
//			+ ") "
//			+ "GROUP by c.category ")
	@Query(value = "Select c "
			+ "From Category c "
			+ "Where ( "
			+ "Select MAX(userName_count) "
			+ "From c.listings l "
			+ "where l.userName = :userName "
			+ "GROUP by c.category, l.userName "
			+ "Order by c.category, userName_count DESC "
			)
	Page<Category> findTopCategoryByUserNameQuery(@Param("userName") String userName, Pageable pageable);
}
