/**
 * 文件说明：校园资讯列表中与ListView控件对应的Adapter，实现ListView与数据的整合
 * 日期：2013/05/20
 */
package org.campusassistant.view;

import org.campusassistant.controller.ActivityNewsContentView;
import org.campusassistant.massample.R;
import org.campusassistant.model.MNewsItem;


import RemoteImage.ImageTools;
import RemoteImage.ImageTools.ImageToolsDelegate;
import android.app.Activity;
import android.content.Intent; 
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterNewsList extends BaseAdapter{
	//////////////////////////////////////////////////////////
	// 显示校园资讯新闻列表的 item 中的控件定义区
	class CellHolder{
		// 显示校园资讯新闻列表的item图标
		ImageView imIcon;
		// 显示校园资讯新闻列表的item标题
		TextView tvTitle;
		// 显示校园资讯新闻列表的item发表时间
		TextView tvDatetime;
		// 显示校园资讯新闻列表的item简介
		TextView tvBrief;
	}	
	// 校园资讯新闻列表图标 显示/下载 代理
	ImageToolsDelegate imageToolsDelegate;
	// 显示新闻列表当前页面的Activity
	Activity activityParent;
	// 填充校园资讯新闻列表的数据
	SparseArray<MNewsItem> arrayData;
	/**
	 *  传入显示校园资讯新闻列表页面的Activity，并返回显示校园资讯新闻列表的AdapterNewsList
	 * @param activity
	 * @return AdapterNewsList
	 */
	public AdapterNewsList setParent(Activity activity){
		this.activityParent = activity;
		return this;
	}
	/**
	 * 传入显示校园资讯新闻列表图标 显示/下载 的代理，并返回 显示校园资讯新闻列表的AdapterNewsList
	 * @param delegate
	 * @return AdapterNewsList
	 */
	public AdapterNewsList setImageDelegate(ImageToolsDelegate delegate){
		imageToolsDelegate = delegate;
		return this;
	}
	/**
	 * 设置填充显示校园资讯新闻列表的数据
	 * @param datas
	 */
	public void setDataSource(SparseArray<MNewsItem> data){
		arrayData = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CellHolder _cellHolder = null;
		final int _position = position;	
		if (convertView == null){
			// 设置 校园资讯新闻列表item的xml布局文件
			convertView = activityParent.getLayoutInflater().inflate(R.layout.cell_news_list, null);
			// 初始化显示校园资讯新闻列表item的CellHolder类
			_cellHolder = new CellHolder();
			// 将显示校园资讯新闻列表item的CellHolder类的成员变量与布局文件中的控件对应
			_cellHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_newlist_title);
			_cellHolder.tvDatetime = (TextView) convertView.findViewById(R.id.tv_newslist_datetime);
			_cellHolder.tvBrief = (TextView) convertView.findViewById(R.id.tv_newslist_brief);
			_cellHolder.imIcon = (ImageView)convertView.findViewById(R.id.iv_newslist_icon);
			// 设置校园资讯新闻列表item的Click监听
			convertView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// 传入校园资讯新闻item的对应的url，完成Activity跳转
					Intent _intent = new Intent().setClass(activityParent, ActivityNewsContentView.class);
					_intent.putExtra("content_url", arrayData.get(_position).getStrContent_url());
					activityParent.startActivity(_intent);
				}
				
			});			  
			convertView.setTag(_cellHolder);
		} else {
			_cellHolder = (CellHolder) convertView.getTag();
		}
		
		// 获取校园资讯新闻item的对应的数据，完成数据与界面的联系
		MNewsItem _data = arrayData.get(position);
		_cellHolder.tvTitle.setText(_data.getStrTitle());
		_cellHolder.tvDatetime.setText(_data.getStrDatetime());
		_cellHolder.tvBrief.setText(_data.getStrBrief());
		
		// 使用 图片显示/下载代理完成校园资讯新闻item中的图标的显示功能
		ImageTools _imageTool = new ImageTools().setDelegate(imageToolsDelegate);
		_imageTool.getImage(activityParent.getApplicationContext(), _data.getStrSmall_icon(), _cellHolder.imIcon);
		
		return convertView;
	}

}
