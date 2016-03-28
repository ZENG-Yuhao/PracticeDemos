package com.example.enzo.practicedemos.Views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.enzo.practicedemos.R;

/**
 * Created by enzoz on 2016/3/28.
 */
public class FooterView extends LinearLayout {

    private LinearLayout xLayoutContainer;
    private ImageView xImgRefreshIcon;
    private RotateAnimation xRotateAnim;

    public FooterView(Context context) {
        super(context);
        init(context);
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        xLayoutContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.footer_view, null);
        addView(xLayoutContainer, layout_params);

        xImgRefreshIcon = (ImageView) findViewById(R.id.footer_view_img_refresh_icon);

    }

    public int getMarginBottom() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) xLayoutContainer.getLayoutParams();
        return lp.bottomMargin;
    }

    public void setMarginBottom(int marginBottom) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) xLayoutContainer.getLayoutParams();
        lp.bottomMargin = marginBottom;
        xLayoutContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        return xLayoutContainer.getHeight();
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) xLayoutContainer.getLayoutParams();
        layoutParams.height = height;
        xLayoutContainer.setLayoutParams(layoutParams);
    }

    public void show() {
        setVisibleHeight(xLayoutContainer.getHeight());
    }

    public void hide() {
        setVisibleHeight(0);
    }

    public void startLoading() {

    }

    public void stopLoading() {

    }
}
