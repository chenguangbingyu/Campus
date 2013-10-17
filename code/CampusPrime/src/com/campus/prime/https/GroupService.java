package com.campus.prime.https;

import org.apache.http.NameValuePair;

import com.campus.prime.app.Auth;
import com.campus.prime.bean.GroupPage;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class GroupService extends CampusService{
	CommonLog log = LogFactory.createLog();
	public GroupService() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public GroupService(CampusClient client){
		super(client);
	}
	
	/**
	 * get user's groups
	 * @param userId
	 * @return
	 */
	public GroupPage getGroupsByUser(int userId){
		GroupPage page = null;
		String url = Urls.GROUPS_USER + userId + '/';
		log.i("getGroupsByUser" + url);
		try{
			page = getClient().setCredential(Auth.token).get(url,GroupPage.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return page;
		}
		return page;
	}
	
}
