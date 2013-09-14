package com.campus.prime.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.campus.prime.ui.fragment.CommonUIFragment;
import com.campus.prime.ui.fragment.MessagesListFragment;
import com.campus.prime.ui.fragment.LaunchUIFragment;

import static com.campus.prime.ui.HomeActivity.ARGUMENTS_NAME;
import static com.campus.prime.ui.HomeActivity.MAX_TAB_SIZE;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter{
		
		public TabFragmentPagerAdapter(FragmentManager fm){
			super(fm);
		}
		

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment ft = null;
			switch(arg0){
			case 0:
				ft = new MessagesListFragment();
				break;
			case 1:
				ft = new LaunchUIFragment();
				break;
			default:
				break;
					
			}
			return ft;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MAX_TAB_SIZE;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return "TAB" + (position + 1);
		}
		
	}
	