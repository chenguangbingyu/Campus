package com.campus.prime.ui.fragment;

import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.adapter.SingleTypeAdapter;
import com.campus.prime.bean.MessageItem;
import com.campus.prime.utils.ViewUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public abstract class ItemListFragment<E> extends Fragment implements
	 	LoaderCallbacks<List<E>>{
	/**
	 * Force refersh
	 */
	public static final String FORCE_REFRESH = "force_refresh";
	
	public static final int FORCE = 1;
	
	
	protected SingleTypeAdapter<E> adapter;
	/**
	 * ListView items
	 */
	protected List<E> items = Collections.emptyList();
	
	/**
	 * ListView
	 */
	protected PullToRefreshListView listView;
	
	/**
	 * Empty TextView
	 */
	protected TextView emptyView;
	
	/**
	 * ProgressBar
	 */
	protected ProgressBar progressBar;
	
	/**
	 * Is the list currently shown
	 */
	protected boolean listShown;
	
	
	
	public PullToRefreshListView getListView(){
		return this.listView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		if(!items.isEmpty())
			setListShown(true,false);
		
		getLoaderManager().initLoader(0,null,this);
			
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.item_list,null);
	}
	
	
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		listShown = false;
		emptyView = null;
		progressBar = null;
		listView = null;
		
		super.onDestroyView();
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		listView = (PullToRefreshListView)view.findViewById(android.R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				onListItemClick((ListView)arg0,arg1,arg2,arg3);
				
			}
		});
		
		ListView actualListView = listView.getRefreshableView();
		registerForContextMenu(actualListView);
		this.adapter = createAdapter();
		actualListView.setAdapter(adapter);
				
		
		
		actualListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return onListItemLongClick((ListView)arg0,arg1,arg2,arg3);
			}
		});
		progressBar = (ProgressBar)view.findViewById(R.id.pb_loading);
		emptyView = (TextView)view.findViewById(android.R.id.empty);
		
		
	}
	
	
	
	
	protected void configureList(){
		this.adapter = createAdapter();
		listView.setAdapter(adapter);
	}
	
	protected SingleTypeAdapter<E> createAdapter(){
		SingleTypeAdapter<E> wrapped = createAdapter(items);
		return wrapped;
	}
	protected abstract SingleTypeAdapter<E> createAdapter(final List<E> items);
	
	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.refresh, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.m_refresh:
			forceRefresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	protected void forceRefresh(){
		Bundle bundle = new Bundle();
		bundle.putInt(FORCE_REFRESH,FORCE);
		refresh(bundle);
	}
	
	
	public void refresh(){
		refresh(null);
	}
	
	protected void refresh(final Bundle args){
		getLoaderManager().restartLoader(0, args, this);
	}
	
	
	
	public ItemListFragment<E> setListShown(final boolean shown,
			final boolean animate){
		if(shown == listShown){
			if(shown){
				if(items.isEmpty())
					hide(listView).show(emptyView);
				else
					hide(emptyView).show(listView);
			}
			return this;
		}
		listShown = shown;
		
		if(shown){
			if(!items.isEmpty())
				hide(progressBar).hide(emptyView).fadeIn(listView,animate)
				.show(listView);
			else
				hide(progressBar).hide(listView).fadeIn(emptyView,animate)
				.show(emptyView);
		}else{
			hide(listView).hide(emptyView).fadeIn(progressBar,animate)
			.show(progressBar);
		}
		return this;
	}
	
	
	private ItemListFragment<E> show(final View view){
		ViewUtils.setGone(view,false);
		return this;
	}
	
	
	private ItemListFragment<E> hide(final View view){
		ViewUtils.setGone(view,true);
		return this;
	}
	
	
	private ItemListFragment<E> fadeIn(final View view,final boolean animate){ 
		if(view != null){
			if(animate)
				view.startAnimation(AnimationUtils.loadAnimation(getActivity(),
						android.R.anim.fade_in));
			else
				view.clearAnimation();
		}
		return this;
	}
	
	
	
	@Override
	public void onLoadFinished(Loader<List<E>> arg0, List<E> arg1) {
		// TODO Auto-generated method stub
		this.items = arg1;
		getListAdapter().setItems(items);
		showList();
	}
	
	protected void showList(){
		setListShown(true,isResumed());
	}
	
	
	@Override
	public void onLoaderReset(Loader<List<E>> arg0) {
		// TODO Auto-generated method stub
		getListAdapter().setItems(Collections.emptyList());
	}
	
	
	@SuppressWarnings("unchecked")
	protected SingleTypeAdapter<E> getListAdapter(){
		return adapter;
	}
	
	
	protected ItemListFragment<E> notifyDataSetChanged(){
		SingleTypeAdapter<E> root = getListAdapter();
		if(root != null){
			root.notifyDataSetChanged();
		}
		return this;
	}
	
	
	 /**
     * Set list adapter to use on list view
     *
     * @param adapter
     * @return this fragment
     */
    protected ItemListFragment<E> setListAdapter(final ListAdapter adapter) {
        if (listView != null)
            listView.setAdapter(adapter);
        return this;
    }
	
    protected ItemListFragment<E> setEmptyText(final String message){
    	if(emptyView != null)
    		emptyView.setText(message);
    	return this;
    }
	
	
	
	 /**
     * Callback when a list view item is clicked
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    /**
     * Callback when a list view item is clicked and held
     *
     * @param l
     * @param v
     * @param position
     * @param id
     * @return true if the callback consumed the long click, false otherwise
     */
    public boolean onListItemLongClick(ListView l, View v, int position, long id) {
        return false;
    }
}
