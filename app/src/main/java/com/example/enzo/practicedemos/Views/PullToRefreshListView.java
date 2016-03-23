package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.OverScroller;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class PullToRefreshListView extends ListView {

    private OverScroller mScroller;
    private HeaderView mHeader;
    private int mHeaderHeight;

    public PullToRefreshListView(Context context) {
        super(context);
        init(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mScroller = new OverScroller(context, new DecelerateInterpolator());

        // initialize header
        mHeader = new HeaderView(context);
        addHeaderView(mHeader);

        // init header height, this method used to avoid getting 0 values of view's width and height when onCreate()
        ViewTreeObserver observer = mHeader.getViewTreeObserver();
        if (null != observer) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mHeaderHeight = mHeader.getHeight();
                    ViewTreeObserver observerLocal = getViewTreeObserver();

                    if (null != observerLocal) {
                        // removeOnGlobalLayoutListener() is only supported by SDK later than JELLY_BEAN
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                            observerLocal.removeGlobalOnLayoutListener(this);
                        else
                            observerLocal.removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    private void updateHeaderHeight(float delta) {
        mHeader.setVisibleHeight((int) delta + mHeader.getVisibleHeight());
        // set the currently selected item
        setSelection(0);
    }

    private void resetHeaderHeight() {
        int currHeight = mHeader.getVisibleHeight();
        if (currHeight == 0) return;

        int finalHeight = 0;
        if (currHeight > mHeaderHeight) {
            finalHeight = mHeaderHeight;
        }


    }
}
