package org.campusassistant.controller;

import org.campusassistant.massample.R;
import org.campusassistant.model.MBuilding;
import org.campusassistant.protocol.ProtocolBuilding;
import org.campusassistant.protocol.ProtocolBuilding.ProtocolBuildingDelegate;
import org.campusassistant.view.AdapterCampusBuilding;



import Network.Network;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.ListView;

public class ActivityCampusBuilding extends Activity implements
		ProtocolBuildingDelegate {

	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取校区建筑成功
	static final int MESSAGE_GETCAMPUSBUILDING_SUCCESS = 0;
	// 网络获取校区建筑失败
	static final int MESSAGE_GETCAMPUSBUILDING_FAILED = 1;
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现校区建筑信息的ListView
	ListView lvCampusBuilding;
	
	/**
	 * 消息处理，主线程
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MESSAGE_GETCAMPUSBUILDING_SUCCESS: {
				// 获取小区建筑信息成功
				// 处理界面刷新
				campusBuildingInit();
			}
				break;
			case MESSAGE_GETCAMPUSBUILDING_FAILED:
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_campus_building);

		findView();
		getCampusBuildingFromNetwork();
	}
	
	/**
	 * 从网络抓取信息
	 */
	void getCampusBuildingFromNetwork() {
		ProtocolBuilding _protocol = new ProtocolBuilding().setDelegate(
				this).setContext(this.getApplicationContext());
		Network _network = new Network();
		_network.setURL(ProtocolBuilding.URL + ProtocolBuilding.COMMAND);

		// 正式代码
//		_network.send(_protocol, Network.GET);
		// 无server的情况下，测试用
		_network.sendForTest(_protocol,ProtocolBuilding.COMMAND , Network.GET);
	}
	/**
	 * 准备界面显示控件
	 */
	void findView(){
		
		lvCampusBuilding = (ListView) findViewById(R.id.lv_campus_building);
	}
	/**
	 * 显示校区建筑的信息（文字信息）
	 */
	void campusBuildingInit(){
		Intent _intent = getIntent();
		String _campus_id = _intent.getStringExtra("campus_id");

		MBuilding _mBuilding = new MBuilding().setContext(getApplicationContext());
		
		AdapterCampusBuilding _adapterCampusBuilding = new AdapterCampusBuilding().setParent(this);
		_adapterCampusBuilding.setCampusData(_mBuilding.readFromDB(_campus_id));
		lvCampusBuilding.setAdapter(_adapterCampusBuilding);
	}
	
	
	@Override
	public void getBuildingSuccess(SparseArray<MBuilding> date) {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETCAMPUSBUILDING_SUCCESS);
	}

	@Override
	public void getBuildingFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETCAMPUSBUILDING_FAILED);
	}
}
