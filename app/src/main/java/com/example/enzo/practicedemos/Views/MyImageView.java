package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/2.
 */
public class MyImageView extends ImageView
{
    private int left, top, right, bottom;

    public MyImageView(Context context)
    {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setFrameOffset(int l, int t, int r, int b)
    {
        this.left = l;
        this.top = t;
        this.right = r;
        this.bottom = b;
        invalidate();
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b)
    {
        return super.setFrame(l - left, t - top, r - right, b - bottom);
    }
}
