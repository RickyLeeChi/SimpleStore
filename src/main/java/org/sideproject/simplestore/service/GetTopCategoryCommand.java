package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GetTopCategoryCommand")
public class GetTopCategoryCommand extends Operation{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public GetTopCategoryCommand() {
		super();
	}

	@Override
	void doAction() {
		Optional<User> users = userRepository.findByUserNameIgnoreCase(getArgs().get(1));
		
		if(!users.isPresent()) {
			setReturnMeasge("Error - unknow user");
			return;
		}
		
		List<Category> category = categoryRepository.findTopCategoryByUserNameQuery(getArgs().get(1));
				
		if(category.isEmpty()) {
			setReturnMeasge("No listing exist");
			return;
		}
		
		setReturnMeasge(category.get(0).getCategory());
	}
}
