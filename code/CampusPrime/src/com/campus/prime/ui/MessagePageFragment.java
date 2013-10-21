package com.campus.prime.ui;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.Message;
import com.campus.prime.core.MessagePage;
import com.campus.prime.core.service.MessageService;

public class MessagePageFragment extends PagedItemFragment<Message>{
	
	/**
	 * service for loading data
	 */
	protected MessageService service = new MessageService();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setCurrentPage(new MessagePage());
	}
	
	@Override
	protected SingleTypeAdapter<Message> createAdapter(
			List<Message> items) {
		// TODO Auto-generated method stub
		return new MessageListViewAdapter(getActivity(),R.layout.messages_listitem);
	}

	
	
	
	@SuppressWarnings("unchecked" )
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		List<Message> result = null;
		try {
			//currentPage = service.getUser(230);
			currentPage = service.getPublic();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(currentPage != null)
			result = (List<Message>)currentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> next() {
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<Message>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}

	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
	}
	
	@Override
	public boolean onListItemLongClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		((ActionBarActivity)getActivity()).startSupportActionMode(mCallback);
		return true;
	}
	
	private ActionMode.Callback mCallback = new ActionMode.Callback() {
		
		@Override
		public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onDestroyActionMode(ActionMode arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
			// TODO Auto-generated method stub
			MenuInflater inflater = arg0.getMenuInflater();
			inflater.inflate(R.menu.message_am,arg1);
			return true;
		}
		
		@Override
		public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
			// TODO Auto-generated method stub
			boolean ret = false;
			switch(arg1.getItemId()){
			case R.id.msg_praise:
				onPraise();
				break;
			case R.id.msg_comment:
				onComment();
				break;
			case R.id.msg_collect:
				onCollect();
				break;
			}
			return ret;
		}
	};
	
	
	protected void onPraise(){
		
	}
	
	protected void onComment(){
		
	}
	
	protected void onCollect(){
		
	}


}
