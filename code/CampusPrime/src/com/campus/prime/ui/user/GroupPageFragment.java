package com.campus.prime.ui.user;

import java.util.List;

import android.os.Bundle;

import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;

public class GroupPageFragment extends PagedItemFragment<GroupItem> {
	
	/**
	 * service for loading data
	 */
	protected GroupService service = new GroupService();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCurrentPage(new GroupPage());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<GroupItem> load() {
		// TODO Auto-generated method stub
		List<GroupItem> result = null;
		currentPage = service.getGroups();
		if(currentPage != null)
			result = (List<GroupItem>)currentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<GroupItem> next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<GroupItem>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}

	@Override
	protected SingleTypeAdapter<GroupItem> createAdapter(List<GroupItem> items) {
		// TODO Auto-generated method stub
		return new GroupListViewAdapter(getActivity(),items);
	}

}
