/**
 * 文件说明：课程详细信息的协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import java.util.ArrayList;

import org.campusassistant.model.MCourse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Log.MyLog;
import Protocol.ProtocolBase;
import android.app.Activity;


public class ProtocolCourse extends ProtocolBase {

	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetCourse";
	
	//传递数据的Activity
	Activity activity;
	
	/**
	 * 使用ProtocolCourseDelegate观察获取和解析数据是否成功
	 * @author Administrator
	 *
	 */
	public interface ProtocolCourseDelegate {
		void getCourseSuccess(ArrayList<MCourse> _courseData);

		void getCourseFailed();
	}
	
	//创建ProtocolCourseDelegate对象
	ProtocolCourseDelegate delegate;
	
	public ProtocolCourse setDelegate(ProtocolCourseDelegate delegate){
		this.delegate = delegate;
		return this;
	}
	
	public ProtocolCourse setParent(Activity parent){
		this.activity = parent;
		return this;
	}
	
	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetCourse";
	}

	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		//创建ArrayList<MCourse>，用来存放课程列表信息
		ArrayList<MCourse> _courseData = new ArrayList<MCourse>();
		
		String _strCourseId, _strCourseName, _strDescription, _strType;
		int _interval, _startWeek, _endWeek;
		double _credit;
		String _strTeachers = "", _strClassrooms = "";
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jtParser = new JSONTokener(strResponse);
		try {
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jtParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONArray的格式返回
			JSONArray _jaRoot = _joRoot.getJSONArray("response");

			for (int _i = 0; _i < _jaRoot.length(); _i++) {
				// 获取JSONArray第_i个json值，以JSONObject格式返回，即：获得第i节课的信息
				JSONObject _joResponse = _jaRoot.getJSONObject(_i);

				_strCourseId = _joResponse.getString("id");
				_strCourseName = _joResponse.getString("name");
				_strDescription = _joResponse.getString("desc");
				_strType = _joResponse.getString("type");
				JSONArray _jaTeachers = _joResponse.getJSONArray("teachers");
				for (int i = 0; i < _jaTeachers.length(); ++i) {
					// 获取JSONArray第i个json值，以JSONObject格式返回，即：获得第i个教师的信息
					JSONObject _joTeacher = _jaTeachers.getJSONObject(i);
					String _strTeacherId = _joTeacher.getString("teacherId");
					String _strTeacherName = _joTeacher.getString("teacherName");
					if (i == 0) {
						_strTeachers = _strTeacherId + ":" + _strTeacherName;
					} else {
						_strTeachers += ";" + _strTeacherId + ":"+ _strTeacherName;
					}
				}
				JSONArray _jaClassrooms = _joResponse.getJSONArray("classrooms");
				for (int i = 0; i < _jaClassrooms.length(); ++i) {
					// 获取JSONArray第i个json值，以JSONObject格式返回，即：获得第i个教师的信息
					JSONObject _joClassroom = _jaClassrooms.getJSONObject(i);
					String _strClassroomId = _joClassroom.getString("classroomId");
					String _strClassroomName = _joClassroom.getString("classroomName");
					if (i == 0) {
						_strClassrooms = _strClassroomId + ":"+ _strClassroomName;
					} else {
						_strClassrooms += ";" + _strClassroomId + ":"+ _strClassroomName;
					}
				}

				_interval = _joResponse.getInt("interval");
				_startWeek = _joResponse.getInt("startWeek");
				_endWeek = _joResponse.getInt("endWeek");
				_credit = _joResponse.getDouble("credit");

				MyLog.e("TEACHERS", _strTeachers);
				MyLog.e("CLASSROOM", _strClassrooms);
				MyLog.e("STARTWEEK", _startWeek+"");
				MyLog.e("ENDWEEK", _endWeek+"");
				MyLog.e("CREDIT",_credit+"");
				
				//创建MCourse对象
				MCourse _courseDataTemp = new MCourse().setContext(activity.getApplicationContext());
				//将数据写入到MCourse对象中
				_courseDataTemp.setStrID(_strCourseId);
				_courseDataTemp.setStrName(_strCourseName);
				_courseDataTemp.setStrDescription(_strDescription);
				_courseDataTemp.setStrType(_strType);
				_courseDataTemp.setStrTeachers(_strTeachers);
				_courseDataTemp.setStrClassrooms(_strClassrooms);
				_courseDataTemp.setIInterval(_interval);
				_courseDataTemp.setStartWeek(_startWeek);
				_courseDataTemp.setEndWeek(_endWeek);
				_courseDataTemp.setCredit(_credit);

				//将MCourse对象添加到ArrayList<MCourse>对象中
				_courseData.add(_courseDataTemp);
				//将MCourse对象存入到数据库
				_courseDataTemp.saveToDB();
			}

			
			MyLog.e("TEACHERS", _strTeachers);
			MyLog.e("CLASSROOM", _strClassrooms);
			
			// 提示数据获取及数据解析成功
			delegate.getCourseSuccess(_courseData);
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
