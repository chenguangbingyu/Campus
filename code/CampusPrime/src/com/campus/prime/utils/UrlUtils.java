package com.campus.prime.utils;

import java.util.Map;
import java.util.Map.Entry;

public class UrlUtils {
	
	
	
	public static void addParam(final String name,final String value,
			final StringBuilder uri){
		if(uri.length() > 0)
			uri.append("&");
		uri.append(name);
		uri.append("=");
		uri.append(value);
	}
	
	
	
	public static void addParams(final Map<String,String> params,
			final StringBuilder uri){
		if(params == null || params.isEmpty())
			return;
		for(Entry<String, String> param : params.entrySet())
			addParam(param.getKey(),param.getValue(),uri);
	}
}
