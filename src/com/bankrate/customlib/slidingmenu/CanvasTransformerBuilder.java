
package com.bankrate.customlib.slidingmenu;

import android.graphics.Canvas;
import android.view.animation.Interpolator;

import com.bankrate.customlib.slidingmenu.SlidingMenu.CanvasTransformer;

/** 
  * @Description: lop CanvasTransformerBuilder cua slidding menu
  * @author:truonglt2
  * @since:Feb 7, 2014 3:56:27 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class CanvasTransformerBuilder {

	private CanvasTransformer mTrans;

	private static Interpolator lin = new Interpolator() {
		public float getInterpolation(float t) {
			return t;
		}
	};

	/**
	*  initTransformer
	*  @author: truonglt2
	*  @return: void
	*  @throws: 
	*/
	private void initTransformer() {
		if (mTrans == null)
			mTrans = new CanvasTransformer() {
			public void transformCanvas(Canvas canvas, float percentOpen) {	}
		};
	}

	/**
	*  zoom
	*  @author: truonglt2
	*  @param openedX
	*  @param closedX
	*  @param openedY
	*  @param closedY
	*  @param px
	*  @param py
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer zoom(final int openedX, final int closedX, 
			final int openedY, final int closedY, 
			final int px, final int py) {
		return zoom(openedX, closedX, openedY, closedY, px, py, lin);
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: truonglt2
	*  @param openedX
	*  @param closedX
	*  @param openedY
	*  @param closedY
	*  @param px
	*  @param py
	*  @param interp
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer zoom(final int openedX, final int closedX, 
			final int openedY, final int closedY,
			final int px, final int py, final Interpolator interp) {
		initTransformer();
		mTrans = new CanvasTransformer() {
			public void transformCanvas(Canvas canvas, float percentOpen) {
				mTrans.transformCanvas(canvas, percentOpen);
				float f = interp.getInterpolation(percentOpen);
				canvas.scale((openedX - closedX) * f + closedX,
						(openedY - closedY) * f + closedY, px, py);
			}			
		};
		return mTrans;
	}

	/**
	*  rotate view
	*  @author: truonglt2
	*  @param openedDeg
	*  @param closedDeg
	*  @param px
	*  @param py
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer rotate(final int openedDeg, final int closedDeg, 
			final int px, final int py) {
		return rotate(openedDeg, closedDeg, px, py, lin);
	}

	/**
	*  rotate view
	*  @author: truonglt2
	*  @param openedDeg
	*  @param closedDeg
	*  @param px
	*  @param py
	*  @param interp
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer rotate(final int openedDeg, final int closedDeg, 
			final int px, final int py, final Interpolator interp) {
		initTransformer();
		mTrans = new CanvasTransformer() {
			public void transformCanvas(Canvas canvas, float percentOpen) {
				mTrans.transformCanvas(canvas, percentOpen);
				float f = interp.getInterpolation(percentOpen);
				canvas.rotate((openedDeg - closedDeg) * f + closedDeg, 
						px, py);
			}			
		};
		return mTrans;
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: truonglt2
	*  @param openedX
	*  @param closedX
	*  @param openedY
	*  @param closedY
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer translate(final int openedX, final int closedX, 
			final int openedY, final int closedY) {
		return translate(openedX, closedX, openedY, closedY, lin);
	}

	public CanvasTransformer translate(final int openedX, final int closedX, 
			final int openedY, final int closedY, final Interpolator interp) {
		initTransformer();
		mTrans = new CanvasTransformer() {
			public void transformCanvas(Canvas canvas, float percentOpen) {
				mTrans.transformCanvas(canvas, percentOpen);
				float f = interp.getInterpolation(percentOpen);
				canvas.translate((openedX - closedX) * f + closedX,
						(openedY - closedY) * f + closedY);
			}			
		};
		return mTrans;
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: truonglt2
	*  @param t
	*  @return
	*  @return: CanvasTransformer
	*  @throws: 
	*/
	public CanvasTransformer concatTransformer(final CanvasTransformer t) {
		initTransformer();
		mTrans = new CanvasTransformer() {
			public void transformCanvas(Canvas canvas, float percentOpen) {
				mTrans.transformCanvas(canvas, percentOpen);
				t.transformCanvas(canvas, percentOpen);
			}			
		};
		return mTrans;
	}

}
