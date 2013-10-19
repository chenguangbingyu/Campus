package com.campus.prime.utils;

import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.campus.prime.R;

import android.app.Activity;
import android.content.Intent;

public class IntentUtil {
	public static void start_activity(Activity activity,Class<?> cls,Map<String,Integer> params)
	{
		Intent intent=new Intent();
		intent.setClass(activity,cls);
		if(params != null){
			for(Map.Entry<String,Integer> entry : params.entrySet()){
				intent.putExtra(entry.getKey(),entry.getValue());
			}
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
}
