/**
 * 文件说明：建筑协议部分
 * 日期：2013/05/23
 */
package org.campusassistant.protocol;

import org.campusassistant.database.DBBuilding;
import org.campusassistant.model.MBuilding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Database.DAOHelper;
import Protocol.ProtocolBase;
import android.content.Context;
import android.util.SparseArray;


public class ProtocolBuilding  extends ProtocolBase {

	//////////////////////////////////////////////////////////
	// 常量（宏）定义区
	// 网络获取数据的url
	public static final String URL = "http://www.baidu.com/";
	// 网络获取数据的指令，与url组合使用
	public static final String COMMAND = "GetBuilding";

	/**
	 * 使用ProtocolBuildingDelegate观察获取和解析数据是否成功
	 * @author v_zhengyan
	 *
	 */
	public interface ProtocolBuildingDelegate {
		public void getBuildingSuccess(SparseArray<MBuilding> data);
		public void getBuildingFailed();
	}
	//创建ProtocolBuildingDelegate对象
	ProtocolBuildingDelegate delegate;
	Context context;
	
	public ProtocolBuilding setContext(Context context){
		this.context = context;
		return this;
	}

	public ProtocolBuilding setDelegate(ProtocolBuildingDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public String packageProtocol() {
		// TODO Auto-generated method stub
		return "GetBuilding";
	}

	/**
	 * 解析传入的JSON数据字符串
	 */
	@Override
	public boolean parseProtocol(String strResponse) {
		// TODO Auto-generated method stub
		
		DBBuilding _db = (DBBuilding) DAOHelper.getInstance().getTable(DBBuilding.TABLE);
		_db.clearAll();
		///////////////////////////////////////////////////////
		// json数据解析区
		// 创建json解析类
		JSONTokener jsonParser = new JSONTokener(strResponse);
		try {
			String _strId = "";
			String _strCampusId ="";
			String _strName = "";
			String _strBrief = "";
			String _strContent_url = "";
			SparseArray<MBuilding> _arrayBuildings = new SparseArray<MBuilding>();
			// JSONTokener对象开始读取json数据
			JSONObject _joRoot = (JSONObject) jsonParser.nextValue();
			// JSONObject对象读取"response"对应的值，将其值以JSONArray的格式返回
			JSONArray _joResponse = _joRoot.getJSONArray("response");
			// 获取_joResponse的json数组长度
			int _count = _joResponse.length();
			for (int i=0;i<_count;++i){
				
				MBuilding _mBuilding = null;
				// 获取json数组第i个json值，以JSONObject格式返回
				JSONObject _jo = _joResponse.getJSONObject(i); 
				
				_strId = _jo.getString("id");
				_strCampusId = _jo.getString("campus_id");
				_strName = _jo.getString("name");
				_strBrief = _jo.getString("brief");
				_strContent_url = _jo.getString("content_url");
				
				// 实例化MBuilding对象
				_mBuilding = new MBuilding().setContext(context);			
				_mBuilding.setStrID(_strId);
				_mBuilding.setStrCompusID(_strCampusId);
				_mBuilding.setStrContent_url(_strContent_url);
				_mBuilding.setStrName(_strName);
				_mBuilding.setStrBrief(_strBrief);
				// 将MBuilding写入到数据库
				_mBuilding.saveToDB();	
				
				_arrayBuildings.append(i, _mBuilding);
			}

			//提示数据获取及数据解析成功
			delegate.getBuildingSuccess(_arrayBuildings);
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
