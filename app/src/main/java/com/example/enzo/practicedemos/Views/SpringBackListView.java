package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by enzoz on 2016/4/3.
 */
public class SpringBackListView extends ListView {
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    private int mMaxYOverscrollDistance;
    private static final String TAG = "SpringBackScrollView";

    public SpringBackListView(Context context) {
        super(context);
        init();
    }

    public SpringBackListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpringBackListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                mMaxYOverscrollDistance, isTouchEvent);
    }
}
