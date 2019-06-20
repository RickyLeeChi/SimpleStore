package org.sideproject.simplestore.service;

public class ResponseObject {
	String message = "";
	enum Status {
		//USER
		REGISTER_USER_SUCCESS("Success"),
		REGISTER_USER_ALREADY_EXISTING("Error - user already existing"),
		
		//LIST
		CREATE_LIST_SUCCESS("[{0}]"),
		CREATE_LIST_UNKNOWN_USER("Error - unknown user"),	
	
		//OTHER 
		UNKNOWN("Unknown");

		private String messages;
		
		Status(String messages){
			this.messages = messages;
		}

		public String getMessages() {
			return this.messages;
		}
	}

	public ResponseObject(Status status, String... message) {
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public String decode(Status status, String... args) {
		if(status == null) {
			return "";
		}
		
		String message = this.messageMap.containsKey(code) ? this.messageMap.get(code)
				: this.messageMap.get(DEFAULT_ERROR_CODE);
		
		for (int i = 0; i < args.length; i++) {
			message = message.replace("{" + i + "}", args[i]);
		}
		
		return message;
	}
}
