package com.campus.prime.ui.user;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.core.User;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeTextView;

public class UserBasicFragment extends UserProfileFragment{
	
	
	private Boolean isDark= false;
	
	ThemeTextView view_nickname;
	LabelTextView view_realname;
	LabelTextView view_lovestate;
	LabelTextView view_description;
	LabelTextView view_school;
	LabelTextView view_academy;
	LabelTextView view_grade;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.user_basic, container,false);
		
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		getView(view);
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}
	
	
	public void getView(View view)
	{
		view_nickname = (ThemeTextView) view.findViewById(R.id.user_nickname);
		view_realname = (LabelTextView) view.findViewById(R.id.user_realname);
		view_lovestate = (LabelTextView) view.findViewById(R.id.user_lovestate);
		view_description = (LabelTextView) view.findViewById(R.id.user_description);
		view_school = (LabelTextView) view.findViewById(R.id.user_school);
		view_academy = (LabelTextView) view.findViewById(R.id.user_academy);
		view_grade = (LabelTextView) view.findViewById(R.id.user_grade);
	}
	
	public void setParams()
	{
		view_realname.setLabel("姓名");
		view_realname.setText(user.getReal_name());
		view_lovestate.setLabel("恋爱状态");
		view_lovestate.setText("单身");
		view_description.setLabel("个人描述");
		view_description.setText(user.getDescription());
		view_school.setLabel("学校");
		view_school.setText("user.getSchool()");
		view_academy.setLabel("学院");
		view_academy.setText("user.getAcademy().getName()");
		view_grade.setLabel("年级");
		view_grade.setText("user.getGrade()");
		
	}
	
	@Override
	protected void onLoadedFinish() {
		// TODO Auto-generated method stub
		super.onLoadedFinish();
		if(user == null)
		{
			Log.i("GetUser","NUll");
		}
		else
		{
			setParams();
		}
		
	}
	
}
