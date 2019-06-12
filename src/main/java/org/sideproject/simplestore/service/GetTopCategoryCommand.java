package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Category.CategoryBuilder;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service("GetTopCategoryCommand")
public class GetTopCategoryCommand extends Operation{
	
//	@Autowired
//	private ListingRepository listingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public GetTopCategoryCommand() {
		super();
	}

	@Override
	void doAction() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<Category> category_Page = categoryRepository.findTopCategoryByUserNameQuery(getArgs().get(1), pageable);
		
		List<Category> category_List = category_Page.getContent();
				
		if(category_List.isEmpty()) {
			return;
		}
	}
}
