package com.campus.prime.protocol;

import java.util.Iterator;
import java.util.List;

import com.campus.prime.bean.User;
import com.campus.prime.database.UserDB;
import com.campus.prime.utils.JsonUtil;

import android.content.Context;
import android.view.accessibility.AccessibilityRecord;
import Database.DAOHelper;
import Protocol.ProtocolBase;
import com.google.gson.reflect.TypeToken;

public class UserProtocol extends ProtocolBase{
	
	public static final String URL = "http://www.freeabsurd.com/";
	
	public static final String COMMAND = "getUserInfo";

	private ProtocolDelegate<User> mDelegate;
	private Context mContext;
	
	
	public UserProtocol setDelegate(ProtocolDelegate<User> delegate){
		this.mDelegate = delegate;
		return this;
	}
	
	public UserProtocol setContext(Context context){
		this.mContext = context;
		return this;
	}
	
	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean parseProtocol(String arg0) {
		// TODO Auto-generated method stub
		UserDB db = (UserDB)DAOHelper.getInstance().getTable(UserDB.TABLE);
		db.clearAll();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)JsonUtil.jsonToList(arg0,new TypeToken<List<User>>(){}.getType());
		if(users != null){
			User user = null;
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()){
				user = iterator.next();
				user.setContext(mContext);
				user.saveToDB();
			}
		}
		//mDelegate.getMessageSuccess(users);
		return true;
	}

}
