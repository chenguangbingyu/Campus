package org.campusassistant.controller;

import java.util.Calendar;

import org.campusassistant.massample.R;
import org.campusassistant.model.MMyCurriculum;
import org.campusassistant.view.AdapterScheduleDay;
import org.campusassistant.view.AdapterScheduleWeek;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class ActivityMyCurriculum extends Activity implements OnGestureListener {
	static final int DISPLAYMODE_DAY = 0;
	static final int DISPLAYMODE_WEEK = 1;
	
	//API key
	private final String mbApiKey = "daKD2szVFMRr3GNtwzAqK1SG";
//    private final static String mbApiKey = "L6g70tBRRIXLsY0Z3HwKqlRE";
	//
	String accessToken;
	SharedPreferences sp;
	String token = null;
	
	static final String[] WEEKDAY_NAME = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	
	ViewFlipper viewFlipperDayMode, viewFlipperWeekMode;
	GestureDetector gesture;
	
	//定义三个view，用来实现日模式屏幕的左右滑动
	LinearLayout[] llViewDayMode = new LinearLayout[3];
	ListView[] lvScheduleViewDayMode = new ListView[3];
	AdapterScheduleDay[] adapterDayMode = new AdapterScheduleDay[3];
	
	//定义三个view，用来实现周模式屏幕的左右滑动
	LinearLayout[] llViewWeekMode = new LinearLayout[3];
	GridView[] gvScheduleViewWeekMode = new GridView[3];
	AdapterScheduleWeek[] adapterWeekMode = new AdapterScheduleWeek[3];
	
	TextView tvCurrentDate;
	Button btnSwitchDisplayMode;
	Button btnPostToPcs, btnGetFromPcs;
	
	int displayMode;
	
	int intYear, intMonth, intDay;
	int intWeekStartYear, intWeekStartMonth, intWeekStartDay;
	
//	MCurriculum myCurriculumData;
	MMyCurriculum myCurriculumData;
	int intWeekday;
	int intIndex;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	void displayCurrentDate(){
		tvCurrentDate.setText(String.format("%04d-%02d-%02d %s", intYear,intMonth+1,intDay, WEEKDAY_NAME[intWeekday-1]));
	}
	
	void displayCurrentWeek(){
		tvCurrentDate.setText(String.format("本周开始日期：%04d-%02d-%02d", intWeekStartYear,intWeekStartMonth+1,intWeekStartDay));
	}

	void findView() {
		viewFlipperDayMode = (ViewFlipper) findViewById(R.id.vf_day);
		viewFlipperWeekMode = (ViewFlipper) findViewById(R.id.vf_week);
		
		tvCurrentDate = (TextView) findViewById(R.id.tv_current_date);
		btnSwitchDisplayMode = (Button) findViewById(R.id.btn_display_mode);
		//上传按钮
		btnPostToPcs = (Button) findViewById(R.id.btn_post_to_pcs);
		//下载按钮
		btnGetFromPcs = (Button) findViewById(R.id.btn_get_from_pcs);

		
		llViewDayMode[0] = (LinearLayout) findViewById(R.id.vd_daymode_1);
		llViewDayMode[1] = (LinearLayout) findViewById(R.id.vd_daymode_2);
		llViewDayMode[2] = (LinearLayout) findViewById(R.id.vd_daymode_3);
		
		llViewWeekMode[0] = (LinearLayout) findViewById(R.id.vd_weekmode_1);
		llViewWeekMode[1] = (LinearLayout) findViewById(R.id.vd_weekmode_2);
		llViewWeekMode[2] = (LinearLayout) findViewById(R.id.vd_weekmode_3);
		
		lvScheduleViewDayMode[0] = (ListView) llViewDayMode[0].findViewById(R.id.lv_schedule_day);
		lvScheduleViewDayMode[1] = (ListView) llViewDayMode[1].findViewById(R.id.lv_schedule_day);
		lvScheduleViewDayMode[2] = (ListView) llViewDayMode[2].findViewById(R.id.lv_schedule_day);
		
		gvScheduleViewWeekMode[0] = (GridView) llViewWeekMode[0].findViewById(R.id.gv_schedule_week);
		gvScheduleViewWeekMode[1] = (GridView) llViewWeekMode[1].findViewById(R.id.gv_schedule_week);
		gvScheduleViewWeekMode[2] = (GridView) llViewWeekMode[2].findViewById(R.id.gv_schedule_week);
		
		btnSwitchDisplayMode.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent _intent = new Intent().setClass(ActivityMyCurriculumDayMode.this, ActivityMyCurriculumWeekMode.class);
//				startActivity(_intent);
				if (displayMode == DISPLAYMODE_DAY){
					viewFlipperDayMode.setVisibility(View.GONE);
					viewFlipperWeekMode.setVisibility(View.VISIBLE);
					setWeekModeAdapter(intIndex,intWeekStartYear,intWeekStartMonth,intWeekStartDay);
					displayMode = DISPLAYMODE_WEEK;
					btnSwitchDisplayMode.setText("日");
					displayCurrentWeek();
				} else if (displayMode == DISPLAYMODE_WEEK){
					viewFlipperDayMode.setVisibility(View.VISIBLE);
					viewFlipperWeekMode.setVisibility(View.GONE);
					setDayModeAdapter(intIndex, intWeekday-1);
					displayMode = DISPLAYMODE_DAY;
					btnSwitchDisplayMode.setText("周");
					displayCurrentDate();
				}
			}
			
		});
		
		//点击上传按钮动作
		btnPostToPcs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 将数据保存到云端
				myCurriculumData.saveToCloud(ActivityMyCurriculum.this, mbApiKey);	
			}
		});
		
		//点击下载按钮动作
		btnGetFromPcs.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 从云端读取煮熟
				myCurriculumData.readFromCloud(ActivityMyCurriculum.this, mbApiKey);
			}
			
		});
			
		gesture = new GestureDetector(this);
	}
	

	boolean moveToNextDate(){
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(intYear, intMonth, intDay);
		_calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		intYear = _calendar.get(Calendar.YEAR);
		intMonth = _calendar.get(Calendar.MONTH);
		intDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		intWeekday = _calendar.get(Calendar.DAY_OF_WEEK);
		intIndex = (intIndex+1)%3;
		setDayModeAdapter(intIndex,intWeekday-1);
		displayCurrentDate();
		return true;
	}
	
	boolean moveToPrevDate(){
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(intYear, intMonth, intDay);
		_calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		intYear = _calendar.get(Calendar.YEAR);
		intMonth = _calendar.get(Calendar.MONTH);
		intDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		intWeekday = _calendar.get(Calendar.DAY_OF_WEEK);
		
		intIndex = (intIndex-1+3)%3;
		setDayModeAdapter(intIndex, intWeekday-1);
		displayCurrentDate();
		return true;
	}
	
	boolean moveToNextWeek(){
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(intWeekStartYear, intWeekStartMonth, intWeekStartDay);
		_calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		intWeekStartYear = _calendar.get(Calendar.YEAR);
		intWeekStartMonth = _calendar.get(Calendar.MONTH);
		intWeekStartDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		intIndex = (intIndex+1)%3;
		setWeekModeAdapter(intIndex,intWeekStartYear, intWeekStartMonth, intWeekStartDay);
		displayCurrentWeek();
		return true;
	}
	
	boolean moveToPrevWeek(){
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(intWeekStartYear, intWeekStartMonth, intWeekStartDay);
		_calendar.add(Calendar.DAY_OF_MONTH, -7);
		
		intWeekStartYear = _calendar.get(Calendar.YEAR);
		intWeekStartMonth = _calendar.get(Calendar.MONTH);
		intWeekStartDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		intIndex = (intIndex+1)%3;
		setWeekModeAdapter(intIndex,intWeekStartYear, intWeekStartMonth, intWeekStartDay);
		displayCurrentWeek();
		return true;
	}
	
	void setDayModeAdapter(int index, int weekday){
		adapterDayMode[index].setIntWeekday(weekday);
		adapterDayMode[index].notifyDataSetChanged();
	}
	
	void setWeekModeAdapter(int index, int year, int month, int day){
		adapterWeekMode[index].setWeekStartDate(year, month, day);
		adapterWeekMode[index].notifyDataSetChanged();
	}
	
	void initScheduleWeekMode(){
		adapterWeekMode[0] = new AdapterScheduleWeek().setParent(this);
		adapterWeekMode[1] = new AdapterScheduleWeek().setParent(this);
		adapterWeekMode[2] = new AdapterScheduleWeek().setParent(this);
		adapterWeekMode[0].setMyCurriculumData(myCurriculumData);
		adapterWeekMode[1].setMyCurriculumData(myCurriculumData);
		adapterWeekMode[2].setMyCurriculumData(myCurriculumData);
		
		gvScheduleViewWeekMode[0].setAdapter(adapterWeekMode[0]);
		gvScheduleViewWeekMode[1].setAdapter(adapterWeekMode[1]);
		gvScheduleViewWeekMode[2].setAdapter(adapterWeekMode[2]);
	}
	
	void initScheduleDayMode(){
		adapterDayMode[0] = new AdapterScheduleDay().setParent(this);
		adapterDayMode[1] = new AdapterScheduleDay().setParent(this);
		adapterDayMode[2] = new AdapterScheduleDay().setParent(this);
		adapterDayMode[0].setMyCurriculumData(myCurriculumData);
		adapterDayMode[1].setMyCurriculumData(myCurriculumData);
		adapterDayMode[2].setMyCurriculumData(myCurriculumData);
		
		lvScheduleViewDayMode[0].setAdapter(adapterDayMode[0]);
		lvScheduleViewDayMode[1].setAdapter(adapterDayMode[1]);
		lvScheduleViewDayMode[2].setAdapter(adapterDayMode[2]);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_curriculum);

		findView();
		
		// 选择今天显示
		Calendar _calendar = Calendar.getInstance();
		intYear = _calendar.get(Calendar.YEAR);
		intMonth = _calendar.get(Calendar.MONTH);
		intDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		intWeekday = _calendar.get(Calendar.DAY_OF_WEEK);
		intIndex = 0;
		
		_calendar.add(Calendar.DAY_OF_MONTH, -intWeekday+1);
		
		intWeekStartYear = _calendar.get(Calendar.YEAR);
		intWeekStartMonth = _calendar.get(Calendar.MONTH);
		intWeekStartDay = _calendar.get(Calendar.DAY_OF_MONTH);
		
		myCurriculumData = new MMyCurriculum().setContext(this.getApplicationContext());
		myCurriculumData.readFromDB();
		
		initScheduleDayMode();
		initScheduleWeekMode();
		// 默认为“日”模式	
		viewFlipperDayMode.setVisibility(View.VISIBLE);
		viewFlipperWeekMode.setVisibility(View.GONE);
			
		setDayModeAdapter(intIndex, intWeekday-1);
		displayCurrentDate();
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		return this.gesture.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		switch(displayMode){
		case DISPLAYMODE_DAY:{
			if (e2.getX() - e1.getX() > 100) {
				// 向右滑动
				moveToPrevDate();
				viewFlipperDayMode.setInAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_in_left));
				viewFlipperDayMode.setOutAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_out_right));
				viewFlipperDayMode.showPrevious();
				return true;
			} else if (e1.getX() - e2.getX() > 100) {
				// 向左滑动
				moveToNextDate();
				viewFlipperDayMode.setInAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_in_right));
				viewFlipperDayMode.setOutAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_out_left));
				viewFlipperDayMode.showNext();
				return true;
			}
		}
			break;
		case DISPLAYMODE_WEEK:{
			if (e2.getX() - e1.getX() > 100) {
				// 向右滑动
				moveToPrevWeek();
				viewFlipperWeekMode.setInAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_in_left));
				viewFlipperWeekMode.setOutAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_out_right));
				viewFlipperWeekMode.showPrevious();
				return true;
			} else if (e1.getX() - e2.getX() > 100) {
				// 向左滑动
				moveToNextWeek();
				viewFlipperWeekMode.setInAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_in_right));
				viewFlipperWeekMode.setOutAnimation(AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.anim_slide_out_left));
				viewFlipperWeekMode.showNext();
				return true;
			}
		}
			break;
		}
		return false;
	}

}
