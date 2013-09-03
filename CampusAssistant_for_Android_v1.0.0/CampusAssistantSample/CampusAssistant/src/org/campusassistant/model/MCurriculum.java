package org.campusassistant.model;

import java.util.ArrayList;

import org.campusassistant.database.DBCurriculum;


import Database.DAOHelper;
import Log.MyLog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MCurriculum {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 一周共7天
	public static final int WEEKDAY_COUNT = 7;
	// 每天共分8节课（小课）
	public static final int SECTION_COUNT = 8;
	//////////////////////////////////////////////////////////
	// 成员变量定义区
	//课程表id，学校，学院，专业，年级，班级，学期，课程表
	String strID, strCollegeName, strInstituteID, strMajorID, strGrade,
			strClass, strSemester, strCurriculum;
	//课程表数据列表
	ArrayList<ArrayList<String>> arrayCurriculum;
	//上下文
	Context context;

	public String getStrID() {
		return strID;
	}

	public void setStrID(String strID) {
		this.strID = strID;
	}

	public String getStrCollegeName() {
		return strCollegeName;
	}

	public void setStrCollegeName(String strCollegeName) {
		this.strCollegeName = strCollegeName;
	}

	public String getStrInstituteID() {
		return strInstituteID;
	}

	public void setStrInstituteID(String strInstituteID) {
		this.strInstituteID = strInstituteID;
	}

	public String getStrMajorID() {
		return strMajorID;
	}

	public void setStrMajorID(String strMajorID) {
		this.strMajorID = strMajorID;
	}

	public String getStrGrade() {
		return strGrade;
	}

	public void setStrGrade(String strGrade) {
		this.strGrade = strGrade;
	}

	public String getStrClass() {
		return strClass;
	}

	public void setStrClass(String strClass) {
		this.strClass = strClass;
	}

	public String getStrSemester() {
		return strSemester;
	}

	public void setStrSemester(String strSemester) {
		this.strSemester = strSemester;
	}

	public String getStrCurriculum() {
		return strCurriculum;
	}

	/**
	 * 将传入的课程表字符串按照天和课时进行分割，存为ArrayList<ArrayList<String>>()形式
	 * @param strCurriculum 传入的课程表字符串，将一周的课连接成一个字符串传入
	 */
	public void setStrCurriculum(String strCurriculum) {
		this.strCurriculum = strCurriculum;
		arrayCurriculum = null;
		arrayCurriculum = new ArrayList<ArrayList<String>>();
		// 解析字符串。";"分隔了星期，","分隔了课程，"|"分隔了每课时的课程名，":"分隔了课程ID和课程名称
		String[] _weekday = strCurriculum.split(";");
		int _weekdayCount = _weekday.length;
		for (int i=0;i<_weekdayCount;++i){
			String _aDay = _weekday[i];
			String[] _course = _aDay.split(",");
			int _courseCount = _course.length;
			ArrayList<String> _arrayCourse = new ArrayList<String>();
			for (int j=0;j<_courseCount;++j){
				_arrayCourse.add(_course[j]);
			}
			//arrayCurriculum.get().get()的String形式是“001：语文：2|002：数学：1”或者“001：语文：2”或者为空
			arrayCurriculum.add(_arrayCourse);
		}
	}
	
	////////////////
	//
	public MCurriculum setContext(Context context){
		this.context = context;
		return this;
	}
	/**
	 * 将MCurriculum实例化对象存入数据库
	 * @return 成功返回true
	 */
	public boolean saveToDB(){
		// 创建一个DBCurriculum对象，也间接创建了DAOCurriculumHelper对象
		DBCurriculum _db = (DBCurriculum) DAOHelper.getInstance().getTable(DBCurriculum.TABLE);
		// 调用DAOCurriculumHelper的getWritableDatabase()方法
		_db.open();
		ContentValues _values = new ContentValues();
		_values.put(DBCurriculum.COLUMN_COLLEGE_NAME, strCollegeName);
		_values.put(DBCurriculum.COLUMN_INSTITUTE_ID, strInstituteID);
		_values.put(DBCurriculum.COLUMN_MAJOR_ID, strMajorID);
		_values.put(DBCurriculum.COLUMN_GRADE, strGrade);
		_values.put(DBCurriculum.COLUMN_CLASS, strClass);
		_values.put(DBCurriculum.COLUMN_SEMESTER, strSemester);
		_values.put(DBCurriculum.COLUMN_CURRICULUM_DESC, strCurriculum);
		_db.saveToDB(_values);
		// 关闭数据库
		_db.close();
		return false;
	}
	
	/**
	 * 请完成一下几点：
	 * 1、这个函数需要改造为根据，学校、系、专业、年级、班级、学期，来查询数据
	 * 2、其中，当从数据库中读取到数据后，要判断返回结果的个数，理论上我们的结果应该有且只有一个。对于我们，如果超过1个，先取第一个。
	 * 3、当读取到数据后，需要将curriculum字段格式化为ArrayList<ArrayList<String>>类型的arrayCurriculum，
	 * 		数据库中的字段格式为: courseId:courseName,...,courseId:courseName;...;courseId:courseName,...,courseId:courseName
	 * 		":"用户分割课程的ID和课程的名称，","用于分割每一节课，";"用于分割一个星期中的每一天，详细见ProtocolCurriculum.java
	 */
	/**
	 * 从数据库当中查询课程表信息
	 * @return 成功返回true
	 */
	public boolean readFromDB(){
		// 创建一个DBCurriculum对象，也间接创建了DAOCurriculumHelper对象
		DBCurriculum _db = (DBCurriculum) DAOHelper.getInstance().getTable(DBCurriculum.TABLE);
		// 调用DAOCurriculumHelper的getWritableDatabase()方法
		_db.open();
		Cursor _cursor = _db.readFromDB("电子信息工程学院", "电子信息工程", "2011", "3", "12132");
		if (_cursor.getCount() != 1)
			return false;
		_cursor.moveToFirst();
		// 根据字段名返回该字段的index
		int _indexCurriculum = _cursor.getColumnIndex(DBCurriculum.COLUMN_CURRICULUM_DESC);
		//根据字段的index返回课程字符串
		String _strCurriculum = _cursor.getString(_indexCurriculum);
		//将_strCurriculum字符串进行分割，写入到ArrayList<ArrayList<String>>中
		//每天的课程
		String[] _tempWeekday = _strCurriculum.split(";");
		ArrayList<ArrayList<String>> _arrayWeekday = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < _tempWeekday.length; ++i) {
			//每课时的课程
			String[] _tempDay = _tempWeekday[i].split(",");
			ArrayList<String> _arrayDay = new ArrayList<String>();
			for (int j = 0; j < _tempDay.length; ++j) {
				_arrayDay.add(_tempDay[j]);
			}
			_arrayWeekday.add(_arrayDay);
		}
		arrayCurriculum = null;
		arrayCurriculum = _arrayWeekday;
		MyLog.e("test Read from DB", _strCurriculum);
		// 关闭cursor
		_cursor.close();
		// 关闭数据库
		_db.close();
		return true;
	}
	
	/**
	 * 获得每课时课程的名称列表，如果有多节课，得到的的是多个字符串
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @return
	 */
	public ArrayList<String> getCourseName(int section, int weekday){
		
		ArrayList<String> _arrayCourseNameList = new ArrayList<String>();
		if(null != arrayCurriculum){
			//以“|”区分多节课
			String[] _courseInfo = arrayCurriculum.get(weekday).get(section).split("\\|");
			
			for (int i=0;i<_courseInfo.length;++i){
				String[] _infos = _courseInfo[i].split(":");
				if (_infos.length > 0)
					_arrayCourseNameList.add(_infos[1]);
			}
		}else{
			_arrayCourseNameList = null;
		}
		
		return _arrayCourseNameList;	
	}
	
	/**
	 * 获得每个课时课程的id列表，如果有多节课，得到的的是多个课程的id
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @return
	 */
	public ArrayList<String> getCourseId(int section, int weekday){
		
		ArrayList<String> _arrayCourseIdList = new ArrayList<String>();
		if(null != arrayCurriculum){
			//以“|”区分多节课
			String[] _courseInfo = arrayCurriculum.get(weekday).get(section).split("\\|");
			for (int i=0;i<_courseInfo.length;++i){
				String[] _infos = _courseInfo[i].split(":");
				if (_infos.length > 0)
					_arrayCourseIdList.add(_infos[0]);
			}
		}else{
			_arrayCourseIdList = null;
		}
		return _arrayCourseIdList;
	}

	/**
	 * 获得每课时课程的类型列表，如果有多节课，应有多个课程类型
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @return
	 */
	public ArrayList<String> getCourseType(int section, int weekday) {
		ArrayList<String> _arrayCourseTypeList = new ArrayList<String>();
		if(null != arrayCurriculum){
			//以“|”区分多节课
			String[] _courseInfo = arrayCurriculum.get(weekday).get(section).split("\\|");
			for (int i=0;i<_courseInfo.length;++i){
				String[] _infos = _courseInfo[i].split(":");
				if (_infos.length > 0)
					_arrayCourseTypeList.add(_infos[2]);
			}
		}else{
			arrayCurriculum = null;
		}
		return _arrayCourseTypeList;
	}
	
	/**
	 * 获得每课时每节课的字符串，如：0001：语文：2，为一个字符串，如果只有一节课，就得到一个这样的字符串
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @param index 该课时下的第几门课，如果只有一门课，index的值为0，两门课index的值可以为0,1
	 * @return 每课时一门课的字符串
	 */
	public String getCourseString(int section, int weekday, int index){
		String[] _courseInfo = arrayCurriculum.get(weekday).get(section).split("\\|");
		return _courseInfo[index];
	}
	
	/**
	 * 获得每个课时的课程数目，多节课之间以“，”分隔
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @return 每个课时的课程数目
	 */
	public int getNumCoursePerClassHour(int section, int weekday) {
		// TODO Auto-generated method stub
		if(null != arrayCurriculum){
			return arrayCurriculum.get(weekday).get(section).split("\\|").length;
		}else
			return 0;
		
	}
	
	/**
	 * 获得一周的课程名称，不包含id和类型
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 一周的课程名称
	 */
	public String getCourseNameByWeek(int section, int weekday, int year, int month, int day){
		String _str = arrayCurriculum.get(weekday).get(section);
		if (_str.equalsIgnoreCase(":") == true)
			return "";
		else 
			return _str.split(":")[1];
	}
}
