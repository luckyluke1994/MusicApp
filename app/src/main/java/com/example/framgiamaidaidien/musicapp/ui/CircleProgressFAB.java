package com.example.framgiamaidaidien.musicapp.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.widget.ImageView;

/**
 * Created by FRAMGIA\mai.dai.dien on 29/12/2016.
 */

public class CircleProgressFAB extends ImageView {
    private static final int STROKE_COLOR = 0xFFFF5722;
    private static final float START_DEGREE = -90;
    private int PADDING;
    private Paint circlePaint = new Paint();
    private int mRadiusBackground;
    private float mDegree = 0;
    private RectF mRectF;
    private boolean mIsClearProgress;

    public CircleProgressFAB(Context context) {
        super(context);
    }

    public CircleProgressFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressFAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int sizeLength = Math.min(w, h);
        mRadiusBackground = sizeLength / 2;
        setup();
    }

    private void setup() {
        PADDING = dpToPx(5);

        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(STROKE_COLOR);
        circlePaint.setStrokeWidth(PADDING);

        mRectF = new RectF(getWidth() / 2 - mRadiusBackground + PADDING,
                getHeight() / 2 - mRadiusBackground + PADDING,
                getWidth() - PADDING, getHeight() - PADDING);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw circle background
        drawCircleImage(canvas);

        if (!isClearProgress()) {
            // draw bound circle progress
            canvas.drawArc(mRectF, START_DEGREE, mDegree, false, circlePaint);
        } else {
            // clear bound circle progress
            circlePaint.setColor(Color.TRANSPARENT);
            canvas.drawArc(mRectF, START_DEGREE, 360, false, circlePaint);
            // reset color to default and flag clear to false and degree to 0
            circlePaint.setColor(STROKE_COLOR);
            mIsClearProgress = false;
            mDegree = 0;
        }
    }

    private void drawCircleImage(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();


        Bitmap roundBitmap = getCroppedBitmap(bitmap, Math.min(w, h));
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        /**
         * it's mean bmp's width and height is not equals
         * so we create bitmap base on bmp with width and height equal to min of width and height
         * of bmp
         */
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2,
                sbmp.getWidth() / 2 - PADDING, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public float getDegree() {
        return mDegree;
    }

    public void setDegree(float degree) {
        mDegree = degree;
        this.postInvalidate();
    }

    public boolean isClearProgress() {
        return mIsClearProgress;
    }

    /**
     * this function clear circle progress
     * after that it set flag to false in draw function
     * @param clearProgress is clear or not (true, false)
     */
    public void setClearProgress(boolean clearProgress) {
        mIsClearProgress = clearProgress;
        this.postInvalidate();
    }

    public static final Property<CircleProgressFAB, Float> DEGREE_PROGRESS =
            new Property<CircleProgressFAB, Float>(Float.class, "DegreeProgress") {
                @Override
                public Float get(CircleProgressFAB object) {
                    return object.getDegree();
                }

                @Override
                public void set(CircleProgressFAB object, Float value) {
                    object.setDegree(value);
                }
            };
}
