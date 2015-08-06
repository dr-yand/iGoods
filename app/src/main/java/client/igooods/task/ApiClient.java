package client.igooods.task;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;

import org.json.JSONObject;

import client.igooods.model.ServerResponseStatus;
import client.igooods.model.User;
import client.igooods.util.Config;


public class ApiClient {
	
	private Context mContext;
	
	public ApiClient(Context context){
		mContext = context;
	}

	public interface OnSignin{
		public void onSignin(ServerResponseStatus responseStatus, boolean result);
	}

	public void singin(final OnSignin listener,String login, String password){
		String action = "/api/v1/employees/sign_in";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jsonObject = new JSONObject(response);
					String employeeId = jsonObject.getString("employee_id");
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onSignin(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("employee[login]", login);
		params.put("employee[password]", password);
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params,requestType).execute(new Void[]{});
	}

	public interface OnSignout{
		public void onSignout(ServerResponseStatus responseStatus, boolean result);
	}

	public void signout(final OnSignout listener, final User user){
		String action = "/api/v1/employees/sign_out";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
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
						listener.onSignout(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();

		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnSendGeo{
		public void onSendGeo(ServerResponseStatus responseStatus, boolean result);
	}

	public void sendGeo(final OnSendGeo listener, final User user, String lat, String lon){
		String action = "/api/v1/employees/geo";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onSendGeo(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("employee_id", user.getId()+"");
		params.put("latitude", lat);
		params.put("longitude", lon);
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnGetOrders{
		public void onGetOrders(ServerResponseStatus responseStatus, boolean result);
	}

	public void getOrders(final OnGetOrders listener, final User user){
		String action = "/api/v1/orders";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.GET;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetOrders(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
//		params.put("employee_id", user.id);
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params,"11bf20ed-83e5-4487-a54f-871cf06db5b2", requestType).execute(new Void[]{});
	}

	public interface OnGetOrder{
		public void onGetOrder(ServerResponseStatus responseStatus, boolean result);
	}

	public void getOrder(final OnGetOrder listener, final User user, String id){
		String action = "/api/v1/orders/"+id;
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetOrder(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnDeclineOrder{
		public void onDeclineOrder(ServerResponseStatus responseStatus, boolean result);
	}

	public void declineOrder(final OnDeclineOrder listener, final User user, String id){
		String action = "/api/v1/orders/"+id+"/decline";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onDeclineOrder(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnPayOrder{
		public void onPayOrder(ServerResponseStatus responseStatus, boolean result);
	}

	public void payOrder(final OnPayOrder listener, final User user, String id){
		String action = "/api/v1/orders/"+id+"/pay";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onPayOrder(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnGetOrdersStats{
		public void onGetOrdersStats(ServerResponseStatus responseStatus, boolean result);
	}

	public void getOrdersStats(final OnGetOrdersStats listener, final User user){
		String action = "/api/v1/orders/stats";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetOrdersStats(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnGetOrdersReturbanleItems{
		public void onGetOrdersReturbanleItems(ServerResponseStatus responseStatus, boolean result);
	}

	public void getOrdersReturbanleItems(final OnGetOrdersReturbanleItems listener, final User user){
		String action = "/api/v1/orders/returnable_items";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetOrdersReturbanleItems(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnSendOrdersReturnItems{
		public void onSendOrdersReturnItems(ServerResponseStatus responseStatus, boolean result);
	}

	public void getSendOrdersReturnItems(final OnSendOrdersReturnItems listener, final User user){
		String action = "/api/v1/orders/return_items";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onSendOrdersReturnItems(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnGetItems{
		public void onGetItems(ServerResponseStatus responseStatus, boolean result);
	}

	public void getItems(final OnGetItems listener, final User user){
		String action = "/api/v1/items";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetItems(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnGetItem{
		public void onGetItem(ServerResponseStatus responseStatus, boolean result);
	}

	public void getItem(final OnGetItem listener, final User user, String id){
		String action = "/api/v1/items/"+id;
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onGetItem(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnItemConfirm{
		public void onItemConfirm(ServerResponseStatus responseStatus, boolean result);
	}

	public void itemConfirm(final OnItemConfirm listener, final User user, String id){
		String action = "/api/v1/items/"+id+"/confirm";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onItemConfirm(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}

	public interface OnBreaks{
		public void onBreaks(ServerResponseStatus responseStatus, boolean result);
	}

	public void breaks(final OnBreaks listener, final User user, String id){
		String action = "/api/v1/breaks";
		GeneralTask.RequestType requestType = GeneralTask.RequestType.POST;
		GeneralTask.OnResultListener resultListener = new GeneralTask.OnResultListener() {
			@Override
			public void onExecuteResult(String response) {
				ServerResponseStatus responseCode = ServerResponseStatus.ERROR;
				boolean result = false;
				try{
					JSONObject jObject= new JSONObject(response);
					
					responseCode = ServerResponseStatus.OK;
				}
				catch(Exception e){
					responseCode = ServerResponseStatus.ERROR;
					e.printStackTrace();
				}
				if(mContext instanceof Activity) {
					if(!((Activity)mContext).isFinishing()
							&& listener!=null){
						listener.onBreaks(responseCode, result);
					}
				}
			}
		};
		Map<String, String> params = new HashMap<>();
		String url = Config.SITE_URL+action;
		new GeneralTask(resultListener, url, mContext, params, requestType).execute(new Void[]{});
	}
}
