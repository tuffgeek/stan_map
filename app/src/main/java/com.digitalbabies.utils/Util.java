package com.digitalbabies.utils;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Util {

	/*
	 * 
	 * news feed link declared in rss feed
	 */
	//http://news.google.com/news?output=rss



	public static final String PhotoUtil = null;

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	public static void CopyStream(java.io.InputStream is, java.io.OutputStream os)
	{
		final int buffer_size=1024;
		try
		{
			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void set_login_uniq_id(SharedPreferences pref, String uniq) {
		pref.edit().putString(PlatfourmPreferences.UNIQ_id, uniq).commit();
	}

	public static String get_login_uniq_id(SharedPreferences pref) {
		return pref.getString(PlatfourmPreferences.UNIQ_id,
				PlatfourmPreferences.UNIQ_ID_VALUE);

	}


	public static Boolean isNetWorking(Context context) {
		try {
			ConnectivityManager ConMgr = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = ConMgr.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnected()) {

				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Show Alert Dialog
	 */

	 public static AlertDialog getAlertDialog(Context context, String msg,
			 Handler handler) {
		 AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		 alertDialog.setTitle("Traafik" + "");

		 alertDialog.setMessage(msg);
		 alertDialog.setCancelable(true);
		 alertDialog.setButton("OK", Message.obtain(handler, 1000));
		 return alertDialog;
	 }

	 /*
	  * Show Toast
	  */
	 public static void show_Toast(Context context, String message) {
		 Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	 }

	 /*
	  * Show Progress Dialog
	  */

	 private static ProgressDialog dialog;
	 public static ProgressDialog showDialog(String message, Context context) {
		 dialog = new ProgressDialog(context);
		 dialog.setMessage(message);
		 dialog.setCancelable(true);
		 dialog.setIndeterminate(false);
		 dialog.show();
		 return dialog;
	 }

	 /*
	  * Dismiss Dialog
	  */
	 public static void dismissDialog(ProgressDialog dialog) {
		 if (dialog != null && dialog.isShowing()) {
			 dialog.cancel();
		 }

	 }
	 /*
	  * Print Logcat with String
	  */
	 public static void printLocCat(String TAG, String message) {
		 Log.e(TAG, message);
	 }

	 /*
	  * Print Log Cat with Value
	  */
	 public static void printLocCatValue(String TAG, String message, String value) {
		 Log.e(TAG, message + value);

	 }


	 public static Typeface getAvailableFont(Context context) {
		 return Typeface.createFromAsset(context.getAssets(), "HELVETICANEUELTSTD-TH.OTF");

	 }

	 /*public static Typeface getAvailableFontBold(Context context) {
		return Typeface.createFromAsset(context.getAssets(), "textfont.ttf");

	}*/

	 public static void setListViewHeightBasedOnChildren(ListView listView) {
		 ListAdapter listAdapter = listView.getAdapter();
		 if (listAdapter == null) {
			 // pre-condition
			 return;
		 }

		 int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
		 for (int i = 0; i < listAdapter.getCount(); i++) {
			 View listItem = listAdapter.getView(i, null, listView);
			 if (listItem instanceof ViewGroup) {
				 listItem.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			 }
			 listItem.measure(0, 0);
			 totalHeight += listItem.getMeasuredHeight();
		 }

		 ViewGroup.LayoutParams params = listView.getLayoutParams();
		 params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		 listView.setLayoutParams(params);
	 }

	 public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		 Bitmap output = null;
		 try {
			 output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
			 Canvas canvas = new Canvas(output);
			 final int color = 0xff424242;
			 final Paint paint = new Paint();
			 final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			 final RectF rectF = new RectF(rect);
			 final float roundPx = 60;
			 paint.setAntiAlias(true);
			 canvas.drawARGB(0, 0, 0, 0);
			 paint.setColor(color);
			 canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			 paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			 canvas.drawBitmap(bitmap, rect, rect, paint);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 return output;
	 }
}
