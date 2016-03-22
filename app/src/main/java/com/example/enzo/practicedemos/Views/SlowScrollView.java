package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/22.
 */
public class SlowScrollView extends ScrollView
{
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    private int mMaxYOverscrollDistance;

    private OverScroller mScroller;

    public SlowScrollView(Context context)
    {
        super(context);
        init(context);
    }

    public SlowScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public SlowScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
        mScroller = new OverScroller(context);
    }

    public void slowScrollTo(int fx, int fy, int duration)
    {
        // replace getScrollX() to mScroller.getFinalX() if it's a normal view
        int dx = fx - getScrollX();
        int dy = fy - getScrollY();
//        int dx = fx - mScroller.getFinalX();
//        int dy = fy - mScroller.getFinalY();
        slowScrollBy(dx, dy, duration);
    }

    public void slowScrollBy(int dx, int dy, int duration)
    {
        // replace getScrollX() to mScroller.getFinalX() if it's a normal view
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, duration);
        //mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, duration);
        // invalidate() must be called, otherwise scrolling may not be visible
        invalidate();
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        // if the scrolling has not finished yet.
        if (mScroller.computeScrollOffset())
        {
            smoothScrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.i("computeScroll", "MyScroller-->" + mScroller.getCurrX() + "," + mScroller.getCurrY());
            // postInvalidate() must be called, otherwise scrolling may not be visible
            invalidate();
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
    {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                mMaxYOverscrollDistance, isTouchEvent);
    }
}
