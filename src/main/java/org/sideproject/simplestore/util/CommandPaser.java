package org.sideproject.simplestore.util;

import java.util.ArrayList;
import java.util.List;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.entity.User.UserBuilder;
import org.sideproject.simplestore.exception.CommandParseFailException;
import org.sideproject.simplestore.exception.UnsupportCommandException;

public class CommandPaser {
	
	public static final String SPACE = " ";
	
	public static final String delimiter = SPACE;

	public static List<String> parse(String input) throws CommandParseFailException {
		List<String> ret = new ArrayList<String>();
		
		if(input == null) {
			throw new CommandParseFailException(input);
		}
		
		try {
			String splitStrings[] = input.split(delimiter);
			
			String temp = "";
			boolean newString = true;
			
			for(String s : splitStrings) {
				boolean hasFisrtAndLastQuote = s.matches("^(\\'.+\\')$");
				boolean hasFirstQuote = s.matches("^(\\'.+)");
				boolean hasLastQuote = s.matches("^(.+\\')");
				
				if(hasFisrtAndLastQuote) {
					ret.add(s.substring(1, s.length()-1));
				}			
				else if(hasFirstQuote) {
					newString = false;
					temp = s.substring(1, s.length());
				}			
				else if(hasLastQuote) {
					ret.add(temp.concat(SPACE).concat(s.substring(0, s.length()-1)));
					temp = "";
					newString = true;
				}
				else if(!newString) {
					temp = temp.concat(SPACE).concat(s);
				}
				else {
					ret.add(s);
				}
			}
		} catch (Exception e) {
			throw new CommandParseFailException(e, input);
		}
		
		return ret;
	}
}
