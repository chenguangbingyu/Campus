package com.campus.prime.ui;

import Database.DAOHelper;
import Network.Network;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;

import com.campus.prime.adapter.HomeDropdownListAdapter;
import com.campus.prime.adapter.TabFragmentPagerAdapter;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.database.MessageDB;
import com.campus.prime.R;

public class HomeActivity extends ActionBarActivity implements TabListener,OnNavigationListener{
	
	
	private ViewPager mViewPager;
	
	public static final int MAX_TAB_SIZE = 2;
	public static final String ARGUMENTS_NAME = "args";
	
	private TabFragmentPagerAdapter mAdapter;
	
	private HomeDropdownListAdapter homeDropDownListAdapter;
	
	
	
	private void initSimulateServer(){
		Network network = new Network();
		network.addTestResponse("getMessages", AppConstant.DEBUG_PROTOCOL_MESSAGES);
		Log.d(AppConstant.DEBUG_TAG,"add test response");
	}
	
	private void initDB(){
		DAOHelper.createInstance(this.getApplicationContext(), AppConstant.DB_NAME, AppConstant.DB_VERSION);
		DAOHelper dbHelper = DAOHelper.getInstance();
		if(dbHelper != null){
			dbHelper.addDBTable(MessageDB.TABLE,new MessageDB());
			Log.d(AppConstant.DEBUG_TAG,"add a table");
		}
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		mViewPager = (ViewPager)this.findViewById(R.id.pager);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
				mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
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
		
		for(int i = 0;i < 2; i++){
			actionBar.addTab(
					actionBar.newTab()
					.setText("Tab" + (i + 1))
					.setTabListener(this));
		}
		
		
		//初始化网络测试信息 
		initSimulateServer();
		//初始化数据库
		initDB();
		Log.d(AppConstant.DEBUG_TAG,"acticvity create");
	}
	
	
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

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
