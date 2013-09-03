/**
 * 文件说明：分享资料中，选择文件部分的Activity
 * 日期：2013/05/20
 */
package org.campusassistant.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.campusassistant.massample.R;
import org.campusassistant.view.AdapterFileExplorer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityFileExplorer extends ListActivity {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	//SD卡根路径
	private String     strRoot = "/sdcard";
	//某一级目录下的所有文件
	private File[]     fMFiles;
	//选择文件的绝对路径（不包含文件名本身）
	private String     strFilePath;
	
	//////////////////////////////////////////////////////////
	// 显示控件定义区
	//需要展现的某一级目录下的文件列表
	private ListView   lvFile;
	//文本控件，用于显示选择的文件的路径
	private TextView   tvFilePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_explorer);
		//关联显示控件
		findView();
		//获取目录
		getDirectory(strRoot);
	}

	/**
	 * 获取当前目录下的文件列表
	 * @param dirPath 当前目录
	 */
	private void getDirectory(String dirPath) {
		// TODO Auto-generated method stub
		try{
			tvFilePath.setText("Location: " + dirPath);
			
			File f = new File(dirPath);
			File[] fFilesTemp = f.listFiles();
			
			sortFilesByDirectory(fFilesTemp);
			
			File[] fFiles = null;
			if(!dirPath.equals(strRoot)){
				fFiles = new File[fFilesTemp.length + 1];
				System.arraycopy(fFilesTemp, 0, fFiles, 1, fFilesTemp.length);
				fFiles[0] = new File(f.getParent());
			}else{
				fFiles = fFilesTemp;
			}
			
			fMFiles = fFiles;
			strFilePath = dirPath;
			
			//绑定adapter监听器	
			AdapterFileExplorer adapterFileExplorer = new AdapterFileExplorer().setParent(this);
			adapterFileExplorer.setFilesData(fFiles,fFilesTemp.length == fFiles.length);
			
			lvFile.setAdapter(adapterFileExplorer);
			adapterFileExplorer.notifyDataSetChanged();
			
		}catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), "搜索文件出错！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 文件列表排序
	 * @param files 目录下的所有文件
	 */
	private void sortFilesByDirectory(File[] files) {
		// TODO Auto-generated method stub
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File file1, File file2) {
				// TODO Auto-generated method stub
				return Long.valueOf(file1.length()).compareTo(file2.length());
			}
		});
		
	}

	/**
	 * 准备界面，显示控件
	 */
	private void findView() {
		// TODO Auto-generated method stub
		tvFilePath = (TextView)findViewById(R.id.tv_file_path);
		lvFile = (ListView)findViewById(android.R.id.list);
	}

	/**
	 * 点击操作，如果是一个目录，就进入到该目录下，如果不是目录，就选择该文件
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = fMFiles[position];
		//判断文件是否为目录
		if (file.isDirectory()) {
			//如果文件可读
			if (file.canRead())
				getDirectory(file.getAbsolutePath());
			else {
				Toast.makeText(getApplicationContext(), "[" + file.getName() + "] folder can't be read!", Toast.LENGTH_SHORT).show();
			}
		} else {
			//回到上传文件页面，并将选择的文件路径和名称返回
			Intent _intent = new Intent().setClass(ActivityFileExplorer.this,ActivityShareFile.class);
			_intent.putExtra("filePath", strFilePath + "/" + file.getName());
			_intent.putExtra("fileName", file.getName());
			startActivity(_intent);
		}
	}

}
