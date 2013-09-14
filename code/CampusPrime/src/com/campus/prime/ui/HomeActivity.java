package com.campus.prime.ui;

import Database.DAOHelper;
import Network.Network;
import android.graphics.drawable.ColorDrawable;
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
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.campus.prime.adapter.HomeDropdownListAdapter;
import com.campus.prime.adapter.TabFragmentPagerAdapter;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.database.MessageDB;
import com.campus.prime.model.User;
import com.campus.prime.slidingmenu.SlidingActivityBase;
import com.campus.prime.slidingmenu.SlidingActivityHelper;
import com.campus.prime.slidingmenu.SlidingMenu;
import com.campus.prime.R;

public class HomeActivity extends BaseSlidingActivity implements TabListener,OnNavigationListener{
	
	
	
	
	
	public static final int MAX_TAB_SIZE = 1;
	public static final String ARGUMENTS_NAME = "args";
	
	private ViewPager mViewPager;
	private TabFragmentPagerAdapter mAdapter;
	
	private HomeDropdownListAdapter mHomeDropDownListAdapter;
	
	private SlidingMenu mSlidingMenu;
	
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
		
		
		final ActionBar actionBar = getSupportActionBar();
		configureActionBar(actionBar);
		
		initTabFragmentPager(actionBar);
		initSlidingMenu();
		
		//初始化网络测试信息 
		initSimulateServer();
		//初始化数据库
		initDB();
		Log.d(AppConstant.DEBUG_TAG,"acticvity create");
	}
	
	/**
	 * 设置actionBar
	 */
	private void configureActionBar(ActionBar actionBar){
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		//隐藏图标  it's a fuck!
		actionBar.setIcon(null);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		mHomeDropDownListAdapter = new HomeDropdownListAdapter(this, new User());
		//SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.test, android.R.layout.simple_spinner_dropdown_item);
		actionBar.setListNavigationCallbacks(mHomeDropDownListAdapter, new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int arg0, long arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
	}
	
	
	
	
	
	/**
	 * 初始化TabFragmentPage
	 */
	private void initTabFragmentPager(final ActionBar actionBar){
		mViewPager = (ViewPager)this.findViewById(R.id.pager);
		
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
		
		for(int i = 0;i < 1; i++){
			actionBar.addTab(
					actionBar.newTab()
					.setText("Tab" + (i + 1))
					.setTabListener(this));
		}
	}
	
	/**
	 * 初始化slidingmenu
	 */
	private void initSlidingMenu() {
        setBehindContentView(R.layout.behind_slidingmenu);
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // slidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
        //slidingMenu.setShadowWidth(20);
        mSlidingMenu.setBehindScrollScale(0);
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
