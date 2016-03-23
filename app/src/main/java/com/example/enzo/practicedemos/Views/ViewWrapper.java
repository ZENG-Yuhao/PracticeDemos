package com.example.enzo.practicedemos.Views;

import android.view.ViewGroup;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/2.
 */
public class ViewWrapper {
    private ViewGroup target;

    public ViewWrapper(ViewGroup target) {
        this.target = target;
    }

    public int getWidth() {
        return target.getLayoutParams().width;
    }

    public void setWidth(int width) {
        target.getLayoutParams().width = width;

            /*
             * When a view is added, there are some methods will be invoke in its lifecycle:
             * -- parent calls addView() --
             * onAttachedToWindow()
             * measure()
             * onMeasure()
             * layout()
             * onLayout()
             * dispatchDraw()
             * draw()
             * onDraw()
             * { invalidate() or requestLayout() }
             *
             * The difference between these two methods is invalidate() recalls dispatchDraw()
             * and requestLayout() restarts from measure()
             *
             */
        target.requestLayout();
    }

    public void setMargin(int l, int t, int r, int b) {
        ViewGroup.MarginLayoutParams source = new ViewGroup.MarginLayoutParams(target.getLayoutParams());
        source.setMargins(l, t, r, b);
    }
}
