package com.bankrate.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.bankrate.R;
import com.bankrate.customlib.slidingmenu.BaseActivity;
import com.bankrate.customlib.slidingmenu.SlidingMenu;
import com.bankrate.fragment.AboutMeFragment;
import com.bankrate.fragment.StoreDetailsFragment;
import com.bankrate.services.MyService;

/** 
  * @Description: activity man hinh chinh
  * @author:truonglt2
  * @since:Feb 7, 2014 4:23:45 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class MainActivity extends BaseActivity implements OnClickListener
 {
    public FragmentManager fragmentManager;
    private ImageView mImgOpenLeftNavigation;
    private ImageView mImgOpenStoreNavigation;
    private ImageView imgStopServiceLocation;
    private ImageView mImgSearchBookNavigation;
    // Fragment
    public AboutMeFragment aboutFragment;
    Toast toastExit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setValue();
        setEvent();
//        SharedPreferences pre = getSharedPreferences(name, mode)
        // Load dash board first
        aboutFragment = AboutMeFragment.newInstance();
        setFragmentContent(aboutFragment, AboutMeFragment.TAG);
    }

    /**
     * This method is used to set fragment content
     * @param fragment
     * @param tag
     */
    public void setFragmentContent(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment, tag);
        fragmentTransaction.commit();
        slidingMenu.showContent();
    }

    /**
    *  khoi tao cac thanh phan
    *  @author: truonglt2
    *  @return: MainActivity
    *  @throws: 
    */
    @SuppressLint("ShowToast")
	@Override
    public void init() {
    	toastExit = Toast.makeText(this, R.string.message_quit_app, Toast.LENGTH_SHORT);
        fragmentManager = getSupportFragmentManager();
        // Image view
        mImgOpenLeftNavigation = (ImageView) findViewById(R.id.img_menu);
        mImgOpenStoreNavigation = (ImageView) findViewById(R.id.img_menu_store);
        mImgSearchBookNavigation = (ImageView) findViewById(R.id.img_menu_search);
        imgStopServiceLocation = (ImageView)findViewById(R.id.img_stop_service_location);
    }



    /**
    *  set gia tri cho cac thanh phan
    *  @author: truonglt2
    *  @return: MainActivity
    *  @throws: 
    */
    @Override
    public void setValue() {
    	
    }

    /**
    *  set su kien cho cac thanh phan
    *  @author: truonglt2
    *  @return: MainActivity
    *  @throws: 
    */
    @Override
    public void setEvent() {
        // Image view
        mImgOpenLeftNavigation.setOnClickListener(this);
        mImgSearchBookNavigation.setOnClickListener(this);
        mImgOpenStoreNavigation.setOnClickListener(this);
        imgStopServiceLocation.setOnClickListener(this);
    }

    /**
    *  xu ly onclick
    *  @author: truonglt2
    *  @param v
    *  @return: MainActivity
    *  @throws: 
    */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.img_menu:
            slidingMenu.setMode(SlidingMenu.LEFT);
            toggle();
            break;
        case R.id.img_menu_search:
            slidingMenu.setMode(SlidingMenu.RIGHT);
            toggle();
            break;
        case R.id.img_menu_store:
        	try {
				StoreDetailsFragment fragmentStotr = StoreDetailsFragment.newInstance();
        		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, fragmentStotr, StoreDetailsFragment.TAG);
                fragmentTransaction.commit();
                slidingMenu.showContent();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	 
            break;
        case R.id.img_stop_service_location:
        	 try {
        		 if(isServiceRunning((Activity)MainActivity.this, MyService.LOG_TAG))
        		 {
        			 stopService(new Intent(MainActivity.this, MyService.class));
        		 }
        		 else
        		 {
        			 startService(new Intent(MainActivity.this, MyService.class));
        		 }
			} catch (Exception e) {
				e.printStackTrace();
			}
            break;
        default:
            break;
        }
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
    }
    /**
    *  check trang thai hoat dong cua service
    *  @author: truonglt2
    *  @param acti
    *  @param serviceClassName
    *  @return
    *  @return: boolean
    *  @throws: 
    */
    public static boolean isServiceRunning(Activity acti,String serviceClassName){
        final ActivityManager activityManager = (ActivityManager)acti.getSystemService(Context.ACTIVITY_SERVICE);
        final List<RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
     }
    /**
    *  xu ly phim bat
    *  @author: truonglt2
    *  @return: MainActivity
    *  @throws: 
    */
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	if (toastExit.getView().isShown()) {
			finish();
		} else {
			toastExit.show();
		}
    	super.onBackPressed();
    }
   
    /**
    *  nhan broadcast
    *  @author: truonglt2
    *  @param action
    *  @param bundle
    *  @return: MainActivity
    *  @throws: 
    */
    @Override
    public void receiveBroadcast(int action, Bundle bundle) {
    	// TODO Auto-generated method stub
    	super.receiveBroadcast(action, bundle);
    }
}
