package com.campus.prime.ui.fragment;

import java.io.IOException;
import java.util.ArrayList;
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

public class HomeFragment extends PagedItemFragment<MessageItem>{

	@Override
	protected SingleTypeAdapter<MessageItem> createAdapter(
			List<MessageItem> items) {
		// TODO Auto-generated method stub
		return new MessageListViewAdapter(getActivity(),R.layout.messages_listitem);
	}

	
	@Override
	public Loader<List<MessageItem>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new AsyncLoader<List<MessageItem>>(getActivity()) {

			@SuppressWarnings("unchecked")
			@Override
			protected List<MessageItem> loadData() {
				// TODO Auto-generated method stub
				/**
				MessagePage page;
				try {
					MessageService service = new MessageService();
					page = service.getUser(2);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				if(page != null)
					return (List<MessageItem>)page.getResults();
				else
					return null;
					**/
				List<MessageItem> list = new ArrayList<MessageItem>();
				for(int i = 0;i<10;i++){
					MessageItem item = new MessageItem();
					item.setId(i);
					item.setContent("content" + i);
					item.setLocation("location" + i);
					item.setMedia("media" + i);
					list.add(item);
				}
				return list;
			}
		};
	}



}
