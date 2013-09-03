package org.campusassistant.model;

import org.campusassistant.database.DBBuilding;

import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;

public class MBuilding {

	//////////////////////////////////////////////////////////
	String strID;
	String strCompusID;
	String strName;
	String strBrief;
	String strContent_url;
	Context context;
	public Context getContext() {
		return context;
	}
	public MBuilding setContext(Context context) {
		this.context = context;
		return this;
	}
	public boolean saveToDB(){
		DBBuilding _db = (DBBuilding) DAOHelper.getInstance().getTable(DBBuilding.TABLE);
		
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBBuilding.COLUMN_ID, strID);
		_value.put(DBBuilding.COLUMN_CAMPUSID, strCompusID);
		_value.put(DBBuilding.COLUMN_NAME, strName);
		_value.put(DBBuilding.COLUMN_BRIEF, strBrief);
		_value.put(DBBuilding.COLUMN_CONTENTURL, strContent_url);
		_db.saveToDB(_value);
		_db.close();
		return true;		
	}	
	public SparseArray<MBuilding> readFromDB(String campusId){
		DBBuilding _db = (DBBuilding) DAOHelper.getInstance().getTable(DBBuilding.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB(campusId);
		_cursor.moveToFirst();
		int _indexBuildingID = _cursor.getColumnIndex(DBBuilding.COLUMN_ID);
		int _indexBuildingCampusID = _cursor.getColumnIndex(DBBuilding.COLUMN_CAMPUSID);
		int _indexBuildingName = _cursor.getColumnIndex(DBBuilding.COLUMN_NAME);
		int _indexBuildingContentUrl = _cursor.getColumnIndex(DBBuilding.COLUMN_CONTENTURL);
		int _indexBuildingBrief = _cursor.getColumnIndex(DBBuilding.COLUMN_BRIEF);
		SparseArray<MBuilding> _arrayBuilding = new SparseArray<MBuilding>();
		int _count = 0;
		while (!_cursor.isAfterLast()) { 
			MBuilding _building = new MBuilding();
			_building.setStrID(_cursor.getString(_indexBuildingID));
			_building.setStrCompusID(_cursor.getString(_indexBuildingCampusID));
			_building.setStrName(_cursor.getString(_indexBuildingName));
			_building.setStrContent_url(_cursor.getString(_indexBuildingContentUrl));
			_building.setStrBrief(_cursor.getString(_indexBuildingBrief));
			_arrayBuilding.append(_count, _building);
			_count++;
			_cursor.moveToNext();
        }
		_cursor.close();
		_db.close();
		return _arrayBuilding;		
	}
	
	
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	public String getStrCompusID() {
		return strCompusID;
	}
	public void setStrCompusID(String strCompusID) {
		this.strCompusID = strCompusID;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrContent_url() {
		return strContent_url;
	}
	public void setStrContent_url(String strContent_url) {
		this.strContent_url = strContent_url;
	}
	public String getStrBrief() {
		return strBrief;
	}
	public void setStrBrief(String strBrief) {
		this.strBrief = strBrief;
	}
}
