package com.guocai.common.utils;

import java.util.HashMap;
import java.util.Map;

public class AppContext {

	public static final String FACILITY = "FACILITY";
	private static Map<String, String> params = new HashMap<String, String>();
	
	public static void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public static String getParam(String key) {
		return params.get(key);
	}
	
	public static String getFacility() {
		return getParam(FACILITY);
	}
}
