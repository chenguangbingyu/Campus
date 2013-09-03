package org.campusassistant.model;

import java.util.ArrayList;

import org.campusassistant.database.DBClassroom;


import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;

public class MClassroom {
	
    ArrayList<String> arrayOpen_info;
	
	Context context;
	String strID;
	String strName;
	String strAddress;
	String strOpen_infor;	
	String dLongitude;
	String dLatitude;
	
	MCourse cCourse;
	public String getStrOpen_infor() {
		return strOpen_infor;
	}
	public void setStrOpen_infor(String strOpen_infor) {
		this.strOpen_infor = strOpen_infor;
		arrayOpen_info = null;
		arrayOpen_info = new ArrayList<String>();
		
		String[] _open_info = strOpen_infor.split(";");
		for (int i=0;i<_open_info.length;++i){
			arrayOpen_info.add(_open_info[i]);
		}
	}
	
	public int getOpen_infoCount(){
		return arrayOpen_info.size();
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
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getdLongitude() {
		return dLongitude;
	}
	public void setdLongitude(String dLongitude) {
		this.dLongitude = dLongitude;
	}
	public String getdLatitude() {
		return dLatitude;
	}
	public void setdLatitude(String dLatitude) {
		this.dLatitude = dLatitude;
	}
	public void getDataFromDB() {
		// TODO Auto-generated method stub
		
	}
	public MClassroom setContext(Context context) {
		// TODO Auto-generated method stub
		
		this.context = context;
		return this;
	}
	
	public boolean saveToDB(){
		DBClassroom _db = (DBClassroom) DAOHelper.getInstance().getTable(DBClassroom.TABLE);
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBClassroom.COLUMN_ID, strID);
		_value.put(DBClassroom.COLUMN_NAME, strName);
		_value.put(DBClassroom.COLUMN_ADDRESS, strAddress);
		_value.put(DBClassroom.COLUMN_LATITUDE, dLatitude);
		_value.put(DBClassroom.COLUMN_LONGITUDE, dLongitude);
		_value.put(DBClassroom.COLUMN_OPENINFO, strOpen_infor);
		_db.saveToDB(_value);
		_db.close();
		return true;
	}
	
	public String getWeekday(int index){
		return arrayOpen_info.get(index).split(":")[0];
	}
	public String getStatus(int index){
		return arrayOpen_info.get(index).split(":")[1];
	}
	public String getStart_time(int index){
		return arrayOpen_info.get(index).split(":")[2];
	}
	public String getEnd_time(int index){
		return arrayOpen_info.get(index).split(":")[3];
	}
	
}
