/**
 * 文件说明：网络课表中，查询课表的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;

import org.campusassistant.massample.MainActivity;
import org.campusassistant.massample.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityQuerySchedule extends Activity{
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	//文本控件，输入要查询的城市
	EditText etChooseCity;
	//文本控件，输入要查询的学校
	EditText etChooseSchool;
	//文本控件，输入要查询的学院
	EditText etChooseInstitute;
	//文本控件，输入要查询的专业
	EditText etChooseMajor;
	//文本控件，输入要查询的年级
	EditText etChooseGrade;
	//文本控件，输入要查询的班级
	EditText etChooseClass;
	//查询按钮
	Button btnQuerySchedule;
	//左上方，返回按钮
	Button btnTitleLeft;
	//该部分的名称，课程信息
	TextView tvTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_schedule);
		//关联显示控件
		findView();
	}

	//创建菜单menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * 准备界面显示控件
	 */
	private void findView() {
		// TODO Auto-generated method stub
		
		tvTitle = (TextView)this.findViewById(R.id.tv_title);
		tvTitle.setText(R.string.top_title2);
		
		etChooseCity = (EditText) this.findViewById(R.id.et_choose_city);
		etChooseSchool = (EditText)this.findViewById(R.id.et_choose_school);
		etChooseInstitute = (EditText)this.findViewById(R.id.et_choose_school);
		etChooseMajor = (EditText)this.findViewById(R.id.et_choose_major);
		etChooseGrade = (EditText)this.findViewById(R.id.et_choose_grade);
		etChooseClass = (EditText)this.findViewById(R.id.et_choose_class);
		//查询课表按钮
		btnQuerySchedule = (Button)this.findViewById(R.id.btn_query_schedule);
		btnQuerySchedule.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String strChooseCity = etChooseCity.getText().toString();
				String strChooseSchool = etChooseSchool.getText().toString();
				String strChooseInstitute = etChooseInstitute.getText().toString();
				String strChooseMajor = etChooseInstitute.getText().toString();
				String strChooseGrade = etChooseGrade.getText().toString();
				String strChooseClass = etChooseClass.getText().toString();
				
				Intent _intent = new Intent(ActivityQuerySchedule.this,ActivityCurriculum.class);
				startActivity(_intent);
				
			}
		});
		
		//返回按钮
		btnTitleLeft = (Button)this.findViewById(R.id.btn_title_left);
		btnTitleLeft.setText(R.string.top_button_ret);
		btnTitleLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent _intent = new Intent(ActivityQuerySchedule.this,MainActivity.class);
				startActivity(_intent);
			}
		});
		
	}
	
}