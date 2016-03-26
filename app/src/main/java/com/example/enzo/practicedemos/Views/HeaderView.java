package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.enzo.practicedemos.R;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class HeaderView extends LinearLayout {
    public final static int STATE_STAND_BY = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    private int resId = R.layout.header_view; // default ressource id to inflate.
    private LinearLayout layoutContainer;
    private ImageView img_arrow;
    private ProgressBar progressBar;
    private TextView txtvw_info, txtvw_date;

    private int currState = STATE_STAND_BY;

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

    //    public HeaderView(Context context, int resId) {
    //        super(context);
    //        this.resId = resId;
    //        init(context);
    //    }

    public void init(Context context) {
        LinearLayout.LayoutParams layout_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutContainer = (LinearLayout) LayoutInflater.from(context).inflate(resId, null);
        addView(layoutContainer, layout_param);
        setGravity(Gravity.BOTTOM);

        img_arrow = (ImageView) findViewById(R.id.header_view_img_arrow);
        txtvw_info = (TextView) findViewById(R.id.header_view_txtvw_info);
        txtvw_date = (TextView) findViewById(R.id.header_view_txtvw_date);
        progressBar = (ProgressBar) findViewById(R.id.header_view_progressbar);
    }

    public void setState(int state) {
        if (state == currState) return;

        switch (state) {
            case STATE_STAND_BY:
                img_arrow.setVisibility(VISIBLE);
                progressBar.setVisibility(INVISIBLE);
                txtvw_info.setText(R.string.header_view_hint_standby);
                break;

            case STATE_READY:
                img_arrow.setVisibility(INVISIBLE);
                progressBar.setVisibility(INVISIBLE);
                txtvw_info.setText(R.string.header_view_hint_ready);
                break;

            case STATE_REFRESHING:
                img_arrow.setVisibility(INVISIBLE);
                progressBar.setVisibility(VISIBLE);
                txtvw_info.setText(R.string.header_view_hint_refreshing);
                break;

            default:
                // Error Exception
        }

        currState = state;
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
