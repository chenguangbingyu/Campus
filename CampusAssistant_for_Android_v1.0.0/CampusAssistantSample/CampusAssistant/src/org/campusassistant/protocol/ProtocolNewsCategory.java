/**
 * 文件说明：资讯类别协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import org.campusassistant.database.DBNewsCategory;
import org.campusassistant.model.MNewsCategory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Database.DAOHelper;
import Protocol.ProtocolBase;
import android.content.Context;
import android.util.SparseArray;


public class ProtocolNewsCategory extends ProtocolBase {

	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetNewsCategory";

	/**
	 * 使用ProtocolNewsCategoryDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolNewsCategoryDelegate {
		public void getNewsCategorySuccess(SparseArray<MNewsCategory> data);
		public void getNewsCategoryFailed();
	}

	//创建ProtocolNewsCategoryDelegate对象
	ProtocolNewsCategoryDelegate delegate;
	Context context;

	public ProtocolNewsCategory setContext(Context context){
		this.context = context;
		return this;
	}

	public ProtocolNewsCategory setDelegate(ProtocolNewsCategoryDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetNewsCategory";
	}
	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		DBNewsCategory _db = (DBNewsCategory) DAOHelper.getInstance().getTable(DBNewsCategory.TABLE);
		_db.clearAll();
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jsonParser = new JSONTokener(strResponse);
		try {
			String _strId = "";
			String _strTitle = "";
			String _strTag = "";
			String _strDescription = "";
			String _strIcon = "";
			SparseArray<MNewsCategory> _arrayCategorys = new SparseArray<MNewsCategory>();
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jsonParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONArray的格式返回
			JSONArray _joResponse = _joRoot.getJSONArray("response");
			// 获取_joResponse的json数组长度
			int _count = _joResponse.length();
			for (int i=0;i<_count;++i){
				
				MNewsCategory _mNewsCategory = null;
				// 获取json数组第i个json值，以JSONObject格式返回
				JSONObject _jo = _joResponse.getJSONObject(i);
				
				_strId = _jo.getString("id");
				_strTitle = _jo.getString("title");
				_strTag = _jo.getString("tag");
				_strDescription = _jo.getString("desc");
				_strIcon = _jo.getString("icon");
				// 实例化MNewsCategory对象ֵ
				_mNewsCategory = new MNewsCategory().setContext(context);			
				_mNewsCategory.setStrID(_strId);
				_mNewsCategory.setStrTitle(_strTitle);
				_mNewsCategory.setStrTag(_strTag);
				_mNewsCategory.setStrDescription(_strDescription);
				_mNewsCategory.setStrIcon(_strIcon);
				//将MNewsCategory写入到数据库
				_mNewsCategory.saveToDB();
				
				_arrayCategorys.append(i, _mNewsCategory);
			}
			//提示数据获取及数据解析成功
			delegate.getNewsCategorySuccess(_arrayCategorys);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
