package com.campus.prime.ui.user;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.ui.user.CustomAdapter.OnItemClickListener;
import com.campus.prime.ui.view.LabelEditText;
import com.campus.prime.ui.view.LabelTextView;
import com.campus.prime.ui.view.ThemeDialog;
import com.campus.prime.ui.view.ThemeImageView;

public class EditUserActivity extends Activity implements OnItemClickListener
{
	private ThemeDialog dialog;
	List <String> loveStates = new ArrayList<String>();
	List <String> schools = new ArrayList<String>();
	List <String> academys = new ArrayList<String>();
	List <String> grades = new ArrayList<String>();
	
	ThemeImageView editUserAvatar;
	
	//ThemeEditText editGender;
	
	LabelTextView editRealName;
	LabelTextView editEmail;
	LabelEditText editNickName;
	LabelTextView editLoveState;
	LabelEditText editDescription;
	LabelTextView editSchool;
	LabelTextView editAcademy;
	LabelTextView editGrade;
	
	TextView currentView;
	
	@Override
	public  void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_user);	
		getData();
		getView();
		setParams();
		setEvent();	
		
	}
			
	public void getLoveStates()
	{
		
			loveStates.add("Alone");
			loveStates.add("Loving");
			loveStates.add("Wondering");
		
	}
	public void getSchools()
	{
		schools.add("北京大学");
		schools.add("浙江大学");
		schools.add("清华大学");
		schools.add("香港大学");
		schools.add("燕京大学");
		schools.add("复旦大学");
		schools.add("河北大学");
		schools.add("河南大学");
		schools.add("辽宁大学");
		schools.add("湖北大学");
		schools.add("云南大学");
		schools.add("湖南大学");		
	}
	public void getAcademys()
	{	
		academys.add("人文学院");
		academys.add("艺术学院");
		academys.add("信息技术学院");
		academys.add("数学与统计学院");
		academys.add("商学院");
		academys.add("会计学院");
		academys.add("金融学院");
		academys.add("旅游学院");
		academys.add("工商学院");
		academys.add("经济管理学院");
		
		
		
	}
	public void getGrades()
	{
		grades.add("2013级");
		grades.add("2012级");
		grades.add("2011级");
		grades.add("2010级");
		grades.add("2009级");
		grades.add("2008级");
		grades.add("2007级");
		grades.add("2006级");
		grades.add("2005级");
		grades.add("2004级");
		
	}
	
	
	public void getData()
	{
		getLoveStates();
		getSchools();
		getAcademys();
		getGrades();
	}
	public void getView()
	{
		
		
		editUserAvatar = (ThemeImageView) this.findViewById(R.id.edit_user_avactar);
				
		//ThemeEditText editGender;
		editRealName = (LabelTextView) this.findViewById(R.id.edit_user_realname);
		editEmail = (LabelTextView) this.findViewById(R.id.edit_user_email);
		editNickName = (LabelEditText) this.findViewById(R.id.edit_user_nickname);
		editLoveState = (LabelTextView) this.findViewById(R.id.edit_user_love);
		editDescription = (LabelEditText) this.findViewById(R.id.edit_user_description);
		editSchool = (LabelTextView) this.findViewById(R.id.edit_user_school);
		editAcademy = (LabelTextView) this.findViewById(R.id.edit_user_academy);
		editGrade = (LabelTextView) this.findViewById(R.id.edit_user_grade);
			
	}
	
	public void setParams()
	{
		editRealName.setLabel("姓名");
		editEmail.setLabel("邮箱");
		editNickName.setLabel("昵称");
		editLoveState.setLabel("恋爱状态");
		editDescription.setLabel("个人描述");
		editSchool.setLabel("所在学校");
		editAcademy.setLabel("所在学院");
		editGrade.setLabel("所在年级");
		
		
	}
		
	public void setEvent()
	{
		
		editUserAvatar.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
			}
		});	
		editLoveState.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				currentView = editLoveState;
				showDialog(loveStates);
					
				}
		});
			
		editSchool.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				//Intent intent = new Intent(EditUserActivity.this,EditUserSchoolActivity.class);
				//startActivity(intent);
				showDialog(schools);
			}
		});		
		editAcademy.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				currentView = editAcademy;
				showDialog(academys);
			}
		});	
		editGrade.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				currentView = editGrade;
				showDialog(grades);
			}
		});	
	}
		
	
	@Override
	public void onClick(String string) 
	{
		// TODO Auto-generated method stub
		dialog.dismiss();
		currentView.setText(string);
	}
		
	public void showDialog(List<String> data)
	{
		dialog = new ThemeDialog(EditUserActivity.this,R.style.my_dialog);
		LayoutInflater inflater = LayoutInflater.from(EditUserActivity.this);
		View view = inflater.inflate(R.layout.dia_list, null);
		ListView listView = (ListView) view.findViewById(R.id.dia_list);
		CustomAdapter adapter = new CustomAdapter(EditUserActivity.this,R.layout.dia_item,R.id.dia_item,data);
		adapter.setListener(this);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);		
		listView.setAdapter(adapter);			
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);			
		dialog.show();
		dialog.setContentView(view,params);
	}


}
