package client.igoods.task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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

	private OnResultListener listener;
	private Context mContext;

	public GeneralTask(OnResultListener listener, Context context, Map<String, String> params){
		this.listener=listener;
		this.mContext=context;
		this.mParams = params;
	}

    @Override
    protected String doInBackground(Void... par) {

        HttpClient httpclient = new DefaultHttpClient();

        String url = "http://igoods.ru";

        HttpPost httppost = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);

        for(String key:mParams.keySet()){
            nameValuePairs.add(new BasicNameValuePair(key, mParams.get(key)));
        }

        HttpResponse response = null;
        String responseString = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = httpclient.execute(httppost);

            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
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
		listener = null;
		super.onCancelled();
	}

	@Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(listener!=null)
        	listener.onExecuteResult(response);
    }
}