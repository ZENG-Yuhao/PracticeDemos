package com.example.enzo.practicedemos.Views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/5.
 */
public class SpringBackLayout extends RelativeLayout
{
    private LinearLayout header, cover_header, footer;
    private SpringBackScrollView mScrollView;
    private RelativeLayout topDrawer;

    public SpringBackLayout(Context context)
    {
        super(context);
    }

}
