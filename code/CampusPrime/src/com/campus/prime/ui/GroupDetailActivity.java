package com.campus.prime.ui;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;
import com.campus.prime.ui.fragment.GroupBasicFragment;
import com.campus.prime.ui.fragment.GroupMembersFragment;
import com.campus.prime.ui.fragment.GroupRecentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.test.RenamingDelegatingContext;
import android.view.Menu;

/**
 * 群组详细资料的activity
 * 包含三个Fragment GroupBasicFragment，GroupmembersFragment,GroupRecentFragment
 * @author absurd
 *
 */
public class GroupDetailActivity  extends BaseTabActivity{

	private static final String BASIC_TITLE = "BASIC";
	private static final String MEMBERS_TITLE = "MEMBERS";
	private static final String RECENT_TITLE = "RECENT";
	private static final int TAB_NUM = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.group_detail, menu);
		return true;
	}
	
	public static class TabAdapter extends FragmentPagerAdapter{

		public TabAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stu
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
				return MEMBERS_TITLE;
		}
	
		
	}

	@Override
	protected int id() {
		// TODO Auto-generated method stub
		return R.layout.activity_group_detail;
	}


	@Override
	protected ViewPager viewPager() {
		// TODO Auto-generated method stub
		return (ViewPager)findViewById(R.id.group_pager);
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
		list.add(MEMBERS_TITLE);
		return list;
	}
	
}
