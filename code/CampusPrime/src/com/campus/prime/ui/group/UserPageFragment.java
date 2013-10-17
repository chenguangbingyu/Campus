package com.campus.prime.ui.group;

import java.util.List;

import android.os.Bundle;

import com.campus.prime.core.UserItem;
import com.campus.prime.core.UserPage;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;


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
		currentPage = service.getUsersByGroup(1);
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

	

}
