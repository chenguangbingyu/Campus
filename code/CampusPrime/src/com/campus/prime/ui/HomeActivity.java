package com.campus.prime.ui;

import Database.DAOHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.util.Log;
import android.view.Menu;

import com.campus.prime.R;
import com.campus.prime.adapter.HomeDropdownListAdapter;
import com.campus.prime.bean.User;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.database.MessageDB;
import com.campus.prime.slidingmenu.SlidingMenu;
import com.campus.prime.ui.fragment.HomeFragment;


/**
 * Ö÷Ò³Ãæ  
 * @author absurd
 *
 */

public class HomeActivity extends BaseSlidingActivity {
	
	private HomeDropdownListAdapter mHomeDropDownListAdapter;
	private SlidingMenu mSlidingMenu;
		
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment f = new HomeFragment();
		ft.add(R.id.container,f);
		ft.commit();
		
		
		final ActionBar actionBar = getSupportActionBar();
		configureActionBar(actionBar);
		initSlidingMenu();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_list_actions, menu);
		return true;
	}

	private void initDB(){
		DAOHelper.createInstance(this.getApplicationContext(), AppConstant.DB_NAME, AppConstant.DB_VERSION);
		DAOHelper dbHelper = DAOHelper.getInstance();
		if(dbHelper != null){
			dbHelper.addDBTable(MessageDB.TABLE,new MessageDB());
			Log.d(AppConstant.TAG,"add a table");
		}
	}
	
	
	/**
	 * configure ActionBar
	 * @param actionBar
	 */
	private void configureActionBar(ActionBar actionBar){
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		mHomeDropDownListAdapter = new HomeDropdownListAdapter(this, new User());
		actionBar.setListNavigationCallbacks(mHomeDropDownListAdapter, new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int arg0, long arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				switch(arg0){
				case 1:
					intent.setClass(context, UserProfileActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent.setClass(context, GroupDetailActivity.class);
					startActivity(intent);
					break;
				default:
					return false;
				}
				return true;
			}
		});
		
	}
	
	/**
	 * init sliding menu
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
	
	
}
