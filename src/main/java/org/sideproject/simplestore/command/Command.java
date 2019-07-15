package org.sideproject.simplestore.command;

import java.util.List;

import org.sideproject.simplestore.exception.UnsupportCommandException;

public abstract class Command {
	private List<String> userInputs;
	
	protected abstract void validateCommand() throws UnsupportCommandException;
	protected abstract void beforeAction();
	protected abstract void afterAction();
	protected abstract ResponseObject doAction();
	
	public abstract String getCommandName();
	public abstract String getCommandUsage();
	
	public List<String> getCommands() {
		return this.userInputs;
	}

	public void setCommands(List<String> userInputs) {
		this.userInputs = userInputs;
	}
	
	public ResponseObject execute() throws UnsupportCommandException{
		validateCommand();
		
		beforeAction();
		
		ResponseObject result = doAction();
		
		afterAction();
		
		return result;
	}
}
