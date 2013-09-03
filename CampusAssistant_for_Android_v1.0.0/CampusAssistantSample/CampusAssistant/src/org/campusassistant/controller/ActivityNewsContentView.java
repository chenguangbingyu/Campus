package org.campusassistant.controller;



import org.campusassistant.massample.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;

public class ActivityNewsContentView extends Activity {
	
	WebView wvNewsContent;
	
	void findView(){
		wvNewsContent = (WebView) findViewById(R.id.wv_html);
		WebSettings _settings = wvNewsContent.getSettings();
		_settings.setJavaScriptEnabled(true);
		_settings.setPluginsEnabled(true);
		_settings.setPluginState(PluginState.ON);
		_settings.setUseWideViewPort(true);
		_settings.setLoadWithOverviewMode(true);
		_settings.setSupportZoom(true);
//		_settings.setBuiltInZoomControls(true);
		
//		wvHtml.setInitialScale(100);
		
		wvNewsContent.setWebChromeClient(new WebChromeClient());
		wvNewsContent.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return false;
			}
			
		});
	}
	
	void loadUrl(String url){
		wvNewsContent.loadUrl(url); 
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		Intent _intent= getIntent();
		String _url = _intent.getStringExtra("content_url");
		
		findView();
		loadUrl(_url);
	}
	
}
