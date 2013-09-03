/**
 * 文件说明：网络课表中，描述课程详细信息中的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;

import java.util.ArrayList;

import org.campusassistant.massample.R;
import org.campusassistant.model.MCourse;
import org.campusassistant.protocol.ProtocolCourse;
import org.campusassistant.protocol.ProtocolCourse.ProtocolCourseDelegate;
import org.campusassistant.view.AdapterCourse;

import Network.Network;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ExpandableListView;


public class ActivityCourse extends Activity implements ProtocolCourseDelegate {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取课程信息成功
	static final int MESSAGE_GETCOURSE_SUCCESS = 0;
	// 网络获取课程信息失败
	static final int MESSAGE_GETCOURSE_FAILED = 1;
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 需要展现的课程ID的列表
	ArrayList<String> arrayCourseId;
	//需要展现的课程信息数据
	ArrayList<MCourse> arrayCourseData;
	//星期几、第几节课，用来确定课程在课程表中的位置
	int intSelectWeekday, intSelectSection;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现课程信息的可折叠ListView
	ExpandableListView elvCourse;
	
	// 主线程消息处理器
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case MESSAGE_GETCOURSE_SUCCESS:{
				// 获取课程信息成功
				// 处理界面刷新
				displayCourseInfo();
			}
				break;
			case MESSAGE_GETCOURSE_FAILED:
				// 获取课程信息失败
				// 提示用户错误信息，或者重试
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	/**
	 * 显示课程的信息（文字信息）
	 */
	void displayCourseInfo(){
		AdapterCourse _adapter = new AdapterCourse().setParent(this);
		_adapter.setCourseData(arrayCourseData);
		_adapter.setCourseWeekdayAndSection(intSelectWeekday, intSelectSection);
		
		elvCourse.setAdapter(_adapter);
		_adapter.notifyDataSetChanged();
		
		int _i = 0;
		while(_i<arrayCourseData.size()){
			elvCourse.expandGroup(_i);
			_i++;
		}
		elvCourse.setGroupIndicator(null);
	}
	
	/**
	 * 准备界面显示控件
	 */
	void findView() {
		// TODO Auto-generated method stub
		// 挂接展现课程的ListView
		elvCourse = (ExpandableListView)findViewById(R.id.elv_course);
	}
	
	/**
	 * 从网络请求课程详细信息
	 * @param strId 课程ID列表，当前课时可能有这些课程
	 */
	void getCourseInfoFromNetwork(ArrayList<String> strId){
		// 创建协议
		ProtocolCourse _protocol = new ProtocolCourse().setDelegate(this).setParent(this);
		// 通过网络请求
		Network _network = new Network();
		_network.setURL(ProtocolCourse.URL + ProtocolCourse.COMMAND);
		_network.sendForTest(_protocol,ProtocolCourse.COMMAND, Network.GET);
	}
	
	/**
	 * 
	 * @param strId
	 * @return
	 */
	boolean getCourseInfoFromDB(String strId){
		return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_info);
		// 获取需要下载的课程ID列表
		Intent _intent = getIntent();
		arrayCourseId = _intent.getStringArrayListExtra("course_id");
		intSelectWeekday = _intent.getIntExtra("weekday", -1);
		intSelectSection = _intent.getIntExtra("section", -1);

		// 关联显示控件
		findView();
		// 用这些ID去缓冲数据库中查找，如果存在，则直接从数据库中加载；
		// 如果不存在，则从网络下载
//		if (this.getCourseInfoFromDB(courseId) == false){
			getCourseInfoFromNetwork(arrayCourseId);
//		} else {
//			handler.sendEmptyMessage(MESSAGE_GETCOURSE_SUCCESS);
//		}
	}

	//////////////////////////////////////////////////////////
	// 代理区
	@Override
	public void getCourseSuccess(ArrayList<MCourse> data) {
		// TODO Auto-generated method stub
		// 网络请求课程信息成功回调
		arrayCourseData = data;
		handler.sendEmptyMessage(MESSAGE_GETCOURSE_SUCCESS);
	}

	@Override
	public void getCourseFailed() {
		// TODO Auto-generated method stub
		// 网络请求课程信息失败回调
		arrayCourseData = null;
		handler.sendEmptyMessage(MESSAGE_GETCOURSE_FAILED);
	}
}
