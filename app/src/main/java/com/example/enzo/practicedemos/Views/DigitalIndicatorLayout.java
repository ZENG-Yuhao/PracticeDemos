package com.example.enzo.practicedemos.Views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.enzo.practicedemos.R;

/**
 * Created by enzoz on 2016/3/28.
 */
public class DigitalIndicatorLayout extends LinearLayout {
    private final static String TAG = "DigitalIndicatorLayout";

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

    private int xRefreshMode = REFRESH_MODE_TOGETHER;
    private AnimatorSet xAnimIn, xAnimOut;

    private int xCurrNumber = 0;
    private int xNumDecimal = 6; // default total number of decimals
    private int[] xCodeDecimals = new int[xNumDecimal];
    private boolean[] hasChanged = new boolean[xNumDecimal];
    private ImageView[] xDigitalImgs;

    private int xLayoutHeight;

    // init default animations
    {
        // flip_in
        Animator alphaAnim = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        alphaAnim.setDuration(0);

        Animator rotationXAnim = ObjectAnimator.ofFloat(null, "rotationX", -90f, 0f);
        rotationXAnim.setDuration(250);
        rotationXAnim.setInterpolator(new DecelerateInterpolator());


        Animator alphaAnim2 = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f);
        alphaAnim2.setDuration(175);

        xAnimIn = new AnimatorSet();
        xAnimIn.play(alphaAnim).with(rotationXAnim).with(alphaAnim2);

        // flip out
        Animator rotationXAnim2 = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f);
        rotationXAnim2.setDuration(250);
        rotationXAnim2.setInterpolator(new DecelerateInterpolator());

        Animator alphaAnim3 = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        alphaAnim2.setDuration(175);

        xAnimOut = new AnimatorSet();
        xAnimOut.play(rotationXAnim2).with(alphaAnim3);
    }

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

    public void setNumber(int number) {
        //Log.i(TAG, "--setNumber--> " + number + ", " + xNumDecimal);
        if (number > RANGE[xNumDecimal]) return;

        if (number == xCurrNumber) return;

        int[] decimals = convertNumberToDecimals(number);

        compare(decimals, xCodeDecimals);

        // refresh decimal codes
        xCodeDecimals = decimals;
        displayAnimation();

    }

    private void init(Context context) {
        xCodeDecimals = new int[xNumDecimal];
        hasChanged = new boolean[xNumDecimal];
        xDigitalImgs = new ImageView[xNumDecimal];
        // configuration layout
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
//                .LayoutParams.WRAP_CONTENT);
//        setLayoutParams(lp);

        ViewTreeObserver observer = this.getViewTreeObserver();
        if (null != observer) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // refresh height
                    xLayoutHeight = getHeight();

                    // refresh images' layout
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(xLayoutHeight, xLayoutHeight);
                    for (int i = 0; i < xDigitalImgs.length; i++) xDigitalImgs[i].setLayoutParams(lp);
                }
            });
        }
        // initialize images
        initImgs(context);

    }

    private void initImgs(Context context) {
        // from high bit to low bit
        // result in layout: L (high bit --- low bit) R
        for (int i = xNumDecimal - 1; i >= 0; i--) {
            ImageView img = new ImageView(context);
            img.setId(i);
            img.setImageResource(IMG_SRC[0]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(xLayoutHeight, xLayoutHeight);
            img.setLayoutParams(lp);
            xDigitalImgs[i] = img;
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
            //Log.i(TAG, "--convertNumberToDecimals--> " + decimals[pos]);
            pos++;
        } while (rest != 0);
        return decimals;
    }

    private int compare(int[] decimals1, int[] decimals2) {
        int count = 0;
        for (int i = 0; i < xNumDecimal; i++) {
            if (decimals1[i] != decimals2[i]) {
                count++;
                hasChanged[i] = true;
            }
        }
        return count;
    }

    private void displayAnimation() {

        // low bit to high bit
        for (int i = 0; i < xNumDecimal; i++) {
            if (hasChanged[i]) {

                if (xRefreshMode == REFRESH_MODE_TOGETHER) {
                    Animator animChangeImg = new ObjectAnimator().ofInt(xDigitalImgs[i], "imageResource", IMG_SRC[0],
                            IMG_SRC[xCodeDecimals[i]]);
                    animChangeImg.setDuration(0);
                    AnimatorSet animIn, animOut;
                    animIn = xAnimIn.clone();
                    animOut = xAnimOut.clone();
                    animIn.setTarget(xDigitalImgs[i]);
                    animOut.setTarget(xDigitalImgs[i]);
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(animOut).before(animChangeImg).before(animIn);
                    animSet.start();
                } else if (xRefreshMode == REFRESH_MODE_NO_ANIMATION) {
                    //Log.i(TAG, "--displayAnimation--> " + i + "," + xCodeDecimals[i]);
                    xDigitalImgs[i].setImageResource(IMG_SRC[xCodeDecimals[i]]);
                }
                // reset state
                hasChanged[i] = false;
            }
        }
    }
}
