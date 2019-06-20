package org.sideproject.simplestore.exception;

public class CommandParseFailException extends SimpleStoreException{
	
	private static final long serialVersionUID = -8980058104648562531L;
	
	private static final String CODE = "112";

	public CommandParseFailException(String... args) {
		super(CODE, args);
	}
	
	public CommandParseFailException(Throwable cause, String... args) {
		super(cause, CODE, args);
	}

}
