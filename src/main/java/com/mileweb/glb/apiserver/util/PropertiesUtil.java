package com.mileweb.glb.apiserver.util;

import java.util.Properties;

/**
 * 
 * @author tongh
 *
 */
public class PropertiesUtil {
	
	private static Properties props = new Properties();

	public static void setProperties(Properties props) {
		PropertiesUtil.props = props;
	}
	
	public static String get(String key) {
		return props.getProperty(key);
	}

	public static String get(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
}
