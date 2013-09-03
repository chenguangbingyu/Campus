/**
 * 教室详细信息的协议部分。
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import java.util.ArrayList;

import org.campusassistant.model.MClassroom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import Log.MyLog;
import Protocol.ProtocolBase;
import android.app.Activity;

public class ProtocolClassroom extends ProtocolBase {
	
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com";
	// 网络获取数据的指令，与url组合使用
	public static final String CMD = "getcalssroom";
	
	//////////////////////////////////////////////////////////
	//数据源部分
	ArrayList<String> arrayClassroomId;
	
	//传递数据的Activity
	Activity activity;
	
	/**
	 * 使用ProtocolClassroomDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolClassroomDelegate{
		public void getClassroomSuccess(MClassroom data);
		public void getClassroomFailed();
	}
	//创建ProtocolClassroomDelegate对象
	ProtocolClassroomDelegate delegate;
	

	public void setStrClassroomId(ArrayList<String> arrayClassroomId) {
		this.arrayClassroomId = arrayClassroomId;
	}

	public ProtocolClassroom setDelegate(ProtocolClassroomDelegate delegate){
		this.delegate = delegate;
		return this;
	}
	
	public ProtocolClassroom setParent(Activity parent){
		this.activity = parent;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "getcalssroom";
	}

	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		
		String _strClassroomId, _strClassroomName, _strAddress;
		String _strLongitude, _strLatitude;
		String _strOpen_infor="";
		String _strStart_time="", _strEnd_time="";
		

		JSONTokener jtParser = new JSONTokener(strResponse);
		try {

			JSONObject _joRoot = (JSONObject) jtParser.nextValue();

			JSONObject _joResponse = _joRoot.getJSONObject("response");
			_strClassroomId = _joResponse.getString("id");
			_strClassroomName = _joResponse.getString("name");
			_strAddress = _joResponse.getString("address");

			JSONObject _joPosition = _joResponse.getJSONObject("position");

			_strLongitude = _joPosition.getString("longitude");
			_strLatitude = _joPosition.getString("latitude");

			JSONArray _jaOpen_info = _joResponse.getJSONArray("open_info");
			
			for (int i=0;i<_jaOpen_info.length();++i){

				JSONObject _joOpen_info = _jaOpen_info.getJSONObject(i);
				String _strWeekday = _joOpen_info.getString("weekday");
				String _strStatus = _joOpen_info.getString("status");
				if(_joOpen_info.has("start_time"))
				{
					_strStart_time = _joOpen_info.getString("start_time");
				
				}
				else
				{
					_strStart_time = " ";
				}
				if(_joOpen_info.has("end_time"))
				{
					_strEnd_time = _joOpen_info.getString("end_time");
				
				}
				else
				{
					_strEnd_time = " ";
				}
				
				if (i==0){
					_strOpen_infor = _strWeekday + ":" + _strStatus + ":"+ _strStart_time + ":" + _strEnd_time;
				} else {
					_strOpen_infor += ";" + _strWeekday + ":" + _strStatus + ":"+ _strStart_time + ":" + _strEnd_time;
				}
			}
			
			MyLog.e("OPEN_INFOR", _strOpen_infor);
			//实例化MClassroom对象
			MClassroom _classroomData = new MClassroom().setContext(activity.getApplicationContext());

			_classroomData.setStrID(_strClassroomId);
			_classroomData.setStrName(_strClassroomName);
			_classroomData.setStrAddress(_strAddress);
			_classroomData.setdLatitude(_strLatitude);
			_classroomData.setdLongitude(_strLongitude);
			_classroomData.setStrOpen_infor(_strOpen_infor);
			
			//提示数据获取及数据解析成功
			delegate.getClassroomSuccess(_classroomData);
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
