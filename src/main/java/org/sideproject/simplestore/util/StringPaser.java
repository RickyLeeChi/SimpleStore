package org.sideproject.simplestore.util;

import java.util.ArrayList;
import java.util.List;

public class StringPaser {
	
	public static final String SPACE = " ";
	
	public static final String delimiter = SPACE;

	public static List<String> parse(String input){
		List<String> ret = new ArrayList<String>();
		
		if(input == null) {
			return ret;
		}
		
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
		return ret;
	}
}
