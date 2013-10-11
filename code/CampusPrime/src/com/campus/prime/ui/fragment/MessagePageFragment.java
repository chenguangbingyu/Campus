package com.campus.prime.ui.fragment;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.campus.prime.R;
import com.campus.prime.adapter.MessageListViewAdapter;
import com.campus.prime.adapter.SingleTypeAdapter;
import com.campus.prime.bean.MessageItem;
import com.campus.prime.bean.MessagePage;
import com.campus.prime.https.MessageService;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

public class MessagePageFragment extends PagedItemFragment<MessageItem>{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setCurrentPage(new MessagePage());
	}
	
	@Override
	protected SingleTypeAdapter<MessageItem> createAdapter(
			List<MessageItem> items) {
		// TODO Auto-generated method stub
		return new MessageListViewAdapter(getActivity(),R.layout.messages_listitem);
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected List<MessageItem> load() {
		// TODO Auto-generated method stub
		List<MessageItem> result = null;
		try {
			currentPage = service.getUser(2);
			
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
			result = (List<MessageItem>)currentPage.getResults();
		return result;
	}

	@Override
	protected List<MessageItem> first() {
		// TODO Auto-generated method stub
		return load();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<MessageItem> next() {
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<MessageItem>)currentPage.getResults();
			else
				return null;
		}
		return null;
		
	}

}
