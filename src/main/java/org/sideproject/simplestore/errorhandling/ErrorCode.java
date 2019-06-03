package org.sideproject.simplestore.errorhandling;

public enum ErrorCode {
	//USER
	SUCCESS(0),
	error1(2),
	error2(3);
	
	private int code;
	
	private ErrorCode(int code) {
		this.code = code;
	}
	
	public int getErrorCode() {
		return this.code;
	}
}
