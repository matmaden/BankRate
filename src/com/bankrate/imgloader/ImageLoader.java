package com.bankrate.imgloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import com.bankrate.R;
import com.bankrate.utils.ImageHelperUtil;

/** 
  * @Description: lop ImageLoader de load hinh
  * @author:truonglt2
  * @since:Feb 7, 2014 5:02:13 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class ImageLoader {

	/*public static final int TYPE_NORMAL = 0;
	public static final int TYPE_CIRCLE = 1;
	public static final int TYPE_ROUND_4_CORNER = 2;
	public static final int TYPE_ROUND_2_CORNER = 3;*/
    
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    boolean mPauseWork = false;
    private final Object mPauseWorkLock = new Object();
    protected Resources mResources;
    private static final int FADE_IN_TIME = 200;
    private static final boolean FADE_IN_BITMAP = false;
    
    public ImageLoader(Context context) {
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
        mResources = context.getResources();
    }
    
    /**
     * This method is used to load image
     * @param url is the url to load image
     * @param imageView is the image view that you need to load image on
     * @param hasImageSmall is true if there is a small bitmap on image view before
     *        hasImageSmall is false if there is no a small bitmap on image view before
     * @param requestWidth is with of image view
     * @param requestHeight is height of image view
     * @param typeImage is the type of image that you want to load. There are four types
     *        TYPE_NORMAL load image normal
     */
    public void DisplayImage(String url, ImageView imageView, int requestWidth, int requestHeight) {
    	imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
        	imageView.setImageBitmap(bitmap);
        } else {
            queuePhoto(url, imageView, requestWidth, requestHeight);
            try {
            	imageView.setImageResource(R.drawable.icon_book_big);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
    }
    
    /**
    *  Task for the queue
    *  @author: truonglt2
    *  @param url
    *  @param imageView
    *  @param requestWidth
    *  @param requestHeight
    *  @return: void
    *  @throws: 
    */
    private void queuePhoto(String url, ImageView imageView, int requestWidth, int requestHeight) {
        PhotoToLoad p = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p, requestWidth, requestHeight));
    }
    
    /**
    *  download url theo height va width
    *  @author: truonglt2
    *  @param url
    *  @param requestWidth
    *  @param requestHeight
    *  @return
    *  @return: Bitmap
    *  @throws: 
    */
    public Bitmap getBitmap(String url, int requestWidth, int requestHeight) {
        File f = fileCache.getFile(url);
        
        // from SD cache
        Bitmap b = decodeFile(f, requestWidth, requestHeight);
        if (b != null)
            return b;
        
        // from web
        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            bitmap = decodeFile(f, requestWidth, requestHeight);
            return bitmap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
    *  decodes image and scales it to reduce memory consumption
    *  @author: truonglt2
    *  @param f
    *  @param requestWidth
    *  @param requestHeight
    *  @return
    *  @return: Bitmap
    *  @throws: 
    */
    private Bitmap decodeFile(File f, int requestWidth, int requestHeight) {
        try {
            //decode image size
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            
            options.inSampleSize = ImageHelperUtil.calculateInSampleSize(options, requestWidth, requestHeight);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (FileNotFoundException e) {}
        return null;
    }

    // Task for the queue
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;
        /**
         * Constructor
         * @param u: string url
         * @param i: imageView
         */
        public PhotoToLoad(String u, ImageView i) {
            url = u; 
            imageView = i;
        }
    }
    
    /** 
      * @Description: Task for the queue
      * @author:truonglt2
      * @since:Feb 7, 2014 5:03:43 PM
      * @version: 1.0
      * @since: 1.0
      * 
      */
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        int requestWidth;
        int requestHeight;
        
        /**
         * Constructor
         * @param photoToLoad
         */
        PhotosLoader(PhotoToLoad photoToLoad, int requestWidth, int requestHeight) {
            this.photoToLoad = photoToLoad;
            this.requestWidth = requestWidth;
            this.requestHeight = requestHeight;
        }
        
        @Override
        public void run() {
        	// Wait here if work is paused and the task is not cancelled
            synchronized (mPauseWorkLock) {
                while (mPauseWork) {
                    try {
                        mPauseWorkLock.wait();
                    } catch (InterruptedException e) {}
                }
            }
        	
            if (imageViewReused(photoToLoad)) {
                return;
            }
            Bitmap bmp = getBitmap(photoToLoad.url, this.requestWidth, this.requestHeight);
            memoryCache.put(photoToLoad.url, bmp);
            if (imageViewReused(photoToLoad)) {
                return;
            }
            BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
            Activity a =(Activity)photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }
    
    /**
     * @param photoToLoad
     * @return true if the photoToLoad == null
     * @return false if the photoToLoad already existed
     */
    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = imageViews.get(photoToLoad.imageView);
        if( tag == null || !tag.equals(photoToLoad.url) )
            return true;
        return false;
    }
    
    // Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        
        /**
         * Constructor
         * @param b
         * @param p
         */
        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
        	bitmap = b;
        	photoToLoad = p;
        }
        
        public void run() {
            if (imageViewReused(photoToLoad))
                return;
            if (bitmap != null) {
            	if (FADE_IN_BITMAP) {
            		final TransitionDrawable td =
                            new TransitionDrawable(new Drawable[] {
                                    new ColorDrawable(android.R.color.transparent),
                                    new BitmapDrawable(mResources, bitmap)
                            });
                    photoToLoad.imageView.setImageDrawable(td);
                    td.startTransition(FADE_IN_TIME);
            	}  else {
            		photoToLoad.imageView.setImageBitmap(bitmap);
            	} // end if
            } else {
            	try {
            		photoToLoad.imageView.setImageResource(R.drawable.icon_book_big);
				} catch (Exception e) {
					e.printStackTrace();
				}
            		
//                photoToLoad.imageView.setImageResource(R.drawable.no_image);
            } // end if
        }
    }
    
    /**
    *  xoa cache
    *  @author: truonglt2
    *  @return: void
    *  @throws: 
    */
    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

    /**
    *  dung tien trinh down va hien thi
    *  @author: truonglt2
    *  @param pauseWork
    *  @return: void
    *  @throws: 
    */
    public void setPauseWork(boolean pauseWork) {
    	synchronized (mPauseWorkLock) {
            mPauseWork = pauseWork;
            if (!mPauseWork) {
                mPauseWorkLock.notifyAll();
            }
        }
    }
}
