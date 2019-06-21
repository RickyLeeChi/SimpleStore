package org.sideproject.simplestore.exception.message;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionMessager {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	public static final String DEFAULT_ERROR_CODE = "000";
	public static final String EXCEPTION_MESSAGE_FILE = "exceptions.messages";
	
	private Map<String, String> messageMap;
	private static ExceptionMessager singleton;
	
	public ExceptionMessager() {
		this.initMessages();
	}
	
	public static ExceptionMessager getInstance() {
		if(singleton == null) {
			return new ExceptionMessager();
		}
		
		return singleton;
	}
	
	private void initMessages() {
		this.messageMap = new HashMap<String, String>();
		
		String messageFile = EXCEPTION_MESSAGE_FILE;		
		File propertiesFile = new File(messageFile);
		
		Parameters paramater = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new 
				FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
				.configure(paramater.fileBased().setFile(propertiesFile));
		
		try {
			Configuration config = builder.getConfiguration();
			Iterator<String> keys = config.getKeys();
			
			while(keys.hasNext()) {
				String code = keys.next();
				String message = config.getString(code, "");
				this.messageMap.put(code, message);
			}
		} catch (Exception e) {
			logger.error("Unable to load exception messages from file [{}]", messageFile, e);
		}
	}
	
	/**
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public String decode(String code, String... args) {
		String message = this.messageMap.containsKey(code) ? this.messageMap.get(code)
				: this.messageMap.get(DEFAULT_ERROR_CODE);
		
		for (int i = 0; i < args.length; i++) {
			message = message.replace("{" + i + "}", args[i]);
		}
		
		return message;
	}
}
