package com.campus.prime.app;


public class Auth {
	/**
	 * current user's token
	 */
	public static String token;
	
	public static String username;
	
	public static boolean isAuth(){
		return token != null ? true : false;
	}
}
