package com.campus.prime.core.service;

import org.apache.http.NameValuePair;

import com.campus.prime.app.Auth;
import com.campus.prime.core.Group;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.client.CampusClient;
import com.campus.prime.core.client.Urls;
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
	 * get user's groups by user id
	 * @param userId
	 * @return
	 */
	public GroupPage getGroupsByUserid(final int userId){
		GroupPage page = null;
		String url = Urls.GROUPS_USER_BY_ID + userId + '/';
		log.i("getGroupsByUserid " + url);
		try {
			page = getClient().setCredential(Auth.token).get(url,GroupPage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;
	}
	/**
	 * get user's groups by username
	 * @param username
	 * @return
	 */
	public GroupPage getGroupsByUsername(final String username){
		GroupPage page = null;
		String url = Urls.GROUPS_USER_BY_NAME + username + '/';
		log.i("getGroupsByUsername " + url);
		try{
			page = getClient().setCredential(Auth.token).get(url,GroupPage.class,(NameValuePair[])null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return page;
	}
	/**
	 * get current user's groups
	 * @return
	 */
	public GroupPage getGroups(){
		return getGroupsByUsername(Auth.username);
	}
	
	/**
	 * get next page
	 * @param url
	 * @return
	 */
	public GroupPage getNext(final String url){
		GroupPage result;
		try {
			result = getClient().get(url,GroupPage.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
		
	}
	
	
	public Group getDetail(final int groupId) {
		Group result;
		String url = Urls.GROUP_PROFILE + groupId + '/';
		try {
			result = getClient().setCredential(Auth.token).get(url,Group.class,(NameValuePair[])null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	
	
	
	
	
}
