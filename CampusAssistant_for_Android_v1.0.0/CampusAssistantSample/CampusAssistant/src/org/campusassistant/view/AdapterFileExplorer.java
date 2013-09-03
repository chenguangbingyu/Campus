/**
 * 文件说明：分享资料中，搜索资料的目录adapter，实现数据与ListView的整合
 * 日期：2013/05/20
 */
package org.campusassistant.view;

import java.io.File;

import org.campusassistant.massample.R;



import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterFileExplorer extends BaseAdapter {
	//////////////////////////////////////////////////////////
	// 显示文件目录的item定义区
	//ListView的组容器，显示目录下的文件
	private class CellHolder{
		//文件图标
		ImageView ivIcon;
		//文件名字
		TextView tvText;
	}
	
	//////////////////////////////////////////////////////////
	// 数据源定义区
	//目录下的所有文件
	File[] files;
	//判断是否为根目录
	boolean isTop;
	//显示课程详细信息的当前页面的Activity
	Activity activity;
	
	/**
	 * 传入文件所在目录的Activity，并返回目录下详细信息的AdapterFileExplorer
	 * @param activity
	 * @return
	 */
	public AdapterFileExplorer setParent(Activity activity){
		this.activity = activity;
		return this;
	}
	/**
	 * 设置目录信息
	 * @param files
	 * @param b
	 */
	public void setFilesData(File[] files, boolean b){
		this.files = files;
		this.isTop = b;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return files.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return files[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//��һ�μ����ļ������б�
		CellHolder _cellHolder = null;
		//��ʾ�ؼ�
		if(convertView == null){
			//第一次加载目录信息
			convertView = activity.getLayoutInflater().inflate(R.layout.cell_file_explorer, null);
			//初始化目录信息
			_cellHolder = new CellHolder();
			//将显示目录详细信息的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.ivIcon = (ImageView)convertView.findViewById(R.id.iv_icon);
			_cellHolder.tvText = (TextView)convertView.findViewById(R.id.tv_text);
			
//			_cellHolder.tvText.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Intent _intent = new Intent().setClass(activity,ActivityShareFile.class);
//					activity.startActivity(_intent);
//				}
//			});
			convertView.setTag(_cellHolder);
		}else{
			_cellHolder = (CellHolder)convertView.getTag();
		}
		//设置文件显示信息
		setRow(_cellHolder,position);
		return convertView;
	}
	
	/**
	 * 设置文件的显示信息
	 * @param _holder
	 * @param position
	 */
	private void setRow(CellHolder _holder, int position) {
		File file = files[position];
		_holder.tvText.setText(file.getName());
		if(position == 0 && !isTop) {
			_holder.ivIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_menu_back));
		} 
		else if (file.isDirectory()) {
			_holder.ivIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_menu_archive));
		} 
		else {			
			_holder.ivIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_menu_gallery));
		}
	}

}
