package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.RelativeLayout;

import com.example.enzo.practicedemos.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class PullToRefreshListView extends ListView {

    private final static int SCROLL_DURATION = 800;
    private final static float OFFSET_RATIO = 1.8f;
    private final static int SCROLL_TARGET_HEADER = 0;
    private final static int SCROLL_TARGET_FOOTER = 1;
    private final static int FIRST_VISIBLE_POSITION = 0;

    private float lastPosY = -1;

    private OverScroller xScroller;
    private boolean xEnableScroll = true;
    private ListAdapter xAdapter;

    // header config
    private HeaderView xHeader;
    private RelativeLayout xHeaderContent;
    private int xHeaderHeight;
    private boolean isRefreshing = false;
    private boolean xEnableRefresh = true;
    private OnRefreshListener xRefreshListener;

    // footer config
    private FooterView xFooter;
    private boolean isLoading = false;
    private boolean xEnableLoad = true;
    private OnLoadMoreListener xLoadMoreListener;

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
        xScroller = new OverScroller(context, new DecelerateInterpolator());

        // initialize header
        xHeader = new HeaderView(context);
        setUpdateTime();
        addHeaderView(xHeader);
        xHeaderContent = (RelativeLayout) xHeader.findViewById(R.id.header_view_content);

        // init header height, this method used to avoid getting 0 values of view's width and height when onCreate()
        // in OnCreate(), height and width have not been valued yet.
        ViewTreeObserver observer = xHeader.getViewTreeObserver();
        if (null != observer) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    xHeaderHeight = xHeaderContent.getHeight();
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
        xRefreshListener = listener;
    }

    public void setOnLoadMoreListner(OnLoadMoreListener listener) {
        xLoadMoreListener = listener;
    }

    public void setEnableRefresh(boolean state) {
        xEnableRefresh = state;
    }

    public void setEnableLoad(boolean state) {
        xEnableLoad = state;
    }

    private void updateHeaderHeight(float delta) {
        xHeader.setVisibleHeight((int) delta + xHeader.getVisibleHeight());

        if (xEnableRefresh && !isRefreshing) {
            if (xHeader.getVisibleHeight() > xHeaderHeight) {
                xHeader.setState(HeaderView.STATE_READY);
            } else {
                xHeader.setState(HeaderView.STATE_STAND_BY);
            }
        }

        // set the currently selected item
        setSelection(0);
    }

    private void resetHeaderHeight() {
        int currHeight = xHeader.getVisibleHeight();
        if (currHeight == 0) return;

        if (isRefreshing && currHeight <= xHeaderHeight) return;

        int finalHeight = 0;
        if (isRefreshing && currHeight > xHeaderHeight) {
            finalHeight = xHeaderHeight;
        }

        //Log.i("resetHeaderHeight", "--resetHeaderHeight-->" + currHeight + "," + xHeaderHeight);
        xScroller.startScroll(0, currHeight, 0, finalHeight - currHeight, SCROLL_DURATION);
        invalidate();
        //setAdapter(this.getAdapter());
        setSelection(0);
    }

    private void updateFooterHeight(float delta) {
        xFooter.setVisibleHeight(xFooter.getVisibleHeight() + (int) delta);
    }

    private void resetFooterHeight() {
        int currHeight = xFooter.getVisibleHeight();
        if (currHeight == 0) return;

        if (isLoading) {

        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        this.xAdapter = adapter;
    }

    @Override
    public void computeScroll() {
        if (xScroller.computeScrollOffset()) {
            // there is only header, no need if statement
            xHeader.setVisibleHeight(xScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastPosY = ev.getRawY();
                //Log.i("ACTION_DOWN", "--ACTION_DOWN-->" + lastPosY);
                break;

            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - lastPosY;
                lastPosY = ev.getRawY();

                // when getVisibleHeight()>0, deltaY can <0 or >0, otherwise deltaY must >0
                if (getFirstVisiblePosition() == FIRST_VISIBLE_POSITION && (xHeader.getVisibleHeight() > 0 || deltaY >
                        0)) {
                    updateHeaderHeight(deltaY / OFFSET_RATIO);
                }
                //Log.i("ACTION_DOWN", "----ACTION_MOVE-->" + lastPosY + "," + deltaY);
                break;

            default:
                if (getFirstVisiblePosition() == FIRST_VISIBLE_POSITION) {
                    if (xEnableRefresh && xHeader.getVisibleHeight() > xHeaderHeight) {
                        isRefreshing = true;
                        xHeader.setState(HeaderView.STATE_REFRESHING);
                        refresh();
                    }
                    resetHeaderHeight();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void refresh() {
        if (null != xRefreshListener) {
            xRefreshListener.onRefresh();
        }
    }

    public void stopRefresh() {
        if (isRefreshing) {
            isRefreshing = false;
            smoothScrollToPosition(0);
            resetHeaderHeight();
        }
    }

    public void setUpdateTime() {
        xHeader.setUpdateTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.FRANCE).format(new Date());
    }

    public void loadMore() {
        if (null != xLoadMoreListener) {
            xLoadMoreListener.onLoadMore();
        }
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
