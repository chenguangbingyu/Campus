package org.campusassistant.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityCampusBuildingView extends Activity {

    WebView wvCampusBuildingContent;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent _intent= getIntent();
		String _content_url = _intent.getStringExtra("content_url");
		//实例化WebView对象 
		wvCampusBuildingContent = new WebView(this); 
        //设置WebView属性，能够执行Javascript脚本 
		wvCampusBuildingContent.getSettings().setJavaScriptEnabled(true); 
        //加载需要显示的网页 
		wvCampusBuildingContent.loadUrl(_content_url); 
        //设置Web视图 
        setContentView(wvCampusBuildingContent);

		
	}
	
}
