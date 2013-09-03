package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBMyCurriculum extends Database {
	
	public static final String TABLE = "DBMyCurriculum";

	public static final String TABLE_NAME = "tb_mycurriculum";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_COLLEGE_NAME = "college";
	public static final String COLUMN_INSTITUTE_ID = "institute_id";
	public static final String COLUMN_MAJOR_ID = "magor_id";
	public static final String COLUMN_GRADE = "grade";
	public static final String COLUMN_CLASS = "class";
	public static final String COLUMN_SEMESTER = "semester";
	public static final String COLUMN_MYCURRICULUM_DESC = "mycurriculum";
	
//	public DBMyCurriculum(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	
	public void clearAll(){
		open();
		delete(TABLE_NAME, null, null);
		close();
	}

	public boolean saveToDB(ContentValues values){
		insert(TABLE_NAME, null, values);
		return true;
	}
	
	public int updateToDB(ContentValues values,String collegeName,String instituteID, 
			String majorID, String gradeName, String className, String semester){
		String where = DBMyCurriculum.COLUMN_COLLEGE_NAME + " = ? and " + 
				DBMyCurriculum.COLUMN_INSTITUTE_ID + " = ? and " + 
				DBMyCurriculum.COLUMN_MAJOR_ID + " = ? and " +
				DBMyCurriculum.COLUMN_GRADE + " = ? and " +
				DBMyCurriculum.COLUMN_CLASS + " = ? and " +
				DBMyCurriculum.COLUMN_SEMESTER + " = ?";
		String[] whereValues = {collegeName,instituteID,majorID,gradeName,className,semester};
		return update(TABLE_NAME, values, where, whereValues);
	}
	
	public Cursor readFromDB(String instituteID, String majorID, String gradeName, 
			String className, String semester){
		String _whereCmd = COLUMN_INSTITUTE_ID + " = " + "\"" + instituteID + "\" AND " +
				COLUMN_MAJOR_ID + " = " + "\"" + majorID + "\" AND " +
				COLUMN_GRADE + " = " + "\"" + gradeName + "\" AND " +
				COLUMN_CLASS + " = " + "\"" + className + "\" AND " +
				COLUMN_SEMESTER + " = " + "\"" + semester + "\"";
		String _orderCmd = null;
		String _groupCmd = null;
		return query(TABLE_NAME, null, _whereCmd, null, _groupCmd, null, _orderCmd);
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
				COLUMN_MYCURRICULUM_DESC + " text " +
				")";
	}

	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}
}
