package org.campusassistant.view;

import java.util.ArrayList;

import org.campusassistant.massample.R;
import org.campusassistant.model.MMyCurriculum;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterScheduleWeek extends BaseAdapter {

	class CellHolder{
		TextView tvCourseName;
	}
	
	Activity activityParent;
	MMyCurriculum myCurriculumData;
	int intYear, intMonth, intDay;

	public void setWeekStartDate(int year, int month, int day){
		intYear = year;
		intMonth = month;
		intDay = day;
	}
	
	public AdapterScheduleWeek setParent(Activity parent){
		activityParent = parent;
		return this;
	}

	

	public void setMyCurriculumData(MMyCurriculum myCurriculumData) {
		this.myCurriculumData = myCurriculumData;
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
		CellHolder _cellHolder = null;
		int section = position / 7;
		int weekday = position % 7;
		
		if (convertView == null){
			convertView = activityParent.getLayoutInflater().inflate(R.layout.cell_schedule_day, null);
			
			_cellHolder = new CellHolder();
			_cellHolder.tvCourseName = (TextView) convertView.findViewById(R.id.tv_schedule_day_course_name);
			
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		
		// 当前可能有多个课程同时存在，采用“/”分割的方式显示
		ArrayList<String> _arrayCourse = myCurriculumData.getCourseNameByWeek(section, weekday, intYear, intMonth, intDay);
		String _strDisplay = "";
		for (int i=0;i<_arrayCourse.size();++i){
			if (i == 0){
				_strDisplay = _arrayCourse.get(i);
			} else {
				_strDisplay += "/" + _arrayCourse.get(i);
			}
		}
		_cellHolder.tvCourseName.setText(_strDisplay);
		
		return convertView;
	}

}
