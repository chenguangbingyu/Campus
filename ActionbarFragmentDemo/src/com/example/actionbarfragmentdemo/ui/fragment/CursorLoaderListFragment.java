package com.example.actionbarfragmentdemo.ui.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Network.Network;
import android.app.Activity;
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

import com.example.actionbarfragmentdemo.constant.AppConstant;
import com.example.actionbarfragmentdemo.model.Message;
import com.example.actionbarfragmentdemo.protocol.MessageProtocol;
import com.example.actionbarfragmentdemo.protocol.MessageProtocol.ProtocolMessageDelegate;

public class CursorLoaderListFragment extends ListFragment implements 
		ProtocolMessageDelegate{
		
		
	//网络获取消息成功
	private static final int MESSAGE_GETMESSAGE_SUCCESS = 0;
	//网络获取消息失败
	private static final int MESSAGE_GETMESSAGE_FAILED = 1;
	
	private ArrayAdapter<String> mAdapter;
	//需要在listView中显示的data
	private List<String> mData;
	
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
	
	private void getMessagesFromNetwork(){
		//创建对应的model的protocol实例
		MessageProtocol protocol = new MessageProtocol().setDelegate(this)
				.setContext(this.getActivity());
		//创建Network实例
		Network network = new Network();
		//设置URL
		network.setURL(MessageProtocol.URL + MessageProtocol.COMMAND);
		//发送http请求
		//network.send(protocol, Network.POST);
		//没有server测试
		network.sendForTest(protocol,MessageProtocol.COMMAND,Network.GET);
	}
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		setEmptyText("No Phone Number");
		//actionbar上添加menu
		setHasOptionsMenu(true);
		
		mData = new ArrayList<String>();

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//从网络获取消息
		getMessagesFromNetwork();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	private void bindListView(){
		mAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_expandable_list_item_1,mData);
		setListAdapter(mAdapter);
	}

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		MenuItem item = menu.add("Search");
		item.setIcon(android.R.drawable.ic_menu_search);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		SearchView sv = new SearchView(getActivity());
		sv.setOnQueryTextListener(null);
		item.setActionView(sv);
	}

	@Override
	public void getMessageSuccess(List<Message> messages) {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_SUCCESS);
		if(messages != null){
			//获取从网络得到的信息-----messages
			Iterator<Message> iterator = messages.iterator();
			while(iterator.hasNext()){
				mData.add(iterator.next().getContent());
			}
			Log.d(AppConstant.DEBUG_TAG,"get message success" + messages.toString());
		}
	}

	@Override
	public void getMessageFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_FAILED);
		Log.d(AppConstant.DEBUG_TAG,"get messages failed");
	}
		

}
