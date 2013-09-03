package org.campusassistant.massample;

import org.campusassistant.Tools.AppConstants;
import org.campusassistant.controller.ActivityManual;
import org.campusassistant.controller.ActivityMyCurriculum;
import org.campusassistant.controller.ActivityNews;
import org.campusassistant.controller.ActivityQuerySchedule;
import org.campusassistant.controller.ActivityShareFile;
import org.campusassistant.database.DBBuilding;
import org.campusassistant.database.DBCampus;
import org.campusassistant.database.DBClassroom;
import org.campusassistant.database.DBCourse;
import org.campusassistant.database.DBCurriculum;
import org.campusassistant.database.DBMyCurriculum;
import org.campusassistant.database.DBNewsCategory;
import org.campusassistant.database.DBNewsList;

import Database.DAOHelper;
import Network.Network;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * 
 * @author v_zhengyan
 *
 */
public class MainActivity extends Activity {
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	// 课表、我的课表、资讯、分享文件、手册
	Button btnCurriculum, btnMyCurriculum, btnNews, btnShareFile,btnManual; 

	//模拟服务器初始化，写入AppConstants中的JSON数据
	void initSimnulateServer(){
		Network _network = new Network();
		_network.addTestResponse("GetBuilding", AppConstants.DEBUG_PROTOCOL_CAMPUSBUILDING);
		_network.addTestResponse("GetCampus", AppConstants.DEBUG_PROTOCOL_CAMPUS);
		_network.addTestResponse("getcalssroom", AppConstants.DEBUG_PROTOCOL_CLASSROOM);
		_network.addTestResponse("GetCourse", AppConstants.DEBUG_PROTOCOL_COURSE);
		_network.addTestResponse("GetCurriculum", AppConstants.DEBUG_PROTOCOL_CURRICULUM);
		_network.addTestResponse("GetNewsCategory", AppConstants.DEBUG_PROTOCOL_NEWSCATEGORY);
		_network.addTestResponse("GetNewsList?categoryId=0001", AppConstants.DEBUG_PROTOCOL_NEWSLIST);
		_network.addTestResponse("GetNewsList?categoryId=0002", AppConstants.DEBUG_PROTOCOL_NEWSLIST_LOST);
	}
	
	void initDB(){
    	// 初始化数据库
    	DAOHelper.createInstance(this.getApplicationContext(), AppConstants.DB_NAME, AppConstants.DB_VERSION);
    	DAOHelper _dbHelper = DAOHelper.getInstance();
    	if (_dbHelper!=null){
    		_dbHelper.addDBTable(DBBuilding.TABLE, new DBBuilding());
    		_dbHelper.addDBTable(DBCampus.TABLE, new DBCampus());
    		_dbHelper.addDBTable(DBClassroom.TABLE, new DBClassroom());
    		_dbHelper.addDBTable(DBCourse.TABLE, new DBCourse());
    		_dbHelper.addDBTable(DBCurriculum.TABLE, new DBCurriculum());
    		_dbHelper.addDBTable(DBMyCurriculum.TABLE, new DBMyCurriculum());
    		_dbHelper.addDBTable(DBNewsCategory.TABLE, new DBNewsCategory());
    		_dbHelper.addDBTable(DBNewsList.TABLE, new DBNewsList());
    	}
    }
    /**
	 * 准备界面显示控件
	 */
    void findView(){
    	btnCurriculum = (Button)this.findViewById(R.id.btn_curriculum);
    	btnMyCurriculum = (Button) this.findViewById(R.id.btn_my_curriculum);
    	btnNews = (Button) this.findViewById(R.id.btn_news);
    	btnShareFile = (Button) this.findViewById(R.id.btn_share_file);
    	btnManual = (Button)this.findViewById(R.id.btn_handbook);
    	// 课表Button监听
    	btnCurriculum.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,ActivityQuerySchedule.class);
				startActivity(_intent);
			}
    		
    	});
    	// 我的课表Button监听
    	btnMyCurriculum.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,ActivityMyCurriculum.class);
				startActivity(_intent);
			}
    		
    	});
    	// 资讯Button监听
    	btnNews.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,ActivityNews.class);
				startActivity(_intent);
			}
		});
    	// 分享文件Button监听
    	btnShareFile.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,ActivityShareFile.class);
				startActivity(_intent);
			}
		});
    	// 校园手册Button监听
    	btnManual.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(MainActivity.this,ActivityManual.class);
				startActivity(_intent);
			}
    		
    	});
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findView();
        initDB();
        initSimnulateServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
