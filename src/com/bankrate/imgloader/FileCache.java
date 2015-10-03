package com.bankrate.imgloader;

import java.io.File;

import com.bankrate.common.Common;

import android.content.Context;

/** 
  * @Description: lop FileCache
  * @author:truonglt2
  * @since:Feb 7, 2014 4:56:59 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class FileCache {
    
    private File cacheDir;
    
    /**
    *  @author: truonglt2
    */
    public FileCache(Context context){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"/Android/data/"+Common.PACKAGE_NAME+"/cache/");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }
    
    /**
    *  get File theo url
    *  @author: truonglt2
    *  @param url
    *  @return
    *  @return: File
    *  @throws: 
    */
    public File getFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename=String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;
        
    }

    /**
    *  xoa cache
    *  @author: truonglt2
    *  @return: void
    *  @throws: 
    */
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }
}