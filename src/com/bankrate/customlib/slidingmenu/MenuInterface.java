
package com.bankrate.customlib.slidingmenu;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

/** 
  * @Description: MenuInterface interface cua sliding menu
  * @author:truonglt2
  * @since:Feb 7, 2014 4:01:39 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public interface MenuInterface {

	/**
	*  scroll Behind To
	*  @author: truonglt2
	*  @param x
	*  @param y
	*  @param cvb
	*  @param scrollScale
	*  @return: void
	*  @throws: 
	*/
	public abstract void scrollBehindTo(int x, int y, 
			CustomViewBehind cvb, float scrollScale);
	
	/**
	*  get Menu Left
	*  @author: truonglt2
	*  @param cvb
	*  @param content
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public abstract int getMenuLeft(CustomViewBehind cvb, View content);
	
	/**
	*  get Abs Left Bound
	*  @author: truonglt2
	*  @param cvb
	*  @param content
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public abstract int getAbsLeftBound(CustomViewBehind cvb, View content);

	/**
	*  get Abs Right Bound
	*  @author: truonglt2
	*  @param cvb
	*  @param content
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public abstract int getAbsRightBound(CustomViewBehind cvb, View content);

	/**
	*  margin Touch Allowed
	*  @author: truonglt2
	*  @param content
	*  @param x
	*  @param threshold
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public abstract boolean marginTouchAllowed(View content, int x, int threshold);
	
	/**
	*  menu Open Touch Allowed
	*  @author: truonglt2
	*  @param content
	*  @param currPage
	*  @param x
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public abstract boolean menuOpenTouchAllowed(View content, int currPage, int x);
	
	/**
	*  menu Touch In Quick Return
	*  @author: truonglt2
	*  @param content
	*  @param currPage
	*  @param x
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public abstract boolean menuTouchInQuickReturn(View content, int currPage, int x);
	
	/**
	*  menu Closed Slide Allowed
	*  @author: truonglt2
	*  @param x
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public abstract boolean menuClosedSlideAllowed(int x);
	
	/**
	*  menu Open Slide Allowed
	*  @author: truonglt2
	*  @param x
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public abstract boolean menuOpenSlideAllowed(int x);
	
	/**
	*  draw Shadow
	*  @author: truonglt2
	*  @param canvas
	*  @param shadow
	*  @param width
	*  @return: void
	*  @throws: 
	*/
	public abstract void drawShadow(Canvas canvas, Drawable shadow, int width);
	
	/**
	*  draw Fade
	*  @author: truonglt2
	*  @param canvas
	*  @param alpha
	*  @param cvb
	*  @param content
	*  @return: void
	*  @throws: 
	*/
	public abstract void drawFade(Canvas canvas, int alpha, 
			CustomViewBehind cvb, View content);
	
	/**
	*  draw Selector
	*  @author: truonglt2
	*  @param content
	*  @param canvas
	*  @param percentOpen
	*  @return: void
	*  @throws: 
	*/
	public abstract void drawSelector(View content, Canvas canvas, float percentOpen);
	
}
