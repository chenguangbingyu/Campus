package com.campus.prime.core.client;

import static org.apache.http.HttpStatus.SC_OK;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Base64;

import com.campus.prime.core.utils.JsonUtil;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class CampusClient {
	private static final CommonLog log = LogFactory.createLog();
	
	private static final String CHARSET_UTF8 = HTTP.UTF_8;
	private static HttpClient customHttpClient;
	
	
	protected static final String HEADER_CONTENT_TYPE = "Content-Type";
	
	protected static final String HEADER_ACCEPT = "Accept";
	
	protected static final String HEADER_AUTHORIZATION = "Authorization";
	
	protected static final String JSON = "application/json";
	
	protected static final String AUTH_TOKEN = "Token";
	
	protected static final String AUTH_BASIC = "Basic";
	
	
	
	private String credential;
	
	
	public CampusClient setCredential(final String username,final String password){
		if(username != null && username.length() > 0 && password != null
				&& password.length() > 0)
			credential = "Basic "+ 
				Base64.encodeToString((username+':'+password).getBytes(), Base64.NO_WRAP);
		else
			credential =null;
		return this;
				
	}
	
	
	public CampusClient setCredential(String token){
		if(token != null && token.length() > 0)
			credential = AUTH_TOKEN +  ' ' + token;
		else
			credential = null;
		return this;
	}

	
	public CampusClient(){
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 判断HttpResponse的头信息中是否是正确的信息
	 * @param code
	 * @return
	 */
	private boolean isOk(final int code){
		switch(code){
		case SC_OK:
		case HttpStatus.SC_CREATED:
		case HttpStatus.SC_ACCEPTED:
			return true;
		default:
			return false;
		}
	}
	/**
	 * 判断HttpReponse的头信息中是否有错误
	 * @param code
	 * @return
	 */
	private boolean isError(final int code){
		switch(code){
		case HttpStatus.SC_BAD_REQUEST:
		case HttpStatus.SC_UNAUTHORIZED:
		case HttpStatus.SC_FORBIDDEN:
		case HttpStatus.SC_NOT_FOUND:
		case HttpStatus.SC_CONFLICT:
		case HttpStatus.SC_GONE:
		case HttpStatus.SC_UNPROCESSABLE_ENTITY:
		case HttpStatus.SC_INTERNAL_SERVER_ERROR:
			return true;
		default:
			return false;
		}
	}
	/**
	 * 判断返回是否为空
	 * @param code
	 * @return
	 */
	private boolean isEmpty(final int code){
		return HttpStatus.SC_NO_CONTENT == code;
	}
	
	
	/**
	 * Parse error from response
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	private <V> V parseJson(String jsonStr,Type type){
		return JsonUtil	.fromJson(jsonStr, type);
	}
	/**
	 * 解析
	 * @param error
	 * @return
	 */
	private RequestError parseError(String error){
		return parseJson(error,RequestError.class);
	}
	
	/***
	 * 处理HttpResponse
	 * @param response
	 * @param type
	 * @return
	 */
	private <V> V handleResponse(final HttpResponse response,
			Class<V> type)throws IOException{
		int code = response.getStatusLine().getStatusCode();
		if(isOk(code)){
			if(type != null){
				String jsonString = EntityUtils.toString(response.getEntity());
				log.i("response json objet " + jsonString);
				return parseJson(jsonString,type);
			}else
				return null;
		}else if(isEmpty(code)){
			return null;
		}
		throw createException(EntityUtils.toString(response.getEntity(),CHARSET_UTF8),
					code);
	}
	
	
	
	/**
	 * 创建RequestException对象
	 * @param response
	 * @param code
	 * @return
	 */
	private IOException createException(String response,int code){
		if(isError(code)){
			final RequestError error;
			error = parseError(response);
			if(null != error)
				return new RequestException(error,code);
		}
		return new IOException("Unknown error occured" + " " + code);

	}
	
	/**
	 * HttpClient POST
	 * @param context
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	public <V> V post(final String url,final Class<V> type,
			NameValuePair... nameValuePairs)throws Exception{
		HttpPost httpPost = createPost(url, nameValuePairs);
		HttpClient client = getHttpClient();
		HttpResponse response = client.execute(httpPost);
		
		return handleResponse(response, type);
	}
	
	/**
	 * HttpClient POST methos /post a json
	 * @param url
	 * @param type
	 * @param jsonObject
	 * @param nameValuePairs
	 * @return
	 * @throws Exception
	 */
	public <V> V post(final String url,final Class<V> type,
			String jsonObject,NameValuePair... nameValuePairs)throws Exception{
		String requestUrl = urlParse(url,nameValuePairs);
		HttpPost httpPost = new HttpPost(requestUrl);
		httpPost = (HttpPost)configureRequest(httpPost);
		httpPost.setEntity(new StringEntity(jsonObject));
		
		HttpClient client = getHttpClient();
		
		HttpResponse response = client.execute(httpPost);
		return handleResponse(response,type);
	}
	
	
	/**
	 * create HttpPost
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private HttpPost createPost(final String url,NameValuePair...nameValuePairs) throws UnsupportedEncodingException{
		//String requestUrl = urlParse(url, nameValuePairs);
		List<NameValuePair> params = getParams(nameValuePairs);

		UrlEncodedFormEntity urlEncoded = new UrlEncodedFormEntity(params,CHARSET_UTF8);
		HttpPost httpPost = new HttpPost(url);
		httpPost = (HttpPost)configureRequest(httpPost);
		httpPost.setEntity(urlEncoded);
		return httpPost;
	}
	
	/**
	 * get params from nameValuePairs
	 * @param nameValuePairs
	 * @return
	 */
	private List<NameValuePair> getParams(NameValuePair...nameValuePairs){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null != nameValuePairs)
			for(int i = 0;i < nameValuePairs.length;i++)
				params.add(nameValuePairs[i]);
		return params;
	}
	/**
	 * create HttpGet
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	private HttpGet createGet(final String url,NameValuePair...nameValuePairs){
		String requestUrl = urlParse(url, nameValuePairs);
		HttpGet httpGet = new HttpGet(requestUrl);
		httpGet = (HttpGet)configureRequest(httpGet);
		return httpGet;
	}
	
	/**
	 * create HttpPut
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private HttpPut createPut(final String url,NameValuePair...nameValuePairs) throws UnsupportedEncodingException{
		String requestUrl = urlParse(url,nameValuePairs);
		List<NameValuePair> params = getParams(nameValuePairs);
		
		UrlEncodedFormEntity urlEncoded = new UrlEncodedFormEntity(params,CHARSET_UTF8);
		HttpPut httpPut = new HttpPut(requestUrl);
		httpPut = (HttpPut)configureRequest(httpPut);
		httpPut.setEntity(urlEncoded);
		
		return httpPut;
	}
	
	
	public <V> V put(final String url,Class<V> type,
			NameValuePair...nameValuePairs)throws Exception{
		HttpPut httpPut = createPut(url, nameValuePairs);
		httpPut = (HttpPut)configureRequest(httpPut);
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpPut);
		
		return handleResponse(response,type);
	}
	/**
	 * create HttpDelete
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	private HttpDelete createDelete(final String url,
			NameValuePair...nameValuePairs){
		String requestUrl = urlParse(url, nameValuePairs);
		HttpDelete httpDelete = new HttpDelete(requestUrl);
		httpDelete = (HttpDelete)configureRequest(httpDelete);
		return httpDelete;
	}
	

	/**
	 * HttpClient GET方法
	 * @param context
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	public <V> V get(final String url,Class<V> type,
			NameValuePair...nameValuePairs) throws Exception{
		HttpGet httpGet = createGet(url, nameValuePairs);
		
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpGet);
		
		return handleResponse(response,type);
	}
	
	
	public CampusResponse get(CampusRequest request) 
			throws ClientProtocolException, IOException{
		HttpGet httpGet= new HttpGet(request.generateUri());
		httpGet = (HttpGet)configureRequest(httpGet);
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpGet);
		int code = response.getStatusLine().getStatusCode();
		if(isOk(code)){
			if(request.getType() != null){
				Object body = parseJson(EntityUtils.toString(response.getEntity(),CHARSET_UTF8),
						request.getType());
				return new CampusResponse(body);
			}
			else
				return new CampusResponse(null);
		}
		if(isEmpty(code))
			return null;
		throw createException(EntityUtils.toString(response.getEntity(),CHARSET_UTF8),
					code);
		
	}
	
	
	
	
	/**
	 * delete this method will throw an Exception
	 * 
	 * when the response status is not a 204
	 * 
	 * @param context
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	public void delete(final String url,
			NameValuePair...nameValuePairs)throws Exception{
		HttpDelete httpDelete = createDelete(url, nameValuePairs);
		httpDelete = (HttpDelete)configureRequest(httpDelete);
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpDelete);
		int code = response.getStatusLine().getStatusCode();
		if(!isEmpty(code))
			throw createException(EntityUtils.toString(response.getEntity(),CHARSET_UTF8),
					code);
	}
	
	/**
	 * configure httpPost httpGet and so on
	 * @param request
	 */
	protected HttpRequestBase configureRequest(HttpRequestBase request){
		if(credential != null)
			request.setHeader(HEADER_AUTHORIZATION,credential);
		request.setHeader(HEADER_CONTENT_TYPE,JSON);
		request.setHeader(HEADER_ACCEPT,JSON);
		return request;
	}
	
	
	
	/***
	 * url添加参数 生成请求url
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	private String urlParse(String url,NameValuePair...nameValuePairs){
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		if(null != nameValuePairs){
			sb.append("?");
			for(int i = 0;i < nameValuePairs.length;i++){
				if(i > 0){
					sb.append("&");
				}
				sb.append(String.format("%s=%s",
						nameValuePairs[i].getName(),
						nameValuePairs[i].getValue()));
				
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 创建HttpClient 实例
	 * @param context
	 * @return
	 */
	private  HttpClient getHttpClient(){
		if(null == customHttpClient){
			HttpParams params = new BasicHttpParams();
			//设置基本参数
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET_UTF8);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params, 
					"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
							+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
			//超时设置
			//从连接池中取连接的超时时间
			ConnManagerParams.setTimeout(params, 1000);
			int ConnectionTimeOut = 9000;
			//设置连接超时
			HttpConnectionParams.setConnectionTimeout(params, ConnectionTimeOut);
			//设置请求超时
			HttpConnectionParams.setSoTimeout(params, 9000);
			
			//设置支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
			schReg.register(new Scheme("https",PlainSocketFactory.getSocketFactory(),443));
			
			// 使用线程安全的连接管理来创建HttpClient
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params,schReg);
			
			customHttpClient = new DefaultHttpClient(conMgr,params);
		}
		return customHttpClient;
					
	}
}
	

