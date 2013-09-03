/**
 * 文件说明：课程表信息协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import org.campusassistant.database.DBCurriculum;
import org.campusassistant.model.MCurriculum;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Database.DAOHelper;
import Log.MyLog;
import Protocol.ProtocolBase;
import android.content.Context;


public class ProtocolCurriculum extends ProtocolBase {

	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetCurriculum";

	/**
	 * 使用ProtocolCurriculumDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolCurriculumDelegate {
		public void getCurriculumSuccess(MCurriculum curriculum);
		public void getCurriculumFailed();
	}

	//创建ProtocolCurriculumDelegate对象
	ProtocolCurriculumDelegate delegate;
	//
	Context context;
	
	public ProtocolCurriculum setContext(Context context){
		this.context = context;
		return this;
	}

	//
	public ProtocolCurriculum setDelegate(ProtocolCurriculumDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetCurriculum";
	}
	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		DBCurriculum _db = (DBCurriculum) DAOHelper.getInstance().getTable(DBCurriculum.TABLE);
		_db.clearAll();
		//创建MCurriculum对象
		MCurriculum _mCurriculum = null;
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jsonParser = new JSONTokener(strResponse);
		try {
			String _strCollegeName, _strInstituteID, _strMajorID;
			String _strGrade, _strClass, _strSemester;
			String _strCurriculum="";
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jsonParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONObject的格式返回
			JSONObject _joResponse = _joRoot.getJSONObject("response");
			
			_strCollegeName = _joResponse.getString("college");
			_strInstituteID = _joResponse.getString("institute");
			_strMajorID = _joResponse.getString("major");
			_strGrade = _joResponse.getString("grade");
			_strClass = _joResponse.getString("class");
			_strSemester = _joResponse.getString("semester");
			JSONArray _jaCurriculum = _joResponse.getJSONArray("curriculum");
			// 获取_jaCurriculum的json数组长度
			int _count = _jaCurriculum.length();
			for (int i=0;i<_count;++i){
				// 获取json数组第i个json值，以JSONArray格式返回，即为每天的课程列表
				JSONArray _jaWeekday = _jaCurriculum.getJSONArray(i);
				// 获取_jaWeekday的json数组长度，即为每天有几节课
				int _courseCount = _jaWeekday.length();
				
				String _strTempWeekday = "";
				for (int j=0;j<_courseCount;++j){
					// 获取json数组第j个json值，以JSONArray格式返回，即为每个课时的课程列表
					JSONArray _jaClassHour = _jaWeekday.getJSONArray(j);
					// 获取_jaClassHour的json数组长度，即为每课时有几节课
					int _perCourseCount = _jaClassHour.length();
					
					String _strTempClass = "";
					for(int k=0;k<_perCourseCount;k++){
						// 获取json数组第k个json值，以JSONObject格式返回，即为每个课时的第k节课
						JSONObject _jo = _jaClassHour.getJSONObject(k);
				//		MyLog.e("第几次",i + "..." + j + "..." + k);
//						String _strTemp = _jo.getString("courseId") + ":" + _jo.getString("courseName") + ":" + _jo.getString("type");
						String _strTemp = _jo.optString("courseId") + ":" + _jo.optString("courseName") + ":" + _jo.optString("type");
						if (k == 0){
							_strTempClass = _strTemp;
						} else {
							_strTempClass += "|"+_strTemp;
						}
					}
					if (j == 0){
						_strTempWeekday = _strTempClass;
					} else {
						_strTempWeekday += ","+_strTempClass;
					}
				}
				
				if (i==0){
					_strCurriculum = _strTempWeekday;
				} else {
					_strCurriculum += ";" + _strTempWeekday;
				}
			}
			MyLog.e("CURRICULUM", _strCurriculum);
			
			//实例化MCurriculum对象
			_mCurriculum = new MCurriculum().setContext(context);
			//将数据写入到MCurriculum对象中
			 _mCurriculum.setStrCollegeName(_strCollegeName);
			 _mCurriculum.setStrInstituteID(_strInstituteID);
			 _mCurriculum.setStrMajorID(_strMajorID);
			 _mCurriculum.setStrGrade(_strGrade);
			 _mCurriculum.setStrClass(_strClass);
			 _mCurriculum.setStrSemester(_strSemester);
			 _mCurriculum.setStrCurriculum(_strCurriculum);
			 //将MCurriculum写入到数据库
			 _mCurriculum.saveToDB();
			 
			 //提示数据获取及数据解析成功
			 delegate.getCurriculumSuccess(_mCurriculum);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
