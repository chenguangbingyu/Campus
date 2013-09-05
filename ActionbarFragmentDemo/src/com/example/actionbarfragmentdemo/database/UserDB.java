package com.example.actionbarfragmentdemo.database;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;

import Database.Database;

public class UserDB extends Database{
	public static final String TABLE = "UserDB";
	public static final String TABLE_NAME = "tb_user";
	//定义数据库表的列名
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USERNAME = "user_name";
	public static final String COLUMN_LOGINNAME = "login_name";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_SEX = "sex";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_AVATAR = "avatat";
	public static final String COLUMN_TOKEN = "token";
	
	
	public void saveToDB(ContentValues values){
		insert(TABLE_NAME,null,values);
	}
	
	public Cursor readFromDB(){
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
				Database._ID + "integer primary key, " + 
				COLUMN_ID + " text," + 
				COLUMN_USERNAME + " text," +
				COLUMN_LOGINNAME + " text," +
				COLUMN_PASSWORD + " text," +
				COLUMN_SEX + " integer," +
				COLUMN_AGE + " numeric," +
				COLUMN_EMAIL + " text," +
				COLUMN_PHONE + " text," +
				COLUMN_AVATAR + " text," +
				COLUMN_TOKEN + " text" +
				")";
		
	}

	@Override
	public String upgradeTableCommander() {
		// TODO Auto-generated method stub
		return null;
	}

}
