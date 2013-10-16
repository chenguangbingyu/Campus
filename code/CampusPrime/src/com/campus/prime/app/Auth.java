package com.campus.prime.app;

import com.campus.prime.bean.UserProfile;

public class Auth {
	/**
	 * current user's token
	 */
	public static String token;
	
	public static UserProfile user;
	
	public static String username;
	
	public static boolean isAuth(){
		return token != null ? true : false;
	}
	
	
	
}
