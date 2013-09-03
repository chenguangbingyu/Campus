package org.campusassistant.model;

import org.campusassistant.database.DBNewsCategory;

import Database.DAOHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;

public class MNewsCategory {
	//////////////////////////////////////////////////////////
	// 资讯类别id
	String strID;
	// 资讯标题
	String strTitle;
	// 资讯标签
	String strTag;
	// 资讯描述
	String strDescription;
	// 资讯图标
	String strIcon;
	// 
	//String strNewsList;
	// 
	Context context;

	public Context getContext() {
		return context;
	}

	/**
	 * 将MNewsCategory实例化对象存入数据库
	 * @return true
	 */
	public boolean saveToDB(){
		// 创建一个DBNewsCategory对象，也间接创建了DAONewsCategoryHelper对象
		DBNewsCategory _db = (DBNewsCategory) DAOHelper.getInstance().getTable(DBNewsCategory.TABLE);
		// 调用DAOBuildingHelper的getWritableDatabase()方法
		_db.open();
		ContentValues _value = new ContentValues();
		_value.put(DBNewsCategory.COLUMN_ID, strID);
		_value.put(DBNewsCategory.COLUMN_TITLE, strTitle);
		_value.put(DBNewsCategory.COLUMN_DESC, strDescription);
		_value.put(DBNewsCategory.COLUMN_TAG, strTag);
		_value.put(DBNewsCategory.COLUMN_ICON, strIcon);
		_db.saveToDB(_value);
		// 关闭数据库
		_db.close();
		return true;
	}
	/**
	 * 从数据库当中查询资讯类别信息
	 * @return
	 */
	public SparseArray<MNewsCategory> readFromDB(){
		DBNewsCategory _db = (DBNewsCategory) DAOHelper.getInstance().getTable(DBNewsCategory.TABLE);
		_db.open();
		Cursor _cursor = _db.readFromDB();
		_cursor.moveToFirst();
		int _indexNewsCategoryID = _cursor
				.getColumnIndex(DBNewsCategory.COLUMN_ID);
		int _indexNewsCategoryTitle = _cursor
				.getColumnIndex(DBNewsCategory.COLUMN_TITLE);
		int _indexNewsCategoryTag = _cursor
				.getColumnIndex(DBNewsCategory.COLUMN_TAG);
		int _indexNewsCategoryDesc = _cursor
				.getColumnIndex(DBNewsCategory.COLUMN_DESC);
		int _indexNewsCategoryIcon = _cursor
				.getColumnIndex(DBNewsCategory.COLUMN_ICON);
		
		SparseArray<MNewsCategory> _arrayNewsCategory = new SparseArray<MNewsCategory>();
		int _count = 0;
		
		while (_cursor.moveToNext()) { 
			MNewsCategory _newsCategory = new MNewsCategory();
			_newsCategory.setStrID(_cursor.getString(_indexNewsCategoryID));
			_newsCategory.setStrTitle(_cursor.getString(_indexNewsCategoryTitle));
			_newsCategory.setStrTag(_cursor.getString(_indexNewsCategoryTag));
			_newsCategory.setStrDescription(_cursor.getString(_indexNewsCategoryDesc));
			_newsCategory.setStrIcon(_cursor.getString(_indexNewsCategoryIcon));
			_arrayNewsCategory.append(_count, _newsCategory);
			_count++;
        }  
		// 关闭cursor
		_cursor.close();
		// 关闭数据库
		_db.close();
		return _arrayNewsCategory;
	}
	public MNewsCategory setContext(Context context) {
		this.context = context;
		return this;
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

	public String getStrTag() {
		return strTag;
	}

	public void setStrTag(String strTag) {
		this.strTag = strTag;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}

	public String getStrIcon() {
		return strIcon;
	}

	public void setStrIcon(String strIcon) {
		this.strIcon = strIcon;
	}

	
}
