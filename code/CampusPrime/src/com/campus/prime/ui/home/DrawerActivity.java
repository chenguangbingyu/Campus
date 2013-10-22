package com.campus.prime.ui.home;

import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.campus.prime.R;
import com.campus.prime.core.GroupItem;
import com.campus.prime.ui.BaseActivity;
import com.campus.prime.ui.user.GroupListViewAdapter;

public class DrawerActivity extends BaseActivity {

	protected DrawerLayout mDrawerLayout;
	protected ListView mDrawerList;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected CharSequence mDrawTitle;
	protected CharSequence mTitle;
	protected String[] mGroupsTitles;
	
	protected List<GroupItem> mGroups = null;
	GroupsListAdapter mGroupsListAdapter;
	
	protected HomeFragment mFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_activity2);
		
		initDrawer();
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if (savedInstanceState == null) {
            selectItem(0);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_activity2, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	private void initDrawer(){
		mTitle = mDrawTitle= getTitle();
		mGroupsTitles = getResources().getStringArray(R.array.groups_array);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (ListView)findViewById(R.id.left_drawer);
		
		 mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
		 /**
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mGroupsTitles));
        **/
		mGroupsListAdapter = new GroupsListAdapter(this,mGroups);
		mDrawerList.setAdapter(mGroupsListAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, 
				mDrawerLayout, 
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mDrawTitle);
				invalidateOptionsMenu();
			}
			
		};
		
		
	}
	
	
	protected void setGroups(List<GroupItem> groups){
		this.mGroups = groups;
		mGroupsListAdapter.setItems(groups);
	}
	
	public class DrawerItemClickListener implements ListView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			selectItem(arg2);
		}		
	}
	
	

	 /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	
	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	
	
	
	private void selectItem(int position) {
        // update the main content by replacing fragments
		mFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        if(mGroups != null)
        	setTitle(mGroups.get(position).getName());
        mDrawerLayout.closeDrawer(mDrawerList);
    }

	
}
