package com.campus.prime.https;


public class Urls {
	public static final String BASIC_URL = "http://1.campusv.duapp.com/api/circle";
	

	/**
	 * user register
	 */
	public static final String USER_REGISTER = BASIC_URL
			+ "/user/";
	/**
	 * user's profile by id
	 */
	public static final String USER_PROFILE_BY_ID = BASIC_URL
			+ "/user/profile/";
	/**
	 * user's profile by username
	 */
	public static final String USER_PROFILE_BY_NAME = BASIC_URL
			+ "/user/profile/username/";
	/**
	 * user login
	 */
	public static final String USER_LOGIN = BASIC_URL
			+ "/api-token-auth/";
	/**
	 * list group's users
	 */
	public static final String USERS_GROUP = BASIC_URL
			+ "/users/group/";
	
	
	
	/**
	 * create a message  
	 */
	public static final String MESSAGE_CRAETE = BASIC_URL
			+ "/message/";
	/**
	 * delete a message
	 */
	public static final String MESSAGE_DELETE = BASIC_URL
			+"/message/";
	/**
	 * praise a message
	 */
	public static final String MESSAGE_PRAISE = BASIC_URL
			+ "/message/praise/";
	/**
	 * praised messages
	 */
	public static final String MESSAGES_PRAISED = BASIC_URL
			+ "/messages/praised/";
	/**
	 * collect a message
	 */
	public static final String MESSAGE_COLLECT = BASIC_URL
			+ "/message/collect/";
	/**
	 * collected messages
	 */
	public static final String MESSAGES_COLLECTED = BASIC_URL
			+ "/messages/collected/";
	/**
	 * forward a message
	 */
	public static final String MESSAGE_FORWARD = BASIC_URL
			+ "/message/forward/";
	
	/**
	 * public time_line
	 */
	public static final String MESSAGES_PULBIC_TIMELINE = BASIC_URL 
			+ "/messages/public_timeline/";
	/**
	 * user's time_line
	 */
	public static final String MESSAGES_USER_TIMELINE = BASIC_URL 
			+ "/messages/user_timeline/";
	/**
	 * group's time_line
	 */
	public static final String MESSAGES_GROUP_TIMELINE = BASIC_URL 
			+ "/messages/group_timeline/";
	

	
	
	/**
	 * user's groups
	 */
	public static final String GROUPS_USER = BASIC_URL
			+ "/groups/user/";
	/**
	 * all groups
	 */
	public static final String GROUPS_LIST = BASIC_URL
			+ "/groups/";
	/**
	 * create a group
	 */
	public static final String GROUP_USER_ADD = BASIC_URL
			+"/group/user/";
	/**
	 * group's profile
	 */
	public static final String GROUP_PROFILE = BASIC_URL
			+"/group/";
	/**
	 * user add a group
	 */
	public static final String GROUP_ADD = BASIC_URL
			+"/group/add/";
	/**
	 * user exit a group
	 */
	public static final String GROUP_EXIT = BASIC_URL
			+"/group/exit";
	
	
	
	
	
	/**
	 * message's comments
	 */
	public static final String COMMENTS_MESSAGE_LIST = BASIC_URL
			+ "/comments/message/";
	/**
	 * create a comment
	 */
	public static final String COMMENT_CREATE = BASIC_URL
			+ "/comment/";
	/**
	 * reply a message
	 */
	public static final String COMMENT_REPLY = BASIC_URL
			+"/reply/";
	/**
	 * delete a comment
	 */
	public static final String COMMENT_DELETE = BASIC_URL
			+ "/comment/";
	
	
}
