package com.campus.prime.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.model.Message;
import com.campus.prime.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class CommonUIFragment extends ListFragment{

	private static final List<Message> listItems = new ArrayList<Message>();
	/*****
	static{
		Message m1 = new Message("title1","content1");
		listItems.add(m1);
		Message m2 = new Message("title2","content2");
		listItems.add(m2);
	}**/
	
	//private MessageListViewAdapter_delete adapter = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//adapter = new MessageListViewAdapter_delete(this.getActivity(), listItems, R.layout.messages_listitem);
		//setListAdapter(adapter);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
}
