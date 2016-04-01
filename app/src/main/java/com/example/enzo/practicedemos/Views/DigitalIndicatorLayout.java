package com.example.enzo.practicedemos.Views;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.enzo.practicedemos.R;

/**
 * Created by enzoz on 2016/3/28.
 */
public class DigitalIndicatorLayout extends LinearLayout {

    private final static int[] IMG_SRC = new int[]{
            R.drawable.ic_number_green_0, R.drawable.ic_number_green_1, R.drawable.ic_number_green_2, R.drawable
            .ic_number_green_3, R.drawable.ic_number_green_4, R.drawable.ic_number_green_5, R.drawable
            .ic_number_green_6, R.drawable.ic_number_green_7, R.drawable.ic_number_green_8, R.drawable
            .ic_number_green_9
    };

    private final static int[] RANGE = new int[]{0, 9, 99, 999, 9999, 99999, 999999};

    public final static int REFRESH_MODE_NO_ANIMATION = -1;
    public final static int REFRESH_MODE_SEQUENCE = 0;
    public final static int REFRESH_MODE_TOGETHER = 1;

    private final static int ANIM_DURATION = 200;
    private final static int INTERVAL_DURATION = 100;

    private int xRefreshMode = REFRESH_MODE_NO_ANIMATION;
    private AnimatorSet xRefreshAnimIn, xRefreshAnimOut;

    private int xNumDecimal = 3; // total number of decimals
    private int[] xCurrDecimals = new int[xNumDecimal];
    private boolean[] hasChanged = new boolean[xNumDecimal];
    private ImageView[] xImgDigitals;

    public DigitalIndicatorLayout(Context context) {
        super(context);
        init(context);
    }

    public DigitalIndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DigitalIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DigitalIndicatorLayout(Context context, int numDecimal, int mode) {
        super(context);
        configure(numDecimal, mode);
        init(context);
    }

    public void configure(int numDecimal, int mode) {
        this.xNumDecimal = numDecimal;
        xRefreshMode = mode;
        removeAllViews();
        init(getContext());
    }

    public void refreshDigitals(int number) {
        if (number > RANGE[xNumDecimal]) return;

        int[] decimals = convertNumberToDecimals(number);

        if (compare(xCurrDecimals, decimals) == 0) return;

        refresh();
    }

    private void init(Context context) {
        xCurrDecimals = new int[xNumDecimal];
        hasChanged = new boolean[xNumDecimal];
        xImgDigitals = new ImageView[xNumDecimal];
        // configuration layout
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
//                .LayoutParams.WRAP_CONTENT);
//        setLayoutParams(lp);

        // initialize images
        initImgs(context);

    }

    private void initImgs(Context context) {
        for (int i = xNumDecimal - 1; i >= 0; i--) {
            ImageView img = new ImageView(context);
            img.setId(i);
            img.setImageResource(IMG_SRC[0]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(60, 60);
            img.setLayoutParams(lp);
            xImgDigitals[i] = img;
            this.addView(img);
        }

        //requestLayout();
    }

    private int[] convertNumberToDecimals(int number) {
        int[] decimals = new int[xNumDecimal];
        int rest = number;
        int lastDecimal;
        int pos = 0; // current position
        do {
            lastDecimal = rest % 10;
            rest /= 10;
            decimals[pos] = lastDecimal;
            pos++;
        } while (rest == 0);
        return decimals;
    }

    private int compare(int[] currDecimals, int[] decimals) {
        int count = 0;
        for (int i = 0; i < xNumDecimal; i++) {
            if (currDecimals[i] != decimals[i]) {
                count++;
                hasChanged[i] = true;
            }
        }
        return count;
    }

    private void refresh() {

    }
}
