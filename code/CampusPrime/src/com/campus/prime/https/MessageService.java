package com.campus.prime.https;

import java.io.IOException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import com.campus.prime.bean.MessagePage;

public class MessageService extends CampusService {
	public MessageService() {
		
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MessageService(CampusClient client){
		super(client);
	}
	
	@SuppressWarnings("unchecked")
	public MessagePage getPublic() 
			throws ClientProtocolException, IOException{
		CampusRequest request = createRequest();
		request.setUri(Urls.MESSAGES_PULBIC_TIMELINE)
		.setParams(null)
		.setType(MessagePage.class);
		CampusResponse response = client.get(request);
		return (MessagePage)response.getBody();
	}
	
	@SuppressWarnings("unchecked")
	public MessagePage getUser(int userId) 
			throws ClientProtocolException, IOException{
		/**
		CampusRequest request = createRequest();
		request.setUri(Urls.MESSAGES_USER_TIMELINE + userId + '/')
		.setParams(null)
		.setType(MessagePage.class);
		CampusResponse response = client.get(request);
		Object body = response.getBody();
		return (MessagePage)body;
		**/

		MessagePage page;
		try {
			page = client.get("http://0.campusv.duapp.com/api/circle/messages/user_timeline/2/",MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;

	}
	
	
	
	
	
	
	
	
}
