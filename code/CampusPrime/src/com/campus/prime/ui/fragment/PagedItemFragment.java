package com.campus.prime.ui.fragment;

import java.util.List;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

import com.campus.prime.bean.MessageItem;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public abstract class PagedItemFragment<E> extends ItemListFragment<E>{
	
	
	public static final int  UP = 2;
	public static final int DOWN = 3;
	
	/**
	 * Scroll direction
	 */
	protected int direction = 2;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		// Configure PullToRefreshListView
		listView.setMode(Mode.BOTH);
		listView.setOnScrollListener(new OnScrollListener() {
			private int firstItem;
			private int lastItem;
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(firstItem == 0 && scrollState == SCROLL_STATE_IDLE){
					direction = UP;
				}else if(lastItem == items.size() && scrollState == SCROLL_STATE_IDLE){
					direction = DOWN;
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				firstItem = firstVisibleItem;
				lastItem = firstVisibleItem + visibleItemCount - 2;
				
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
							DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				
				new GetDataTask().execute();
			}
		});
		
	}
	
	public class GetDataTask extends AsyncTask<Void,Void,MessageItem>{

		@Override
		protected MessageItem doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {  
                Thread.sleep(500);  
            } catch (InterruptedException e) {  
            }  
			MessageItem item = new MessageItem();
					item.setId(0);
					item.setContent("refresh add");
					item.setLocation("location");
					item.setMedia("media");
				return item;
		}
			
		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(MessageItem result) {
			// TODO Auto-generated method stub
			if(direction == UP){
				items.add(0,(E)result);
				getListAdapter().setItems(items);
			}else if(direction == DOWN){
				items.add(items.size(),(E)result);
				getListAdapter().setItems(items);
			}
			listView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
	
	
	
	
}
