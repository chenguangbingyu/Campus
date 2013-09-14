package com.campus.prime.protocol;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.campus.prime.common.utils.JsonUtil;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.database.MessageDB;
import com.campus.prime.model.Message;
import com.google.gson.reflect.TypeToken;

import Database.DAOHelper;
import Protocol.ProtocolBase;

public class MessageProtocol extends ProtocolBase{
	
	public static final String URL = "http://www.freeabsurd.com/";
	
	public static final String COMMAND = "getMessages";
	

	
	/**
	 * 创建委托
	 * 观察者模式
	 * @author absurd
	 *
	 */
	/**
	public interface ProtocolMessageDelegate{
		public void getMessageSuccess(List<Message> messages);
		public void getMessageFailed();
	}
	**/
	//创建delegate的对象
	//ProtocolMessageDelegate delegate;
	ProtocolDelegate<Message> mDelegate;
	Context mContext;
	
	public MessageProtocol setContext(Context context){
		this.mContext = context;
		return this;
	}
	
	public MessageProtocol setDelegate(ProtocolDelegate<Message> delegate){
		this.mDelegate = delegate;
		return this;
	}
	
	
	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 解析传入的json字符串
	 * @param arg0
	 * @return
	 */
	@Override
	public boolean parseProtocol(String arg0) {
		// TODO Auto-generated method stub
		// 清空旧的缓存
		MessageDB db = (MessageDB)DAOHelper.getInstance().getTable(MessageDB.TABLE);
		db.clearAll();
		//解析json
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>) JsonUtil.jsonToList(arg0,new TypeToken<List<Message>>(){}.getType());
		if(messages != null){
			//添加到数据库
			Message message = null;
			Iterator<Message> iterator = messages.iterator();
			while(iterator.hasNext()){
				message = iterator.next();
				message.setContext(mContext);
				Log.d(AppConstant.DEBUG_TAG,message.toString());
				message.saveToDB();
				
			}
		}
		mDelegate.getMessageSuccess(messages);
		return true;
	}
	
}
