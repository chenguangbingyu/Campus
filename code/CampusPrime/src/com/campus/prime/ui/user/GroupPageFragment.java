package com.campus.prime.ui.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.PagedItemFragment;
import com.campus.prime.ui.SingleTypeAdapter;
import com.campus.prime.ui.group.GroupActivity;
import com.campus.prime.utils.IntentUtil;

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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		int groupId = ((GroupItem)adapter.getItem(position-1)).getId();
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("groupId", groupId);
		IntentUtil.start_activity(this.getActivity(), GroupActivity.class, params);
	}
	

}
