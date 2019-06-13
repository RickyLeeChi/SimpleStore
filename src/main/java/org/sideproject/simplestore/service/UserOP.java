package org.sideproject.simplestore.service;

/**
 * Define user commands
 * @author rickylee
 *
 */
public enum UserOP {
	//USER
	CREATEUSER("REGISTER", "RegisterUserCommand"),
	
	//LISTING
	CREATELIST("CREATE_LISTING", "CreateListingCommand"),
	GETLIST("GET_LISTING", "GetListingCommand"),
	DELETELIST("DELETE_LISTING", "DeleteListingCommand"),
	
	//CATEGORY
	GETCATEGORY("GET_CATEGORY", "GetCategoryCommand"),
	GETTOPCATEGORY("GET_TOP_CATEGORY", "GetTopCategoryCommand");
	
	private String opKeyword;
	private String className;
	
	private	UserOP(String opKeyword, String serviceName) {
		this.opKeyword = opKeyword; 
		this.className = serviceName;
	}
	
	public String getOpkeyword() {
		return this.opKeyword;
	}
	
	public String getClassName() {
		return this.className;
	}
}
