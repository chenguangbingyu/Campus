package org.campusassistant.controller;


import org.campusassistant.massample.R;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityMapClassroomPosition extends Activity{
	
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	//地图管理者
	BMapManager bMapManager;
	//地图的控制权
	MapController mapController;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 展现地图信息的View
	MapView mMapView;
	//精度、纬度信息
    Double longitude, latitude;
	
    
	//自定义图层
	class ClassroomOverlayItem extends ItemizedOverlay<OverlayItem>{

		public ClassroomOverlayItem(Drawable arg0, MapView arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}
		//点击图层，显示教室的详细信息
		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(ActivityMapClassroomPosition.this.getApplicationContext(), "阶梯教室301", Toast.LENGTH_LONG).show();
			return super.onTap(arg0, arg1);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		bMapManager = new BMapManager(getApplicationContext());
		// init方法的第一个参数需填入申请的API Key 
		bMapManager.init("803A37740A24D80F90C5A1ACE546B631B9548E6E", null);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//获取精度、纬度信息
		Intent _intent = getIntent();
		Bundle _data = _intent.getExtras();
		longitude=Double.parseDouble(_data.getString("longitude"));
		latitude=Double.parseDouble(_data.getString("latitude"));
		//关联显示控件
		findView();
		//显示地图
		initMapView(longitude,latitude);
		
	}

	/**
	 * 显示地图，设置地图相关参数
	 * @param longitude 精度
	 * @param latitude 纬度
	 */
	private void initMapView(double longitude, double latitude) {
		// TODO Auto-generated method stub
		
		//设置启用内置的缩放控件
		mMapView.setBuiltInZoomControls(true);
		//
		//得到mMapView的控制权，可以用它控制和驱动平移和缩放
		mapController = mMapView.getController();
		//用教室的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		GeoPoint point = new GeoPoint((int)(latitude* 1E6),(int)(longitude* 1E6));
		//设置地图中心点
		mapController.setCenter(point);
		//设置地图zoom级别
		mapController.setZoom(17);
		
		//创建图标资源（用于显示在overlayItem所标记的位置）, 添加教室所在位置
		Drawable _marker = this.getResources().getDrawable(R.drawable.icon_marka);
		mMapView.getOverlays().clear();
		OverlayItem _point = new OverlayItem(point, null, null);
		ClassroomOverlayItem _layout = new ClassroomOverlayItem(_marker, mMapView);
		_layout.addItem(_point);
		mMapView.getOverlays().add(_layout);
	}

	/**
	 * 准备界面显示控件
	 */
	private void findView() {
		// TODO Auto-generated method stub
		mMapView = (MapView)findViewById(R.id.mv_classroom);
	}
	
	//activity生命周期
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mMapView.destroy();
		
		if(bMapManager != null){
			bMapManager.destroy();
			bMapManager = null;
		}
		super.onDestroy();
	}
	
	//activity生命周期
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mMapView.onPause();
		if(bMapManager != null){
			bMapManager.stop();
		}
		super.onPause();
	}

	//activity生命周期
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mMapView.onResume();
		if(bMapManager != null){
			bMapManager.start();
		}
		super.onResume();
	}



}
