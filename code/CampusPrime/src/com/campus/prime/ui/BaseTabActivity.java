package com.campus.prime.ui;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;

/**
 * 所有实现tabs fragment的父类
 * 使用方法：
 * 继承该类
 * 实现抽象方法   方法的使用见具体方法说明
 * @author absurd
 *
 */
public abstract class BaseTabActivity extends BaseActivity implements TabListener,OnNavigationListener{
	
	private ViewPager mPager;
	private FragmentPagerAdapter mAdapter;
	private List<String> tabNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(id());
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabNames = tabNames();
		initTabFragmentPager(actionBar);
	}
	
	/**
	 * configure FragmentPager
	 * @param actionBar
	 */
	private void initTabFragmentPager(final ActionBar actionBar){
		mPager = viewPager();
		mAdapter = adapter();
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
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
		
		actionBar.addTab(actionBar.newTab().setText(tabNames.get(0)).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(tabNames.get(1)).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(tabNames.get(2)).setTabListener(this));
	}
	/**
	 * 返回activity的layout resource id
	 * @return
	 */
	protected abstract int id();
	/**
	 * 返回ViewPager resource id
	 * @return
	 */
	protected abstract ViewPager viewPager();
	/**
	 * 返回FragmentPageAdapter
	 * @return
	 */
	protected abstract FragmentPagerAdapter adapter();
	/**
	 * 返回Fragment名称列表
	 * @return
	 */
	protected abstract List<String> tabNames();
	
	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
