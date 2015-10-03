package com.bankrate.imgloader;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Bitmap;

/** 
  * @Description: MemoryCache
  * @author:truonglt2
  * @since:Feb 7, 2014 5:05:51 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class MemoryCache {

    private Map<String, Bitmap> cache=Collections.synchronizedMap(
            new LinkedHashMap<String, Bitmap>(10,1.5f,true));//Last argument true for LRU ordering
    private long size=0;//current allocated size
//    private long limit=1000000;//max memory in bytes
    private long limit=1024*1024*10;//max memory in bytes

    public MemoryCache(){
        //use 20% of available heap size
        setLimit(Runtime.getRuntime().maxMemory()/5);
    }
    
    public void setLimit(long new_limit) {
        limit=new_limit;
//        Log.i(TAG, "MemoryCache will use up to "+limit/1024./1024.+"MB");
    }

    /**
    *  lay bitmap tu id cache
    *  @author: truonglt2
    *  @param id
    *  @return
    *  @return: Bitmap
    *  @throws: 
    */
    public Bitmap get(String id){
        try{
            if(!cache.containsKey(id))
                return null;
            //NullPointerException sometimes happen here http://code.google.com/p/osmdroid/issues/detail?id=78 
            return cache.get(id);
        }catch(NullPointerException ex){
            return null;
        }
    }

    /**
    *  dua vao cache
    *  @author: truonglt2
    *  @param id
    *  @param bitmap
    *  @return: void
    *  @throws: 
    */
    public void put(String id, Bitmap bitmap){
        try{
            if(cache.containsKey(id))
                size-=getSizeInBytes(cache.get(id));
            cache.put(id, bitmap);
            size+=getSizeInBytes(bitmap);
            checkSize();
        }catch(Throwable th){
            th.printStackTrace();
        }
    }
    
    /**
    *  check Size of bitmap
    *  @author: truonglt2
    *  @return: void
    *  @throws: 
    */
    private void checkSize() {
//        Log.i(TAG, "cache size="+size+" length="+cache.size());
//        Log.i(TAG, "LIMIT IS " + limit);
        if(size>limit){
            Iterator<Entry<String, Bitmap>> iter=cache.entrySet().iterator();//least recently accessed item will be the first one iterated  
            while(iter.hasNext()){
                Entry<String, Bitmap> entry=iter.next();
                size-=getSizeInBytes(entry.getValue());
                iter.remove();
                if(size<=limit)
                    break;
            }
//            Log.i(TAG, "Clean cache. New size "+cache.size());
        }
    }

    /**
    *  clear cache
    *  @author: truonglt2
    *  @return: void
    *  @throws: 
    */
    public void clear() {
        cache.clear();
    }

    /**
    *  get Size In bytes of bitmap
    *  @author: truonglt2
    *  @param bitmap
    *  @return
    *  @return: long
    *  @throws: 
    */
    long getSizeInBytes(Bitmap bitmap) {
        if(bitmap==null)
            return 0;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}