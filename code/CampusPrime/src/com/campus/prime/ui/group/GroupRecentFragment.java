package com.campus.prime.ui.group;

import java.util.List;

import com.campus.prime.core.Message;
import com.campus.prime.ui.MessagePageFragment;

import android.os.Bundle;

public class GroupRecentFragment extends MessagePageFragment{
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		List<Message> result = null;
		currentPage = service.getGroup(((GroupActivity)this.getActivity()).getGroupId());
		if(currentPage != null)
			result = (List<Message>)currentPage.getResults();
		return result;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Recent Message");
	}
}
