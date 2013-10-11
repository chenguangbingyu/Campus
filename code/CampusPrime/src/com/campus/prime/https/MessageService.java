package com.campus.prime.https;

import java.io.IOException;
import java.net.URI;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import static com.campus.prime.constant.AppConstant.TAG;
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
			String url = Urls.MESSAGES_USER_TIMELINE + userId + '/';
			log.i(url);
			page = client.get(url,MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;

	}
	
	public MessagePage getNext(String url){
		MessagePage page;
		try {
			page = client.get(url,MessagePage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;
	}
	
	
	
	
	
	
}
