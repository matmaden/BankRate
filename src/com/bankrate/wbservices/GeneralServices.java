package com.bankrate.wbservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

public class GeneralServices extends BaseWebservice{

	//private static String Authorization = "";
	
	public GeneralServices()
	{
		//Authorization user and pass admin
		//Authorization = BusinessProcess.getB64Auth();
	}
	
	
	
	public JSONObject getResultFetchInforUser(String username,String point, String deviceId)
	{	
		String wsUrl = "http://thuetrosinhvien.com/rest_slim/v1/registerNH";
		String errorLog = "Error while fetch-registerNH";
		
		Log.e("wsUrl", wsUrl);
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>(6);
		listParams.add(new BasicNameValuePair("name", username));
		listParams.add(new BasicNameValuePair("email", "lttruongx4@gmail.com"));
		listParams.add(new BasicNameValuePair("birthday", deviceId));
		listParams.add(new BasicNameValuePair("address", deviceId));
		listParams.add(new BasicNameValuePair("phone", deviceId));
		listParams.add(new BasicNameValuePair("gender", deviceId));
		return doGetJSONObjPOST(wsUrl, errorLog, listParams);
	}
	public String getResultBankRate()
	{	
		String wsUrl = "http://thuetrosinhvien.com/rest_slim/v1/InterestRate";
		String errorLog = "Error while fetch-getResultBankRate";
		Log.e("wsUrl", wsUrl);
		return getWSAsString(wsUrl, errorLog);
	}
	
	/*public JSONObject addAddress(String street1, String street2, String street3,
			String city, String zip, String regionId, String countryId, String typeId, String mailing, String primary)
	{	
		String wsUrl = Common.serverName + "/api/secure/jsonws/address/add-address";
		String errorLog = "Error while add-address";
		
		Log.e("wsUrl", wsUrl);
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>(12);
		listParams.add(new BasicNameValuePair("className", "com.liferay.portal.model.Contact"));
		listParams.add(new BasicNameValuePair("classPK", ApplicationConfig.contactId));
		listParams.add(new BasicNameValuePair("street1", street1));
		listParams.add(new BasicNameValuePair("street2", street2));
		listParams.add(new BasicNameValuePair("street3", street3));
		listParams.add(new BasicNameValuePair("city", city));
		listParams.add(new BasicNameValuePair("zip", zip));
		listParams.add(new BasicNameValuePair("regionId", regionId));//0
		listParams.add(new BasicNameValuePair("countryId", countryId));
		listParams.add(new BasicNameValuePair("typeId", typeId));//11000
		listParams.add(new BasicNameValuePair("mailing", mailing));
		listParams.add(new BasicNameValuePair("primary", primary));
		
		return doGetJSONObjPOST(wsUrl, errorLog, listParams);
	}
	public JSONObject addPhone(String number, String extension,
			String typeId, String primary)
	{	
		String wsUrl = Common.serverName + "/api/secure/jsonws/phone/add-phone";
		String errorLog = "Error while add-phone";
		
		Log.e("wsUrl", wsUrl);
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>(6);
		listParams.add(new BasicNameValuePair("className", "com.liferay.portal.model.Contact"));
		listParams.add(new BasicNameValuePair("classPK", ApplicationConfig.contactId));
		listParams.add(new BasicNameValuePair("number", number));
		listParams.add(new BasicNameValuePair("extension", extension));
		listParams.add(new BasicNameValuePair("typeId", typeId));//11006
		listParams.add(new BasicNameValuePair("primary", primary));
		
		return doGetJSONObjPOST(wsUrl, errorLog, listParams);
	}*/
	/*public JSONObject getImage()
	{	
		String wsUrl = Common.serverName + "/api/secure/jsonws/image/get-image";
		String errorLog = "Error while -get-image";
		
		Log.e("wsUrl", wsUrl);
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>(1);
		listParams.add(new BasicNameValuePair("imageId", ApplicationConfig.portraitId));
		
		return doGetJSONObjPOST(wsUrl, errorLog, listParams);
	}*/
}
