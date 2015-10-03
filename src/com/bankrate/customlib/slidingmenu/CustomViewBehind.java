
package com.bankrate.customlib.slidingmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bankrate.R;
import com.bankrate.customlib.slidingmenu.SlidingMenu.CanvasTransformer;

/** 
  * @Description: lop CustomViewBehind cua sliding menu
  * @author:truonglt2
  * @since:Feb 7, 2014 3:57:26 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
/** 
  * @Description: mo ta muc dich lop
  * @author:truonglt2
  * @since:Feb 7, 2014 4:07:04 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class CustomViewBehind extends ViewGroup {

	private static final String TAG = "CustomViewBehind";

	private static final int MARGIN_THRESHOLD = 48; // dips
	private int mTouchMode = SlidingMenu.TOUCHMODE_MARGIN;

	private CustomViewAbove mViewAbove;

	private View mContent;
	private View mSecondaryContent;
	private int mMarginThreshold;
	private int mWidthOffset;
	private CanvasTransformer mTransformer;
	private boolean mChildrenEnabled;

	public CustomViewBehind(Context context) {
		this(context, null);
	}

	/**
	*  @author: truonglt2
	*/
	public CustomViewBehind(Context context, AttributeSet attrs) {
		super(context, attrs);
		mMarginThreshold = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				MARGIN_THRESHOLD, getResources().getDisplayMetrics());
	}

	/**
	*  set Custom View Above
	*  @author: truonglt2
	*  @param customViewAbove
	*  @return: void
	*  @throws: 
	*/
	public void setCustomViewAbove(CustomViewAbove customViewAbove) {
		mViewAbove = customViewAbove;
	}

	/**
	*  set Canvas Transformer
	*  @author: truonglt2
	*  @param t
	*  @return: void
	*  @throws: 
	*/
	public void setCanvasTransformer(CanvasTransformer t) {
		mTransformer = t;
	}

	/**
	*  set Width Offset
	*  @author: truonglt2
	*  @param i
	*  @return: void
	*  @throws: 
	*/
	public void setWidthOffset(int i) {
		mWidthOffset = i;
		requestLayout();
	}
	
	/**
	*  set Margin Threshold
	*  @author: truonglt2
	*  @param marginThreshold
	*  @return: void
	*  @throws: 
	*/
	public void setMarginThreshold(int marginThreshold) {
		mMarginThreshold = marginThreshold;
	}
	
	public int getMarginThreshold() {
		return mMarginThreshold;
	}

	public int getBehindWidth() {
		return mContent.getWidth();
	}

	public void setContent(View v) {
		if (mContent != null)
			removeView(mContent);
		mContent = v;
		addView(mContent);
	}

	public View getContent() {
		return mContent;
	}

	/**
	 * Sets the secondary (right) menu for use when setMode is called with SlidingMenu.LEFT_RIGHT.
	 * @param v the right menu
	 */
	public void setSecondaryContent(View v) {
		if (mSecondaryContent != null)
			removeView(mSecondaryContent);
		mSecondaryContent = v;
		addView(mSecondaryContent);
	}

	public View getSecondaryContent() {
		return mSecondaryContent;
	}

	public void setChildrenEnabled(boolean enabled) {
		mChildrenEnabled = enabled;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		if (mTransformer != null)
			invalidate();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent e) {
		return !mChildrenEnabled;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return !mChildrenEnabled;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (mTransformer != null) {
			canvas.save();
			mTransformer.transformCanvas(canvas, mViewAbove.getPercentOpen());
			super.dispatchDraw(canvas);
			canvas.restore();
		} else
			super.dispatchDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int width = r - l;
		final int height = b - t;
		mContent.layout(0, 0, width-mWidthOffset, height);
		if (mSecondaryContent != null)
			mSecondaryContent.layout(0, 0, width-mWidthOffset, height);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getDefaultSize(0, widthMeasureSpec);
		int height = getDefaultSize(0, heightMeasureSpec);
		setMeasuredDimension(width, height);
		final int contentWidth = getChildMeasureSpec(widthMeasureSpec, 0, width-mWidthOffset);
		final int contentHeight = getChildMeasureSpec(heightMeasureSpec, 0, height);
		mContent.measure(contentWidth, contentHeight);
		if (mSecondaryContent != null)
			mSecondaryContent.measure(contentWidth, contentHeight);
	}

	private int mMode;
	private boolean mFadeEnabled;
	private final Paint mFadePaint = new Paint();
	private float mScrollScale;
	private Drawable mShadowDrawable;
	private Drawable mSecondaryShadowDrawable;
	private int mShadowWidth;
	private float mFadeDegree;

	/**
	*  set Mode
	*  @author: truonglt2
	*  @param mode
	*  @return: void
	*  @throws: 
	*/
	public void setMode(int mode) {
		if (mode == SlidingMenu.LEFT || mode == SlidingMenu.RIGHT) {
			if (mContent != null)
				mContent.setVisibility(View.VISIBLE);
			if (mSecondaryContent != null)
				mSecondaryContent.setVisibility(View.INVISIBLE);
		}
		mMode = mode;
	}

	/**
	*  get Mode
	*  @author: truonglt2
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public int getMode() {
		return mMode;
	}

	/**
	*  set Scroll Scale
	*  @author: truonglt2
	*  @param scrollScale
	*  @return: void
	*  @throws: 
	*/
	public void setScrollScale(float scrollScale) {
		mScrollScale = scrollScale;
	}

	/**
	*  get Scroll Scale
	*  @author: truonglt2
	*  @return
	*  @return: float
	*  @throws: 
	*/
	public float getScrollScale() {
		return mScrollScale;
	}

	/**
	* set Shadow Drawable
	*  @author: truonglt2
	*  @param shadow
	*  @return: void
	*  @throws: 
	*/
	public void setShadowDrawable(Drawable shadow) {
		mShadowDrawable = shadow;
		invalidate();
	}

	/**
	*  set Secondary Shadow Drawable
	*  @author: truonglt2
	*  @param shadow
	*  @return: void
	*  @throws: 
	*/
	public void setSecondaryShadowDrawable(Drawable shadow) {
		mSecondaryShadowDrawable = shadow;
		invalidate();
	}

	/**
	*  set Shadow Width
	*  @author: truonglt2
	*  @param width
	*  @return: void
	*  @throws: 
	*/
	public void setShadowWidth(int width) {
		mShadowWidth = width;
		invalidate();
	}

	/**
	*  set Fade Enabled
	*  @author: truonglt2
	*  @param b
	*  @return: void
	*  @throws: 
	*/
	public void setFadeEnabled(boolean b) {
		mFadeEnabled = b;
	}

	/**
	*  set Fade Degree
	*  @author: truonglt2
	*  @param degree
	*  @return: void
	*  @throws: 
	*/
	public void setFadeDegree(float degree) {
		if (degree > 1.0f || degree < 0.0f)
			throw new IllegalStateException("The BehindFadeDegree must be between 0.0f and 1.0f");
		mFadeDegree = degree;
	}

	/**
	*  get Menu Page
	*  @author: truonglt2
	*  @param page
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public int getMenuPage(int page) {
		page = (page > 1) ? 2 : ((page < 1) ? 0 : page);
		if (mMode == SlidingMenu.LEFT && page > 1) {
			return 0;
		} else if (mMode == SlidingMenu.RIGHT && page < 1) {
			return 2;
		} else {
			return page;
		}
	}

	/**
	*  scroll Behind To
	*  @author: truonglt2
	*  @param content
	*  @param x
	*  @param y
	*  @return: void
	*  @throws: 
	*/
	public void scrollBehindTo(View content, int x, int y) {
		int vis = View.VISIBLE;		
		if (mMode == SlidingMenu.LEFT) {
			if (x >= content.getLeft()) vis = View.INVISIBLE;
			scrollTo((int)((x + getBehindWidth())*mScrollScale), y);
		} else if (mMode == SlidingMenu.RIGHT) {
			if (x <= content.getLeft()) vis = View.INVISIBLE;
			scrollTo((int)(getBehindWidth() - getWidth() + 
					(x-getBehindWidth())*mScrollScale), y);
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			mContent.setVisibility(x >= content.getLeft() ? View.INVISIBLE : View.VISIBLE);
			mSecondaryContent.setVisibility(x <= content.getLeft() ? View.INVISIBLE : View.VISIBLE);
			vis = x == 0 ? View.INVISIBLE : View.VISIBLE;
			if (x <= content.getLeft()) {
				scrollTo((int)((x + getBehindWidth())*mScrollScale), y);				
			} else {
				scrollTo((int)(getBehindWidth() - getWidth() + 
						(x-getBehindWidth())*mScrollScale), y);				
			}
		}
		if (vis == View.INVISIBLE)
			Log.v(TAG, "behind INVISIBLE");
		setVisibility(vis);
	}

	/**
	*  get Menu Left
	*  @author: truonglt2
	*  @param content
	*  @param page
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public int getMenuLeft(View content, int page) {
		if (mMode == SlidingMenu.LEFT) {
			switch (page) {
			case 0:
				return content.getLeft() - getBehindWidth();
			case 2:
				return content.getLeft();
			}
		} else if (mMode == SlidingMenu.RIGHT) {
			switch (page) {
			case 0:
				return content.getLeft();
			case 2:
				return content.getLeft() + getBehindWidth();	
			}
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			switch (page) {
			case 0:
				return content.getLeft() - getBehindWidth();
			case 2:
				return content.getLeft() + getBehindWidth();
			}
		}
		return content.getLeft();
	}

	/**
	*  get Abs Left Bound
	*  @author: truonglt2
	*  @param content
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public int getAbsLeftBound(View content) {
		if (mMode == SlidingMenu.LEFT || mMode == SlidingMenu.LEFT_RIGHT) {
			return content.getLeft() - getBehindWidth();
		} else if (mMode == SlidingMenu.RIGHT) {
			return content.getLeft();
		}
		return 0;
	}

	/**
	* get Abs Right Bound
	*  @author: truonglt2
	*  @param content
	*  @return
	*  @return: int
	*  @throws: 
	*/
	public int getAbsRightBound(View content) {
		if (mMode == SlidingMenu.LEFT) {
			return content.getLeft();
		} else if (mMode == SlidingMenu.RIGHT || mMode == SlidingMenu.LEFT_RIGHT) {
			return content.getLeft() + getBehindWidth();
		}
		return 0;
	}

	/**
	*  margin Touch Allowed
	*  @author: truonglt2
	*  @param content
	*  @param x
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public boolean marginTouchAllowed(View content, int x) {
		int left = content.getLeft();
		int right = content.getRight();
		if (mMode == SlidingMenu.LEFT) {
			return (x >= left && x <= mMarginThreshold + left);
		} else if (mMode == SlidingMenu.RIGHT) {
			return (x <= right && x >= right - mMarginThreshold);
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			return (x >= left && x <= mMarginThreshold + left) || 
					(x <= right && x >= right - mMarginThreshold);
		}
		return false;
	}

	/**
	*  set Touch Mode
	*  @author: truonglt2
	*  @param i
	*  @return: void
	*  @throws: 
	*/
	public void setTouchMode(int i) {
		mTouchMode = i;
	}

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
	public boolean menuOpenTouchAllowed(View content, int currPage, float x) {
		switch (mTouchMode) {
		case SlidingMenu.TOUCHMODE_FULLSCREEN:
			return true;
		case SlidingMenu.TOUCHMODE_MARGIN:
			return menuTouchInQuickReturn(content, currPage, x);
		}
		return false;
	}

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
	public boolean menuTouchInQuickReturn(View content, int currPage, float x) {
		if (mMode == SlidingMenu.LEFT || (mMode == SlidingMenu.LEFT_RIGHT && currPage == 0)) {
			return x >= content.getLeft();
		} else if (mMode == SlidingMenu.RIGHT || (mMode == SlidingMenu.LEFT_RIGHT && currPage == 2)) {
			return x <= content.getRight();
		}
		return false;
	}

	/**
	*  menu Closed Slide Allowed
	*  @author: truonglt2
	*  @param dx
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public boolean menuClosedSlideAllowed(float dx) {
		if (mMode == SlidingMenu.LEFT) {
			return dx > 0;
		} else if (mMode == SlidingMenu.RIGHT) {
			return dx < 0;
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			return true;
		}
		return false;
	}

	/**
	*  menu Open Slide Allowed
	*  @author: truonglt2
	*  @param dx
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public boolean menuOpenSlideAllowed(float dx) {
		if (mMode == SlidingMenu.LEFT) {
			return dx < 0;
		} else if (mMode == SlidingMenu.RIGHT) {
			return dx > 0;
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			return true;
		}
		return false;
	}

	/**
	*  draw Shadow
	*  @author: truonglt2
	*  @param content
	*  @param canvas
	*  @return: void
	*  @throws: 
	*/
	public void drawShadow(View content, Canvas canvas) {
		if (mShadowDrawable == null || mShadowWidth <= 0) return;
		int left = 0;
		if (mMode == SlidingMenu.LEFT) {
			left = content.getLeft() - mShadowWidth;
		} else if (mMode == SlidingMenu.RIGHT) {
			left = content.getRight();
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			if (mSecondaryShadowDrawable != null) {
				left = content.getRight();
				mSecondaryShadowDrawable.setBounds(left, 0, left + mShadowWidth, getHeight());
				mSecondaryShadowDrawable.draw(canvas);
			}
			left = content.getLeft() - mShadowWidth;
		}
		mShadowDrawable.setBounds(left, 0, left + mShadowWidth, getHeight());
		mShadowDrawable.draw(canvas);
	}

	/**
	*  draw Fade
	*  @author: truonglt2
	*  @param content
	*  @param canvas
	*  @param openPercent
	*  @return: void
	*  @throws: 
	*/
	public void drawFade(View content, Canvas canvas, float openPercent) {
		if (!mFadeEnabled) return;
		final int alpha = (int) (mFadeDegree * 255 * Math.abs(1-openPercent));
		mFadePaint.setColor(Color.argb(alpha, 0, 0, 0));
		int left = 0;
		int right = 0;
		if (mMode == SlidingMenu.LEFT) {
			left = content.getLeft() - getBehindWidth();
			right = content.getLeft();
		} else if (mMode == SlidingMenu.RIGHT) {
			left = content.getRight();
			right = content.getRight() + getBehindWidth();			
		} else if (mMode == SlidingMenu.LEFT_RIGHT) {
			left = content.getLeft() - getBehindWidth();
			right = content.getLeft();
			canvas.drawRect(left, 0, right, getHeight(), mFadePaint);
			left = content.getRight();
			right = content.getRight() + getBehindWidth();			
		}
		canvas.drawRect(left, 0, right, getHeight(), mFadePaint);
	}
	
	private boolean mSelectorEnabled = true;
	private Bitmap mSelectorDrawable;
	private View mSelectedView;
	
	/**
	*  draw Selector
	*  @author: truonglt2
	*  @param content
	*  @param canvas
	*  @param openPercent
	*  @return: void
	*  @throws: 
	*/
	public void drawSelector(View content, Canvas canvas, float openPercent) {
		if (!mSelectorEnabled) return;
		if (mSelectorDrawable != null && mSelectedView != null) {
			String tag = (String) mSelectedView.getTag(R.id.selected_view);
			if (tag.equals(TAG+"SelectedView")) {
				canvas.save();
				int left, right, offset;
				offset = (int) (mSelectorDrawable.getWidth() * openPercent);
				if (mMode == SlidingMenu.LEFT) {
					right = content.getLeft();
					left = right - offset;
					canvas.clipRect(left, 0, right, getHeight());
					canvas.drawBitmap(mSelectorDrawable, left, getSelectorTop(), null);		
				} else if (mMode == SlidingMenu.RIGHT) {
					left = content.getRight();
					right = left + offset;
					canvas.clipRect(left, 0, right, getHeight());
					canvas.drawBitmap(mSelectorDrawable, right - mSelectorDrawable.getWidth(), getSelectorTop(), null);
				}
				canvas.restore();
			}
		}
	}
	
	/**
	*  set Selector Enabled
	*  @author: truonglt2
	*  @param b
	*  @return: void
	*  @throws: 
	*/
	public void setSelectorEnabled(boolean b) {
		mSelectorEnabled = b;
	}

	/**
	*  set Selected View
	*  @author: truonglt2
	*  @param v
	*  @return: void
	*  @throws: 
	*/
	public void setSelectedView(View v) {
		if (mSelectedView != null) {
			mSelectedView.setTag(R.id.selected_view, null);
			mSelectedView = null;
		}
		if (v != null && v.getParent() != null) {
			mSelectedView = v;
			mSelectedView.setTag(R.id.selected_view, TAG+"SelectedView");
			invalidate();
		}
	}

	/**
	*  get Selector Top
	*  @author: truonglt2
	*  @return
	*  @return: int
	*  @throws: 
	*/
	private int getSelectorTop() {
		int y = mSelectedView.getTop();
		y += (mSelectedView.getHeight() - mSelectorDrawable.getHeight()) / 2;
		return y;
	}

	/**
	* set Selector Bitmap
	*  @author: truonglt2
	*  @param b
	*  @return: void
	*  @throws: 
	*/
	public void setSelectorBitmap(Bitmap b) {
		mSelectorDrawable = b;
		refreshDrawableState();
	}

}
