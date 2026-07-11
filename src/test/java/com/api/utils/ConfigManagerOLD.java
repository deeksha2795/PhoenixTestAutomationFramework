package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {

	private ConfigManagerOLD() {}
	private static Properties prop = new Properties();
	
	static {
		File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		//Using file separator makes the code platform independent and File.separator decides which file separator to use on different OS
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	//WAPto create a utility to read from config file
	public static String getProperty(String key)  throws IOException
	{
	
		return prop.getProperty(key);
	}
}
