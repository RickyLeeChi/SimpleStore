package org.sideproject.simplestore.exception;

import org.sideproject.simplestore.exception.message.ExceptionMessager;

public abstract class SimpleStoreException extends Exception{

	private static final long serialVersionUID = 8617285529408182146L;

	public SimpleStoreException(Throwable cause, String code, String... args) { 
		super(ExceptionMessager.getInstance().decode(code, args), cause);
	}
	
	protected SimpleStoreException(String code, String... args) { 
		super(ExceptionMessager.getInstance().decode(code, args));
	}
}
