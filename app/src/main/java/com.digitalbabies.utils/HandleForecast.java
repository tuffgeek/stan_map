package com.digitalbabies.utils;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

public class HandleForecast {
	
	   private String temperature = "temperature";
	   private String humidity[] = new String[7];
	   private String pressure[] = new String[7];
	   private String urlString = null;
	   private String[] description = new String[7];
	   private String[] max=new String[7];
	   private String[] min=new String[7];

	   public volatile boolean parsingComplete = true;
	   private String weather;
	private String[] mainvalue=new String[7];
	   public HandleForecast(String url){
	      this.urlString = url;
	   }
	   public String getWeather(){
	      return weather;
	   }
	   public String getTemperature(){
	      return temperature;
	   }
	   public String[] getHumidity(){
	      return humidity;
	   }
	   public String[] getPressure(){
	      return pressure;
	   }
	   public String[] getMax(){
		      return max;
		   }
	   public String[] getMin(){
		      return min;
		   }
	   public CharSequence[] getDescription() {
			// TODO Auto-generated method stub
			return description;
		}
	   public String[] getMainValue() {
			// TODO Auto-generated method stub
			return mainvalue;
		}


	   @SuppressLint("NewApi")
	   public void readAndParseJSON(String in) {
	      try {
	    	  
	         JSONObject reader = new JSONObject(in);
	         if(!reader.has("list"))
	         {
	        	 System.out.println("can't find location");
	        	 parsingComplete = false;
	        	 this.max[0]="empty";

	         }else{
	          JSONArray list = reader.getJSONArray("list");
	          Log.e("list", ""+list);
	          for(int i=0;i<list.length();i++)
	          {
	        	  JSONObject listobjects = list.getJSONObject(i);
	        	  JSONObject temp=listobjects.getJSONObject("temp");
		          this.max[i]=temp.getString("max");
		          this.min[i]=temp.getString("min");
		          System.out.println(max+"maxxx"+min);
		          pressure[i] = listobjects.getString("pressure");
			      humidity[i] = listobjects.getString("humidity");
			      JSONArray weatherArray  = listobjects.getJSONArray("weather");
			         
			          JSONObject main = weatherArray.getJSONObject(0);
			          description[i]=main.getString("description");
			          mainvalue[i]=main.getString("main");

	          }
	          		          

	          
	          
	          
		         // Log.e("desc", ""+desc);
	         //Log.e("main", ""+mains);
	         // String desc=main.getString("description");
	         

	         /**/

	         parsingComplete = false;


	         }
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
	        	 System.out.println("url="+urlString);
	            URL url = new URL(urlString);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(10000 /* milliseconds */);
	            conn.setConnectTimeout(15000 /* milliseconds */);
	            conn.setRequestMethod("GET");
	            conn.setDoInput(true);
	            // Starts the query
	            conn.connect();
	         InputStream stream = conn.getInputStream();

	      String data = convertStreamToString(stream);

	      Log.w("data=", "data"+data);
	      readAndParseJSON(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }
	   static String convertStreamToString(InputStream is) {
	      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	      return s.hasNext() ? s.next() : "";
	   }

}
