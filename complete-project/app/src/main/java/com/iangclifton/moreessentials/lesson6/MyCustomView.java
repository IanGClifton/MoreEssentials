package com.iangclifton.moreessentials.lesson6;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.iangclifton.moreessentials.R;

/**
 * View that demonstrates measurement and basic drawing.
 *
 * @author Ian G. Clifton
 */
public class MyCustomView extends View {
    private static final String TAG = "MyCustomView";

    private int mDefaultSize;
    private int mInterval;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
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
        int top = getPaddingTop();
        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();
        int bottom = getHeight() - getPaddingBottom();

        while (top <= bottom) {
            canvas.drawLine(left, top, right, top, mPaint);
            top += mInterval;
        }
    }

    private void init() {
        setWillNotDraw(false);
        mDefaultSize = getResources().getDimensionPixelSize(R.dimen.custom_view_default_size);
        mInterval = getResources().getDimensionPixelSize(R.dimen.custom_view_interval);
        mPaint.setColor(Color.DKGRAY);
    }
}
