package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.OverScroller;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class PullToRefreshListView extends ListView {

    private final static int SCROLL_DURATION = 800;
    private final static float OFFSET_RATIO = 1.8f;
    private OverScroller mScroller;


    private HeaderView mHeader;
    private int mHeaderHeight;
    private int FIRST_VISIBLE_POSITION = 0;

    private float lastPosY = -1;

    private boolean mEnableRefresh = true;
    private OnRefreshListener onRefreshListener;

    private boolean mEnableLoad = true;
    private OnLoadMoreListener onLoadMoreListener;

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

    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
    }

    public void setOnLoadMoreListner(OnLoadMoreListener listner) {
        onLoadMoreListener = listner;
    }

    public void setEnableRefresh(boolean state) {
        mEnableRefresh = state;
    }

    public void setEnableLoad(boolean state) {
        mEnableLoad = state;
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

        mScroller.startScroll(0, currHeight, 0, finalHeight - currHeight, SCROLL_DURATION);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            // there is only header, no need if statement
            mHeader.setVisibleHeight(mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastPosY = ev.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - lastPosY;
                lastPosY = ev.getRawY();

                if (getFirstVisiblePosition() == FIRST_VISIBLE_POSITION && deltaY > 0) {
                    updateHeaderHeight(deltaY / OFFSET_RATIO);
                }
                break;

            default:
                resetHeaderHeight();

        }

        return super.onTouchEvent(ev);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
