package com.campus.prime.https;

import org.apache.http.protocol.HTTP;

import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class CustomHttpClient {
	private static final String TAG = "CustomHttpClient";
	private static final CommonLog log = LogFactory.createLog();
	
	private static final String CHARSET_UTF8 = HTTP.UTF_8;
}
