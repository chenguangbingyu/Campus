package org.campusassistant.model;

import java.util.ArrayList;

import org.campusassistant.database.DBCourse;


import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MCourse {
	//strType 选修还是必修
	String strID, strName, strDescription, strType;
	String strTeachers, strClassrooms;
	//间隔，用来描述每隔几周上一次课，如果是0表示连续上课，如果是1表示隔一周上课，如果是2表示隔两周上课，以此类推
	int iInterval;
//	String strWeekType;
//	boolean isOddWeek, isEvenWeek;
	//开始周，结束周
	int iStartWeek, iEndWeek;
	//学分
	double dCredit;
	
	ArrayList<String> arrayTeachers, arrayClassrooms;
//	String strTeacherList = "", strClassroomList = "";
	
	Context context;
	
	/**
	 * 属性访问接口
	 * @return
	 */
	
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
	public String getStrDescription() {
		return strDescription;
	}
	
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	
	public String getStrTeachers() {
		return strTeachers;
	}
	public void setStrTeachers(String strTeachers) {
		this.strTeachers = strTeachers;
		
		arrayTeachers = null;
		arrayTeachers = new ArrayList<String>();
		
		String[] _teachers = strTeachers.split(";");
		for(int i=0;i<_teachers.length;++i){
			arrayTeachers.add(_teachers[i]);
//			strTeacherList = strTeacherList + "|" + _teachers[i];
		}
	}
	public String getStrClassrooms() {
		return strClassrooms;
	}
	public void setStrClassrooms(String strClassrooms) {
		this.strClassrooms = strClassrooms;
		
		arrayClassrooms = null;
		arrayClassrooms = new ArrayList<String>();
		
		String[] _classrooms = strClassrooms.split(";");
		for (int i=0;i<_classrooms.length;++i){
			arrayClassrooms.add(_classrooms[i]);
		}
	}
	
	public int getStartWeek() {
		// TODO Auto-generated method stub
		return iStartWeek;
	}
	
	public void setStartWeek(int iStartWeek){
		this.iStartWeek = iStartWeek;
	}
	public int getEndWeek() {
		// TODO Auto-generated method stub
		return iEndWeek;
	}
	
	public void setEndWeek(int iEndWeek){
		this.iEndWeek = iEndWeek;
	}
//	public boolean getOddWeek() {
//		// TODO Auto-generated method stub
//		return isOddWeek;
//	}
//	
//	public void setOddWeek(boolean isOddWeek){
//		this.isOddWeek = isOddWeek;
//	}
//	public boolean getEvenWeek() {
//		// TODO Auto-generated method stub
//		return isEvenWeek;
//	}
//	
//	public void setEvenWeek(boolean isEvenWeek){
//		this.isEvenWeek = isEvenWeek;
//	}
	
//	public String getStrWeekType(){
//		return strWeekType;
//	}
//	public void setStrWeekType(String strWeekType){
//		this.strWeekType = strWeekType;
//	}
	
	public int getIInterval(){
		return iInterval;
	}
	public void setIInterval(int iInterval){
		this.iInterval = iInterval;
	}
	
	public double getCredit() {
		// TODO Auto-generated method stub
		return dCredit;
	}
	
	public void setCredit(double dCredit){
		this.dCredit = dCredit;
	}
	
	public MCourse setContext(Context context){
		this.context = context;
		return this;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	public boolean saveToDB(){
		DBCourse _db = (DBCourse) DAOHelper.getInstance().getTable(DBCourse.TABLE);
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBCourse.COLUMN_ID, strID);
		_value.put(DBCourse.COLUMN_NAME, strName);
		_value.put(DBCourse.COLUMN_DESC, strDescription);
		_value.put(DBCourse.COLUMN_TEACHERS, strTeachers);
		_value.put(DBCourse.COLUMN_CLASSROOMS, strClassrooms);
		_value.put(DBCourse.COLUMN_INTERVAL, iInterval);
		_value.put(DBCourse.COLUMN_STARTWEEK, iStartWeek);
		_value.put(DBCourse.COLUMN_ENDWEEK, iEndWeek);
		_value.put(DBCourse.COLUMN_CREDIT, dCredit);
		_db.saveToDB(_value);
		_db.close();
		return true;
	}
	
	public boolean getCourseFromDB(String strID){
		boolean _bResult = false;
		DBCourse _db = (DBCourse) DAOHelper.getInstance().getTable(DBCourse.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB(strID);
		if (_cursor.getCount() != 1){
			_bResult = false;
		} else {
			_cursor.moveToFirst();
			int _indexTeacher = _cursor.getColumnIndex(DBCourse.COLUMN_TEACHERS);
			int _indexClassroom = _cursor.getColumnIndex(DBCourse.COLUMN_CLASSROOMS);
			
			String _strTeachers = _cursor.getString(_indexTeacher);
			String _strClassrooms = _cursor.getString(_indexClassroom);
			
			// 解析教师信息
			arrayTeachers = null;
			arrayTeachers = new ArrayList<String>();
			String[] _teachers = _strTeachers.split(";");
			for (int i=0;i<_teachers.length;++i){
				arrayTeachers.add(_teachers[i]);
			}
			// 解析教室信息
			arrayClassrooms = null;
			arrayClassrooms = new ArrayList<String>();
			String[] _classrooms = _strClassrooms.split(";");
			for (int i=0;i<_classrooms.length;++i){
				arrayClassrooms.add(_classrooms[i]);
			}
			_bResult = true;
		}
		
		_cursor.close();
		_db.close();
		return _bResult;
	}
	
	/**
	 * 从数据库中取数据
	 */
	public void getDataFromDB() {
		// TODO Auto-generated method stub
		//取课程数据操作
		
	}
	
	public int getTeachersCount(){
		return arrayTeachers.size();
	}
	
	public int getClassroomCount(){
		return arrayClassrooms.size();
	}

	public String getTeacherName(int index){
		return arrayTeachers.get(index).split(":")[1];
	}
	public String getTeacherId(int index){
		return arrayTeachers.get(index).split(":")[0];
	}
	
	public String getClassroomName(int index){
		return arrayClassrooms.get(index).split(":")[1];
	}
	public String getClassroomId(int index){
		return arrayClassrooms.get(index).split(":")[0];
	}
	public String getWeekType(){
		int _startWeek = iStartWeek;
		int _interval = iInterval;
		String strWeekType = "";
		String _temp = "";
		if(_interval == 0){
			_temp = "连续每周上课";
		}else if(_interval == 1){
			if(_startWeek%2 == 0)
			_temp = "双周上课";
			else 
				_temp = "单周上课";
		}else 
			_temp = "不连续上课";
		
		strWeekType = _temp;
		return strWeekType;
	}


}
