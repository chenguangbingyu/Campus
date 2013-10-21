package com.campus.prime.ui;

import com.campus.prime.R;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TitlePageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public abstract class TabPagerActivity<V extends FragmentPagerAdapter> 
		extends ActionBarActivity{
	/**
	 * pager Adapter
	 */
	protected V mAdapter;
	/**
	 * View Pager
	 */
	protected ViewPager mPager;
	
	/*
	 * View Indicator
	 */
	protected PageIndicator mIndicator;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		super.onCreate(arg0);
		View view = getLayoutInflater().inflate(R.layout.tab_pager, null);
		setContentView(view);
		mAdapter = createAdapter();
		mPager = (ViewPager)view.findViewById(R.id.user_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (TitlePageIndicator)findViewById(R.id.user_indicator);
		mIndicator.setViewPager(mPager);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		View actionView = getLayoutInflater().inflate(R.layout.org_item,null);
		actionBar.setCustomView(actionView);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			this.finish();
		default:
			super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	protected abstract V createAdapter();
	
	
	
}
