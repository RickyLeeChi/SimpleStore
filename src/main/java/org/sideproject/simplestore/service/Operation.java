package org.sideproject.simplestore.service;

import java.util.List;

public abstract class Operation implements Command{
	int errorCode;
	String errorMeassge;
	private List<String> args;
	
	abstract void doAction();
	
	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
	public void setErrorMessge(String errorMeassge) {
		this.errorMeassge = errorMeassge;
	}
	
	public String getErrorMessge() {
		return this.errorMeassge;
	}
	
	@Override
	public void execute() {		
		doAction();
	}
}
