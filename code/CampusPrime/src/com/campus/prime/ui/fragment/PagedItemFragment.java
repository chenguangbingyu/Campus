package com.campus.prime.ui.fragment;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.campus.prime.bean.PageBase;
import com.campus.prime.https.MessageService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public abstract class PagedItemFragment<E> extends ItemListFragment<E>{
	
	
	public static final int  UP = 2;
	public static final int DOWN = 3;
	
	/**
	 * Entity Page
	 */
	protected PageBase currentPage;
	
	/**
	 * Scroll direction
	 */
	protected int direction = 2;
	
	/**
	 * service for loading data
	 */
	protected MessageService service;
	
	
	protected PageBase getCurrentPage(){
		return currentPage;
	}
	
	protected void setCurrentPage(PageBase page){
		this.currentPage = page;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		// Configure PullToRefreshListView
		listView.setMode(Mode.DISABLED);
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
	

	@Override
	public Loader<List<E>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		// intialize MessageService
		service = new MessageService();
		return new AsyncLoader<List<E>>(getActivity()) {

			@Override
			protected List<E> loadData() {
				// TODO Auto-generated method stub
				return load();
			}
		};
	}
	

	@Override
	public void onLoadFinished(Loader<List<E>> arg0, List<E> arg1) {
		// TODO Auto-generated method stub
		if(!isUsable())
			return;
		this.items = arg1;
		getListAdapter().setItems(items);
		showList();
		// set mode of PullToRefereshListView
		if(items != null)
			listView.setMode(Mode.BOTH);
	}
	
	public class GetDataTask extends AsyncTask<Void,Void,List<E>>{

		@Override
		protected List<E> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			if(direction == UP)
				return first();
			else if(direction == DOWN)
				return next();
			else
				return null;
		}
			
		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(List<E> result) {
			// TODO Auto-generated method stub
			if(!isUsable())
				return;
			if(direction == UP){
				items = result;
			}else if(direction == DOWN){
				//items.add(items.size(),(E)result);
				if(result != null){
					items.addAll(result);
				}
			}
			getListAdapter().setItems(items);
			showList();
			listView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
	
	/**
	 * refresh messages
	 */
	/**
	 * async load data
	 * @return
	 */
	protected abstract List<E> load();
	/**
	 * get first page
	 */
	protected abstract List<E> first();
	
	/**
	 * get next page
	 */
	protected abstract List<E> next();
	
	/**
	 * has next page
	 */
	protected boolean hasNext(){
			return (currentPage == null || currentPage.getNext() == null) ? false : true;
	}
	
}
