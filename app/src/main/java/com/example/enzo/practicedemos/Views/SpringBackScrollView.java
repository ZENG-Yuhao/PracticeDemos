package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/2.
 */
public class SpringBackScrollView extends ScrollView
{
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 80;
    private int mMaxYOverscrollDistance;
    private View view;

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

    public void setView(View view)
    {
        this.view = view;
    }

    private void initDistance(Context context)
    {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int
            scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean
                                           isTouchEvent)
    {
        return super.overScrollBy(deltaX, (deltaY + 1) / 2, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY)
    {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        Log.i("onOverScrolled", "OnOverscrolled-->" + scrollX + "," + scrollY);
        if (view != null)
        {
            if (scrollY < -1)
            {
                if (view.getVisibility() != INVISIBLE)
                    view.setVisibility(INVISIBLE);
            } else
            {
                if (view.getVisibility() != VISIBLE)
                    view.setVisibility(VISIBLE);
            }
        }
    }
}
