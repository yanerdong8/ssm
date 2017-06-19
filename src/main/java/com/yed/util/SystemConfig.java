package com.yed.util;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SystemConfig {
	private static Properties props = new Properties();
	static {
		try {
			props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSysParamValue(String key) {
		return props.get(key).toString();
	}

	public static void main(String[] args) {
		System.out.println(SystemConfig.getSysParamValue("KeyAlgorithm"));
	}
}
