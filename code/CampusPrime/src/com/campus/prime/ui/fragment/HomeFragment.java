package com.campus.prime.ui.fragment;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.campus.prime.bean.Message;
import com.campus.prime.bean.MessagePage;

import android.os.Bundle;

public class HomeFragment extends MessagePageFragment{
	
	private int currentGroup;
	
	public void setCurrentGroup(int id){
		currentGroup = id;
	}
	
	/**
	public MessagesUpdateListenser onMessageUpdateListener;
	
	public void setMessageUpdateListener(MessagesUpdateListenser listener){
		this.onMessageUpdateListener = listener;
	}
	*/
	public interface MessagesUpdateListenser{
		/**
		 * update fragment MessagelistView
		 * @param id
		 * @return
		 */
		public int update();
	};
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		//currentGroup = onMessageUpdateListener.update();
		List<Message> result = null;
		if(currentGroup == -1){
			try {
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
		}else{
			currentPage = service.getGroup(currentGroup);
		}
		if(currentPage != null)
			result = (List<Message>) currentPage.getResults();
		return result;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setEmptyText("No Message");
	}
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(null);
	}
	
}
