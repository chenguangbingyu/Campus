package com.campus.prime.protocol;

import java.util.Iterator;
import java.util.List;

import com.campus.prime.common.utils.JsonUtil;
import com.campus.prime.database.UserDB;
import com.campus.prime.model.User;

import android.content.Context;
import android.view.accessibility.AccessibilityRecord;
import Database.DAOHelper;
import Protocol.ProtocolBase;
import com.google.gson.reflect.TypeToken;

public class UserProtocol extends ProtocolBase{

	private ProtocolDelegate<User> delegate;
	private Context context;
	
	
	public UserProtocol setDelegate(ProtocolDelegate<User> delegate){
		this.delegate = delegate;
		return this;
	}
	
	public UserProtocol setContext(Context context){
		this.context = context;
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
				user.setContext(context);
				user.saveToDB();
			}
		}
		delegate.getMessageSuccess(users);
		return true;
	}

}
