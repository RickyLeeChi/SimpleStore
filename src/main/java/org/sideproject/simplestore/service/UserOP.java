package org.sideproject.simplestore.service;

public enum UserOP {
	//USER
	CREATEUSER("REGISTER"),
	UPDATEUSER,
	DELETEUSER,
	
	//LISTING
	CREATELIST("CREATE_LISTING"),
	GETLIST("GET_LISTING"),
	DELETELIST("DELETE_LISTING"),
	
	//CATEGORY
	GETCATEGORY("GET_CATEGORY");
	
	private String opKeyword;
	
	private UserOP() {
		this(null);
	}
	
	private	UserOP(String opKeyword) {
		this.opKeyword = opKeyword; 
	}
	
	public String getOpkeyword() {
		return this.opKeyword;
	}
}
