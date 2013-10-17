package com.campus.prime.ui.user;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.campus.prime.R;
import com.campus.prime.ui.BaseTabActivity;
import com.campus.prime.ui.home.HomeFragment;


/**
 * 用户详细资料的activity
 * 包含三个Fragment UserBasicFragment,UserRecentFragment,UserGroupsFragment
 * @author absurd
 *
 */
public class UserActivity extends BaseTabActivity{

	private static final String BASIC_TITLE = "BASIC";
	private static final String RECENT_TITLE = "RECENT";
	private static final String GROUPS_TITLE = "GROUPS";
	private static final int TAB_NUM = 3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	
	public static class TabAdapter extends FragmentPagerAdapter{

		
		public TabAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment ft = null;
			switch(arg0){
			case 0:
				ft = new UserBasicFragment();
				break;
			case 1:
				ft = new UserRecentFragment();
				break;
			case 2:
				ft = new UserGroupsFragment();
				break;
			default:
				break;
			}
			return ft;
		}
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return TAB_NUM;
		}
		
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			if(position == 0)
				return BASIC_TITLE;
			else if(position == 1)
				return RECENT_TITLE;
			else
				return GROUPS_TITLE;
		}
	}


	@Override
	protected int id() {
		// TODO Auto-generated method stub
		return R.layout.activity_user_profile;
	}


	@Override
	protected ViewPager viewPager() {
		// TODO Auto-generated method stub
		return (ViewPager)findViewById(R.id.user_pager);
	}


	@Override
	protected FragmentPagerAdapter adapter() {
		// TODO Auto-generated method stub
		return new TabAdapter(getSupportFragmentManager());
	}


	@Override
	protected List<String> tabNames() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add(BASIC_TITLE);
		list.add(RECENT_TITLE);
		list.add(GROUPS_TITLE);
		return list;
	}

	
	

}
