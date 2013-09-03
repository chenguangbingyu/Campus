package org.campusassistant.model;

import org.campusassistant.database.DBCampus;

import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;

public class MCampus {
	//////////////////////////////////////////////////////////
	// 校区id
	String strID;
	// 校区名称
	String strName; 
	// 
	Context context;
	
	public Context getContext() {
		return context;
	}
	public MCampus setContext(Context context) {
		this.context = context;
		return this;
	}
	/**
	 * 存入数据库
	 * @return true
	 */
	public boolean saveToDB(){
		// 创建一个DBCampus对象，也间接创建了DAOCampusHelper对象
		DBCampus _db = (DBCampus) DAOHelper.getInstance().getTable(DBCampus.TABLE);
		// 调用DAOCampusHelper的getWritableDatabase()方法
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBCampus.COLUMN_ID, strID);
		_value.put(DBCampus.COLUMN_NAME, strName);
		_db.saveToDB(_value);
		// 关闭数据库
		_db.close();
		return true;
		
	}
	/**
	 * 从数据库当中查询校区信息
	 * @param campusId
	 * @return
	 */
	public SparseArray<MCampus> readFromDB(){
		// 创建一个DBCampus对象，也间接创建了DAOCampusHelper对象
		DBCampus _db = (DBCampus) DAOHelper.getInstance().getTable(DBCampus.TABLE);
		// 调用DAOCampusHelper的getWritableDatabase()方法
		_db.open();
		
		Cursor _cursor = _db.readFromDB();
		
		int _indexCampusID = _cursor
				.getColumnIndex(DBCampus.COLUMN_ID);
		int _indexCampusName = _cursor
				.getColumnIndex(DBCampus.COLUMN_NAME);
		
		SparseArray<MCampus> _arrayCampus = new SparseArray<MCampus>();
		int _count = 0;
		 
		while (_cursor.moveToNext()) { 
			MCampus _campus = new MCampus();
			_campus.setStrID(_cursor.getString(_indexCampusID));
			_campus.setStrName(_cursor.getString(_indexCampusName));
			_arrayCampus.append(_count, _campus);
			_count++;
        }  
		_cursor.close();
		_db.close();
		return _arrayCampus;
		
	}
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
}
