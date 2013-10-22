package com.campus.prime.ui.group;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campus.prime.R;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeImageView;
import com.campus.prime.ui.view.ThemeTextView;



public class GroupBasicFragment extends GroupDetailFragment{
	
	
	
	
	ThemeImageView view_groupAvactar;
	ThemeTextView view_groupName;
	ThemeTextView view_cares;
	ThemeTextView view_mems;
	LabelTextView view_type;
	LabelTextView view_description;
	LabelTextView view_creator;
	LabelTextView view_information;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.group_basic, container,false);
		
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		getViews(view);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	protected void onLoadedFinish() {
		// TODO Auto-generated method stub
		super.onLoadedFinish();
		if(group == null)
		{
			Log.i("GetGroup","NUll");
		}
		else
		{
			setParams();
		}
		
	}
	
	
	public void getViews(View view)
	{
		view_groupAvactar = (ThemeImageView) view.findViewById(R.id.group_avactar);
		view_groupName = (ThemeTextView) view.findViewById(R.id.group_name);
		view_cares = (ThemeTextView) view.findViewById(R.id.group_cares);
		view_mems = (ThemeTextView) view.findViewById(R.id.group_mems);
		view_type = (LabelTextView) view.findViewById(R.id.group_type);
		view_description = (LabelTextView) view.findViewById(R.id.group_description);
		view_creator = (LabelTextView) view.findViewById(R.id.group_creator);
		view_information = (LabelTextView) view.findViewById(R.id.group_information);
		
	}
	public void setParams()
	{
		view_mems.setText(group.getTotal()+"");
		view_type.setLabel("圈子类型");
		view_type.setText("无");
		view_description.setLabel("圈子描述");
		view_description.setText(group.getDescription());
		view_creator.setLabel("创建者");
		view_creator.setText(group.getCreated());
		view_information.setLabel("最新公告");
		view_information.setText("无");
	}
	
	
	
	
	
}