package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBNewsCategory extends Database {
	public static final String TABLE = "DBNewsCategory";

	public static final String TABLE_NAME = "tb_newscategory";

	public static final String COLUMN_ID = "id";

	public static final String COLUMN_TITLE = "title";

	public static final String COLUMN_TAG = "tag";

	public static final String COLUMN_DESC = "description";

	public static final String COLUMN_ICON = "icon";
	

//	public DBNewsCategory(Context context) {
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
	 * @return
	 */
	public Cursor readFromDB(){
		String _whereCmd = null;
		String _groupCmd = null;
		String _orderCmd = null;
		return query(TABLE_NAME,null,_whereCmd,null,_groupCmd,null,_orderCmd);
	}
	/**
	 * 清除数据库
	 */
	public void clearAll() {
		// TODO Auto-generated method stub
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
				COLUMN_TITLE + " text," +
				COLUMN_TAG + " text," +
				COLUMN_DESC + " text," +
				COLUMN_ICON + " text " +			
				")";
	}
	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
