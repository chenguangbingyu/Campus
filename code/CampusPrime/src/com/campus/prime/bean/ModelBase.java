package com.campus.prime.bean;

import android.content.Context;
import android.util.SparseArray;

public abstract class ModelBase {
	private Context mContext;
	
	public Context getContext(){
		return mContext;
	}
	
	public ModelBase setContext(Context mContext){
		this.mContext = mContext;
		return this;
	}
	
	public abstract boolean saveToDB();
	public abstract SparseArray<ModelBase> readFromDB();
}
