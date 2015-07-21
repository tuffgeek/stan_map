package com.digitalbabies.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalbabies.traafik.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ImageLoader {
 
    public MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<TextView, String> textViews = Collections.synchronizedMap(new WeakHashMap<TextView, String>());
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    // Handler to display images in UI thread
    Handler handler = new Handler();
	private Context context;
    public ImageLoader(Context context) {
    	
    	this.context=context;
        fileCache = new FileCache(context);
        
        executorService = Executors.newFixedThreadPool(5);
    }
 
    final int stub_id =R.drawable.icon;
    public void DisplayImage(String url, ImageView imageView) {
    	try{
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
        imageView.setImageBitmap(bitmap);
        else {
        queuePhoto(url, imageView);
        imageView.setImageResource(stub_id);
        }
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    @SuppressLint("NewApi")
	public void DisplayImageOnText(String url, TextView imageView) {
    	try{
        textViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
        imageView.setBackground(new BitmapDrawable(bitmap));
        else {
        queuePhotoForText(url, imageView);
        imageView.setBackground(context.getResources().getDrawable(stub_id));
        }
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
 
    private void queuePhoto(String url, ImageView imageView) {
        PhotoToLoad p = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }
    private void queuePhotoForText(String url, TextView imageView) {
    	PhotoToLoadForText p = new PhotoToLoadForText(url, imageView);
        executorService.submit(new PhotosLoaderForTextView(p));
    }
 
    public Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);
        Bitmap b = decodeFile(f);
        if (b != null)
        return b;
        // Download Images from the Internet
        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Util.CopyStream(is, os);
            os.close();
            conn.disconnect();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            if (ex instanceof OutOfMemoryError)
            memoryCache.clear();
            return null;
        }
    }
 
    // Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();
            // Find the correct scale value. It should be the power of 2.
            // Recommended Size 512
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE|| height_tmp / 2 < REQUIRED_SIZE)
            break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
            }
 
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    // Task for the queue
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i) {
        url = u;
        imageView = i;
        }
    }
    private class PhotoToLoadForText {
        public String url;
        public TextView imageView;
        public PhotoToLoadForText(String u, TextView i) {
        url = u;
        imageView = i;
        }
    }
 
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad) {
        this.photoToLoad = photoToLoad;
        }
 
        @Override
        public void run() {
            try {
                if (imageViewReused(photoToLoad))
                return;
                Bitmap bmp = getBitmap(photoToLoad.url);
                memoryCache.put(photoToLoad.url, bmp);
                if (imageViewReused(photoToLoad))
                return;
                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
                handler.post(bd);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
    
    class PhotosLoaderForTextView implements Runnable {
    	PhotoToLoadForText photoToLoad;
        PhotosLoaderForTextView(PhotoToLoadForText photoToLoad) {
        this.photoToLoad = photoToLoad;
        }
 
        @Override
        public void run() {
            try {
                if (imageViewReused(photoToLoad))
                return;
                Bitmap bmp = getBitmap(photoToLoad.url);
                memoryCache.put(photoToLoad.url, bmp);
                if (imageViewReused(photoToLoad))
                return;
                BitmapDisplayerForText bd = new BitmapDisplayerForText(bmp, photoToLoad);
                handler.post(bd);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
 
    boolean imageViewReused(PhotoToLoadForText photoToLoad) {
        String tag = textViews.get(photoToLoad.imageView);
        if (tag == null || !tag.equals(photoToLoad.url))
        return true;
        return false;
    }
    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = imageViews.get(photoToLoad.imageView);
        if (tag == null || !tag.equals(photoToLoad.url))
        return true;
        return false;
    }
 
    // Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
    
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
        bitmap = b;
        photoToLoad = p;
        }
 
        public void run() {
        	try{
            if (imageViewReused(photoToLoad))
            return;
            if (bitmap != null)
            photoToLoad.imageView.setImageBitmap(bitmap);
            else
            photoToLoad.imageView.setImageResource(stub_id);
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    	
    	
    }
    
 // Used to display bitmap in the UI thread
    class BitmapDisplayerForText implements Runnable {
    
        Bitmap bitmap;
        PhotoToLoadForText photoToLoad;
        public BitmapDisplayerForText(Bitmap b, PhotoToLoadForText p) {
        bitmap = b;
        photoToLoad = p;
        }
 
        @SuppressLint("NewApi")
		public void run() {
        	try{
            if (imageViewReused(photoToLoad))
            return;
            if (bitmap != null)
            photoToLoad.imageView.setBackground(new BitmapDrawable(bitmap));
            else
            photoToLoad.imageView.setBackgroundResource(stub_id);
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    	
    	
    }
 
    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }
 
}