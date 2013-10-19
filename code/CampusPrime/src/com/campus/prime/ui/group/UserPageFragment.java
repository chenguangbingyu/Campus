package com.campus.prime.ui.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.campus.prime.core.UserItem;
import com.campus.prime.core.UserPage;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.IntentUtil;


public class UserPageFragment extends PagedItemFragment<UserItem>{
	
	/**
	 * service for loading user page data
	 */
	protected UserService service = new UserService(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCurrentPage(new UserPage());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<UserItem> load() {
		// TODO Auto-generated method stub
		List<UserItem> result = null;
		currentPage = service.getUsersByGroup(((GroupActivity) this.getActivity()).getGroupId());
		if(currentPage != null)
			result = (List<UserItem>)currentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<UserItem> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<UserItem>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}

	@Override
	protected SingleTypeAdapter<UserItem> createAdapter(List<UserItem> items) {
		// TODO Auto-generated method stub
		return new UserListViewAdapter(getActivity(), items);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		int userId = adapter.getItem(position-1).getId();
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("userId",userId);
		IntentUtil.start_activity(this.getActivity(), UserActivity.class, params);
		
	}
	

}
