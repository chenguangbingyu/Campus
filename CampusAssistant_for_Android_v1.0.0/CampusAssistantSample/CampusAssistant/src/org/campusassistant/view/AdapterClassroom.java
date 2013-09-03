package org.campusassistant.view;

import org.campusassistant.controller.ActivityMapClassroomPosition;
import org.campusassistant.massample.R;
import org.campusassistant.model.MClassroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class AdapterClassroom extends BaseExpandableListAdapter {
	
	class SectionHolder{
		TextView tvTitle;
	}
	
	class CellHolder{
		TextView tvValue;
	}
	
	////////////////////////////////////////////////////////////////////

	MClassroom classroomData;
	Activity activity;
	
	// section部分的文字描述
	static final String[] arrayGroupTitle = {"教室名称","教室地址","经度","纬度","教室信息"};
	////////////////////////////////////////////////////////////////////
	
	public AdapterClassroom setParent(Activity activity){
		this.activity = activity;
		return this;
		
	}

	public void setCourseData(MClassroom classroomData) {
		this.classroomData = classroomData;
	}


	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	void showClassroomMapActivity(String longitude, String latitude){
		Intent _intent = new Intent().setClass(activity, ActivityMapClassroomPosition.class);		
		Bundle data = new Bundle();  
		data.putString("longitude",longitude);  
		data.putString("latitude",latitude); 
		_intent.putExtras(data);
		activity.startActivity(_intent);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int _groupIndex = groupPosition;
		CellHolder _cellHolder = null;
		if(convertView ==null){
			convertView = activity.getLayoutInflater().inflate(R.layout.cell_classroom_info, null);
			
			_cellHolder = new CellHolder();
			_cellHolder.tvValue = (TextView)convertView.findViewById(R.id.tv_classroom_child);
			_cellHolder.tvValue.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					switch(_groupIndex){
					case 2:{
						// 查看地图
					}
						break;
					case 3:{
						// 查看地图
						showClassroomMapActivity(classroomData.getdLongitude(),classroomData.getdLatitude());
					}
						break;
					}
				}
				
			});
			convertView.setTag(_cellHolder);
		}else{
			_cellHolder = (CellHolder)convertView.getTag();
		}
		
		switch (groupPosition){
		case 0:
			_cellHolder.tvValue.setText(classroomData.getStrName());
			break;
		case 1:
			_cellHolder.tvValue.setText(classroomData.getStrAddress());
			break;
		case 2:
			_cellHolder.tvValue.setText(classroomData.getdLongitude());
			break;
		case 3:
			_cellHolder.tvValue.setText(classroomData.getdLatitude());
			break;
		case 4:
			_cellHolder.tvValue.setText(classroomData.getWeekday(childPosition)+" "
		                               +classroomData.getStatus(childPosition)+" "
		                               +classroomData.getStart_time(childPosition)+" "
		                               +classroomData.getEnd_time(childPosition));
			break;
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0){
		case 0:
		case 1:
			return 1;
		case 2:
			return 1;
		case 3:
			return 1;
		case 4:
			return classroomData.getOpen_infoCount();
		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return arrayGroupTitle.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SectionHolder _sectionHolder = null;
		if(convertView == null){
			convertView = activity.getLayoutInflater().inflate(R.layout.group_classroom, null);
			
			_sectionHolder = new SectionHolder();
			_sectionHolder.tvTitle = (TextView)convertView.findViewById(R.id.tv_classroom_group);
			
			convertView.setTag(_sectionHolder);
			
		}else{
			_sectionHolder = (SectionHolder)convertView.getTag();
		}
		
		_sectionHolder.tvTitle.setText(arrayGroupTitle[groupPosition]);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
