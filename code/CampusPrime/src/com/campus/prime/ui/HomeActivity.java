package com.campus.prime.ui;

import Database.DAOHelper;
import Network.Network;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.campus.prime.R;
import com.campus.prime.adapter.HomeDropdownListAdapter;
import com.campus.prime.adapter.MessageListViewAdapter;
import com.campus.prime.bean.MessagesList;
import com.campus.prime.bean.User;
import com.campus.prime.constant.AppConstant;
import com.campus.prime.database.MessageDB;
import com.campus.prime.protocol.MessageProtocol;
import com.campus.prime.protocol.ProtocolDelegate;
import com.campus.prime.slidingmenu.SlidingMenu;


/**
 * 主页面  
 * @author absurd
 *
 */
public class HomeActivity extends BaseSlidingActivity implements 
		ProtocolDelegate<MessagesList>, ImageToolsDelegate{
	
		
	public static final String ARGUMENTS_NAME = "args";
	private static final int FLAG_CREATE_MESSAGE = 0;
	//网络获取消息成功
	private static final int MESSAGE_GETMESSAGE_SUCCESS = 0;
	//网络获取消息失败
	private static final int MESSAGE_GETMESSAGE_FAILED = 1;
	
	private HomeDropdownListAdapter mHomeDropDownListAdapter;
	private SlidingMenu mSlidingMenu;
		
	private Context context;
	private MessageListViewAdapter mAdapter;
	//需要在listView中显示的data
	private MessagesList mData;
	private ListView mListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		
		mListView = (ListView)findViewById(R.id.listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(context,MessageDetailActivity.class);
				startActivity(intent);
			}
		});
		
		final ActionBar actionBar = getSupportActionBar();
		configureActionBar(actionBar);
		initSlidingMenu();
		//初始化网络测试信息 
		initSimulateServer();
		//初始化数据库
		initDB();
		getMessagesFromNetwork();
		Log.d(AppConstant.DEBUG_TAG,"acticvity create");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_list_actions, menu);
		return true;
	}

	/**
	 * 处理从网络接受消息线程发送的message
	 */
	Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case MESSAGE_GETMESSAGE_SUCCESS:
				//将接受到的信息绑定到listView
				mAdapter = new MessageListViewAdapter(context, mData.getResults(), R.layout.messages_listitem);
				mListView.setAdapter(mAdapter);
				break;
			case MESSAGE_GETMESSAGE_FAILED:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	
	

	private void initSimulateServer(){
		Network network = new Network();
		//network.addTestResponse("getMessages", AppConstant.DEBUG_PROTOCOL_MESSAGES);
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
	
	
	
	/**
	 * 从网络获取消息 
	 */
	private void getMessagesFromNetwork(){
		//创建对应的model的protocol实例
		MessageProtocol protocol = new MessageProtocol().setDelegate(this)
				.setContext(this);
		Network network = new Network();
		network.setURL(MessageProtocol.URL + MessageProtocol.COMMAND);
		//发送http请求
		network.send(protocol, Network.GET);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.action_edit:
				// 转到edit_fragment
				Intent intent = new Intent();
				intent.setClass(this, CreateMessageActivity.class);
				startActivityForResult(intent, FLAG_CREATE_MESSAGE);
				return true;
			case R.id.action_refresh:
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == FLAG_CREATE_MESSAGE){
			return;
		}
	}
	
	@Override
	public void getMessageSuccess(MessagesList messages) {
		// TODO Auto-generated method stub
		if(messages != null){
			Log.d(AppConstant.DEBUG_TAG,"get message success" + messages.getResults().toString());
		}
		mData = messages;
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_SUCCESS);
	}

	@Override
	public void getMessageFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETMESSAGE_FAILED);
		Log.d(AppConstant.DEBUG_TAG,"get messages failed");
	}


	@Override
	public void downlaodImageFailed() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void downloadImageSuccess() {
		// TODO Auto-generated method stub
		
	}
	
	
}
