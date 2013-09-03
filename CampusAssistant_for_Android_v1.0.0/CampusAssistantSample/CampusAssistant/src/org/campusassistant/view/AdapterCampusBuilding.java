/**
 * 文件说明：校园手册建筑列表中与ListView控件对应的展现校区建筑列表的Adapter，实现ListView与数据的整合
 * 日期：2013/05/21
 */
package org.campusassistant.view;

import org.campusassistant.controller.ActivityCampusBuildingView;
import org.campusassistant.massample.R;
import org.campusassistant.model.MBuilding;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCampusBuilding extends BaseAdapter{
	//////////////////////////////////////////////////////////
	// 显示校园手册建筑列表的 item 中的控件定义区
	class CellHolder{
		// 显示建筑列表item的图标
		ImageView buildingIcon;
		// 显示建筑列表item的名称
		TextView tvName;
		// 显示建筑列表item的简介
		TextView tvBrief;
	}
	// 显示建筑列表当前页面的Activity
	Activity activity;
	// 填充建筑列表的数据
	SparseArray<MBuilding> buildingData = new SparseArray<MBuilding>();
	/**
	 *  传入显示建筑列表页面的Activity，并返回显示建筑列表的AdapterCampusBuilding
	 * @param activity
	 * @return AdapterCampusBuilding
	 */
	public AdapterCampusBuilding setParent(Activity activity){
		this.activity = activity;
		return this;
	}
	/**
	 * 设置填充显示建筑列表的数据
	 * @param data
	 */
	public void setCampusData(SparseArray<MBuilding> data){
		this.buildingData = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return buildingData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CellHolder _cellHolder = null;
		final int _position = position;
		// 准备显示控件
		if (convertView == null){
			// 设置建筑列表 item的xml布局文件
			convertView = activity.getLayoutInflater().inflate(R.layout.cell_campusbuilding_list, null);
			// 初始化显示建筑列表item的CellHolder类
			_cellHolder = new CellHolder();
			// 将显示建筑列表item的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.buildingIcon = (ImageView) convertView.findViewById(R.id.iv_building_icon);
			_cellHolder.tvName = (TextView) convertView.findViewById(R.id.tv_building_name);
			_cellHolder.tvBrief = (TextView) convertView.findViewById(R.id.tv_building_brief);
			// 设置建筑列表item的Click监听
			convertView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// 传入建筑列表item的对应的url，完成Activity跳转
					Intent _intent = new Intent().setClass(activity, ActivityCampusBuildingView.class);
					_intent.putExtra("content_url", buildingData.get(_position).getStrContent_url());
					activity.startActivity(_intent);
				}				
			});
			  
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		// 获取建筑item的对应的数据，完成数据与界面的联系
		_cellHolder.tvName.setText(buildingData.get(position).getStrName());
		_cellHolder.tvBrief.setText(buildingData.get(position).getStrBrief());
				
		return convertView;
	}
	
}
