package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.SpringBackScrollView;

public class HeaderFooterActivity extends AppCompatActivity
{

    private LinearLayout header, header_cover;
    private SpringBackScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(getText());

        header_cover = (LinearLayout) findViewById(R.id.header_cover);
        header = (LinearLayout) findViewById(R.id.header);

        TextView title = (TextView) header_cover.findViewById(R.id.title);
        title.setText("Header Cover");

        mScrollView = (SpringBackScrollView) findViewById(R.id.scrollView);
        mScrollView.setSmoothScrollingEnabled(true);
        mScrollView.setView(header_cover);
//        mScrollView.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                if (event.getAction() == MotionEvent.ACTION_MOVE)
//                {
//
//                    if (mScrollView.getScrollY() <= 0)
//                    {
////                        Log.i("TOP", "------->Reached top." + mScrollView.getScrollY());
//                        if (header_cover.getVisibility() != View.INVISIBLE)
//                            header_cover.setVisibility(View.INVISIBLE);
//                    } else
//                    {
////                        Log.i("TOP", "------->Did not reach top." + mScrollView.getScrollY());
//                        if (header_cover.getVisibility() != View.VISIBLE)
//                            header_cover.setVisibility(View.VISIBLE);
//                    }
//                }
//                return false;
//            }
//        });


    }

    private String getText()
    {
        String str = "";
        for (int i = 0; i < 1000; i++)
        {
            str += "TEXT ";
        }
        return str;
    }
}
