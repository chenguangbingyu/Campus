package com.campus.prime.core.service;

import static com.campus.prime.constant.AppConstant.TAG;

import org.apache.http.NameValuePair;

import com.campus.prime.app.Auth;
import com.campus.prime.core.Token;
import com.campus.prime.core.User;
import com.campus.prime.core.UserPage;
import com.campus.prime.core.client.CampusClient;
import com.campus.prime.core.client.Urls;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class UserService extends CampusService{
	CommonLog log = LogFactory.createLog(TAG);
	
	
	public UserService() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public UserService(CampusClient client){
		super(client);
	}
	/**
	 * login
	 * @param username
	 * @param password
	 * @return
	 */
	public Token login(String username,String password){
		Token token = null;
		String url = Urls.USER_LOGIN;
		log.i(url);
		try {
			String jsonObject = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"; 
			token = getClient().post(url,Token.class,jsonObject,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return token;
		}
		return token;
	}
	/**
	 * register a new user
	 * @param username
	 * @param email
	 * @param password
	 * @return
	 */
	public User register(String username,String email,String password){
		User user = null;
		String url = Urls.USER_REGISTER;
		try{
			String jsonObject =  "{\"username\":\"" + username + "\",\"email\":\"" + "\",\"password\":\"" + password + "\"}";
			user = getClient().post(url, User.class, jsonObject,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return user;
		}
		return user;
	}
	
	/**
	 * get current user's profile
	 * @return
	 */
	public User getProfile(){
		User user;
		if(!Auth.isAuth())
			return null;
		String url = Urls.USER_PROFILE_BY_NAME + Auth.username + '/';
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,User.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		log.i(user.toString());
		return user;
	}
	/**
	 * get username's profile
	 * @param username
	 * @return
	 */
	public User getProfile(String username){
		User user;
		if(!Auth.isAuth())
			return null;
		log.i(Auth.token);
		String url = Urls.USER_PROFILE_BY_NAME + username + '/';
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,User.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	/**
	 * get id's profile
	 * @param id
	 * @return
	 */
	public User getProfile(int id){
		User user;
		if(!Auth.isAuth())
			return null;
		String url = Urls.USER_PROFILE_BY_ID + id + '/'	;
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,User.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	
	/**
	 * get group's users
	 * @param groupId
	 * @return
	 */
	public UserPage getUsersByGroup(int groupId){
		UserPage page = null;
		String url = Urls.USERS_GROUP + groupId + '/';
		log.i(url);
		try {
			page = getClient().setCredential(Auth.token).get(url,UserPage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return page;
		}
		return page;
	}
	/**
	 * get next page 
	 * @param url
	 * @return
	 */
	public UserPage getNext(String url){
		UserPage result;
		try {
			result = getClient().get(url,UserPage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
}
