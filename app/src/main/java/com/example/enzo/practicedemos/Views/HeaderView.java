package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enzo.practicedemos.R;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class HeaderView extends LinearLayout {
    public final static int STATE_STAND_BY = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    public final static int ROTATE_ANIM_DURATION = 180;

    private int resId = R.layout.header_view; // default ressource id to inflate.
    private LinearLayout xLayoutContainer;
    private ImageView xImgArrow;
    private ImageView xImgRefreshIcon;
    //private ProgressBar xProgressBar;
    private TextView xTextViewInfo, xTextViewDate;

    private RotateAnimation xAnimUptoDown, xAnimDownToUp, xAnimRotate;

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
        xLayoutContainer = (LinearLayout) LayoutInflater.from(context).inflate(resId, null);
        addView(xLayoutContainer, layout_param);
        setState(STATE_STAND_BY);

        xImgRefreshIcon = (ImageView) findViewById(R.id.footer_view_img_refresh_icon);
        xImgArrow = (ImageView) findViewById(R.id.header_view_img_arrow);
        xTextViewInfo = (TextView) findViewById(R.id.header_view_txtvw_info);
        xTextViewDate = (TextView) findViewById(R.id.header_view_txtvw_date);
        //xProgressBar = (ProgressBar) findViewById(R.id.header_view_progressbar);
        xImgRefreshIcon = (ImageView) findViewById(R.id.header_view_img_refresh_icon);

        xAnimDownToUp = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        xAnimDownToUp.setDuration(ROTATE_ANIM_DURATION);
        xAnimDownToUp.setFillAfter(true);
        xAnimUptoDown = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        xAnimUptoDown.setDuration(ROTATE_ANIM_DURATION);
        xAnimUptoDown.setFillAfter(true);

        xAnimRotate = new RotateAnimation(0.0f, -360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.57f);
        xAnimRotate.setDuration(700);
        xAnimRotate.setRepeatCount(-1);
        xAnimRotate.setInterpolator(new LinearInterpolator());
        xAnimRotate.setFillAfter(true);

    }

    public void setState(int state) {
        if (state == currState) return;

        if (state == STATE_REFRESHING) {
            xImgArrow.setVisibility(INVISIBLE);
            xImgArrow.clearAnimation();
            xImgRefreshIcon.setVisibility(VISIBLE);
        } else {
            xImgArrow.setVisibility(VISIBLE);
            xImgRefreshIcon.setVisibility(INVISIBLE);
            xImgRefreshIcon.clearAnimation();
        }

        switch (state) {
            case STATE_STAND_BY:
                if (currState == STATE_READY) {
                    xImgArrow.clearAnimation();
                    xImgArrow.startAnimation(xAnimUptoDown);
                }
                xTextViewInfo.setText(R.string.header_view_hint_standby);
                break;

            case STATE_READY:
                //xImgArrow.setImageResource(R.drawable.arrow_up);
                if (currState == STATE_STAND_BY) {
                    xImgArrow.clearAnimation();
                    xImgArrow.startAnimation(xAnimDownToUp);
                }
                xTextViewInfo.setText(R.string.header_view_hint_ready);
                break;

            case STATE_REFRESHING:
                xImgRefreshIcon.startAnimation(xAnimRotate);
                xTextViewInfo.setText(R.string.header_view_hint_refreshing);
                break;

            default:
                // Error Exception
        }

        currState = state;
    }

    public void setUpdateTime(String str_date) {
        xTextViewDate.setText(str_date);
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) xLayoutContainer.getLayoutParams();
        layoutParams.height = height;
        xLayoutContainer.setLayoutParams(layoutParams);
    }

    public int getVisibleHeight() {
        return xLayoutContainer.getHeight();
    }

    public void clearAnimation(){
        xImgRefreshIcon.clearAnimation();
    }

}
