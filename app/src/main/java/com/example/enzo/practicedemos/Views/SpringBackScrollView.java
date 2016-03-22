package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/2.
 */
public class SpringBackScrollView extends ScrollView
{
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    private static final String TAG = "SpringBackScrollView";
    private boolean flag = false, btnClicked = false;
    private int mMaxYOverscrollDistance;
    private View topView;
    private View outsideTopView;
    private RelativeLayout.LayoutParams params;
    private ScrollByRecorder mScrollByRecorder;
    private OverScroller mScroller;

    public SpringBackScrollView(Context context)
    {
        super(context);
        initDistance(context);

    }

    public SpringBackScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initDistance(context);
    }

    public SpringBackScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initDistance(context);
    }

    public void setTopView(View topView)
    {
        this.topView = topView;
    }

    public void setOutsideTopView(View outsideTopView)
    {
        this.outsideTopView = outsideTopView;
        params = new RelativeLayout.LayoutParams(outsideTopView.getLayoutParams());

    }

    public void setButtonClicked(boolean btnClicked)
    {
        this.btnClicked = btnClicked;
    }

    private void initDistance(Context context)
    {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
        mScrollByRecorder = new ScrollByRecorder();
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
            Log.i("onOverScrolled", "MyScroller-->" + mScroller.getCurrX() + "," + mScroller.getCurrY());
            // postInvalidate() must be called, otherwise scrolling may not be visible
            invalidate();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void invokeOverScrollBy()
    {
        mScrollByRecorder.invokeOverScrollBy();
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
    {

        //Log.i(TAG, "--onOverScrolled-->");
//        Log.i("onOverScrolled", "OnOverscrolled-->" + scrollX + "," + scrollY + "; " + MAX_Y_OVERSCROLL_DISTANCE +
// "," +
//                " " + mMaxYOverscrollDistance);
        if (topView != null)
        {
            // because everytime scrollview back to its initial position, scrollY will not be 0 but -1.
            // so we set the offset -1 here.
            if (scrollY < -1)
            {
                params.setMargins(0, -mMaxYOverscrollDistance - scrollY, 0, 0);
                //outsideTopView.setLayoutParams(params);
                if (topView.getVisibility() != INVISIBLE)
                    topView.setVisibility(INVISIBLE);
            } else
            {
                params.setMargins(0, -mMaxYOverscrollDistance, 0, 0);
                //outsideTopView.setLayoutParams(params);
                if (topView.getVisibility() != VISIBLE)
                    topView.setVisibility(VISIBLE);
            }

            if (scrollY < -mMaxYOverscrollDistance + 5 && !btnClicked)
            {
                flag = true;
                mScrollByRecorder.set(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                        maxOverScrollY, isTouchEvent);
            }

            if (btnClicked)
                flag = false;
        }
//        Log.i(TAG, "--overScrollBy-->" + flag);
        Log.i(TAG, "--overScrollBy-->" + deltaX + " ," + deltaY + " ," + scrollRangeX + " ," + scrollRangeY);

        int newDeltaY = (deltaY + 2) / 2;
        if (flag)
        {
            return super.overScrollBy(deltaX, 0, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                    mMaxYOverscrollDistance, isTouchEvent);
        } else
        {
            return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                    mMaxYOverscrollDistance, isTouchEvent);
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY)
    {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public void fling(int velocityY)
    {
        super.fling(velocityY);
    }


    private class ScrollByRecorder
    {
        public int tmp_deltaX, tmp_deltaY, tmp_scrollX, tmp_scrollY, tmp_scrollRangeX, tmp_scrollRangeY,
                tmp_maxOverScrollX, tmp_maxOverScrollY;
        public boolean tmp_isTouchEvent;

        public void set(int tmp_deltaX, int tmp_deltaY, int tmp_scrollX, int tmp_scrollY, int
                tmp_scrollRangeX, int tmp_scrollRangeY, int tmp_maxOverScrollX, int tmp_maxOverScrollY,
                        boolean tmp_isTouchEvent)
        {
            this.tmp_deltaX = tmp_deltaX;
            this.tmp_deltaY = tmp_deltaY;
            this.tmp_scrollX = tmp_scrollX;
            this.tmp_scrollY = tmp_scrollY;
            this.tmp_scrollRangeX = tmp_scrollRangeX;
            this.tmp_scrollRangeY = tmp_scrollRangeY;
            this.tmp_maxOverScrollX = tmp_maxOverScrollX;
            this.tmp_maxOverScrollY = tmp_maxOverScrollY;
            this.tmp_isTouchEvent = tmp_isTouchEvent;
        }

        public void invokeOverScrollBy()
        {
            overScrollBy(tmp_deltaX, tmp_deltaY, tmp_scrollX, tmp_scrollY, tmp_scrollRangeX, tmp_scrollRangeY,
                    tmp_maxOverScrollX, tmp_maxOverScrollY, tmp_isTouchEvent);
        }
    }
}
