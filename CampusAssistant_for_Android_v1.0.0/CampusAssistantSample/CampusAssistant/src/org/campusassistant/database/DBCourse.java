package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBCourse extends Database {
	public static final String TABLE = "DBCourse";
	
	public static final String TABLE_NAME = "tb_course";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESC = "description";
	public static final String COLUMN_TEACHERS = "teacher_id";
	public static final String COLUMN_CLASSROOMS = "classrom_id";
	public static final String COLUMN_INTERVAL = "interval";
	public static final String COLUMN_STARTWEEK = "startweek";
	public static final String COLUMN_ENDWEEK = "endweek";
	public static final String COLUMN_CREDIT = "credit";

//	public DBCourse(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	/**
	 * 将传入的ContentValues类型数据存入数据库
	 * @param values
	 */
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
	}

	/**
	 * 查询数据库，返回查询结果Cursor对象
	 * @param strID
	 * @return
	 */
	public Cursor readFromDB(String strID){
		String _whereCmd = COLUMN_ID + "=" + strID;
		String _groupCmd = null;
		String _orderCmd = null;
		return query(TABLE_NAME,null,_whereCmd,null,_groupCmd,null,_orderCmd);
	}
	
	@Override
	public String createTableCommander(){
		return "Create Table " + TABLE_NAME + 
				"(" +
				Database._ID + " integer primary key, " +
				COLUMN_ID + " text,"+
				COLUMN_NAME + " text," +
				COLUMN_DESC + " text," +
				COLUMN_TEACHERS + " text," +
				COLUMN_CLASSROOMS + " text, " +
				COLUMN_INTERVAL + " integer, " +
				COLUMN_STARTWEEK + " integer, " +
				COLUMN_ENDWEEK + " integer, " +
				COLUMN_CREDIT + " double " +
				
				")";
	}
	
	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
