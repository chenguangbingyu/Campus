package org.campusassistant.database;
import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBBuilding extends Database {
	public static final String TABLE = "DBBuilding";
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 数据库表的名称
	public static final String TABLE_NAME = "tb_campusbuilding";
	// 字段：建筑id
	public static final String COLUMN_ID = "id";
	// 字段：所在校区id
	public static final String COLUMN_CAMPUSID = "campus_id";
	// 字段 ：建筑名称
	public static final String COLUMN_NAME = "name";
	// 字段：建筑简介
	public static final String COLUMN_BRIEF = "brief";
	// 字段：建筑内容链接
	public static final String COLUMN_CONTENTURL = "content_url";
	
	/**
	 * 将传入的ContentValues类型数据存入数据库
	 * @param values
	 */
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
	}
	
	/**
	 * 查询数据库，返回查询结果Cursor对象
	 * @param campusId
	 * @return
	 */
	public Cursor readFromDB(String campusId){
		String _whereCmd = COLUMN_CAMPUSID + " = " + campusId;
		String _groupCmd = null;
		String _orderCmd = null;
		return query(TABLE_NAME,null,_whereCmd,null,_groupCmd,null,_orderCmd);
	}

	/**
	 * 清空数据库表
	 */
	public void clearAll() {
		// TODO Auto-generated method stub
		open();
		delete(TABLE_NAME, null, null);
		close();
	}
	
	@Override
	public String createTableCommander(){
		return "Create Table " + TABLE_NAME + 
				"(" +
				Database._ID + " integer primary key, " +
				COLUMN_ID + " text,"+
				COLUMN_CAMPUSID + " text," +
				COLUMN_NAME + " text," +
				COLUMN_CONTENTURL + " text," +
				COLUMN_BRIEF + " text" +
				")";
	}
	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
