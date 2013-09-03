package com.example.actionbarfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.example.actionbarfragmentdemo.adapter.TabFragmentPagerAdapter;

public class MainActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener{
	
	
	private ViewPager mViewPager;
	
	public static final int MAX_TAB_SIZE = 3;
	public static final String ARGUMENTS_NAME = "args";
	
	private TabFragmentPagerAdapter mAdapter;
	
	

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mViewPager = (ViewPager)this.findViewById(R.id.pager);
		
		final ActionBar mActionBar = getSupportActionBar();
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
				mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				mActionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		for(int i = 0;i < 3; i++){
			mActionBar.addTab(
					mActionBar.newTab()
					.setText("Tab" + (i + 1))
					.setTabListener(this));
		}
		/**
		Tab tab = mActionBar.newTab()
				.setText("tab 1")
				.setTabListener(new TabListener<LaunchUIFragment>(this,"tab 1",LaunchUIFragment.class));
		
		mActionBar.addTab(tab);
		tab = mActionBar.newTab()
				.setText("tab 2")
				.setTabListener(new TabListener<CursorLoaderListFragment>(this,"tab 2",CursorLoaderListFragment.class));
		mActionBar.addTab(tab);
		tab = mActionBar.newTab()
				.setText("tab 3")
				.setTabListener(new TabListener<CommonUIFragment>(this, "tab 3", CommonUIFragment.class));
		mActionBar.addTab(tab);
		
		**/
	}
	
	
	/**
		public static class TabListener<T extends Fragment> implements android.support.v7.app.ActionBar.TabListener{
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		public TabListener(Activity activity, String tag, Class<T> clz){
			this.mTag = tag;
			this.mClass = clz;
			this.mActivity = activity;
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			if(mFragment == null){
				mFragment = Fragment.instantiate(mActivity,mClass.getName());
				
				arg1.add(android.R.id.content,mFragment,mTag);
			}else{
				arg1.attach(mFragment);
				//≤ªø…“‘commit
			}
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			if(mFragment != null){
				arg1.detach(mFragment);
				arg1.commit();
			}
		}
		
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(arg0.getPosition());
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

}
