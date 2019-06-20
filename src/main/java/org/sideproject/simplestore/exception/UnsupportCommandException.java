package org.sideproject.simplestore.exception;

public class UnsupportCommandException extends SimpleStoreException{

	private static final long serialVersionUID = 66423571824252196L;
	
	private static final String CODE = "111";
		
	public UnsupportCommandException(String... args) {
		super(CODE, args);
	}
	
	public UnsupportCommandException(Throwable throwable, String... args) {
		super(throwable, CODE, args);
	}

}
