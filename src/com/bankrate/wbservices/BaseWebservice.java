package com.bankrate.wbservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.bankrate.common.Common;


public abstract class BaseWebservice {
	
//	public static void changeServerPath(String newServerPath){
//		Common.SERVER = newServerPath;
//		Common.VIRTUAL_HOST = Common.SERVER;
//		if(Common.VIRTUAL_HOST.contains("http"))
//			Common.VIRTUAL_HOST.replace("http://", "");
//	}
	
	public static void init(){
//		Common.AUTHORIZE_STRING = "Basic "+ Common.authorization(UrlAPI.AUTHORIZATION_SERVER);
//		System.out.println("chuoi Common.AUTHORIZE_STRING:"+Common.AUTHORIZE_STRING);
	}
	
	public BaseWebservice()
	{
		
	}
	
	public String getWSAsString(String wsUrl, String errorLog){
		String ret = null;
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				ret = convertStreamToString(is).trim();			
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * get Service from Server
	 * @param wsUrl
	 * @param errorLog
	 * @return
	 */
	protected JSONArray doGet(String wsUrl, String errorLog){
		JSONArray ret = null;		
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doGet:::"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is);
				ret = new JSONArray(str);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
		}
		return ret;
	}
	
	protected JSONArray doGetJSONArrayPost(String wsUrl, String errorLog,List<NameValuePair> listParams){
		JSONArray ret = null;		
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doPost:::"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is);
				ret = new JSONArray(str);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	protected JSONArray doGetJSONArrayPostUTF_8(String wsUrl, String errorLog,List<NameValuePair> listParams){
		JSONArray ret = null;		
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		params.setBooleanParameter("http.protocol.expect-continue", false);
		HttpClient client = new DefaultHttpClient(params);
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doPost:::"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				/*InputStream is = entity.getContent();
				String str = convertStreamToString(is);*/
				String str= EntityUtils.toString(entity, HTTP.UTF_8);
				ret = new JSONArray(str);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	protected JSONObject doGetJSONObjectPostUTF_8_2(String wsUrl, String errorLog,List<NameValuePair> listParams){
		JSONObject ret = null;		
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		params.setBooleanParameter("http.protocol.expect-continue", false);
		HttpClient client = new DefaultHttpClient(params);
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doPost:::"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
//			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				/*InputStream is = entity.getContent();
				String str = convertStreamToString(is);*/
				String str= EntityUtils.toString(entity, HTTP.UTF_8);
				System.out.println(str);
				ret = new JSONObject(str);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	public JSONObject doGetJSONObj(String wsUrl, String errorLog){
		JSONObject ret = null;		
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doGetJSONObj"+ wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is);
				
				ret = new JSONObject(str);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	
	public JSONObject doGetJSONObjPOST(String wsUrl,String errorLog, List<NameValuePair> nameValuePairs){
		JSONObject ret = null;
		HttpClient client = new DefaultHttpClient();
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is).trim();
				System.out.println("chuoi str:"+ str);
				ret = new JSONObject(str);
				is.close();
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	public long doGetJSONObjPOST(String wsUrl,String errorLog, MultipartEntity nameValuePairs)
	{
		long ret = 0;
		HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpClient client = new DefaultHttpClient(params);
		System.out.println("WsURl:"+wsUrl);
		try
		{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization", Common.AUTHORIZE_STRING);
			post.setEntity(nameValuePairs);
			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null)
			{
				InputStream is = entity.getContent();
				String str = convertStreamToString(is).trim();
				ret = Long.parseLong(str);
				System.out.println(ret);
			}
			client.getConnectionManager().shutdown();
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	public boolean doBooleanPOST(String wsUrl,String errorLog, List<NameValuePair> nameValuePairs){
		boolean ret = false;
		HttpClient client = new DefaultHttpClient();
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is).trim();
				if(str.equals("true"))
					ret = true;
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return ret;
	}
	
	
	public JSONObject doGetNotAuth(String wsUrl, String errorLog){
		JSONObject ret = null;		
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("doGetJSONObj"+ wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String str = convertStreamToString(is);
				
				ret = new JSONObject(str);
			}
		}
		catch(Exception ex){
			
			Log.e(tag, errorLog);
		}
		return ret;
	}
	
	/**
	 * parse JSONArray to ArrayList<ArrayList<String>>
	 * @param jArr
	 * @param params
	 * @return
	 */
	public ArrayList<ArrayList<String>> jsonArrTo2DimenArrayList(JSONArray jArr, String[] params){
		ArrayList<ArrayList<String>> ret = null;
		if(jArr != null){
			try{
				ret = new ArrayList<ArrayList<String>>();
				int count = jArr.length();
				for(int i = 0; i < count; i++){
					ArrayList<String> item = new ArrayList<String>();
					JSONObject obj = jArr.getJSONObject(i);
					for(String iter : params){
						item.add(obj.getString(iter));
					}					
					ret.add(item);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * parse JSONArray to ArrayList<ArrayList<String>>
	 * print errorLog when error occur.
	 * @param jArr
	 * @param params
	 * @param errorLog
	 * @return
	 */
	public ArrayList<ArrayList<String>> jsonArrTo2DimenArrayList(JSONArray jArr, String[] params, String errorLog){
		ArrayList<ArrayList<String>> ret = null;
		if(jArr != null){
			try{
				ret = new ArrayList<ArrayList<String>>();
				int count = jArr.length();
				for(int i = 0; i < count; i++){
					ArrayList<String> item = new ArrayList<String>();
					JSONObject obj = jArr.getJSONObject(i);
					for(String iter : params){
						item.add(obj.getString(iter));
					}					
					ret.add(item);
				}
			}
			catch(Exception ex){
				Log.e(tag, errorLog);
				ex.printStackTrace();
			}
		}
		return ret;
	}
	
	public long doLong(String wsUrl, String errorLog){
		long kq = 0;
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String ret = convertStreamToString(is).trim();	
				kq = Long.parseLong(ret);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return kq;
	}
	
	public long doLongPOST(String wsUrl,String errorLog, List<NameValuePair> nameValuePairs){
		long kq = 0;
		HttpClient client = new DefaultHttpClient();
		System.out.println("WsURl:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String ret = convertStreamToString(is).trim();
				System.out.println("ket qua ret:"+ret);
				kq = Long.parseLong(ret);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return kq;
	}
	public boolean doBoolean(String wsUrl, String errorLog){
		boolean kq = false;
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				String ret = convertStreamToString(is).trim();	
				kq = Boolean.parseBoolean(ret);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return kq;
	}
	
	public String doStringPost(String wsUrl, String errorLog,List<NameValuePair> listParams){
		String kq = "";
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				kq = convertStreamToString(is).trim();
				if(kq.startsWith("\""))
					kq = kq.substring(1);
				if(kq.endsWith("\""))
					kq = kq.substring(0,kq.length() - 1);
				
				Log.e("kq doStringPost", kq);
				
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return kq;
	}
	public String doStringPOST(String wsUrl, String errorLog,List<NameValuePair> listParams){
		String kq = "";
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				kq = convertStreamToString(is).trim();
				System.out.println("kq doStringPost:"+ kq);
			}
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
		return kq;
	}
	public void doPost(String wsUrl, String errorLog,List<NameValuePair> listParams){
		HttpClient client = new DefaultHttpClient();
		wsUrl = wsUrl.replace(" ", "%20");
		System.out.println("getWSAsString:"+wsUrl);
		try{
			HttpPost post = new HttpPost(wsUrl);
			post.setHeader("Authorization",Common.AUTHORIZE_STRING);
			post.setEntity(new UrlEncodedFormEntity(listParams));
			client.execute(post);
		}
		catch(Exception ex){
			Log.e(tag, errorLog);
			ex.printStackTrace();
		}
	}
	
	public static String convertStreamToString(InputStream stream) throws IOException 
	{ 
		//Get Response	
      BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
      String line;
      StringBuffer response = new StringBuffer(); 
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();
		
	}
	private static final String tag = BaseWebservice.class.getSimpleName();
}
