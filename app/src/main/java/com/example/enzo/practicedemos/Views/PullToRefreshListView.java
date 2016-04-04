package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
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
import java.util.TreeMap;

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

    private boolean isTouching = false;
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    private int mMaxYOverscrollDistance;

    private OverScroller xScroller;
    private int xScrollTarget;
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
    private RelativeLayout xFooterContent;
    private int xTotalItems;
    private int xFooterHeight;
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

        //initialize footer;
        xFooter = new FooterView(context);
        addFooterView(xFooter);
        xFooterContent = (RelativeLayout) xFooter.findViewById(R.id.footer_view_content);

        // init header height, this method used to avoid getting 0 values of view's width and height when onCreate()
        // in OnCreate(), height and width have not been valued yet.
        ViewTreeObserver observer_header = xHeader.getViewTreeObserver();
        if (null != observer_header) {
            observer_header.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
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

        ViewTreeObserver observer_footer = xFooter.getViewTreeObserver();
        if (null != observer_footer) {
            observer_footer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    xFooterHeight = xFooterContent.getHeight();
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
        // for ios like overscroll
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (!isTouching && !isLoading && xEnableLoad && getLastVisiblePosition() == xTotalItems + 1 && xFooter
                .getVisibleHeight() >= xFooterHeight) {
            loadMore();
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (isTouching)
            return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                    maxOverScrollY, isTouchEvent);
        else
            return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                    mMaxYOverscrollDistance, isTouchEvent);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        xRefreshListener = listener;
    }

    public void setOnLoadMoreListner(OnLoadMoreListener listener) {
        xLoadMoreListener = listener;
    }


    public boolean setRefreshing() {
        if (!isLoading) {
            isRefreshing = true;
            xEnableLoad = false;
            return true;
        } else return false;
    }

    public boolean setLoading() {
        if (!isRefreshing) {
            isLoading = true;
            xEnableRefresh = false;
            return true;
        } else return false;
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

        // select the header, otherwise when we scroll to top, part of the header will be hidden by scrolling.
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
        xScrollTarget = SCROLL_TARGET_HEADER;
        xScroller.startScroll(0, currHeight, 0, finalHeight - currHeight, SCROLL_DURATION);
        invalidate();
        //setAdapter(this.getAdapter());
        //setSelection(0);
    }

    private void updateFooterHeight(float delta) {
        xFooter.setVisibleHeight(xFooter.getVisibleHeight() + (int) delta);
        //setSelection(xTotalItems + 1);
    }

    private void resetFooterHeight() {
        int currHeight = xFooter.getVisibleHeight();
        if (currHeight == 0) return;

        if (isLoading && currHeight <= xFooterHeight) return;

        //int finalHeight = 0;
        int finalHeight = xFooterHeight;
        if (isLoading && currHeight > xFooterHeight) {
            finalHeight = xFooterHeight;
        }

        xScrollTarget = SCROLL_TARGET_FOOTER;
        xScroller.startScroll(0, currHeight, 0, finalHeight - currHeight, SCROLL_DURATION);
        invalidate();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        this.xAdapter = adapter;
        xTotalItems = xAdapter.getCount();
    }

    @Override
    public void computeScroll() {
        if (xScroller.computeScrollOffset()) {
            // there is only header, no need if statement
            if (xScrollTarget == SCROLL_TARGET_HEADER)
                xHeader.setVisibleHeight(xScroller.getCurrY());
            else
                xFooter.setVisibleHeight(xScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouching = true;
                lastPosY = ev.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - lastPosY;
                lastPosY = ev.getRawY();

                // when getVisibleHeight()>0, deltaY can <0 or >0, otherwise deltaY must >0
                if (!isLoading && getFirstVisiblePosition() == FIRST_VISIBLE_POSITION && (xHeader.getVisibleHeight() > 0
                        || deltaY > 0)) {
                    updateHeaderHeight(deltaY / OFFSET_RATIO);
                }

                Log.i("onTouchEvent", "--onTouchEvent-->" + getLastVisiblePosition() + "," + xTotalItems + "," +
                        deltaY + "m" + xFooter.getVisibleHeight() + "," + xFooterHeight);
                if (!isRefreshing && getLastVisiblePosition() == xTotalItems + 1 && (xFooter.getVisibleHeight() >=
                        xFooterHeight || deltaY < 0)) {
                    updateFooterHeight(-deltaY / OFFSET_RATIO);
                }
                break;


            default:
                // action up
                isTouching = false;
                if (getFirstVisiblePosition() == FIRST_VISIBLE_POSITION) {
                    if (xEnableRefresh && xHeader.getVisibleHeight() > xHeaderHeight) {
                        xHeader.setState(HeaderView.STATE_REFRESHING);
                        refresh();
                    }
                    resetHeaderHeight();
                } else if (getLastVisiblePosition() == xTotalItems + 1) {
                    if (xEnableLoad && xFooter.getVisibleHeight() >= xFooterHeight) {
                        loadMore();
                    }
                    resetFooterHeight();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void refresh() {
        if (null != xRefreshListener) {
            isRefreshing = true;
            xRefreshListener.onRefresh();
        }
    }

    public void stopRefreshing() {
        if (isRefreshing) {
            smoothScrollToPosition(0);
            isRefreshing = false;
            resetHeaderHeight();
        }
    }

    public void loadMore() {
        if (null != xLoadMoreListener) {
            isLoading = true;
            xLoadMoreListener.onLoadMore();
        }
    }

    public void stopLoading() {
        if (isLoading) {
            smoothScrollToPosition(xTotalItems - 20);
            xTotalItems = xAdapter.getCount();
            isLoading = false;
            resetFooterHeight();

        }
    }

    public void setUpdateTime() {
        xHeader.setUpdateTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.FRANCE).format(new Date());
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    public void onFinished() {
        xHeader.clearAnimation();
        xFooter.clearAnimation();
    }
}
