package restapi;

import android.os.Looper;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class RestApiCallPost extends Thread{
	String url;
	RestApiCallListener listener;
	
	int pageId;
	List<NameValuePair>nvp;
	public RestApiCallPost(String url, RestApiCallListener listener,
			 List<NameValuePair>nvp, int pageId) {
		this.url = url;
		this.listener = listener;
		this.pageId = pageId;
		this.nvp=nvp;
		
	}

	@Override
	public void run() {
		Looper.prepare();
		try {
            Log.e("URL","URL"+url.toString());
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nvp));
		
			Log.e("NVP","NVP DATA"+nvp.toString());
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
		    /*Checking response */
               StringBuilder sb=new StringBuilder();
               if(response!=null){
                   InputStream in = response.getEntity().getContent(); //Get the data in the entity
                   BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                   String line = null;
                   while((line = reader.readLine()) != null){
                       sb.append(line);
                   }
                   sendResponse(sb.toString(), pageId);
               }
               else {
				sendError("No Data Found");
			}

		} catch (ClientProtocolException e) {
			sendError("No Data Found");
			 e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			sendError("No Data Found");
			Looper.loop();
			sendError("No Data Found");
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			sendError("No Data Found");
			Looper.loop();
			sendError("Time out Exception.");
		}
	}


	private void sendResponse(String response, int pageId) {
		if (listener != null) {
			listener.onResponse(response, pageId);
		}
	}

	private void sendError(String error) {
		if (listener != null) {
			listener.onError(error);
		}
	}

}
