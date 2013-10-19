package com.campus.prime.ui;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;

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

}
