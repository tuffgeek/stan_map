package com.digitalbabies.utils;
/*package com.example.first;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

public class HandleJSON {
	//private String country = "country";
	   private String temp_max = "";
	   private String humidity = "humidity";
	   private String pressure = "pressure";
	   private String urlString = null;
	   private String description = "description";
	   private String max="max";
	   private String temp_min;
	   public volatile boolean parsingComplete = true;
	private String temp;
	
	   public HandleJSON(String url){
	      this.urlString = url;
	   }
	   
	   
	   public String getTemperature(){
	      return temp;
	   }
	   public String getHumidity(){
	      return humidity;
	   }
	   public String getPressure(){
	      return pressure;
	   }
	   public CharSequence getDescription() {
			// TODO Auto-generated method stub
			return description;
		}
	   public CharSequence getMax() {
			// TODO Auto-generated method stub
			return temp_max;
		}
	   public CharSequence getMin() {
			// TODO Auto-generated method stub
			return temp_min;
		}


	   

	@SuppressLint("NewApi")
	   public void readAndParseJSON(String in) {
	      try {
	         JSONObject reader = new JSONObject(in);

	         //JSONObject sys  = reader.getJSONObject("sys");
	         //country = sys.getString("country");

	         JSONObject main  = reader.getJSONObject("main");
	         temp=main.getString("temp");
	         temp_max = main.getString("temp_max");
	         temp_min=main.getString("temp_min");
	         pressure = main.getString("pressure");
	         humidity = main.getString("humidity");
	         
	         JSONArray weather=reader.getJSONArray("weather");
	      
	         JSONObject jObject=weather.getJSONObject(0);
	    	   description=jObject.getString("description");
	    	   Log.e("desc", ""+description);
      
	         for(int l=0;l<weather.length();l++)
	         {
	    	   JSONObject jObject=weather.getJSONObject(l);
	    	   description=jObject.getString("description");
	    	   Log.e("desc", ""+description);
	         }

	         parsingComplete = false;

	        } catch (Exception e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	        }

	   }
	   public void fetchJSON(){
	      Thread thread = new Thread(new Runnable(){
	         @Override
	         public void run() {
	         try {
	            URL url = new URL(urlString);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(10000  milliseconds );
	            conn.setConnectTimeout(15000  milliseconds );
	            conn.setRequestMethod("GET");
	            conn.setDoInput(true);
	            // Starts the query
	            conn.connect();
	         InputStream stream = conn.getInputStream();

	      String data = convertStreamToString(stream);

	      readAndParseJSON(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }
	   static String convertStreamToString(java.io.InputStream is) {
	      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	      return s.hasNext() ? s.next() : "";
	   }
	
}
*/