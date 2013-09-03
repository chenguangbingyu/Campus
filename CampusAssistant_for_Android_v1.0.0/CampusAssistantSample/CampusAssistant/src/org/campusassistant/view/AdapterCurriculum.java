/**
 * 文件说明：网络课表的Adapter，实现GridView与数据的整合
 * 日期：2013/05/20
 */
package org.campusassistant.view;

import java.util.ArrayList;

import org.campusassistant.controller.ActivityCourse;
import org.campusassistant.massample.R;
import org.campusassistant.model.MCurriculum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class AdapterCurriculum extends BaseAdapter {
	//////////////////////////////////////////////////////////
	// 显示课程表内课程的定义区
	//容器，显示课程名称
	class CellHolder{
		//文本控件，显示课程名称
		TextView tvCourse;
	}

	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 填充课程表的数据
	MCurriculum curriculumData;
	//显示课程表当前页面的Activity
	Activity activityParent;

	/**
	 * 传入显示课程表的Activity，返回显示课程表的数据AdapterCurriculum
	 * @param activity 显示课程表的Activity
	 * @return AdapterCurriculum 显示课程表的数据AdapterCurriculum
	 */
	public AdapterCurriculum setParent(Activity activity){
		this.activityParent = activity;
		return this;
	}
	/**
	 * 设置填充课程表的数据
	 * @param data 课程表数据
	 */
	public void setCurriculumData(MCurriculum data){
		this.curriculumData = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7*8;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 设置数据源显示，_section行，_weekday列
		final int _section = position / 7;
		final int _weekday = position % 7;
		
		// 第一次加载课程表信息
		CellHolder _cellHolder = null;
		// 准备显示控件
		if (convertView == null){
			//设置课程表的布局文件
			convertView = activityParent.getLayoutInflater().inflate(R.layout.cell_curriculum, null);
			//初始化课程表数据
			_cellHolder = new CellHolder();
			//将显示课程表的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.tvCourse = (TextView) convertView.findViewById(R.id.tv_course);
			_cellHolder.tvCourse.setOnClickListener(new OnClickListener(){
				//设置课程表内课程的监听事件
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//课程ID
					ArrayList<String> _arrayIds = curriculumData.getCourseId(_section, _weekday);
					if (_arrayIds.size() > 0){
						Intent _intent = new Intent().setClass(activityParent, ActivityCourse.class);
						//intent传递课程id列表和第几行第几列的位置信息
						_intent.putStringArrayListExtra("course_id", _arrayIds);
						_intent.putExtra("weekday", _weekday);
						_intent.putExtra("section", _section);
						activityParent.startActivity(_intent);
					} else {
						new AlertDialog.Builder(activityParent)
								.setTitle("提示:")
								.setMessage("这节没有课")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
											}
										}).show();
					}
				}
				
			});
			
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		
		//返回的是课程名称的数组
		ArrayList<String> _arrayCourseName = curriculumData.getCourseName(_section, _weekday);
		//获得课程类型的ArrayList
		ArrayList<String> _arrayCourseTypeList = curriculumData.getCourseType(_section, _weekday);
		int _courseCountPerSection = curriculumData.getNumCoursePerClassHour(_section, _weekday);
		if (_arrayCourseTypeList.size() <= 0 || _arrayCourseName.size() <= 0){
			_cellHolder.tvCourse.setText("");
		}
		else {
			//将课程名称数组拼接成一个字符串显示，中间以“/”连接
			//在_strCourseNameList中嵌入html标记，用来标记不同的颜色
			String _strCourseNameList = "";
			for(int i=0;i<_courseCountPerSection;i++){
				
				if(i == 0){
					
					if(_arrayCourseTypeList.get(i).equals("1"))
						//如果是必修，即课程type=1，显示红色
						_strCourseNameList = "<font color=#ff0000>" + _arrayCourseName.get(0) + "</font>";
					else if(_arrayCourseTypeList.get(i).equals("2"))
						//如果是选修，即课程type=2，显示蓝色
						_strCourseNameList = "<font color=#0000ff>" + _arrayCourseName.get(0) + "</font>";
					else
						//其他情况，显示黑色
						_strCourseNameList = _arrayCourseName.get(0);
				}
				else{
					if(_arrayCourseTypeList.get(i).equals("1"))
						//如果是必修，即课程type=1，显示红色
						_strCourseNameList += "/" + "<font color=#ff0000>" + _arrayCourseName.get(i) + "</font>";
					else if(_arrayCourseTypeList.get(i).equals("2"))
						//如果是选修，即课程type=2，显示蓝色
						_strCourseNameList += "/" + "<font color=#0000ff>" + _arrayCourseName.get(i) + "</font>";
					else
						//其他情况，显示黑色
						_strCourseNameList += "/" + _arrayCourseName.get(i);
				}
			}
			//设置课程表内的课程数据，完成界面与数据的绑定，通过内嵌html标签，实现必修课、选修课等的不同颜色标记
			_cellHolder.tvCourse.setText(Html.fromHtml(_strCourseNameList));
		}
		
		
		return convertView;
	}
}
