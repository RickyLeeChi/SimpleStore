package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Optional<Category> findByCategory(String category) {
		return categoryRepository.findByCategory(category);
	}
	
	@Override
	public List<Listing> findAllListingByUserNameAndCategoryQuery(String userName, String category, Sort sort) {
		return categoryRepository.findAllListingByUserNameAndCategoryQuery(userName, category, sort);
	}

	@Override
	public List<Category> findTopCategoryByUserNameQuery(String userName) {
		return categoryRepository.findTopCategoryByUserNameQuery(userName);
	}
	
	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
}
