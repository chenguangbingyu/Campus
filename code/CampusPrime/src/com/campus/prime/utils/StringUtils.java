package com.campus.prime.utils;

public class StringUtils {
	
	/**
	 * ÅĞ¶Ï¸ø¶¨×Ö·ûÊÇ·ñÊÇ¿Õ°××Ö·û
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input){
		if(input == "" || "".equals(input)){
			return true;
		}
		for(int i = 0; i< input.length();i++){
			char c = input.charAt(i);
			if(c != ' ' && c != '\t' && c != '\r' && c != '\n'){
				return false;
			}
		}
		return true;
	}
}
