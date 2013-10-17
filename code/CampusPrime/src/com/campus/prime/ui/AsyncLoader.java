package com.campus.prime.ui;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class AsyncLoader<D> extends AsyncTaskLoader<D>{
	
	private D data;
	
	public AsyncLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected abstract D loadData();
	
	@Override
	public D loadInBackground() {
		// TODO Auto-generated method stub
		return loadData();
	}
	
	@Override
	public void deliverResult(final D data) {
		// TODO Auto-generated method stub
		if(isReset()){
			return;
		}
		this.data = data;
		super.deliverResult(data);
	}
	
	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub
		if(data != null){
			deliverResult(data);
		}
		if(takeContentChanged() || data == null){
			forceLoad();
		}
	}
	
	@Override
	protected void onStopLoading() {
		// TODO Auto-generated method stub
		cancelLoad();
	}
	
	
	@Override
	protected void onReset() {
		// TODO Auto-generated method stub
		super.onReset();
		
		onStopLoading();
		
		data = null;
	}
	

	
}
