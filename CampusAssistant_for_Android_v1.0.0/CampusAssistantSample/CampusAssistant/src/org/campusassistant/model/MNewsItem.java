package org.campusassistant.model;

import org.campusassistant.database.DBNewsList;

import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;

public class MNewsItem {
	String strID;
	String strTitle;
	String strBrief;
	String strDatetime;
	String strAuthor;
	String strTag;
	String strType;
	String strSmall_icon;
	String strBig_image;
	String strContent_url;
	
	Context context;

	public Context getContext() {
		return context;
	}

	public MNewsItem setContext(Context context) {
		this.context = context;
		return this;
	}
	
	public boolean saveToDB(){
		DBNewsList _db = (DBNewsList) DAOHelper.getInstance().getTable(DBNewsList.TABLE);
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBNewsList.COLUMN_ID, strID);
		_value.put(DBNewsList.COLUMN_TITLE, strTitle);
		_value.put(DBNewsList.COLUMN_BRIEF, strBrief);
		_value.put(DBNewsList.COLUMN_DATETIME, strDatetime);
		_value.put(DBNewsList.COLUMN_AUTHOR, strAuthor);
		_value.put(DBNewsList.COLUMN_TAG, strTag);
		_value.put(DBNewsList.COLUMN_TYPE, strType);
		_value.put(DBNewsList.COLUMN_SMALLICON, strSmall_icon);
		_value.put(DBNewsList.COLUMN_BIGIMAGE, strBig_image);
		_value.put(DBNewsList.COLUMN_CONTENTURL, strContent_url);
		_db.saveToDB(_value);
		_db.close();
		return true;
	}

	public SparseArray<MNewsItem> readFromDB(){
		DBNewsList _db = (DBNewsList) DAOHelper.getInstance().getTable(DBNewsList.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB();
		int _indexNewsCategoryID = _cursor
				.getColumnIndex(DBNewsList.COLUMN_ID);
		int _indexNewsCategoryTitle = _cursor
				.getColumnIndex(DBNewsList.COLUMN_TITLE);
		int _indexNewsCategoryBrief = _cursor
				.getColumnIndex(DBNewsList.COLUMN_BRIEF);
		int _indexNewsCategoryDatetime = _cursor
				.getColumnIndex(DBNewsList.COLUMN_DATETIME);
		int _indexNewsCategoryAuthor = _cursor
				.getColumnIndex(DBNewsList.COLUMN_AUTHOR);
		int _indexNewsCategoryTag = _cursor
				.getColumnIndex(DBNewsList.COLUMN_TAG);
		int _indexNewsCategoryType = _cursor
				.getColumnIndex(DBNewsList.COLUMN_TYPE);
		int _indexNewsCategorySmallIcon = _cursor
				.getColumnIndex(DBNewsList.COLUMN_SMALLICON);
		int _indexNewsCategoryBigImage = _cursor
				.getColumnIndex(DBNewsList.COLUMN_BIGIMAGE);
		int _indexNewsCategoryContentURL = _cursor
				.getColumnIndex(DBNewsList.COLUMN_CONTENTURL);
		
		SparseArray<MNewsItem> _arrayNewsList = new SparseArray<MNewsItem>();
		int _count = 0;
		while (_cursor.moveToNext()) { 
			MNewsItem _newsList = new MNewsItem();
			_newsList.setStrID(_cursor.getString(_indexNewsCategoryID));
			_newsList.setStrTitle(_cursor.getString(_indexNewsCategoryTitle));
			_newsList.setStrBrief(_cursor.getString(_indexNewsCategoryBrief));
			_newsList.setStrDatetime(_cursor.getString(_indexNewsCategoryDatetime));
			_newsList.setStrAuthor(_cursor.getString(_indexNewsCategoryAuthor));
			_newsList.setStrTag(_cursor.getString(_indexNewsCategoryTag));
			_newsList.setStrType(_cursor.getString(_indexNewsCategoryType));
			_newsList.setStrSmall_icon(_cursor.getString(_indexNewsCategorySmallIcon));
			_newsList.setStrBig_image(_cursor.getString(_indexNewsCategoryBigImage));
			_newsList.setStrContent_url(_cursor.getString(_indexNewsCategoryContentURL));
			_arrayNewsList.append(_count, _newsList);
			_count++;
        }  
		_cursor.close();
		_db.close();
		return _arrayNewsList;
	}
	public String getStrID() {
		return strID;
	}

	public void setStrID(String strID) {
		this.strID = strID;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrBrief() {
		return strBrief;
	}

	public void setStrBrief(String strBrief) {
		this.strBrief = strBrief;
	}

	public String getStrDatetime() {
		return strDatetime;
	}

	public void setStrDatetime(String strDatetime) {
		this.strDatetime = strDatetime;
	}

	public String getStrAuthor() {
		return strAuthor;
	}

	public void setStrAuthor(String strAuthor) {
		this.strAuthor = strAuthor;
	}

	public String getStrTag() {
		return strTag;
	}

	public void setStrTag(String strTag) {
		this.strTag = strTag;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getStrSmall_icon() {
		return strSmall_icon;
	}

	public void setStrSmall_icon(String strSmall_icon) {
		this.strSmall_icon = strSmall_icon;
	}

	public String getStrBig_image() {
		return strBig_image;
	}

	public void setStrBig_image(String strBig_image) {
		this.strBig_image = strBig_image;
	}

	public String getStrContent_url() {
		return strContent_url;
	}

	public void setStrContent_url(String strContent_url) {
		this.strContent_url = strContent_url;
	}
}
