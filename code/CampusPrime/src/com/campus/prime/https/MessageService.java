package com.campus.prime.https;

import java.io.IOException;
import java.net.URI;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import static com.campus.prime.constant.AppConstant.TAG;

import com.campus.prime.app.Auth;
import com.campus.prime.bean.MessagePage;
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
	public MessagePage getUser(int userId) 
			throws ClientProtocolException, IOException{
		MessagePage page;
		try {
			String url = Urls.MESSAGES_USER_TIMELINE + userId + '/';
			log.i(url);
			page = getClient().setCredential(Auth.token).get(url,MessagePage.class,(NameValuePair[])null);
			log.i(page.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;
	}
	
	/**
	 * get currentpage's next page
	 * @param url
	 * @return
	 */
	public MessagePage getNext(String url){
		MessagePage page;
		try {
			page = getClient().get(url,MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;
	}
	
	/**
	 * get group's timeline
	 * @param id
	 * @return
	 */
	public MessagePage getGroup(int id){
		MessagePage page;
		try{
			String url = Urls.MESSAGES_GROUP_TIMELINE + id + '/';
			log.i(url);
			page = getClient().setCredential(Auth.token).get(url,MessagePage.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return page;
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
