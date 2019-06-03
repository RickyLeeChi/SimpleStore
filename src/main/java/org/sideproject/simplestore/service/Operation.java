package org.sideproject.simplestore.service;

public abstract class Operation implements Command{
	int errorCode;
	String errorMeassge;
	
	abstract void doAction();
	
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
