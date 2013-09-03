package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBCurriculum extends Database {
	public static final String TABLE = "DBCurriculum";

	public static final String TABLE_NAME = "tb_curriculum";

	public static final String COLUMN_ID = "id";

	public static final String COLUMN_COLLEGE_NAME = "college";

	public static final String COLUMN_INSTITUTE_ID = "institute_id";

	public static final String COLUMN_MAJOR_ID = "magor_id";

	public static final String COLUMN_GRADE = "grade";

	public static final String COLUMN_CLASS = "class";

	public static final String COLUMN_SEMESTER = "semester";

	public static final String COLUMN_CURRICULUM_DESC = "curriculum";

//	public DBCurriculum(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	/**
	 * 将传入的ContentValues类型数据存入数据库
	 * @param values
	 */
	public boolean saveToDB(ContentValues values){
		insert(TABLE_NAME, null, values);
		return true;
	}
	/**
	 * 查询数据库，返回查询结果Cursor对象
	 * @param instituteID
	 * @param majorID
	 * @param gradeName
	 * @param className
	 * @param semester
	 * @return
	 */
	public Cursor readFromDB(String instituteID, String majorID, String gradeName, String className, String semester){
		String _whereCmd = COLUMN_INSTITUTE_ID + " = " + "\"" + instituteID + "\" AND " +
				COLUMN_MAJOR_ID + " = " + "\"" + majorID + "\" AND " +
				COLUMN_GRADE + " = " + "\"" + gradeName + "\" AND " +
				COLUMN_CLASS + " = " + "\"" + className + "\" AND " +
				COLUMN_SEMESTER + " = " + "\"" + semester + "\"";
		String _orderCmd = null;
		String _groupCmd = null;
		return query(TABLE_NAME, null, _whereCmd, null, _groupCmd, null, _orderCmd);
	}
	
	/**
	 * 清除数据库
	 */
	public void clearAll(){
		open();
		delete(TABLE_NAME, null, null);
		close();
	}
	@Override
	public String createTableCommander() {
		// TODO Auto-generated method stub
		return "Create Table " + TABLE_NAME + 
				"(" +
				Database._ID + " integer primary key, " +
				COLUMN_ID + " text,"+
				COLUMN_COLLEGE_NAME + " text," +
				COLUMN_INSTITUTE_ID + " text," +
				COLUMN_MAJOR_ID + " text," +
				COLUMN_GRADE + " text," +
				COLUMN_CLASS + " text," +
				COLUMN_SEMESTER + " text," +
				COLUMN_CURRICULUM_DESC + " text " +
				")";
	}
	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
