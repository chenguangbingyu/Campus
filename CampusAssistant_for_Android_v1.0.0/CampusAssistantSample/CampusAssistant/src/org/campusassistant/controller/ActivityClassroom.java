/**
 * 文件说明：网络课表中，课程详细信息中的上课地点的详细描述的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;

import java.util.ArrayList;

import org.campusassistant.massample.R;
import org.campusassistant.model.MClassroom;
import org.campusassistant.protocol.ProtocolClassroom;
import org.campusassistant.protocol.ProtocolClassroom.ProtocolClassroomDelegate;
import org.campusassistant.view.AdapterClassroom;

import Network.Network;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ExpandableListView;


public class ActivityClassroom extends Activity implements ProtocolClassroomDelegate {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取教室信息成功
	static final int MESSAGE_GETCLASSROOM_SUCCESS = 0;
	// 网络获取教室信息失败
	static final int MESSAGE_GETCLASSROOM_FAILED = 1;
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 需要展现的教室ID的列表
	ArrayList<String> arrayClassroomId;
	// 教室数据Model
	MClassroom classroomData;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现教室信息的可折叠ListView
	ExpandableListView elvClassroom;
	
	// 主线程消息处理器
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MESSAGE_GETCLASSROOM_SUCCESS:{
				// 获取教室信息成功
				// 处理界面刷新
				displayClassroomInfo();
			}
				break;
			case MESSAGE_GETCLASSROOM_FAILED:{
				// 获取教室信息失败
				// 提示用户错误信息，或者重试
			}
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	/**
	 * 显示教室的信息（文字信息）
	 */
	void displayClassroomInfo(){
		AdapterClassroom _adapter = new AdapterClassroom().setParent(this);
		_adapter.setCourseData(classroomData);
		
		// 示例代码
		elvClassroom.setAdapter(_adapter);
		_adapter.notifyDataSetChanged();
		elvClassroom.expandGroup(0);
		elvClassroom.expandGroup(1);
		elvClassroom.expandGroup(2);
		elvClassroom.expandGroup(3);
		elvClassroom.expandGroup(4);
		elvClassroom.setGroupIndicator(null);
	}
	
	/**
	 * 准备界面显示控件
	 */
	void findView() {
		// 挂接展现教室的ListView
		elvClassroom = (ExpandableListView)findViewById(R.id.elv_classroom);
	}
	
	/**
	 * 从网络请求教室详细信息
	 * @param arrayId 教室ID列表，当前课程可能在这些教室里上课
	 */
	void getClassroomInfoFromNetwork(ArrayList<String> arrayId){
		// 创建协议
		ProtocolClassroom _protocol = new ProtocolClassroom().setDelegate(this).setParent(this);
		_protocol.setStrClassroomId(arrayId);
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolClassroom.URL + ProtocolClassroom.CMD);
		_network.sendForTest(_protocol,ProtocolClassroom.CMD, Network.GET);
	}
	/**
	 * 
	 * @param arrayId
	 * @return
	 */
	boolean getClassroomInfoFromDB(ArrayList<String> arrayId){
		// 
		return false;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classroom);
		// 获取需要下载的教室ID列表
		Intent _intent = getIntent();
		arrayClassroomId = _intent.getStringArrayListExtra("classroom_id");
		// 关联显示控件
		findView();
		// 用这些ID去缓冲数据库中查找，如果存在，则直接从数据库中加载；
		// 如果不存在，则从网络下载
		if (getClassroomInfoFromDB(arrayClassroomId) == true){
			handler.sendEmptyMessage(MESSAGE_GETCLASSROOM_SUCCESS);
		} else {
			getClassroomInfoFromNetwork(arrayClassroomId);
		}
	}

	//////////////////////////////////////////////////////////
	// 代理区
	@Override
	public void getClassroomSuccess(MClassroom data) {
		// 网络请求教室信息成功回调
		classroomData = data;
		handler.sendEmptyMessage(MESSAGE_GETCLASSROOM_SUCCESS);
	}

	@Override
	public void getClassroomFailed() {
		// 网络请求教室信息失败回调
		classroomData = null;
		handler.sendEmptyMessage(MESSAGE_GETCLASSROOM_FAILED);
	}

}
