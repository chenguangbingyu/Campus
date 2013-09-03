package org.campusassistant.controller;

import org.campusassistant.massample.R;
import org.campusassistant.model.MNewsCategory;
import org.campusassistant.model.MNewsItem;
import org.campusassistant.protocol.ProtocolNewsCategory;
import org.campusassistant.protocol.ProtocolNewsList;
import org.campusassistant.protocol.ProtocolNewsCategory.ProtocolNewsCategoryDelegate;
import org.campusassistant.protocol.ProtocolNewsList.ProtocolNewsListDelegate;
import org.campusassistant.view.AdapterNewsCategory;
import org.campusassistant.view.AdapterNewsList;
import org.campusassistant.view.AdapterNewsCategory.TabChangedDelegate;


import Network.Network;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;

public class ActivityNews extends Activity implements ProtocolNewsCategoryDelegate, TabChangedDelegate, ProtocolNewsListDelegate, ImageToolsDelegate{

	TabHost thNewsCategory;
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取资讯新闻类别信息成功
	static final int MESSAGE_GETNEWSCATEGORY_SUCCESS = 0;
	// 网络获取资讯新闻类别信息失败
	static final int MESSAGE_GETNEWSCATEGORY_FAILED = 1;
	// 资讯新闻列表切换
	static final int MESSAGE_TAB_CHANGED = 2;
	// 网络获取资讯新闻列表信息成功
	static final int MESSAGE_GETNEWSLIST_SUCCESS = 3;
	// 网络获取资讯新闻列表信息失败
	static final int MESSAGE_GETNEWSLIST_FAILED = 4;
	// 网络获取资讯新闻列表图片成功
	static final int MESSAGE_DOWNLOAD_IMAGE_SUCCESS = 5;
	// 网络获取资讯新闻列表图片失败
	static final int MESSAGE_DOWNLOAD_IMAGE_FAILED = 6;
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 需要展现的资讯新闻类别的列表
	SparseArray<MNewsCategory> arrayCategorys;
	// 需要展现的资讯新闻的列表
	SparseArray<MNewsItem> arrayNews;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现资讯新闻类别的GridView
	GridView gvCategory;
	// 展现资讯新闻的ListView
	ListView lvNewsList;
	// 展现资讯新闻类别数据的AdapterNewsCategory
	AdapterNewsCategory adapterCategory;
	// 展现资讯新闻数据的AdapterNewsList
	AdapterNewsList adapterNewsList;
	
	// 选中的Tab键id
	int currentIndex=0;
	/**
	 * 消息处理，主线程
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MESSAGE_GETNEWSCATEGORY_SUCCESS: {
				// 获取资讯新闻类别信息成功
				// 处理界面刷新
				displayCategory();
				// 获取选中tab的id对应的MNewsCategory对象
				MNewsCategory _categoryInfo = arrayCategorys.get(currentIndex);
				// 从网络获取与选中tab的资讯类别对应的资讯新闻数据列表
				getNewsListFromNetwork(_categoryInfo.getStrID());
			}
				break;
			case MESSAGE_GETNEWSCATEGORY_FAILED:
				break;
			case MESSAGE_TAB_CHANGED:{
				// 切换Tab键
				adapterCategory.setCurrentSelected(currentIndex);
				adapterCategory.notifyDataSetChanged();
				// 获取选中tab的id对应的MNewsCategory对象
				MNewsCategory _categoryInfo = arrayCategorys.get(currentIndex);
				// 从网络获取与选中tab的资讯类别对应的资讯新闻数据列表
				getNewsListFromNetwork(_categoryInfo.getStrID());
			}
				break;
			case MESSAGE_GETNEWSLIST_SUCCESS:{
				displayList();
			}
				break;
			case MESSAGE_GETNEWSLIST_FAILED:{
				
			}
				break;
			case MESSAGE_DOWNLOAD_IMAGE_SUCCESS:{
				// 图片下载成功，刷新界面
				adapterNewsList.notifyDataSetChanged();
			}
				break;
			case MESSAGE_DOWNLOAD_IMAGE_FAILED:{
				
			}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};
	/**
	 * 准备界面显示控件
	 */
	void findView(){
		gvCategory = (GridView) findViewById(R.id.gv_news_category);
		lvNewsList = (ListView) findViewById(R.id.lv_news_list);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		findView();
		getNewsCategotyFromNetwork();
	}
	/**
	 * 显示资讯类别的信息（文字信息）
	 */
	void displayCategory(){
		adapterCategory = new AdapterNewsCategory().setParent(this);
		adapterCategory.setDataSource(arrayCategorys);
		adapterCategory.setSelectedDelegate(this);
		
		gvCategory.setAdapter(adapterCategory);
		adapterCategory.notifyDataSetChanged();
	}
	/**
	 * 显示资讯类别对应的新闻列表信息（文字信息）
	 */
	void displayList(){
		adapterNewsList = new AdapterNewsList().setParent(this).setImageDelegate(this);
		adapterNewsList.setDataSource(arrayNews);
		
		lvNewsList.setAdapter(adapterNewsList);
		adapterNewsList.notifyDataSetChanged();
	}
	
	/**
	 * 从网络抓取资讯类别信息
	 */
	void getNewsCategotyFromNetwork() {
		ProtocolNewsCategory _protocol = new ProtocolNewsCategory().setDelegate(this).setContext(this.getApplicationContext());
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolNewsCategory.URL + ProtocolNewsCategory.COMMAND);
		_network.sendForTest(_protocol,ProtocolNewsCategory.COMMAND, Network.GET);
	}
	/**
	 * 从网络抓取资讯新闻列表
	 */
	void getNewsListFromNetwork(String categoryId){
		ProtocolNewsList _protocol = new ProtocolNewsList().setDelegate(this).setContext(this.getApplicationContext());
		_protocol.setCategoryId(categoryId);
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolNewsList.URL + ProtocolNewsList.COMMAND);
		_network.sendForTest(_protocol,_protocol.packageProtocol(), Network.GET);
	}
	
	
	@Override
	public void getNewsCategorySuccess(SparseArray<MNewsCategory> data) {
		// TODO Auto-generated method stub
		arrayCategorys = data;
		handler.sendEmptyMessage(MESSAGE_GETNEWSCATEGORY_SUCCESS);
	}

	@Override
	public void getNewsCategoryFailed() {
		// TODO Auto-generated method stub

		handler.sendEmptyMessage(MESSAGE_GETNEWSCATEGORY_FAILED);
	}

	@Override
	public void onTabChanged(int selected) {
		// TODO Auto-generated method stub
		currentIndex = selected;
		handler.sendEmptyMessage(MESSAGE_TAB_CHANGED);
	}

	@Override
	public void getNewsListSuccess(SparseArray<MNewsItem> datas) {
		// TODO Auto-generated method stub
		arrayNews = datas;
		handler.sendEmptyMessage(MESSAGE_GETNEWSLIST_SUCCESS);
	}

	@Override
	public void getNewsListFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETNEWSLIST_FAILED);
	}

	@Override
	public void downloadImageSuccess() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_DOWNLOAD_IMAGE_SUCCESS);
	}

	@Override
	public void downlaodImageFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_DOWNLOAD_IMAGE_FAILED);
	}
}
