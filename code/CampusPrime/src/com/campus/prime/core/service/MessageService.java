package com.campus.prime.core.service;

import java.io.IOException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import static com.campus.prime.constant.AppConstant.TAG;

import com.campus.prime.app.Auth;
import com.campus.prime.core.MessagePage;
import com.campus.prime.core.client.CampusClient;
import com.campus.prime.core.client.CampusRequest;
import com.campus.prime.core.client.CampusResponse;
import com.campus.prime.core.client.Urls;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class MessageService extends CampusService {
	
	
	CommonLog log = LogFactory.createLog(TAG);
	
	public MessageService() {
		
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MessageService(CampusClient client){
		super(client);
	}
	
	/**
	 * get public timeline
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public MessagePage getPublic() 
			throws ClientProtocolException, IOException{
		CampusRequest request = createRequest();
		request.setUri(Urls.MESSAGES_PULBIC_TIMELINE)
		.setParams(null)
		.setType(MessagePage.class);
		CampusResponse response = getClient().get(request);
		return (MessagePage)response.getBody();
	}
	
	/**
	 * get user's timeline
	 * @param userId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public MessagePage getUserById(int userId) 
			throws ClientProtocolException, IOException{
		MessagePage result;
		try {
			String url = Urls.MESSAGES_USER_TIMELINE_BY_ID + userId + '/';
			log.i(url);
			result = getClient().setCredential(Auth.token).get(url,MessagePage.class,(NameValuePair[])null);
			log.i(result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * get username's timeline
	 * @param username
	 * @return
	 */
	public MessagePage getUserByName(String username){
		MessagePage result;
		String url = Urls.MESSAGES_USER_TIMELINE_NY_NAME + username + '/';
		log.i(url);
		try {
			result = getClient().setCredential(Auth.token).get(url,MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
		
	}
	
	/**
	 * get current authed user's timeline
	 * @return
	 */
	public MessagePage getUser(){
		return getUserByName(Auth.username);
	}
	
	/**
	 * get currentpage's next page
	 * @param url
	 * @return
	 */
	public MessagePage getNext(String url){
		MessagePage result;
		try {
			result = getClient().get(url,MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * get group's timeline
	 * @param id
	 * @return
	 */
	public MessagePage getGroup(int id){
		MessagePage result;
		try{
			String url = Urls.MESSAGES_GROUP_TIMELINE + id + '/';
			log.i(url);
			result = getClient().setCredential(Auth.token).get(url,MessagePage.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	/**
	 * create a message
	 * @return
	 */
	public MessagePage createMessage(){
		return null;
	}
	
	/**
	 * delete a message
	 */
	public void deleteMessage(){
		
	}
	
	
	
}
