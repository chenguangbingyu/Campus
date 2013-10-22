package com.campus.prime.ui.home;

import static com.campus.prime.constant.AppConstant.SETTING_INFOS;
import static com.campus.prime.constant.AppConstant.TOKEN;
import static com.campus.prime.constant.AppConstant.USERNAME;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.core.GroupItem;
import com.campus.prime.core.GroupPage;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.AsyncLoader;
import com.campus.prime.ui.BaseSlidingActivity;
import com.campus.prime.ui.slidingmenu.SlidingMenu;
import com.campus.prime.ui.user.UserActivity;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.IntentUtil;
import com.campus.prime.utils.LogFactory;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.sso.SsoHandler;
import com.weibo.sdk.android.util.AccessTokenKeeper;


/**
 * MainActivity
 * @author absurd
 *
 */

public class HomeActivity extends DrawerActivity
		implements LoaderCallbacks<List<GroupItem>>{
	
	
	/**
	 * log
	 */
	private CommonLog mLog = LogFactory.createLog();
	
	/**
	 * Adapter
	 */
	private HomeDropdownListAdapter mHomeDropDownListAdapter;
	private GroupsDropDownListAdapter mGroupsDownListAdapter;
	private SlidingMenu mSlidingMenu;
	
	
	/**
	 * UserService
	 */
	
	private Button mWeiboButton;
	private TextView mAuthInfoTextView;
	private Button mLogginButton;
	private Button mRegisterButton;
	private Button mUserProfileButton;
	
	/**
	 * current showed Group
	 * -1 refer to public
	 */
	private int mCurrentGroup = -1;
	
		
	
	/**
	 * weibo access token
	 */
	private Oauth2AccessToken mAccessToken;
	private SsoHandler mSsoHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/**
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(false);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		mHomeFragment= new HomeFragment();
		ft.add(R.id.container,mHomeFragment);
		ft.commit();
	**/
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getSupportActionBar();
		configureActionBar(actionBar);
		//initSlidingMenu();
		
		//initView();
		getInfos();
		
		/*
		// get accessToken from sharedPreference
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            String date = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
                    .format(new java.util.Date(mAccessToken.getExpiresTime()));
            mAuthInfoTextView.setText("access_token ������Ч����,�����ٴε�¼: \naccess_token:"
                    + mAccessToken.getToken() + "\n��Ч�ڣ�" + date);
        } else {
            mAuthInfoTextView.setText("ʹ��SSO��¼ǰ�������ֻ����Ƿ��Ѿ���װ����΢���ͻ��ˣ�"
                    + "Ŀǰ��3.0.0������΢���ͻ��˰汾֧��SSO�����δ��װ�����Զ�תΪOauth2.0������֤");
        }
		
		*/
	}
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(Auth.isAuth()){
			getSupportLoaderManager().initLoader(0, null, this);
		}
	}
	
	private void getInfos(){
		if(!Auth.isAuth()){
			SharedPreferences infos = getSharedPreferences(SETTING_INFOS, 0);
			if(infos == null)
				return;
			Auth.token = infos.getString(TOKEN,null);
			Auth.username = infos.getString(USERNAME,null);
			mLog.i("on start app get user infos " + Auth.token);
			mLog.i("on start app get user infos " + Auth.username);
		}
	}
	
	
	/**
	 * init control view
	 */
	/**
	private void initView(){
		mWeiboButton = (Button)findViewById(R.id.weibo_auth_btn);
		mWeiboButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Weibo weibo = Weibo.getInstance(AppConstant.APP_KEY, AppConstant.REDIRECT_URL, AppConstant.SCOPE);
				//weibo.anthorize(HomeActivity.this, new AuthDialogListener());
				mSsoHandler = new SsoHandler(HomeActivity.this,weibo);
				mSsoHandler.authorize(new AuthDialogListener(),null);
				Toast.makeText(getApplicationContext(), "weibo_auth", Toast.LENGTH_SHORT).show();
			}
		});
		
		mLogginButton = (Button)findViewById(R.id.login);
		mLogginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
		
		mRegisterButton = (Button)findViewById(R.id.register);
		mRegisterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,RegisterActivity.class);
				startActivity(intent);
				
			}
		});
		
		mUserProfileButton = (Button)findViewById(R.id.user_profile);
		mUserProfileButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentUtil.start_activity(HomeActivity.this, UserActivity.class, (Map<String,Integer>)null);
			}
		});
		
		mAuthInfoTextView = (TextView)findViewById(R.id.auth_info);
	}
	**/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_list_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_edit:
			break;
		case R.id.action_refresh:
			mFragment.refresh();
			break;
		default:
			super.onOptionsItemSelected(item);
		}
		return true;
	}
	/**
	 * configure ActionBar
	 * @param actionBar
	 */
	private void configureActionBar(ActionBar actionBar){
		/**
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mGroupsDownListAdapter = new GroupsDropDownListAdapter(this);
		actionBar.setListNavigationCallbacks(mGroupsDownListAdapter, new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int arg0, long arg1) {
				// TODO Auto-generated method stub
				mCurrentGroup = mGroupsDownListAdapter.getGroupId(arg0);
				mHomeFragment.setCurrentGroup(mCurrentGroup);
				if(mStart&&arg0 != 0)
					mStart = false;
				if(!mStart)
					mHomeFragment.refresh();
				return true;
			}
		});
		**/
	}
	
	
	
	
	/**
	 * init sliding menu
	 */
	/**
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
	**/



	@Override
	public Loader<List<GroupItem>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new AsyncLoader<List<GroupItem>>(HomeActivity.this) {
			@Override
			protected List<GroupItem> loadData() {
				// TODO Auto-generated method stub
				GroupService service = new GroupService();
				GroupPage page = null;
				page = service.getGroupsByUsername(Auth.username);
				if(page == null)
					return null;
				mLog.i(page.toString());
				List<GroupItem> results = page.getResults();
				return results;
			}
		};
	}

	@Override
	public void onLoadFinished(Loader<List<GroupItem>> arg0, List<GroupItem> arg1) {
		// TODO Auto-generated method stub
		//mGroupsDownListAdapter.setOrgs(arg1);
		setGroups(arg1);
	}

	@Override
	public void onLoaderReset(Loader<List<GroupItem>> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
		
	/**
	 * weibo authentication callback class
	 * @author absurd
	 *
	 */
	class AuthDialogListener implements WeiboAuthListener{

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			 Toast.makeText(getApplicationContext(), "Auth cancel", Toast.LENGTH_LONG).show();
		}

		@SuppressLint("SimpleDateFormat")
		@Override
		public void onComplete(Bundle arg0) {
			// TODO Auto-generated method stub
			String token = arg0.getString("access_token");
			String expires_in = arg0.getString("expires_in");
			mAccessToken = new Oauth2AccessToken(token, expires_in);
			if(mAccessToken.isSessionValid()){
				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(new java.util.Date(mAccessToken.getExpiresTime()));
				mAuthInfoTextView.setText("success:  \r\naccess_token: " + token + 
						"\r\nexpire_in" + expires_in + "\r\ndate" + date);
				// write accesskey to sharedPreferences
				AccessTokenKeeper.keepAccessToken(HomeActivity.this,mAccessToken);
				Toast.makeText(HomeActivity.this, "auth successful", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onError(WeiboDialogError arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), 
                    "Auth error : " + arg0.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), 
                    "Auth exception : " + arg0.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	

}
