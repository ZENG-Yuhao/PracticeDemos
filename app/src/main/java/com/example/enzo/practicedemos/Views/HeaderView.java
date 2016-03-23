package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.enzo.practicedemos.R;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class HeaderView extends LinearLayout {
    private int resId = R.layout.header; // default ressource id to inflate.
    private LinearLayout layoutContainer;

    public HeaderView(Context context) {
        super(context);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HeaderView(Context context, int resId) {
        super(context);
        this.resId = resId;
        init(context);
    }

    public void init(Context context) {
        LinearLayout.LayoutParams layout_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutContainer = (LinearLayout) LayoutInflater.from(context).inflate(resId, null);
        addView(layoutContainer, layout_param);
        setGravity(Gravity.BOTTOM);
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layoutContainer.getLayoutParams();
        layoutParams.height = height;
        layoutContainer.setLayoutParams(layoutParams);
    }

    public int getVisibleHeight() {
        return layoutContainer.getHeight();
    }
}
