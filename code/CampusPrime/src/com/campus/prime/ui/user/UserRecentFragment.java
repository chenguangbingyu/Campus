package com.campus.prime.ui.user;


import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.campus.prime.core.Message;
import com.campus.prime.core.MessagePage;
import com.campus.prime.ui.MessagePageFragment;
import com.campus.prime.ui.SingleTypeAdapter;


public class UserRecentFragment extends MessagePageFragment{
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		int userId = ((UserActivity)getActivity()).getUserId();
		MessagePage page = null;
		if(userId == -1)
			page = (MessagePage) service.getUser();
		else{
			try {
				page = (MessagePage)service.getUserById(userId);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		if(page != null)
			return page.getResults();
		return null;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No user Recent");
	}

	
	
	
	
	
}
