package org.campusassistant.controller;

import org.campusassistant.massample.R;
import org.campusassistant.model.MCampus;
import org.campusassistant.protocol.ProtocolCampus;
import org.campusassistant.protocol.ProtocolCampus.ProtocolCampusDelegate;
import org.campusassistant.view.AdapterCampus;

import Network.Network;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;


public class ActivityManual extends Activity implements ProtocolCampusDelegate{
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取校区信息成功	
	static final int MESSAGE_GETCAMPUS_SUCCESS = 0;
	// 网络获取校区信息失败
	static final int MESSAGE_GETCAMPUS_FAILED = 1;
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现教室信息的ListView
	ListView lvHandBookCampus;
	/**
	 * 消息处理，主线程
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MESSAGE_GETCAMPUS_SUCCESS: {
				// 获取校区信息成功
				// 处理界面刷新
				campusDataInit();
			}
				break;
			case MESSAGE_GETCAMPUS_FAILED:
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
        setContentView(R.layout.activity_handbook);
        
        findView();
    
        getCampusFromNetwork();
    }
	
	/**
	 * 从网络抓取信息
	 */
	void getCampusFromNetwork() {
		ProtocolCampus _protocol = new ProtocolCampus().setDelegate(
				this).setContext(this.getApplicationContext());
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolCampus.URL + ProtocolCampus.COMMAND);
		_network.sendForTest(_protocol,ProtocolCampus.COMMAND, Network.GET);
	}
	/**
	 * 显示校区的信息（文字信息）
	 */
	public void campusDataInit() {
		
		MCampus _mCampus = new MCampus()
				.setContext(getApplicationContext());
		ListView _lvCampusList;
		_lvCampusList = (ListView) findViewById(R.id.lv_handbook_campus);

		AdapterCampus adapterCampusList = new AdapterCampus()
				.setParent(this);
		adapterCampusList.setCampusData(_mCampus.readFromDB());
		_lvCampusList.setAdapter(adapterCampusList);
	}
	/**
	 * 准备界面显示控件
	 */
	void findView(){
		lvHandBookCampus = (ListView)findViewById(R.id.lv_handbook_campus);
	}

	@Override
	public void getCampusSuccess(MCampus mHangbook) {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETCAMPUS_SUCCESS);
	}

	@Override
	public void getCampusFailed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(MESSAGE_GETCAMPUS_FAILED);
	}
}
