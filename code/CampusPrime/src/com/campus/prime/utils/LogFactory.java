package com.campus.prime.utils;

public class LogFactory {
	private static String TAG = "CAMPUS";
	private static CommonLog log = null;
	
	public static CommonLog createLog(){
		if(log == null){
			log = new CommonLog();
			
		}
		log.setTag(TAG);
		return log;
	}
	
	public static CommonLog createLog(String tag){
		if(log == null){
			log = new CommonLog();
		}
		if(tag == null || tag.length() < 1){
			log.setTag(TAG);
		}else{
			log.setTag(TAG + " - " + tag);
		}
		return log;
		
	}
}
