package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.springframework.data.domain.Sort;

public interface CategoryService {
	public Category save(Category category);
	
	public Optional<Category> findByCategory(String category);
	public List<Listing> findAllListingByUserNameAndCategoryQuery(String userName, String category, Sort sort);
	public List<Category> findTopCategoryByUserNameQuery(String userName);
	public void delete(Category category);
}
