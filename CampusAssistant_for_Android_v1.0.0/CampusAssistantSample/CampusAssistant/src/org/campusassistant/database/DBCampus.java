package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBCampus extends Database {
	
	public static final String TABLE = "DBCampus";
	public static final String TABLE_NAME = "tb_campus";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	
	
//	public DBCampus(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
	}
	
	public Cursor readFromDB(){
		String _whereCmd = null;
		String _groupCmd = null;
		String _orderCmd = null;
		return query(TABLE_NAME,null,_whereCmd,null,_groupCmd,null,_orderCmd);
	}

	
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
				COLUMN_NAME + " text" +		
				")";
	}
	
	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
