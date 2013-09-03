/**
 * 文件说明：我的课表model层的数据
 * 日期：2013/05/23
 */
package org.campusassistant.model;

import java.util.ArrayList;

import org.campusassistant.Tools.AppConstants;
import org.campusassistant.database.DBCurriculum;
import org.campusassistant.database.DBMyCurriculum;

import com.baidu.oauth.BaiduOAuth;
import com.baidu.oauth.BaiduOAuth.BaiduOAuthResponse;
import com.baidu.oauth.BaiduOAuth.OAuthListener;

import BDCloud.BDCloud;
import BDCloud.BDCloud.BDQueryResult;
import BDCloud.BDColumn;
import BDCloud.BDRecord;
import BDCloud.BDValue;
import Database.DAOHelper;
import Log.MyLog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Looper;
import android.widget.Toast;

public class MMyCurriculum {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	//创建Preference的文件名称
	private String PREF_NAME = "ACCESS_TOKEN"; 
	//Preference文件中的键值
	private String KEY = "access_token"; 
	//云存储成功
	static final int ACTION_SAVE_TO_CLOUD = 0;
	//云存储失败
	static final int ACTION_READ_FROM_CLOUD = 1;
	// 一周共7天
	public static final int WEEKDAY_COUNT = 7;
	// 每天共分8节课（小课）
	public static final int SECTION_COUNT = 8;
	//////////////////////////////////////////////////////////
	// 成员变量定义区
	//我的课程表id，学校，学院，专业，年级，班级，学期，我的课程表
	String strID, strCollegeName, strInstituteID, strMajorID, strGrade,
			strClass, strSemester, strMyCurriculum;
	//我的课程表数据列表
	ArrayList<ArrayList<String>> arrayMyCurriculum;
	//上下文
	Context context;
	
	//操作状态，标记上传或者下载
	int loginActionState;
	
	// 云端表
//	Table tableCloud;
	

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

	public String getStrMyCurriculum() {
		return strMyCurriculum;
	}

	/**
	 * 我的课程表，内容为空，通过网络课表和手动添加的方式加入课程
	 */
	public MMyCurriculum(){
		arrayMyCurriculum = null;
		arrayMyCurriculum = new ArrayList<ArrayList<String>>();
		for(int i=0;i<WEEKDAY_COUNT;++i){
			ArrayList<String> _arrayADay = new ArrayList<String>();
			for(int j=0;j<SECTION_COUNT;++j){
				_arrayADay.add("");
			}
			arrayMyCurriculum.add(_arrayADay);
		}
	}

	/**
	 * 将我的课程表的课程拼接成一个字符串，以“，”区分每节课，以“；”区分每天
	 */
	void getMyCurriculumToString(){
		strMyCurriculum = "";
		for (int i=0;i<arrayMyCurriculum.size();++i){
			String _strADay = "";
			ArrayList<String> _arrayADay = arrayMyCurriculum.get(i);
			for (int j=0;j<_arrayADay.size();++j){
				if (j==0){
					_strADay = _arrayADay.get(j);
				} else {
					_strADay += "," + _arrayADay.get(j);
				}
			}
			if (i==0){
				strMyCurriculum = _strADay;
			} else {
				strMyCurriculum += ";" + _strADay;
			}
		}
	}
	
	/**
	 * 将传入的二维list 解析为id:name,id:name;id:name,id:name;.....
	 * @param arrayMyCurriculum 传入的二维课程表
	 */
	public void setStrMyCurriculum(ArrayList<ArrayList<String>> arrayMyCurriculum) {
		
		String _strCellMyCurriculum="";
		// 将array<array>解析为字符串。";"合并了星期，","合并了课程，":"合并了课程ID和课程名称
		int _weekdayCount = arrayMyCurriculum.size();
		for(int i = 0; i< _weekdayCount; i++)
		{
			if (i==0){
			} else {
				_strCellMyCurriculum += ";";
			}
			ArrayList<String> _arrayMyDayCurriculum = new ArrayList<String>();
			int _dayCount = _arrayMyDayCurriculum.size();
			for(int j = 0; j < _dayCount; j++){
				if(j == 0){
					_strCellMyCurriculum += _arrayMyDayCurriculum.get(j);
				}
				
				_strCellMyCurriculum += "," + _arrayMyDayCurriculum.get(j);
			}
			
			
		}		
		
		this.strMyCurriculum = _strCellMyCurriculum;
	}

	// //////////////
	//
	public MMyCurriculum setContext(Context context) {
		this.context = context;
		return this;
	}

	/**
	 * 将MMyCurriculum的实例化对象存入到数据库中
	 * @return 成功返回true
	 */
	public boolean saveToDB() {
		// 创建一个DBMyCurriculum对象，也间接创建了DAOMCurriculumHelper对象
		DBMyCurriculum _db = (DBMyCurriculum) DAOHelper.getInstance().getTable(DBMyCurriculum.TABLE);
		// 调用DAOMCurriculumHelper的getWritableDatabase()方法
		_db.open();
		ContentValues _values = new ContentValues();
		_values.put(DBMyCurriculum.COLUMN_COLLEGE_NAME, strCollegeName);
		_values.put(DBMyCurriculum.COLUMN_INSTITUTE_ID, strInstituteID);
		_values.put(DBMyCurriculum.COLUMN_MAJOR_ID, strMajorID);
		_values.put(DBMyCurriculum.COLUMN_GRADE, strGrade);
		_values.put(DBMyCurriculum.COLUMN_CLASS, strClass);
		_values.put(DBMyCurriculum.COLUMN_SEMESTER, strSemester);
		_values.put(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC, strMyCurriculum);
		_db.saveToDB(_values);
		// 关闭数据库
		_db.close();
		return false;
	}

	/**
	 * 从数据库当中查询我的课程表信息
	 * @return 成功返回true
	 */
	public boolean readFromDB() {
		DBMyCurriculum _db = new DBMyCurriculum();
		_db.open();
		Cursor _cursor = _db.readFromDB("计算机系", "软件工程", "2000", "0002","2001年第一学期");
		if (_cursor.getCount() != 1){
			_cursor.close();
			_db.close();
			return false;
		}
		// 将光标移动到开始  	
		_cursor.moveToFirst();
		// 根据字段名返回该字段的index
		int _indexCurriculum = _cursor.getColumnIndex(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC);
		//根据字段的index返回我的课程字符串
		String _strCurriculum = _cursor.getString(_indexCurriculum);
		
		if (_strCurriculum == null){
			// 关闭cursor
			_cursor.close();
			// 关闭数据库
			_db.close();
			return false;
		}
		//将得到的我的课程表字符串进行分割，存入到ArrayList<ArrayList<String>>类型中
		String[] _tempWeekday = _strCurriculum.split(";");
		ArrayList<ArrayList<String>> _arrayWeekday = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < _tempWeekday.length; ++i) {
			String[] _tempDay = _tempWeekday[i].split(",");
			ArrayList<String> _arrayDay = new ArrayList<String>();
			for (int j = 0; j < _tempDay.length; ++j) {
				_arrayDay.add(_tempDay[j]);
			}
			_arrayWeekday.add(_arrayDay);
		}
		arrayMyCurriculum = null;
		arrayMyCurriculum = _arrayWeekday;
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
	public ArrayList<String> getCourseName(int row, int column){
		ArrayList<String> _arrayMyCourseNameList = new ArrayList<String>();
		if (row >= arrayMyCurriculum.get(column).size()){
			_arrayMyCourseNameList.add("");
		} else {
			String _strCourse = arrayMyCurriculum.get(column).get(row);
			if (_strCourse.equalsIgnoreCase("") == true){
				_arrayMyCourseNameList.add("");
			} else {
				String[] _myCourseNameInfo = arrayMyCurriculum.get(column).get(row).split("\\|");
				
				int _i = 0;
				while(_i<_myCourseNameInfo.length){
					_arrayMyCourseNameList.add(_myCourseNameInfo[_i].split(":")[1]);
					_i++;
				}
			}
		}
		
		return _arrayMyCourseNameList;	
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
	public ArrayList<String> getCourseNameByWeek(int section, int weekday, int year, int month, int day){
		ArrayList<String> _arrayMyCourseNameList = new ArrayList<String>();
		if (section >= arrayMyCurriculum.get(weekday).size()){
			_arrayMyCourseNameList.add("");
		} else {
			String _strCourse = arrayMyCurriculum.get(weekday).get(section);
			if (_strCourse.equalsIgnoreCase("") == true){
				_arrayMyCourseNameList.add("");
			} else {
				String[] _myCourseNameInfo = arrayMyCurriculum.get(weekday).get(section).split("\\|");
				
				int _i = 0;
				while(_i<_myCourseNameInfo.length){
					_arrayMyCourseNameList.add(_myCourseNameInfo[_i].split(":")[1]);
					_i++;
				}
			}
		}
		
		return _arrayMyCourseNameList;
	}
	
	/**
	 * 获得每个课时课程的id列表，如果有多节课，得到的的是多个课程的id
	 * @param section GridView的行
	 * @param weekday GridView的列
	 * @return
	 */
	public ArrayList<String> getCourseId(int row, int column){
		
		ArrayList<String> _arrayMyCourseNameList = new ArrayList<String>();
		String[] _myCourseNameInfo = arrayMyCurriculum.get(column).get(row).split("\\|");
		
		int _i = 0;
		while(_i<_myCourseNameInfo.length){
			_arrayMyCourseNameList.add(_myCourseNameInfo[_i].split(":")[0]);
			_i++;
		}
		return _arrayMyCourseNameList;	
	}
	
	/**
	 * 选择某节课，将其加入到我的课表中
	 * @param weekday GridView的行
	 * @param section GridView的列
	 * @param courseId 课程的id
	 * @param courseName 课程的名称
	 */
	public void selectCourse(int weekday, int section, String courseId, String courseName){
		// 判读我的课表数据库是否存在
		
		// 打开网络课程表
		MCurriculum _curriculumData = new MCurriculum().setContext(context);
		// 读取数据库
		_curriculumData.readFromDB();
		// 所有必选课进入课表
		for (int i=0;i<MCurriculum.WEEKDAY_COUNT;++i){
			for (int j=0;j<MCurriculum.SECTION_COUNT;++j){
				ArrayList<String> _types = _curriculumData.getCourseType(j, i);
				String _myCourses = "";
				int _resultCount = 0;
				for (int k=0;k<_types.size();++k){
					//如果课程的类型为必修课（设置为1），就添加到我的课表
					if (_types.get(k).equalsIgnoreCase("1")){
						if (_resultCount == 0){
							_myCourses = _curriculumData.getCourseString(j, i, k);
						} else {
							_myCourses += "\\|" + _curriculumData.getCourseString(j, i, k);
						}
						_resultCount++;
					}
				}
				// 添加到我的课表中
				arrayMyCurriculum.get(i).set(j, _myCourses);
			}
		}
		// 只要所选的课程的ID与网络课程表中，某一个单元格中的ID一致，则表示选中了这门课程
		//7天
		for (int i=0;i<7;++i){
			//8节课
			for (int j=0;j<8;++j){
				ArrayList<String> _course = _curriculumData.getCourseId(j, i);
				for (int k=0;k<_course.size();++k){
					if (_course.get(k).equalsIgnoreCase(courseId)){
						// 所选的课
						String _strOldCourse = arrayMyCurriculum.get(i).get(j);
						if (_strOldCourse.equalsIgnoreCase(""))
							arrayMyCurriculum.get(i).set(j, courseId+":"+courseName+":"+"2");
						else
							arrayMyCurriculum.get(i).set(j, _strOldCourse+"\\|"+courseId+":"+courseName+":"+"2");
					}
				}
			}
		}
		// 将ArrayList<ArrayList<String>>转换为String表示（格式化字符串）
		getMyCurriculumToString();
		
		// 保存（写）数据库
		DBMyCurriculum _db = (DBMyCurriculum) DAOHelper.getInstance().getTable(DBMyCurriculum.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB("计算机系", "软件工程", "2000", "0002","2001年第一学期");
		
		// 生成数据库串
		ContentValues _values = new ContentValues();
		_values.put(DBMyCurriculum.COLUMN_COLLEGE_NAME, strCollegeName);
		_values.put(DBMyCurriculum.COLUMN_INSTITUTE_ID, strInstituteID);
		_values.put(DBMyCurriculum.COLUMN_MAJOR_ID, strMajorID);
		_values.put(DBMyCurriculum.COLUMN_GRADE, strGrade);
		_values.put(DBMyCurriculum.COLUMN_CLASS, strClass);
		_values.put(DBMyCurriculum.COLUMN_SEMESTER, strSemester);
		_values.put(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC, strMyCurriculum);
		_values.put(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC, strMyCurriculum);
		
		if (_cursor.getCount() == 0){
			// 当前无数据，则插入数据库
			_db.saveToDB(_values);
		} else {
			// 当前有数据，更新数据库
			_db.updateToDB(_values, strCollegeName, strInstituteID, strMajorID, strGrade, strClass, strSemester);
		}
		_cursor.close();
		_db.close();
		
	}
	
	/**
	 *  取消选择某节课
	 * @param weekday GridView的行
	 * @param section GridView的列
	 */
	public void cancelCourse(int weekday, int section){
		arrayMyCurriculum.get(weekday).set(section, "");
	}
	
	/**
	 *  从网络课表生成我的课表
	 * @return 成功返回true
	 */
	public boolean generate(){
		DBCurriculum _db = (DBCurriculum) DAOHelper.getInstance().getTable(DBCurriculum.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB("计算机系", "软件工程", "2000", "0002","2001年第一学期");
		if (_cursor.getCount() != 1)
			return false;
		_cursor.moveToFirst();
		int _indexCurriculum = _cursor.getColumnIndex(DBCurriculum.COLUMN_CURRICULUM_DESC);
		String _strCurriculum = _cursor.getString(_indexCurriculum);

		// 这里还需要判断是否是选修课，以做选择性的导入
		String[] _tempWeekday = _strCurriculum.split(";");
		ArrayList<ArrayList<String>> _arrayWeekday = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < _tempWeekday.length; ++i) {
			String[] _tempDay = _tempWeekday[i].split(",");
			ArrayList<String> _arrayDay = new ArrayList<String>();
			for (int j = 0; j < _tempDay.length; ++j) {
				_arrayDay.add(_tempDay[j]);
			}
			_arrayWeekday.add(_arrayDay);
		}
		arrayMyCurriculum = null;
		arrayMyCurriculum = _arrayWeekday;
		_cursor.close();
		_db.close();
		
		return true;
	}
	
	/**
	 * 获取我的全部课程
	 * 按照学校、学院、专业、年纪、班级、学期，来获取我的必修课和选修课的MCourse对象列表。
	 * 如果想获取不同学期的我的课程信息，可以创建多个MMyCurriculum对象来实现。
	 */
	public ArrayList<MCourse> getMyCourses(){
		return null;
	}

	
	/**
	 * 从云端读取课表
	 * @param context 设置上下文
	 * @param apiKey 用户Access token
	 */
	void getMyCurriculumFromCloud(final Context context, final String apiKey) {
		//设置查询条件
		String _condition = DBMyCurriculum.COLUMN_COLLEGE_NAME + " == " +this.strCollegeName + ";"
				+ DBMyCurriculum.COLUMN_INSTITUTE_ID + " == " + this.strInstituteID + ";"
				+ DBMyCurriculum.COLUMN_MAJOR_ID + " == " + this.strMajorID + ";"
				+ DBMyCurriculum.COLUMN_GRADE + " == " + this.strGrade + ";"
				+ DBMyCurriculum.COLUMN_CLASS + " == " + this.strClass + ";"
				+ DBMyCurriculum.COLUMN_SEMESTER + " == " + this.strSemester;
		String[] _columns = {DBMyCurriculum.COLUMN_MYCURRICULUM_DESC};
		//查询结果集
		BDQueryResult _result = BDCloud.getInstance().queryItemOnCloud(context,apiKey, _columns, _condition);
		if (_result.getReturnCode() == BDCloud.ERROR_SUCCESS){
			BDRecord _record = _result.getRecords().get(0);
			this.strMyCurriculum = (String) _record.getValue(_columns[0]);
		}
		return;
	}
	
	/**
	 * 
	 * @param records
	 */
//	void parseMyCurriculumFromRecord(RecordSet records){
//		List<Record> _arrayRecord = records.getRecords();
//		if (_arrayRecord.size() != 1)
//			return;
//		Record _recordCurriculumInfo = _arrayRecord.get(0);
//		this.strID = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_ID);
//		this.strCollegeName = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_COLLEGE_NAME);
//		this.strInstituteID = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_INSTITUTE_ID);
//		this.strMajorID = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_MAJOR_ID);
//		this.strGrade = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_GRADE);
//		this.strClass = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_CLASS);
//		this.strSemester = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_SEMESTER);
//		this.strMyCurriculum = (String) _recordCurriculumInfo.get(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC);
//	}
	
	/**
	 * 创建云端表
	 * @param context 设置上下文
	 * @param accessToken 开发者Access token
	 */
	void createCloudTable(Context context,String accessToken){
		//设置云端表的名字
		BDCloud.getInstance().setTableName(DBMyCurriculum.TABLE_NAME);
		//实例化ArrayList
		ArrayList<BDColumn> _arrayColumn = new ArrayList<BDColumn>();
		//写入字段信息
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_ID,"curriculum id",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_COLLEGE_NAME,"my college",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_INSTITUTE_ID,"my institute id",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_MAJOR_ID,"my major id",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_GRADE,"my grade",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_CLASS,"my class",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_SEMESTER,"semester",true));
		_arrayColumn.add(new BDColumn(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC,"my curriculum",true));
		//在百度云端创建我的课表
		int i = BDCloud.getInstance().creatTableOnCloud(context, accessToken, _arrayColumn);
		Looper.prepare();
		switch(i){
		case 0:
			MyLog.e("i = :", i + "---->创建表成功");
			break;
		case 1:
			MyLog.e("i = :", i + "---->无Access token，需要登录授权获取Access token");
			break;
		case 2:
			MyLog.e("i = :", i + "---->table已经存在");
			break;
			default:
				break;
		}
		
	}
	
	/**
	 * 向云端表中插入数据
	 * @param context 设置上下文
	 * @param accessToken 开发者Access token
	 */
	void insertItemToCloudTable(Context context,String accessToken){
		//实例化ArrayList
		ArrayList<BDValue> _arrayValues = new ArrayList<BDValue>();
		//向ArrayList中加入数据
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_ID,this.strID));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_COLLEGE_NAME,this.strCollegeName));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_INSTITUTE_ID,this.strInstituteID));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_MAJOR_ID,this.strMajorID));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_GRADE,this.strGrade));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_CLASS,this.strClass));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_SEMESTER,this.strSemester));
		_arrayValues.add(new BDValue(DBMyCurriculum.COLUMN_MYCURRICULUM_DESC,this.strMyCurriculum));
		//向云端表中插入数据
		BDCloud.getInstance().insertItemOnCloud(context,accessToken, _arrayValues);
	}
	
	/**
	 * 往云中写数据
	 * @param context 设置上下文
	 * @param apiKey 开发者Access token
	 * @return 成功返回true
	 */
	public boolean saveToCloud(final Context context, final String apiKey){
		final String _developerAccessToken = AppConstants.getDeveloperAccessToken();
		final String _userAccessToken = AppConstants.getUserAccessToken();
		if (_userAccessToken == null || _userAccessToken.length() <= 0){
			loginActionState = ACTION_SAVE_TO_CLOUD;
			loginBaiduOAuth(context, apiKey);
		} else {
			new Thread(){
				@Override
				public void run() {
					// 创建我的课表table，在云端
					//createCloudTable(context, apiKey);
					createCloudTable(context, _developerAccessToken);
					// 插入数据
					//insertItemToCloudTable(context,apiKey);
					insertItemToCloudTable(context,_userAccessToken);
				}
			}.start();
		}
		return true;
	}
	
	/**
	 * 从云中读取数据
	 * @param context
	 * @param apiKey
	 * @return
	 */
	public boolean readFromCloud(final Context context, final String apiKey){
		String _accessToken = AppConstants.getUserAccessToken();
		if (_accessToken == null || _accessToken.length() <= 0){
			loginActionState = ACTION_READ_FROM_CLOUD;
			loginBaiduOAuth(context, apiKey);
		} else {
			new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//从云端取得数据
					getMyCurriculumFromCloud(context, apiKey);
				}
				
			}.start();
		}
		return true;
	}
	
	/**
	 * 百度OAuth
	 */
	void loginBaiduOAuth(final Context context, final String apiKey){
		BaiduOAuth oauthClient = new BaiduOAuth();
		oauthClient.startOAuth(context, apiKey, new String[] {"basic","netdisk "}, new OAuthListener() {
			
			@Override
			public void onException(String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Login failed" + msg, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onComplete(BaiduOAuthResponse response) {
				// TODO Auto-generated method stub
				if(response != null){

					String accessToken = response.getAccessToken();
					AppConstants.setUserAccessToken(accessToken);
//					Toast.makeText(getApplicationContext(), accessToken, Toast.LENGTH_SHORT).show();
					MyLog.i("accessToken", accessToken);
					// 保存到配置文件中
					writeTokenToConfig(context, accessToken);
					switch(loginActionState){
					case ACTION_SAVE_TO_CLOUD:{
						// 登录成功后，再次保存
						saveToCloud(context, apiKey);
					}
						break;
					case ACTION_READ_FROM_CLOUD:{
						readFromCloud(context, apiKey);
					}
						break;
					}
					
				}
			}			

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Login cancelled", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * 从配置中读取AcccessToken
	 */
	void readTokenFromConfig(Context context){
		SharedPreferences _sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		
		String _accessToken = _sp.getString(KEY, "");
		AppConstants.setUserAccessToken(_accessToken);
	}
	
	/**
	 * 将AccessToken写入配置文件中
	 */
	void writeTokenToConfig(Context context,String accessToken){
		//保存access_token，文件名： ACCESS_TOKEN，KEY：access_token
		SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); 
		Editor ed = sp.edit();
		//清除所有信息
		ed.clear();
		ed.putString(KEY, accessToken);
		ed.commit();
	}
}

