package com.iangclifton.moreessentials.lesson7;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.iangclifton.moreessentials.R;

/**
 * View that demonstrates measurement, drawing, touch interactions and
 * custom attributes.
 *
 * Remember when interacting with touch events that you are tracking a
 * specific pointer ID over time, but each method requires the index (which
 * can change from one event to the next). That means you always need to
 * look up the index because it may have changed from one onTouchEvent
 * call to the next. Don't forget to return true when you want to continue
 * receiving touch events, including from the onDown method of your
 * GestureDetector.
 *
 * @author Ian G. Clifton
 */
public class MyBetterCustomView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final String TAG = "MyCustomView";

    private int mDefaultSize;
    private int mInterval;
    private Bitmap mBitmap;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String mTextToDisplay = "Nothing happening";

    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;

    private GestureDetectorCompat mGestureDetector;
    private float mOffsetX = 0;
    private float mOffsetY = 0;

    private boolean mDrawGrid;

    public MyBetterCustomView(Context context) {
        super(context);
        init();
    }

    public MyBetterCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setAttributes(attrs);
    }

    public MyBetterCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setAttributes(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyBetterCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        setAttributes(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Measure width
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int width;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = mDefaultSize + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        // Measure height
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mDefaultSize + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        setMeasuredDimension(width, height);
        Log.d(TAG, "Width: " + width + "; height: " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawARGB(100, 200, 255, 255);

        // Save the Canvas state
        canvas.save();
        //canvas.clipRect(getPaddingLeft(), getPaddingTop(), getWidth() / 2, getHeight() / 2);
        canvas.rotate(90f, getWidth() / 2, getHeight() / 2);

        int top = (int) (getPaddingTop() + mOffsetY);
        int left = (int) (getPaddingLeft() + mOffsetX);
        int right = getWidth() - getPaddingRight();
        int bottom = getHeight() - getPaddingBottom();

//        canvas.drawLine(left, top, left + mDefaultSize, top, mPaint);
        if (mDrawGrid) {
            while (top <= bottom) {
                canvas.drawLine(left, top, right, top, mPaint);
                top += mInterval;
            }
        }

        // Draw Bitmap
        canvas.drawBitmap(mBitmap, left, getPaddingTop(), mPaint);

        // Restore the Canvas state
        canvas.restore();

        canvas.drawText(mTextToDisplay, left, getPaddingTop(), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return true;
    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mActivePointerId != MotionEvent.INVALID_POINTER_ID) {
                    return false;
                }
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                if (mActivePointerId == MotionEvent.INVALID_POINTER_ID) {
                    Log.w(TAG, "Invalid pointer ID");
                    return false;
                }

                mTextToDisplay = "Action down: " + MotionEventCompat.getX(event, 0) + ", " + MotionEventCompat.getY(event, 0);
                invalidate();
            return true;

            case MotionEvent.ACTION_MOVE: {
                int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                if (pointerIndex == -1) {
                    Log.d(TAG, "Pointer ID not found, ignoring action move");
                    return false;
                }

                mTextToDisplay = "Action move: " + MotionEventCompat.getX(event, pointerIndex) + ", " + MotionEventCompat.getY(event, pointerIndex);
                invalidate();
                return true;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                if (pointerIndex == -1) {
                    Log.d(TAG, "Pointer ID not found, ignoring action up/cancel");
                    return false;
                }
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;

                mTextToDisplay = "Action up/cancel: " + MotionEventCompat.getX(event, pointerIndex) + ", " + MotionEventCompat.getY(event, pointerIndex);
                invalidate();
            return true;

        }

        return super.onTouchEvent(event);
    }
    */

    private void init() {
        setWillNotDraw(false);
        mDefaultSize = getResources().getDimensionPixelSize(R.dimen.custom_view_default_size);
        mInterval = getResources().getDimensionPixelSize(R.dimen.custom_view_interval);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setTextSize(50f);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        mGestureDetector = new GestureDetectorCompat(getContext(), new OurScrollListener());
//        mGestureDetector.setIsLongpressEnabled(true);
//        mGestureDetector.setOnDoubleTapListener(this);
    }

    /**
     * Reads the custom attributes that we care about
     *
     * If you need a more detailed example of reading attributes, take a look at the example in
     * the book Android User Interface Design, Second Edition. Appendix B, Listing B.10 includes
     * reading from all types of attributes and is available via GitHub:
     * https://github.com/IanGClifton/auid2/blob/master/appendixb/listing_b.10.txt
     *
     * @param attrs AttributeSet from the constructor
     */
    private void setAttributes(AttributeSet attrs) {
        TypedArray customAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.MyBetterCustomView);

        mDrawGrid = customAttrs.getBoolean(R.styleable.MyBetterCustomView_booleanExample, false);

        int textColor = customAttrs.getColor(R.styleable.MyBetterCustomView_android_textColor, Color.DKGRAY);
        mPaint.setColor(textColor);

        customAttrs.recycle();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mTextToDisplay = "onDown detected";
        invalidate();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        mTextToDisplay = "onShowPress detected";
        invalidate();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        mTextToDisplay = "onSingleTapUp detected";
        invalidate();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mTextToDisplay = "onScroll detected; distanceX: " + distanceX;
        invalidate();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mTextToDisplay = "onLongPress detected";
        invalidate();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mTextToDisplay = "onFling detected; velocityX: " + velocityX;
        invalidate();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        mTextToDisplay = "onSingleTapConfirmed detected";
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        mTextToDisplay = "onDoubleTap detected";
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        mTextToDisplay = "onDoubleTapEvent detected";
        invalidate();
        return true;
    }

    private class OurScrollListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // handle scroll
            mOffsetX += distanceX;
            mOffsetY += distanceY;
            invalidate();
            return true;
        }
    }
}
