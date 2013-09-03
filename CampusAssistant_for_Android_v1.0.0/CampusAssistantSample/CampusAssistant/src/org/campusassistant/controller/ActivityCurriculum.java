/**
 * 文件说明：网络课表的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;

import org.campusassistant.massample.R;
import org.campusassistant.model.MCurriculum;
import org.campusassistant.protocol.ProtocolCurriculum;
import org.campusassistant.protocol.ProtocolCurriculum.ProtocolCurriculumDelegate;
import org.campusassistant.view.AdapterCurriculum;


import Network.Network;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.GridView;

public class ActivityCurriculum extends Activity implements
		ProtocolCurriculumDelegate {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取课程表信息成功
	static final int MESSAGE_GETCURRICULUM_SUCCESS = 0;
	// 网络获取课程表信息失败
	static final int MESSAGE_GETCURRICULUM_FAILED = 1;
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 需要展现的课程表数据
	MCurriculum curriculumData;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	//展现课程表信息的网格TextView
	GridView gvCurriculum;

	// 主线程消息处理器
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MESSAGE_GETCURRICULUM_SUCCESS: {
				// 获取课程表信息成功
				// 处理界面刷新
				// 实行显示的功能，把数据显示到view上
				displayCurriculum(curriculumData);
			}
				break;
			case MESSAGE_GETCURRICULUM_FAILED:
				// 获取课程表信息失败
				// 提示用户错误信息，或者重试
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};


	/**
	 * 从网络请求课程表信息
	 */
	void getCurriculumFromNetwork() {
		// 创建协议
		ProtocolCurriculum _protocol = new ProtocolCurriculum().setDelegate(
				this).setContext(this.getApplicationContext());
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolCurriculum.URL + ProtocolCurriculum.COMMAND);

		// 正式代码
//		_network.send(_protocol, Network.GET);
		// 无server的情况下，测试用
		_network.sendForTest(_protocol,ProtocolCurriculum.COMMAND, Network.GET);
	}
	
	/**
	 * 准备界面显示控件
	 */
	void findView() {
		// 挂接展现课程表的TextView
		gvCurriculum = (GridView)findViewById(R.id.gv_curriculum);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_curriculum);
		// 关联显示控件
		findView();
		//从网络下载课程表
		this.getCurriculumFromNetwork();
	}

	/**
	 * 显示课程表内容
	 * @param course
	 */
	void displayCurriculum(MCurriculum curriculum) {
		// 读取数据
		MCurriculum _curriculum =new MCurriculum().setContext(getApplicationContext());
		_curriculum.readFromDB();
		// 显示课程表内容
		AdapterCurriculum adapterCurriculum = new AdapterCurriculum().setParent(this);
		adapterCurriculum.setCurriculumData(_curriculum);
		
		gvCurriculum.setAdapter(adapterCurriculum);
		adapterCurriculum.notifyDataSetChanged();
	}
	
	//////////////////////////////////////////////////////////
	// 代理区
	@Override
	public void getCurriculumFailed() {
		// TODO Auto-generated method stub
		// 网络请求课程表信息成功回调
		handler.sendEmptyMessage(MESSAGE_GETCURRICULUM_FAILED);
	}

	@Override
	public void getCurriculumSuccess(MCurriculum curriculum) {
		// TODO Auto-generated method stub
		// 网络请求课程表信息失败回调
		handler.sendEmptyMessage(MESSAGE_GETCURRICULUM_SUCCESS);
	}
}
