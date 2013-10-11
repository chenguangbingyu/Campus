package com.campus.prime.https;


public class Urls {
	public static final String BASIC_URL = "http://0.campusv.duapp.com/api/circle";
	
	/**
	 * user interface 
	 */
	public static final String USER_REGISTER = BASIC_URL
			+ "/user/";
	public static final String USER_PROFILE = BASIC_URL
			+ "/user/profile/%s/";
	/**
	 * message interface
	 */
	public static final String MESSAGES_PULBIC_TIMELINE = BASIC_URL 
			+ "/messages/public_timeline/";
	
	public static final String MESSAGES_USER_TIMELINE = BASIC_URL 
			+ "/messages/user_timeline/";
	
	public static final String MESSAGES_GROUP_TIMELINE = BASIC_URL 
			+ "/messages/group_timeline/";
	
	public static final String MESSAGE_POST = BASIC_URL
			+ "/message/";
	
	public static final String MESSAGE_DELETE = BASIC_URL 
			+ "/message/";
	
	/**
	 * group interface
	 */
	
	public static final String GROUPS_USER_LIST = BASIC_URL
			+ "/groups/user/";
	public static final String GROUPS_LIST = BASIC_URL
			+ "/groups/";
	public static final String GROUP_USER_ADD = BASIC_URL
			+"/group/user/";
	public static final String GROUP_PROFILE = BASIC_URL
			+"/group/%s/";
	
	/**
	 * comment interface
	 *
	 */
	
	public static final String COMMENTS_MESSAGE_LIST = BASIC_URL
			+ "/comments/message/";
	public static final String COMMENT_CREATE = BASIC_URL
			+ "/comment/";
	public static final String COMMENT_REPLY = BASIC_URL
			+"/reply/";
	public static final String COMMENT_DELETE = BASIC_URL
			+ "/comment/";
	
	
	
	/**
	 * authencation
	 */
	public static final String AUTH = BASIC_URL
			+"api-token-auth/";
	
}
