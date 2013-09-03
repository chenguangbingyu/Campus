/**
 * 文件说明：校园资讯类别列表中与ListView控件对应的Adapter，实现ListView与数据的整合
 * 日期：2013/05/21
 */
package org.campusassistant.view;

import org.campusassistant.massample.R;
import org.campusassistant.model.MNewsCategory;

import android.app.Activity;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterNewsCategory extends BaseAdapter {
	//////////////////////////////////////////////////////////
	// 显示校园资讯类别列表的 item中的控件定义区
	class CellHolder{
		// 显示校园资讯类别列表的item标题
		TextView tvTitle;
	}
	// Tab切换代理
	public interface TabChangedDelegate{
		public void onTabChanged(int selected);
	}
	// 选中的TabId
	int currentSelected = 0;
	// 显示校园资讯类别列表当前页面的Activity
	Activity activityParent;
	// 填充校园资讯类别列表的数据
	SparseArray<MNewsCategory> arrayData;	
	
	TabChangedDelegate selectedDelegate;
	/**
	 * 设置切换代理
	 * @param delegate
	 */
	public void setSelectedDelegate(TabChangedDelegate delegate){
		selectedDelegate = delegate;
	}
	/**
	 *  传入显示校园资讯类别列表页面的Activity，并返回显示校园资讯类别列表的AdapterNewsCategory
	 * @param activity
	 * @return AdapterNewsCategory
	 */
	public AdapterNewsCategory setParent(Activity activity){
		activityParent = activity;
		return this;
	}
	/**
	 * 设置填充显示校园资讯类别列表的的数据
	 * @param data
	 */
	public void setDataSource(SparseArray<MNewsCategory> data){
		arrayData = data;
	}
	/**
	 * 获取当前选中tab的id
	 * @return currentSelected
	 */
	public int getCurrentSelected() {
		return currentSelected;
	}
	/**
	 * 设置this.currentSelected为传入的currentSelected值
	 * @param currentSelected
	 */
	public void setCurrentSelected(int currentSelected) {
		this.currentSelected = currentSelected;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Math.min(arrayData.size(), 5);
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
		final int _index = position;
		CellHolder _cellHolder = null;
		if (convertView == null){
			// 设置校园资讯类别列表tab键的xml布局文件
			convertView = activityParent.getLayoutInflater().inflate(R.layout.cell_news_category_tab, null);			
			_cellHolder = new CellHolder();
			// 将显示校园资讯类别列表item的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_newscategory_title);
			// 设置校园资讯类别列表tab键的Click监听
			convertView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					selectedDelegate.onTabChanged(_index);
				}
				
			});
			
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		// 获取校园资讯类别列表的对应的数据，完成数据与界面的联系
		MNewsCategory _data = arrayData.get(position);
		_cellHolder.tvTitle.setText(_data.getStrTitle());
		// 根据tab键选中与否改变tab键背景等属性
		if (position == currentSelected){
			// 当前选中项
			_cellHolder.tvTitle.setBackgroundColor(Color.GRAY);
			_cellHolder.tvTitle.setTextColor(Color.WHITE);
			convertView.setEnabled(false);
		} else {
			_cellHolder.tvTitle.setBackgroundColor(Color.WHITE);
			_cellHolder.tvTitle.setTextColor(Color.BLACK);
			convertView.setEnabled(true);
		}
		
		return convertView;
	}

}
