package com.campus.prime.ui.user;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserPagerAdapter extends FragmentPagerAdapter{
	protected static final String[] CONTENT = new String[]{"BASIC","RECENT","GROUPS"};
	
	private int mCount = CONTENT.length;
	
	public UserPagerAdapter(FragmentManager fm) {
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
		}
		return ft;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return UserPagerAdapter.CONTENT[position % CONTENT.length];
	}
	
	
	
	
	
	
}