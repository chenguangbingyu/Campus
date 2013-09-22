package com.campus.prime.ui.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Network.Network;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.campus.prime.adapter.MessageListViewAdapter;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.model.MessagesList;
import com.campus.prime.protocol.MessageProtocol;
import com.campus.prime.protocol.ProtocolDelegate;
import com.campus.prime.ui.CreateMessageActivity;
import com.campus.prime.R;

public class MessagesListFragment extends ListFragment implements 
		ProtocolDelegate<MessagesList>, ImageToolsDelegate{
		
	
	private static final int FLAG_CREATE_MESSAGE = 0;
		
	//网络获取消息成功
	private static final int MESSAGE_GETMESSAGE_SUCCESS = 0;
	//网络获取消息失败
	private static final int MESSAGE_GETMESSAGE_FAILED = 1;
	
	private MessageListViewAdapter mAdapter;
	//需要在listView中显示的data
	private MessagesList mData;
	
	/**
	 * 处理从网络接受消息线程发送的message
	 */
	Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case MESSAGE_GETMESSAGE_SUCCESS:
				//将接受到的信息绑定到listView
				bindListView();
				break;
			case MESSAGE_GETMESSAGE_FAILED:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	/**
	 * 从网络获取消息 
	 */
	private void getMessagesFromNetwork(){
		//创建对应的model的protocol实例
		MessageProtocol protocol = new MessageProtocol().setDelegate(this)
				.setContext(this.getActivity());
		Network network = new Network();
		network.setURL(MessageProtocol.URL + MessageProtocol.COMMAND);
		//发送http请求
		network.send(protocol, Network.GET);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//从网络获取消息    
		getMessagesFromNetwork();
		//初始化mData   又是巨坑无比，，，，，，
		//mData = new ArrayList<Message>();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Messages");
		//actionbar上添加menu
		setHasOptionsMenu(true);
		
	}

	private void bindListView(){
		mAdapter = new MessageListViewAdapter(this.getActivity(), mData.getResults(), R.layout.messages_listitem);
		setListAdapter(mAdapter);
	}

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.message_list_actions,menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.action_edit:
				// 转到edit_fragment
				Intent intent = new Intent();
				intent.setClass(getActivity(), CreateMessageActivity.class);
				startActivityForResult(intent, FLAG_CREATE_MESSAGE);
				return true;
			case R.id.action_refresh:
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == FLAG_CREATE_MESSAGE){
			return;
		}
	}
	
	@Override
	public void getMessageSuccess(MessagesList messages) {
		// TODO Auto-generated method stub
		if(messages != null){
			Log.d(AppConstant.DEBUG_TAG,"get message success" + messages.getResults().toString());
		}
		mData = messages;
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_SUCCESS);
	}

	@Override
	public void getMessageFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_FAILED);
		Log.d(AppConstant.DEBUG_TAG,"get messages failed");
	}


	@Override
	public void downlaodImageFailed() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void downloadImageSuccess() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
		

}
