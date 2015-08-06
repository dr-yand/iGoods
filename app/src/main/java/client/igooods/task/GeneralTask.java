package client.igooods.task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class GeneralTask extends AsyncTask<Void, String, String>{

	private Map<String, String> mParams;
  
	public interface OnResultListener {
		public void onExecuteResult(String response);
	}

    public enum RequestType{
        GET, POST;
    }

	private OnResultListener mListener;
	private Context mContext;
    private String mUrl;

    private String mRequestId;
    private RequestType mRequestType;

	public GeneralTask(OnResultListener listener, String url, Context context, Map<String, String> params, String requestId, RequestType requestType){
        this(listener,url,context,params,requestType);
        this.mRequestId = requestId;
    }

    public GeneralTask(OnResultListener listener, String url, Context context, Map<String, String> params, RequestType requestType){
        this.mListener=listener;
        this.mContext=context;
        this.mParams = params;
        this.mUrl = url;
        this.mRequestType = requestType;
    }

    @Override
    protected String doInBackground(Void... par) {
        if(mRequestType==RequestType.POST){
            return postRequest();
        }
        else{
            return getRequest();
        }
    }

    private String postRequest(){
        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httprequest = new HttpPost(mUrl);

        if(mRequestId!=null){
            httprequest.addHeader("X-Request-Id" , "11bf20ed-83e5-4487-a54f-871cf06db5b2");
        }

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);

        for(String key:mParams.keySet()){
            nameValuePairs.add(new BasicNameValuePair(key, mParams.get(key)));
        }

        HttpResponse response = null;
        String responseString = null;
        try {
            httprequest.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = httpclient.execute(httprequest);

            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();

                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    Log.i("header","Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                }

//                String server = response.getFirstHeader("Server").getValue();

                Log.i("responseString",responseString);
            } else{
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    private String getRequest(){
        HttpClient httpclient = new DefaultHttpClient();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
        for(String key:mParams.keySet()){
            nameValuePairs.add(new BasicNameValuePair(key, mParams.get(key)));
        }
        String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");

        HttpGet httprequest = new HttpGet(mUrl+"?"+paramsString);

        if(mRequestId!=null){
            httprequest.addHeader("X-Request-Id" , "11bf20ed-83e5-4487-a54f-871cf06db5b2");
        }

        HttpResponse response = null;
        String responseString = null;
        try {
//            httprequest.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = httpclient.execute(httprequest);

            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();

                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    Log.i("header","Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                }

//                String server = response.getFirstHeader("Server").getValue();

                Log.i("responseString",responseString);
            } else{
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }


    @Override
	protected void onCancelled() {
		mListener = null;
		super.onCancelled();
	}

	@Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(mListener!=null)
            mListener.onExecuteResult(response);
    }
}