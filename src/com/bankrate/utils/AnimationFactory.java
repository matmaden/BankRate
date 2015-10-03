package com.bankrate.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/** 
  * @Description: lop animation factory de tao hinh anh chuyen dong
  * @author:truonglt2
  * @since:Feb 7, 2014 5:26:12 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class AnimationFactory {
	/**
	 * @return Animation that moves from left to position of View
	 */
	public static Animation inFromLeft() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	/**
	 * @return Animation that moves from Right to position of View
	 */
	public static Animation inFromRight() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * @return Animation that moves from Top to position of View
	 */
	public static Animation inFromTop() {
		Animation inFromTop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromTop.setInterpolator(new AccelerateInterpolator());
		return inFromTop;
	}

	/**
	 * @return Animation that moves from Bottom to position of View
	 */
	public static Animation inFromBottom() {
		Animation inFromBottom = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromBottom.setInterpolator(new AccelerateInterpolator());
		return inFromBottom;
	}

	/**
	 * @return Animation that fades in
	 */
	public static Animation inFade() {
		Animation inFade = new AlphaAnimation(0.0f, 1.0f);
		inFade.setInterpolator(new AccelerateInterpolator());
		return inFade;
	}

	/**
	 * @return Animation that moves from position of View to left
	 */
	public static Animation outToLeft() {
		Animation outToLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToLeft.setInterpolator(new AccelerateInterpolator());
		return outToLeft;
	}
	
	

	/**
	 * @return Animation that moves from position of View to right
	 */
	public static Animation outToRight() {
		Animation outToRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToRight.setInterpolator(new AccelerateInterpolator());
		return outToRight;
	}

	/**
	 * @return Animation that moves from position of View to top
	 */
	public static Animation outToTop() {
		Animation outToTop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f);
		outToTop.setInterpolator(new AccelerateInterpolator());
		return outToTop;
	}

	/**
	 * @return Animation that moves from position of View to bottom
	 */
	public static Animation outToBottom() {
		Animation outToBottom = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f);
		outToBottom.setInterpolator(new AccelerateInterpolator());
		return outToBottom;
	}

	/**
	 * @return Animation that fades out
	 */
	public static Animation outFade() {
		Animation outFade = new AlphaAnimation(1.0f, 0.0f);
		outFade.setInterpolator(new AccelerateInterpolator());
		return outFade;
	}
	
	public static Animation toLeft1() {
		Animation outToLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -0.15f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToLeft.setDuration(5000);
		return outToLeft;
	}
	
	public static Animation toRight1() {
		Animation outToRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -0.15f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToRight.setDuration(5000);
		return outToRight;
	}
	
	/**
	*  set do alpha hide
	*  @author: Truonglt2
	*  @return
	*  @return: Animation
	*  @throws: 
	*/
	public static Animation alphaHide() {
		Animation outFade = new AlphaAnimation(1.0f, 0.2f);
		outFade.setDuration(1000);
//		outFade.setInterpolator(new AccelerateInterpolator());
		return outFade;
	}
	
	/**
	*  set do alpha khi show
	*  @author: truonglt2
	*  @return
	*  @return: Animation
	*  @throws: 
	*/
	public static Animation alphaShow() {
		Animation outFade = new AlphaAnimation(0.2f, 1.0f);
		outFade.setDuration(1000);
//		outFade.setInterpolator(new AccelerateInterpolator());
		return outFade;
	}
}
