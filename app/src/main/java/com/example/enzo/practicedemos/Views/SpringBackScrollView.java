package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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
        params = new RelativeLayout.LayoutParams
                (outsideTopView.getLayoutParams());

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
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean
                                           isTouchEvent)
    {
        Log.i(TAG, "--overScrollBy-->" + flag);
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

        //Log.i(TAG, "--onOverScrolled-->");
        //Log.i("onOverScrolled", "OnOverscrolled-->" + scrollX + "," + scrollY);
        if (topView != null)
        {

            if (scrollY < 0)
            {
                params.setMargins(0, -mMaxYOverscrollDistance - scrollY, 0, 0);
                outsideTopView.setLayoutParams(params);
                if (topView.getVisibility() != INVISIBLE)
                    topView.setVisibility(INVISIBLE);
            } else
            {
                params.setMargins(0, -mMaxYOverscrollDistance, 0, 0);
                outsideTopView.setLayoutParams(params);
                if (topView.getVisibility() != VISIBLE)
                    topView.setVisibility(VISIBLE);
            }

            if (scrollY < -MAX_Y_OVERSCROLL_DISTANCE - 10 && !btnClicked)
                flag = true;

            if (btnClicked)
                flag = false;
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }
}