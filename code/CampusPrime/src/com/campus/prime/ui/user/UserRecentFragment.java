package com.campus.prime.ui.user;


import java.util.List;

import android.os.Bundle;

import com.campus.prime.core.Message;
import com.campus.prime.core.MessagePage;
import com.campus.prime.ui.home.MessagePageFragment;


public class UserRecentFragment extends MessagePageFragment{

	
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		MessagePage page = null;
		page = (MessagePage) service.getUser();
		return page.getResults();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No user Recent");
	}
	
}
