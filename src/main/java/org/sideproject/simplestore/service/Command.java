package org.sideproject.simplestore.service;

import java.util.List;

import org.sideproject.simplestore.exception.UnsupportCommandException;

public abstract class Command {
	private List<String> commands;
	private ResponseObject retObj = new ResponseObject(ResponseObject.Status.UNKNOWN);
	
	public abstract void validateCommand() throws UnsupportCommandException;
	public abstract void beforeAction();
	public abstract void afterAction();
	public abstract void doAction();
	
	public abstract String getCommandName();
	public abstract String getCommandUsage();
	
	public List<String> getCommands() {
		return this.commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	
	public ResponseObject getRetObj() {
		return retObj;
	}
	
	public void setRetObj(ResponseObject retObj) {
		this.retObj = retObj;
	}
	
	public void execute() throws UnsupportCommandException{
		validateCommand();
		
		beforeAction();
		
		doAction();
		
		afterAction();
	}
}
