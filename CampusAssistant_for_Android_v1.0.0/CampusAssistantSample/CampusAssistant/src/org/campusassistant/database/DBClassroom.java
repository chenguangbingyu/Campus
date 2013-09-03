package org.campusassistant.database;

import Database.Database;
import android.content.ContentValues;
import android.database.Cursor;

public class DBClassroom extends Database {
	public static final String TABLE = "DBClassroom";
	public static final String TABLE_NAME = "tb_classroom";
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_OPENINFO = "open_info";
	
//	public DBClassroom(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
	}
	
	public Cursor readFromDB(String strID){
		String _whereCmd = COLUMN_ID + "=" + strID;
		String _groupCmd = null;
		String _orderCmd = null;
		return query(TABLE_NAME,null,_whereCmd,null,_groupCmd,null,_orderCmd);
	}

	@Override
	public String createTableCommander() {
		// TODO Auto-generated method stub
		return "Create Table " + TABLE_NAME + 
				"(" +
				Database._ID + " integer primary key, " +
				COLUMN_ID + " text,"+
				COLUMN_NAME + " text," +
				COLUMN_LONGITUDE + " text," +
				COLUMN_LATITUDE + " text," +
				COLUMN_ADDRESS + " text," +
				COLUMN_OPENINFO + " text" +
				")";
	}

	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
