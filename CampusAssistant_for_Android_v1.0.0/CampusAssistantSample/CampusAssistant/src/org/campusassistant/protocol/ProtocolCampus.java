/**
 * 文件说明：校园手册协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import org.campusassistant.database.DBCampus;
import org.campusassistant.model.MCampus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Database.DAOHelper;
import Protocol.ProtocolBase;
import android.content.Context;


public class ProtocolCampus  extends ProtocolBase {
	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetCampus";

	/**
	 * 使用ProtocolCampusDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolCampusDelegate {
		public void getCampusSuccess(MCampus mHangbook);
		public void getCampusFailed();
	}

	//创建ProtocolCampusDelegate对象
	ProtocolCampusDelegate delegate;
	Context context;
	
	public ProtocolCampus setContext(Context context){
		this.context = context;
		return this;
	}

	//
	public ProtocolCampus setDelegate(ProtocolCampusDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetCampus";
	}
	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		
		DBCampus _db = (DBCampus) DAOHelper.getInstance().getTable(DBCampus.TABLE);
		_db.clearAll();
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jsonParser = new JSONTokener(strResponse);
		try {
			String _strId = "";
			String _strName = "";
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jsonParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONArray的格式返回
			JSONArray _joResponse = _joRoot.getJSONArray("response");
			// 获取_joResponse的json数组长度
			int _count = _joResponse.length();
			for (int i=0;i<_count;++i){
				
				MCampus _mCampus = null;
				// 获取json数组第i个json值，以JSONObject格式返回
				JSONObject _jo = _joResponse.getJSONObject(i);
				
				_strId = _jo.getString("id");
				_strName = _jo.getString("name");
				// 实例化MBuilding对象
				_mCampus = new MCampus().setContext(context);					
				_mCampus.setStrID(_strId);
				_mCampus.setStrName(_strName);
				//将MCampus写入到数据库
				_mCampus.saveToDB();
				//提示数据获取及数据解析成功
				delegate.getCampusSuccess(_mCampus);
			}

			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
