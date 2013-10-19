package com.campus.prime.ui.group;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GroupPagerAdapter extends FragmentPagerAdapter{
protected static final String[] CONTENT = new String[]{"BASIC","RECENT","MEMBERS"};
	
	private int mCount = CONTENT.length;
	
	public GroupPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment ft = null;
		switch(arg0){
		case 0:
			ft = new GroupBasicFragment();
			break;
		case 1:
			ft = new GroupRecentFragment();
			break;
		case 2:
			ft = new GroupMembersFragment();
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
		return GroupPagerAdapter.CONTENT[position % CONTENT.length];
	}
	
	
	
	
}
