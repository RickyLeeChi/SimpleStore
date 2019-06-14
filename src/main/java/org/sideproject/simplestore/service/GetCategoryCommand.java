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

@Service("GET_CATEGORY")
public class GetCategoryCommand extends Operation{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public GetCategoryCommand() {
		super();
	}

	@Override
	void doAction() {
		Optional<User> users = userRepository.findByUserNameIgnoreCase(getArgs().get(1));
		
		if(!users.isPresent()) {
			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Sort sort = getSort(getArgs().get(3), getArgs().get(4));
		List<Listing> lists = categoryRepository.findAllListingByUserNameAndCategoryQuery(getArgs().get(1), getArgs().get(2), sort);
		
		if(lists.isEmpty()) {
			setReturnMeasge("Error - category not found");
			return;
		}
		
		StringBuilder ret = new StringBuilder();
		for(Listing l : lists) {
			if(ret.length() > 0) {
				ret.append("\n");
			}
			ret.append(l);
		}
		
		setReturnMeasge(ret.toString());	
	}
	
	public Sort getSort(String sortColumn, String orderMethod) {
		String column = GetCategoryCommandOP_SortColumn.valueOf(sortColumn).getSortColumn();
		
		Sort sort = Sort.by(column);
		
		if(orderMethod.equalsIgnoreCase(GetCategoryCommandOP_OrderMethod.asc.getOrderMethod())) {
			sort = sort.ascending();
		}
		else if(orderMethod.equalsIgnoreCase(GetCategoryCommandOP_OrderMethod.dsc.getOrderMethod())) {
			sort = sort.descending();
		}
		return sort;
	}
	
	private enum GetCategoryCommandOP_SortColumn {
		sort_time("list.creationTime"),
		sort_price("list.price");
	
		private String sortColumn;
		
		private	GetCategoryCommandOP_SortColumn(String sortColumn) {
			this.sortColumn = sortColumn;
		}
		
		public String getSortColumn() {
			return this.sortColumn;
		}
	}
	
	private enum GetCategoryCommandOP_OrderMethod {
		asc("ASC"),
		dsc("DSC");
	
		private String orderMethod;
		
		private	GetCategoryCommandOP_OrderMethod(String orderMethod) {
			this.orderMethod = orderMethod;
		}
		
		public String getOrderMethod() {
			return this.orderMethod;
		}
	}
	
//	public class SortFactory {
//		public Sort getSort(String sort_item, String orderMethod) {
//			String column = GetCategoryCommandOP_SortColumn.valueOf(sort_item).getSortColumn();
//			
//			
//		}
//	}
}
