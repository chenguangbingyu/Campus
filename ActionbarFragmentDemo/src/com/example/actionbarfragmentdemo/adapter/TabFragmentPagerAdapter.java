package com.example.actionbarfragmentdemo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.actionbarfragmentdemo.fragment.CommonUIFragment;
import com.example.actionbarfragmentdemo.fragment.CursorLoaderListFragment;
import com.example.actionbarfragmentdemo.fragment.LaunchUIFragment;
import static com.example.actionbarfragmentdemo.MainActivity.ARGUMENTS_NAME;
import static com.example.actionbarfragmentdemo.MainActivity.MAX_TAB_SIZE;

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
				ft = new LaunchUIFragment();
				break;
			case 1:
				ft = new CursorLoaderListFragment();
				break;
			default:
				ft = new CommonUIFragment();
				
				Bundle args = new Bundle();
				args.putString(ARGUMENTS_NAME,"TAB" + (arg0 + 1));
				ft.setArguments(args);
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
	