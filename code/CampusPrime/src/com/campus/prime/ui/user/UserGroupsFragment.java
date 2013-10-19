package com.campus.prime.ui.user;



import java.util.List;

import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;

import android.os.Bundle;

public class UserGroupsFragment extends GroupPageFragment{
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No Groups");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<GroupItem> load() {
		// TODO Auto-generated method stub
		int userId = ((UserActivity)getActivity()).getUserId();
		List<GroupItem> result = null;
		GroupPage page;
		if(userId == -1)
			page = (GroupPage) service.getGroups();
		else
			page = (GroupPage) service.getGroupsByUserid(userId);
		if(page != null)
			result = page.getResults();
		return result;
	}
	
}
