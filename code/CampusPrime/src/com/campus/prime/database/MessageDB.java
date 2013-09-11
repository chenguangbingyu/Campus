package com.campus.prime.database;

import android.content.ContentValues;
import android.database.Cursor;
import Database.Database;

public class MessageDB extends Database{
	
	public static final String TABLE = "MessageDB";
	public static final String TABLE_NAME = "tb_message";
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_CONTENTTYPE = "content_type";
	public static final String COLUMN_USERID = "user_id";
	public static final String COLUMN_MEDIAURL = "media_url";
	public static final String COLUMN_DATETIME = "date_time";
	public static final String COLUMN_COMMENTCOUNT = "comment_count";
	
	
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
		
	}
	
	public Cursor readFromDB() {
		String whereCmd = null;
		String groupCmd = null;
		String orderCmd = null;
		return query(TABLE_NAME,null,whereCmd,null,groupCmd,null,orderCmd);
	}
	
	public void clearAll(){
		open();
		delete(TABLE_NAME,null,null);
		close();
	}
	
	@Override
	public String createTableCommander() {
		// TODO Auto-generated method stub
		return "Create Table " + TABLE_NAME +
				"(" +
				Database._ID + " integer primary key, " +
				COLUMN_ID + " text," +
				COLUMN_CONTENT + " text," +
				COLUMN_CONTENTTYPE + " text," +
				COLUMN_USERID + " integer," +
				COLUMN_MEDIAURL + " text," +
				COLUMN_DATETIME + " text," +
				COLUMN_COMMENTCOUNT + " integer" +
				")";
	}
	
	

	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
