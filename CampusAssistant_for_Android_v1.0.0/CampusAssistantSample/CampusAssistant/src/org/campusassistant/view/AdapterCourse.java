/**
 * 文件说明：网络课表中，课程详细信息的Adapter，实现ExpandableListView与数据的整合
 * 日期：2013/05/20
 */
package org.campusassistant.view;

import java.util.ArrayList;

import org.campusassistant.controller.ActivityClassroom;
import org.campusassistant.massample.R;
import org.campusassistant.model.MCourse;
import org.campusassistant.model.MMyCurriculum;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class AdapterCourse extends BaseExpandableListAdapter {
	//////////////////////////////////////////////////////////
	// 显示课程详细信息的item定义区
	//ExpandableListView的组容器，显示课程名称
	class SectionHolder {
		//文本控件，显示课程名称
		TextView tvTitle;
		//单选按钮，标识课程是否被选入到我的课表
		CheckBox cbChoose;
	}
	//ExpandableListView的子容器，显示课程名称下的详细信息
	class CellHolder {
		//文本控件，显示课程详细信息下的标题
		TextView tvLable;
		//文本控件，显示课程详细信息下的内容
		TextView tvValue;
	}

	//////////////////////////////////////////////////////////
	// 数据源定义区
	// 选择的是星期几的第几节课，由activity传入 
	int intWeekday, intSection;
	//课程详细信息数据列表，采用ArrayList，课表上同一时间可能有多节课
	ArrayList<MCourse> courseData;
	//课程详细信息中教室ID列表，采用ArrayList，课表上同一时间可能有多节课对应多个教室
	ArrayList<String> arrayClassroomIds;
	//我的课程表数据
	MMyCurriculum myCurriculumData;
	//显示课程详细信息的当前页面的Activity
	Activity activity;
	
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// cell部分课程详细信息的标题的文字描述
	static final String[] LABELS = { "课程名称", "课程描述", "周数类型", "开始周", "结束周", "学分", "授课教师", "上课地点" };

	/**
	 * 传入显示课程详细信息页面的Activity，并返回课程详细信息的AdapterCourse
	 * @param activity 显示课程详细信息页面的Activity
	 * @return AdapterCourse 课程详细信息的AdapterCourse
	 */
	public AdapterCourse setParent(Activity activity) {
		this.activity = activity;
		return this;
	}
	/**
	 * 设置课程详细信息的数据
	 * @param arrayCourseData 课程详细信息的数据
	 */
	public void setCourseData(ArrayList<MCourse> arrayCourseData) {
		this.courseData = arrayCourseData;
	}
	//设置课程数据在我的课表中的填充位置
	public void setCourseWeekdayAndSection(int weekday, int section){
		this.intWeekday = weekday;
		this.intSection = section;
	}
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	//展示ExpandableListView的子控件数据 
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int _groupIndex = groupPosition;
		final int _childIndex = childPosition;
		CellHolder _cellHolder = null;
		//第一次加载课程信息
		if (convertView == null) {
			//设置课程详细信息的布局文件
			convertView = activity.getLayoutInflater().inflate(R.layout.cell_course_info, null);
			//初始化课程详细信息
			_cellHolder = new CellHolder();
			//将显示课程详细信息的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.tvLable = (TextView) convertView.findViewById(R.id.tv_course_child1);
			_cellHolder.tvValue = (TextView) convertView.findViewById(R.id.tv_course_child2);
			//设置课程详细信息的监听事件
			_cellHolder.tvValue.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					switch (_childIndex) {
					case 0:// 课程名称
					case 1:// 课程描述
					case 2:// 周数类型
					case 3:// 开始周
					case 4:// 结束周
					case 5:// 学分
					case 6: {
						// 查看教师详细信息
					}
						break;
					case 7: {
						// 查看教室详细信息
						showClassroomInfoActivity(_groupIndex);
					}
						break;
					}
				}

			});

			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}

		_cellHolder.tvLable.setText(LABELS[_childIndex]);
		//获取课程详细信息的数据，完成数据与界面之间的联系
		MCourse _data = courseData.get(_groupIndex);
		String _strDisplay = "";
		switch (_childIndex) {
		case 0:
			//课程名称
			_strDisplay = _data.getStrName();
			break;
		case 1:
			//课程描述
			_strDisplay=_data.getStrDescription();
			break;
		case 2:
			//课程类型
			_strDisplay=_data.getWeekType();
			break;
		case 3:
			//开始周
			_strDisplay= ""+_data.getStartWeek();
			break;
		case 4:
			//结束周
			_strDisplay=""+_data.getEndWeek();
			break;
		case 5:
			//学分
			_strDisplay=_data.getCredit()+"分";
			break;
		case 6:{
			//教师信息
			int _count = _data.getTeachersCount();
			String _strTeachers="";
			for (int i=0;i<_count;++i){
				if (i == 0){
					_strTeachers = _data.getTeacherName(i);
				} else {
					_strTeachers += "," + _data.getTeacherName(i);
				}
			}
			_strDisplay=_strTeachers;
		}
			break;
		case 7:{
			//教室信息
			int _count = _data.getClassroomCount();
			String _strClassrooms = "";
			for (int i=0;i<_count;++i){
				if (i==0){
					_strClassrooms = _data.getClassroomName(i);
				} else {
					_strClassrooms += "," + _data.getClassroomName(i);
				}
			}
			_strDisplay=_strClassrooms;
		}
			break;
		}
		_cellHolder.tvValue.setText(_strDisplay);
		
		return convertView;
	}

	/**
	 * 查看教室详细信息
	 * @param index 当前课时下的第几门课
	 */
	protected void showClassroomInfoActivity(int index) {
		// TODO Auto-generated method stub
		int _count = courseData.get(index).getClassroomCount();
		String _strClassroomIds = "";
		for(int _i=0;_i<_count;_i++){
			arrayClassroomIds = new ArrayList<String>();
			_strClassroomIds = courseData.get(index).getClassroomId(_i);
			arrayClassroomIds.add(_strClassroomIds);
		}
		Intent _intent = new Intent().setClass(activity,ActivityClassroom.class);
		_intent.putStringArrayListExtra("classroom_id", arrayClassroomIds);
		activity.startActivity(_intent);

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return LABELS.length;
//		return courseData.get(groupPosition).getTeachersCount()+ courseData.get(groupPosition).getClassroomCount() + 6;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		if (courseData == null)
			return 0;
		return courseData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	//展示ExpandableListView的组控件数据
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SectionHolder _sectionHolder = null;
		//第一次加载课程名称
		if (convertView == null) {
			//设置课程名称的布局文件
			convertView = activity.getLayoutInflater().inflate(R.layout.group_course, null);
			//初始化课程名称列表
			_sectionHolder = new SectionHolder();
			//将显示课程名称的SctionHolder类的成员变量与布局文件中的控件对应
			_sectionHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_course_group);
			_sectionHolder.cbChoose = (CheckBox) convertView.findViewById(R.id.cb_course);

			convertView.setTag(_sectionHolder);

		} else {
			_sectionHolder = (SectionHolder) convertView.getTag();
		}
		
		// 获取当前section下的课程信息的数据
		final MCourse _data = courseData.get(groupPosition);
		// 设置课程的名称，完成数据与界面的联系
		_sectionHolder.tvTitle.setText(_data.getStrName());
		_sectionHolder.cbChoose.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				// 创建我的课表
				// 正式产品，请先检查“我的课表”数据库是否存在，如果存在，这从“我的课表”数据库中读取
				// 如果“我的课表”数据库不存在，这从“网络课表”中生成
				// 现在假设每次“我的课表”都不存在
				myCurriculumData = new MMyCurriculum().setContext(activity.getApplicationContext());
				myCurriculumData.setStrCollegeName("清华大学");
				myCurriculumData.setStrInstituteID("计算机系");
				myCurriculumData.setStrMajorID("软件工程");
				myCurriculumData.setStrGrade("2000");
				myCurriculumData.setStrClass("0002");
				myCurriculumData.setStrSemester("2001年第一学期");
				// 测试：假设全部课程都为选修课
				if (isChecked == true){
					// 选课
					// 修改我的课表中的对应字段
					myCurriculumData.selectCourse(intWeekday, intSection, _data.getStrID(), _data.getStrName());
				} else {
					// 取消选课
					myCurriculumData.cancelCourse(intWeekday, intSection);
				}
			}
		});

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
