package client.igoods.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.prefs.PreferenceChangeEvent;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import client.igoods.model.ServerResponseStatus;
import client.igoods.model.User;
import client.igoods.util.PreferenceUtils;


public class ApiClient {
	
	private Context mContext;
	
	public ApiClient(Context context){
		mContext = context;
	}

	public interface OnRegCheck{
		public void onRegCheck(ServerResponseStatus responseStatus, boolean result);
	}

	public void regCheck(final OnRegCheck listener,final String login){
		String action = "reg_check";
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					String email = jObject.getString("email");
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onRegCheck(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("action", action);
		params.put("email", login);
		new GeneralTask(resultListener, mContext, params).execute(new Void[]{});
	}

	public interface OnAuth{
		public void onAuth(ServerResponseStatus responseStatus, boolean result);
	}

	public void auth(final OnAuth listener, final User user){
		String action = "auth";
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					String auth = jObject.getString("auth");
					if(auth.equals("false")) {
						result = false;
					}
					else {
						result = true;
					}
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onAuth(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("action", action);
		new GeneralTask(resultListener, mContext, params).execute(new Void[]{});
	}

	public interface OnRegister{
		public void onRegister(ServerResponseStatus responseStatus, boolean result);
	}

	public void register(final OnRegister listener, final User user){
		String action = "register";
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					String email = jObject.getString("email");
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onRegister(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("action", action);
		new GeneralTask(resultListener, mContext, params).execute(new Void[]{});
	}
}
