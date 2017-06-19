package com.yed.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
	private static Properties prop;
	static{
		prop = new Properties();
		InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String name) {
		String value = null;
		try {
			value = (String) prop.get(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}
