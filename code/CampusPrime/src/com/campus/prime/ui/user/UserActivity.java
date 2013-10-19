package com.campus.prime.ui.user;

import com.campus.prime.R;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TitlePageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class UserActivity extends FragmentActivity{
	UserPagerAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	
	private int userId;
	
	public int getUserId(){
		return userId;
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.user_pager);
		mAdapter = new UserPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager)findViewById(R.id.user_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (TitlePageIndicator)findViewById(R.id.user_indicator);
		mIndicator.setViewPager(mPager);
		
		Intent intent = getIntent();
		userId = intent.getIntExtra("userId", -1);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
}
