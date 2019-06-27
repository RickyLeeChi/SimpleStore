package org.sideproject.simplestore.service;

public class ResponseObject {
	private Status status = Status.UNKNOWN; 
	private String[] args; 
	
	public enum Status {
		//USER
		REGISTER_USER_SUCCESS("Success"),
		REGISTER_USER_ALREADY_EXISTING("Error - user already existing"),
		
		//LIST
		CREATE_LIST_SUCCESS("{0}"),
		CREATE_LIST_UNKNOWN_USER("Error - unknown user"),
		
		GET_LISTING_UNKNOWN_USER("Error - unknown user"),
		GET_LISTING_NOT_FOUND("Error - not found"),
		GET_LISTING_SUCCESS("{0}"),
		
		//DELETE
		DELETE_LISTING_SUCCESS("Success"),
		DELETE_LISTING_UNKNOWN_USER("Error - unknow user"),
		DELETE_LISTING_NOT_EXISTING("Error - listing does not exist"),
		DELETE_LISTING_OWNER_MISMATCH("Error - listing does not exist"),
		
		//CATEGORY
		GET_CATEGORY_SUCCESS("{0}"),
		GET_CATEGORY_UNKNOWN_USER("Error - unknown user"),
		GET_CATEGORY_NOT_FOUND("Error - category not found"),
		
		GET_TOP_CATEGORY_SUCCESS("{0}"),
		GET_TOP_CATEGORY_UNKNOWN_USER("Error - unknow user"),
		GET_TOP_CATEGORY_NO_LIST_EXISTING("Error - no listing exist"),
		
		//EXIT
		CLOSE_APPLICATION("Close application"),
		
		//HELP
		HELP("{0}"),
		
		//
		EXCEPTION_OCCUR("{0}"),
		
		//OTHER 
		UNKNOWN("Error - Unknown");

		private String message;
		
		Status(String message){
			this.message = message;
		}

		public String getMessages() {
			return this.message;
		}
	}

	public ResponseObject(Status status) {
		this.status = status;
	}
	
	public ResponseObject(Status status, String... args) {
		this.status = status;
		this.args = args;
	}
	
	public String getMessage() {
		return decode(this.status.getMessages(), this.args);
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * 
	 * @param message
	 * @param args
	 * @return
	 */
	private String decode(String message, String... args) {
		String retMsg = message;
		
		if(message == null) {
			return "";
		}
		
		if(this.args == null) {
			return retMsg;
		}
				
		
		for(int i = 0 ; i < args.length; i++) {
			retMsg = retMsg.replace("{" + i + "}", args[i]);
		}
		
		return retMsg;
	}
}
