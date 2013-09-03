/**
 * 文件说明：资讯信息协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import org.campusassistant.database.DBNewsList;
import org.campusassistant.model.MNewsItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Database.DAOHelper;
import Protocol.ProtocolBase;
import android.content.Context;
import android.util.SparseArray;


public class ProtocolNewsList extends ProtocolBase {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetNewsList";

	/**
	 * 使用ProtocolNewsListDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolNewsListDelegate {
		public void getNewsListSuccess(SparseArray<MNewsItem> data);
		public void getNewsListFailed();
	}

	//创建ProtocolCurriculumDelegate对象
	ProtocolNewsListDelegate delegate;
	Context context;
	
	String categoryId;
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public ProtocolNewsList setContext(Context context){
		this.context = context;
		return this;
	}

	public ProtocolNewsList setDelegate(ProtocolNewsListDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetNewsList?categoryId=" + categoryId;
	}

	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		DBNewsList _db = (DBNewsList) DAOHelper.getInstance().getTable(DBNewsList.TABLE);
		_db.clearAll();
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jsonParser = new JSONTokener(strResponse);
		try {
			String _strID = "";
			String _strTitle = "";
			String _strBrief = "";
			String _strDatetime = "";
			String _strAuthor = "";
			String _strTag = "";
			String _strType = "";
			String _strSmall_icon = "";
			String _strBig_image = "";
			String _strContent_url = "";
			
			SparseArray<MNewsItem> _arrayNewsItem = new SparseArray<MNewsItem>();
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jsonParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONArray的格式返回
			JSONArray _joResponse = _joRoot.getJSONArray("response");
			// 获取_joResponse的json数组长度
			int _count = _joResponse.length();
			for (int i=0;i<_count;++i){
				
				MNewsItem _mNewsItem = null;
				// 获取json数组第i个json值，以JSONObject格式返回。
				JSONObject _jo = _joResponse.getJSONObject(i);
				
				_strID = _jo.getString("id");
				_strTitle = _jo.getString("title");
				_strBrief = _jo.getString("brief");
				_strDatetime = _jo.getString("date_time");
				_strAuthor = _jo.getString("author");
				_strTag = _jo.getString("tag");
				_strTag = _jo.getString("type");
				_strSmall_icon = _jo.getString("small_icon");
				_strBig_image = _jo.getString("big_image");
				_strContent_url = _jo.getString("content_url");
				//实例化MNewsItem对象
				_mNewsItem = new MNewsItem().setContext(context);			
				_mNewsItem.setStrID(_strID);
				_mNewsItem.setStrTitle(_strTitle);
				_mNewsItem.setStrBrief(_strBrief);
				_mNewsItem.setStrDatetime(_strDatetime);
				_mNewsItem.setStrAuthor(_strAuthor);
				_mNewsItem.setStrTag(_strTag);
				_mNewsItem.setStrType(_strType);
				_mNewsItem.setStrSmall_icon(_strSmall_icon);
				_mNewsItem.setStrBig_image(_strBig_image);
				_mNewsItem.setStrContent_url(_strContent_url);
				//将MNewsItem写入到数据库
				_mNewsItem.saveToDB();
				
				_arrayNewsItem.append(i, _mNewsItem);
			}
			//提示数据获取及数据解析成功
			delegate.getNewsListSuccess(_arrayNewsItem);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
