package com.campus.prime.https;

import static com.campus.prime.constant.AppConstant.TAG;

import org.apache.http.NameValuePair;

import com.campus.prime.app.Auth;
import com.campus.prime.bean.Token;
import com.campus.prime.bean.UserProfile;
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
	 * get current user's profile
	 * @return
	 */
	public UserProfile getProfile(){
		UserProfile user;
		if(!Auth.isAuth())
			return null;
		String url = Urls.USER_PROFILE_BY_NAME + Auth.username + '/';
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,UserProfile.class,(NameValuePair[])null);
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
	public UserProfile getProfile(String username){
		UserProfile user;
		if(!Auth.isAuth())
			return null;
		log.i(Auth.token);
		String url = Urls.USER_PROFILE_BY_NAME + username + '/';
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,UserProfile.class,(NameValuePair[])null);
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
	public UserProfile getProfile(int id){
		UserProfile user;
		if(!Auth.isAuth())
			return null;
		String url = Urls.USER_PROFILE_BY_ID + id + '/'	;
		log.i(url);
		try{
			user = getClient().setCredential(Auth.token).get(url,UserProfile.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	
	
}
