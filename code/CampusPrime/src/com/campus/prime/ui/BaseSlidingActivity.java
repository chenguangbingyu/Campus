package com.campus.prime.ui;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.campus.prime.slidingmenu.SlidingActivityBase;
import com.campus.prime.slidingmenu.SlidingActivityHelper;
import com.campus.prime.slidingmenu.SlidingMenu;

public class BaseSlidingActivity extends BaseActivity implements SlidingActivityBase{

	private SlidingActivityHelper slidingHelper;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		slidingHelper = new SlidingActivityHelper(this);
		slidingHelper.onCreate(savedInstanceState);
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		slidingHelper.onPostCreate(savedInstanceState);
	}
	
	
	
	@Override
	public View findViewById(int id) {
		// TODO Auto-generated method stub
		View v = super.findViewById(id);
		if(v != null){
			return v;
		}
		return slidingHelper.findViewById(id);
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		slidingHelper.onSaveInstanceState(outState);
	}
	
	
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		setContentView(getLayoutInflater().inflate(layoutResID, null));
	}
	
	@Override
	public void setContentView(View view) {
		// TODO Auto-generated method stub
		setContentView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
	}
	
	@Override
	public void setContentView(View view, LayoutParams params) {
		// TODO Auto-generated method stub
		super.setContentView(view,params);
		slidingHelper.registerAboveContentView(view, params);
	}
	
	
	
	@Override
	public void setBehindContentView(View view, LayoutParams layoutParams) {
		// TODO Auto-generated method stub
		slidingHelper.setBehindContentView(view, layoutParams);
	}

	@Override
	public void setBehindContentView(View view) {
		// TODO Auto-generated method stub
		setBehindContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void setBehindContentView(int layoutResID) {
		// TODO Auto-generated method stub
		setBehindContentView(getLayoutInflater().inflate(layoutResID, null));
	}

	@Override
	public SlidingMenu getSlidingMenu() {
		// TODO Auto-generated method stub
		return slidingHelper.getSlidingMenu();
	}

	@Override
	public void toggle() {
		// TODO Auto-generated method stub
		slidingHelper.toggle();
	}

	@Override
	public void showContent() {
		// TODO Auto-generated method stub
		slidingHelper.showContent();
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		slidingHelper.showMenu();
	}

	@Override
	public void showSecondaryMenu() {
		// TODO Auto-generated method stub
		slidingHelper.showSecondaryMenu();
		
	}

	@Override
	public void setSlidingActionBarEnabled(boolean slidingActionBarEnabled) {
		// TODO Auto-generated method stub
		slidingHelper.setSlidingActionBarEnabled(slidingActionBarEnabled);
	}
	
	
	
	
	

}
