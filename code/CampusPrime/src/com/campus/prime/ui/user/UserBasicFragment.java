package com.campus.prime.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeTextView;

public class UserBasicFragment extends UserProfileFragment{
	
	private Boolean isDark= false;
	
	
	private String user_nickname;
	private String user_realname;
	private String user_gender;
	private String user_description;
	private String user_school;
	private String user_academy;
	private String user_grade;
	
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
		setParams();
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
		view_nickname.setText("xiaoqi");
		view_realname.setLabel("姓名");
		view_realname.setText("小七");
		view_lovestate.setLabel("恋爱状态");
		view_lovestate.setText("单身");
		view_description.setLabel("个人描述");
		view_description.setText("我是来自哈姆雷特星球的小七，初到宝地，请多多关照!");
		view_school.setLabel("学校");
		view_school.setText("菲律宾大学");
		view_academy.setLabel("学院");
		view_academy.setText("扛饿继续教育学院");
		view_grade.setLabel("年级");
		view_grade.setText("2013级");
		
	}
	
	
	
	
}
