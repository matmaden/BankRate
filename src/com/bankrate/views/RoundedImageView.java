
package com.bankrate.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bankrate.R;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: truonglt2
 *  @version: 1.0
 *  @since: 1.0
 */
public class RoundedImageView extends ImageView {
    private int borderWidth = 10;

    private int viewWidth;

    private int viewHeight;

    private Bitmap image;

    private Paint paint;

    private Paint paintBorder;

    private BitmapShader shader;

    /**
     * 
     * Constructor
     * @param context
     */
    public RoundedImageView(Context context) {
        super(context);
        setup();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        // init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        setBorderColor(getResources().getColor(R.color.white));
        paintBorder.setAntiAlias(true);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    private void loadBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        //load the bitmap
        loadBitmap();

        // init shader
        if (image != null) {
            shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(),
                    canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            int circleCenter = viewWidth / 2;

            // circleCenter is the x or y of the view's center
            // radius is the radius in pixels of the cirle to be drawn
            // paint contains the shader that will texture the shape
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter
                    + borderWidth, paintBorder);
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter,
                    paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

        viewWidth = width - (borderWidth * 2);
        viewHeight = height - (borderWidth * 2);

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = viewWidth;

        }

        return result;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = viewHeight;
        }
        return result;
    }
}
