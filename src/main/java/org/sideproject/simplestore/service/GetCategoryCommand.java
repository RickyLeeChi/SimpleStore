package org.sideproject.simplestore.service;

import java.util.List;
import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.sideproject.simplestore.entity.Category.CategoryBuilder;
import org.sideproject.simplestore.entity.Listing;
import org.sideproject.simplestore.entity.Listing.ListingBuilder;
import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.exception.UnsupportCommandException;
import org.sideproject.simplestore.repository.CategoryRepository;
import org.sideproject.simplestore.repository.ListingRepository;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("GET_CATEGORY")
public class GetCategoryCommand extends Command{	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	private String commandName = "GET_CATEGORY";
	private String commandUsage = "GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}";
	
	public GetCategoryCommand() {
		super();
	}
	
	@Override
	public String getCommandName() {
		return this.commandName;
	}
	
	@Override
	public String getCommandUsage() {
		return this.commandUsage;
	}

	@Override
	public void doAction() {
		Optional<User> users = userService.findByUserNameIgnoreCase(getCommands().get(1));
		
		if(!users.isPresent()) {
			setRetObj(new ResponseObject(ResponseObject.Status.GET_CATEGORY_UNKNOWN_USER));
//			setReturnMeasge("Error - unknow user");
			return;
		}
		
		Sort sort = getSort(getCommands().get(3), getCommands().get(4));
		List<Listing> lists = categoryService.findAllListingByUserNameAndCategoryQuery(getCommands().get(1), getCommands().get(2), sort);
		
		if(lists.isEmpty()) {
			setRetObj(new ResponseObject(ResponseObject.Status.GET_CATEGORY_NOT_FOUND));
//			setReturnMeasge("Error - category not found");
			return;
		}
		
		StringBuilder ret = new StringBuilder();
		for(Listing l : lists) {
			if(ret.length() > 0) {
				ret.append("\n");
			}
			ret.append(l);
		}
		
		setRetObj(new ResponseObject(ResponseObject.Status.GET_CATEGORY_SUCCESS, ret.toString()));
//		setReturnMeasge(ret.toString());	
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
	
	@Override
	public void validateCommand() throws UnsupportCommandException {
		List<String> commands = getCommands();
		
		try {
			//Check command	
			if(!commands.get(0).equalsIgnoreCase(commandName)) {
				throw new UnsupportCommandException(commands.get(0));
			}
			
		} catch (Exception e) {
			throw new UnsupportCommandException(e, commands.get(0));
		}
	}
	
	@Override
	public void beforeAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAction() {
		// TODO Auto-generated method stub
		
	}
	
//	public class SortFactory {
//		public Sort getSort(String sort_item, String orderMethod) {
//			String column = GetCategoryCommandOP_SortColumn.valueOf(sort_item).getSortColumn();
//			
//			
//		}
//	}
}
