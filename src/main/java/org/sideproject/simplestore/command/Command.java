package org.sideproject.simplestore.command;

import java.util.List;

import org.sideproject.simplestore.exception.UnsupportCommandException;

public abstract class Command {
	private List<String> commands;
	
	public abstract void validateCommand() throws UnsupportCommandException;
	public abstract void beforeAction();
	public abstract void afterAction();
	public abstract ResponseObject doAction();
	
	public abstract String getCommandName();
	public abstract String getCommandUsage();
	
	public List<String> getCommands() {
		return this.commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	
	public ResponseObject execute() throws UnsupportCommandException{
		validateCommand();
		
		beforeAction();
		
		ResponseObject result = doAction();
		
		afterAction();
		
		return result;
	}
}
