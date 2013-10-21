package com.campus.prime.ui.user;

import com.campus.prime.R;
import com.campus.prime.ui.TabPagerActivity;
import com.campus.prime.ui.indicator.PageIndicator;
import com.campus.prime.ui.indicator.TitlePageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class UserActivity extends TabPagerActivity<UserPagerAdapter>{
	private int userId;
	
	public int getUserId(){
		return userId;
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		Intent intent = getIntent();
		userId = intent.getIntExtra("userId", -1);
		
		
	}
	
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	@Override
	protected UserPagerAdapter createAdapter() {
		// TODO Auto-generated method stub
		return new UserPagerAdapter(getSupportFragmentManager());
	}
}
