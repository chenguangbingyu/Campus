/**
 * 文件说明：校园手册校区列表中与ListView控件对应的展现校区列表的Adapter，实现ListView与数据的整合
 * 日期：2013/05/20
 */
package org.campusassistant.view;

import org.campusassistant.controller.ActivityCampusBuilding;
import org.campusassistant.massample.R;
import org.campusassistant.model.MCampus;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterCampus extends BaseAdapter{
	//////////////////////////////////////////////////////////
	// 显示校园手册校区列表的 item 中的控件定义区
	class CellHolder{
		// 展现校区列表item的名字
		TextView tvName;
	}
	// 显示校园手册当前页面的Activity
	Activity activity;
	// 填充校园手册校区列表的数据
	SparseArray<MCampus> campusData = new SparseArray<MCampus>();
	/**
	 *  传入显示校园手册校区列表页面的Activity，并返回显示校园手册校区列表的AdapterCampus
	 * @param activity
	 * @return AdapterCampus
	 */
	public AdapterCampus setParent(Activity activity){
		this.activity = activity;
		return this;
	}
	/**
	 * 设置填充显示校园手册校区列表的AdapterCampus的数据
	 * @param data
	 */
	public void setCampusData(SparseArray<MCampus> data){
		this.campusData = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return campusData.size();
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
		if (convertView == null){
			// 设置校园手册校区列表 item的xml布局文件
			convertView = activity.getLayoutInflater().inflate(R.layout.cell_campus, null);
			// 初始化显示校园手册校区列表item的CellHolder类
			_cellHolder = new CellHolder();
			// 将显示校园手册校区列表item的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.tvName = (TextView) convertView.findViewById(R.id.tv_campus_name);
			// 设置校园手册校区列表item的Click监听
			convertView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// 传入校园手册校区item的对应的id，完成Activity跳转
					Intent _intent = new Intent().setClass(activity, ActivityCampusBuilding.class);
					_intent.putExtra("campus_id", campusData.get(_position).getStrID());
					activity.startActivity(_intent);
				}				
			});
			  
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		// 获取校园手册校区item的对应的数据，完成数据与界面的联系
		_cellHolder.tvName.setText(campusData.get(position).getStrName());
				
		return convertView;
	}
	
}
