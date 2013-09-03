/**
 * 文件说明：网络课表中，分享资料的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;


import org.campusassistant.massample.R;

import BDCloud.BDCloudFile;
import BDCloud.BDCloudFile.BDCloudFileDelegate;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.oauth.BaiduOAuth;
import com.baidu.oauth.BaiduOAuth.BaiduOAuthResponse;

public class ActivityShareFile extends Activity implements BDCloudFileDelegate {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	//Preference存储的文件名称
	private String PREF_NAME = "ACCESS_TOKEN"; 
	//Preference存储数据的键值
	private String KEY = "access_token"; 
	//在百度开发者上注册应用时的API KEY
	private final static String mbApiKey = "daKD2szVFMRr3GNtwzAqK1SG"; //your api_key";
//	private final static String mbApiKey = "L6g70tBRRIXLsY0Z3HwKqlRE";
	//设置上传文件的路径，"/apps/" + 开启API权限时输入的路径
	private final static String mbRootPath =  "/apps/Baidu_College";
//	private final static String mbRootPath =  "/apps/pcstest_oauth";
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	//显示需要上传的文件路径
	private EditText etPath;
	//搜索文件按钮，上传文件按钮
	private Button btnShareFile, btnFileExplorer;
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	//百度账号登陆的秘钥
	private String accessToken;	
	//存储accessToken
	SharedPreferences sp;
	//上传的文件路径
	private String strFilePath;
	//
	String strFileName;
	//Handler处理上传线程，刷新界面
	private Handler mbUiThreadHandler = null;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_file);
		mbUiThreadHandler = new Handler();
		//获得SharedPreferences实例
		sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		//从SharedPreferences中获得accessToken
		accessToken = sp.getString(KEY, null); 
		//关联显示控件
		findView();

	}

	/**
	 * 准备界面显示控件
	 */
	private void findView() {
		// TODO Auto-generated method stub
		//获得上传的文件路径
		Intent _intent = getIntent();
		strFilePath = _intent.getStringExtra("filePath");
		strFileName = _intent.getStringExtra("fileName");
		
		etPath = (EditText) findViewById(R.id.et_path);
		etPath.setText(strFilePath);

		//实例化Button
		btnShareFile = (Button) findViewById(R.id.btn_share_file);
		btnFileExplorer = (Button) findViewById(R.id.btn_file_explorer);
		//搜索文件
		btnFileExplorer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent().setClass(ActivityShareFile.this, ActivityFileExplorer.class);
				startActivity(_intent);
			}});
		//上传文件
		btnShareFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareFile();
			}
		});

	}
	
	/**
	 * 上传文件操作
	 */
	protected void shareFile() {
		// TODO Auto-generated method stubString 

		if(null == accessToken){
			login();
		}
		else {
			BDCloudFile _cloudFile = new BDCloudFile().setDelegate(this);
			_cloudFile.sendToCloud(this.getApplicationContext(), accessToken, strFilePath, mbRootPath);
		}

//			Thread workThread = new Thread(new Runnable(){
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					String tempFile = "/mnt" + strFilePath;
//					
//					BaiduPCSClient api = new BaiduPCSClient();
//					api.setAccessToken(accessToken);
//					
//					final BaiduPCSActionInfo.PCSFileInfoResponse response = api.uploadFile(tempFile, mbRootPath + "/" + strFileName, new BaiduPCSStatusListener() {
//						
//						@Override
//						public void onProgress(long bytes, long total) {
//							// TODO Auto-generated method stub
//							final long _lBytes = bytes;
//							final long _lTotal = total;
//							
//							mbUiThreadHandler.post(new Runnable(){
//
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									Toast.makeText(getApplicationContext(), "total: " + _lTotal + "bytes: " + _lBytes, Toast.LENGTH_SHORT).show();
//								}});
//						}
//
//						@Override
//						public long progressInterval() {
//							// TODO Auto-generated method stub
//							return 100;
//						}
//					});
//					
//					mbUiThreadHandler.post(new Runnable(){
//
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							Toast.makeText(getApplicationContext(), response.status.errorCode + " " + response.status.message + " " + response.commonFileInfo.blockList, Toast.LENGTH_SHORT).show();
//							
//						}});
//				}});
//			workThread.start();
		
	}
	
	/**
	 * 下载文件
	 */
//    private void downloadFile(){
//    	if(null == accessToken)
//			login();
//
//    		Thread workThread = new Thread(new Runnable(){
//				public void run() {
//
//		    		BaiduPCSClient api = new BaiduPCSClient();
//		    		api.setAccessToken(accessToken);
//		    		String source = mbRootPath + "/zzz.jpg";
//		    		String target = "/mnt/sdcard/123.mp4";
//		    		final BaiduPCSActionInfo.PCSSimplefiedResponse ret = api.downloadFileFromStream(source, target, new BaiduPCSStatusListener(){
//		    			
//						@Override
//						public void onProgress(long bytes, long total) {
//							// TODO Auto-generated method stub
//							final long _lBytes = bytes;
//							final long _lTotal = total;
//
//				    		mbUiThreadHandler.post(new Runnable(){
//				    			public void run(){
//				    				Toast.makeText(getApplicationContext(), "total: " + _lTotal + "    downloaded:" + _lBytes, Toast.LENGTH_SHORT).show();
//				    			}
//				    		});		
//						}
//						
//						@Override
//						public long progressInterval(){
//							return 500;
//						}
//		    			
//		    		});
//		    		
//		    		mbUiThreadHandler.post(new Runnable(){
//		    			public void run(){
//		    				Toast.makeText(getApplicationContext(), "Download files:  " + ret.errorCode + "   " + ret.message, Toast.LENGTH_SHORT).show();
//		    			}
//		    		});	
//				}
//			});
//			 
//    		workThread.start();
//    }
    
	/**
	 * 百度OAuth
	 */
	private void login() {
		// TODO Auto-generated method stub
		BaiduOAuth oauthClient = new BaiduOAuth();
		oauthClient.startOAuth(this, mbApiKey, new BaiduOAuth.OAuthListener() {
			@Override
			public void onException(String msg) {
				Toast.makeText(getApplicationContext(), "Login failed " + msg, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onComplete(BaiduOAuthResponse response) {
				if(null != response){
					accessToken = response.getAccessToken();
					Toast.makeText(getApplicationContext(), "Token: " + accessToken + "    User name:" + response.getUserName(), Toast.LENGTH_SHORT).show();
					
					//保存access_token，文件名： ACCESS_TOKEN，KEY：access_token
					Context ctx = ActivityShareFile.this;
					sp = ctx.getSharedPreferences("ACCESS_TOKEN", MODE_PRIVATE); 
					Editor ed = sp.edit();
					//清除信息
					ed.clear();
					ed.putString("access_token", accessToken);
					ed.commit();
				}
			}
			@Override
			public void onCancel() {
				Toast.makeText(getApplicationContext(), "Login cancelled", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void updateDone(int arg0, String arg1) {
		// TODO Auto-generated method stub
		final int code = arg0;
		final String msg = arg1;
		mbUiThreadHandler.post(new Runnable(){
		public void run(){
			Toast.makeText(getApplicationContext(), "Download files:  " + code + "   " + msg, Toast.LENGTH_SHORT).show();
		}
	});	
	}

	@Override
	public void uploading(long arg0, long arg1) {
		// TODO Auto-generated method stub
		final long _lBytes = arg0;
		final long _lTotal = arg1;
		mbUiThreadHandler.post(new Runnable(){
		public void run(){
			Toast.makeText(getApplicationContext(), "total: " + _lTotal + "    downloaded:" + _lBytes, Toast.LENGTH_SHORT).show();
		}
	});	
	}
	
	

}
