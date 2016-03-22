package com.example.enzo.practicedemos.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.SpringBackScrollView;


public class SwipeViewFragment extends Fragment
{
    private Button btn;
    private SpringBackScrollView mScrollView;
    private MyHandler myHandler;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_swipe_view,
//                container, false);
//        return rootView;
        View view = inflater.inflate(R.layout.activity_header_footer, null);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(getText());

        LinearLayout header_cover = (LinearLayout) view.findViewById(R.id.header_cover);
        LinearLayout header = (LinearLayout) view.findViewById(R.id.header);

        TextView title = (TextView) header_cover.findViewById(R.id.title);
        title.setText("Header Cover");

        imageView = (ImageView) view.findViewById(R.id.image_view);

        mScrollView = (SpringBackScrollView) view.findViewById(R.id
                .scrollView);
        mScrollView.setSmoothScrollingEnabled(true);
        mScrollView.setTopView(header_cover);
        mScrollView.setOutsideTopView(imageView);
        //mScrollView.slowScrollTo(0, 30, 1000);
        //mScrollView.scrollTo(0, 200);
        //mScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        myHandler = new MyHandler();
        Button btn = (Button) view.findViewById(R.id.btn_profile);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "setButtonClicked - true", Toast.LENGTH_SHORT).show();
                MyThread myThread = new MyThread();
                myThread.start();
            }
        });
        return view;
    }


    private String getText()
    {
        String str = "";
        for (int i = 0; i < 100; i++)
        {
            str += "TEXT TEXT TEXT TEXT " + i + " \n";
        }
        return str;
    }

    private class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 1)
            {
                mScrollView.setButtonClicked(true);
                mScrollView.slowScrollTo(0, 0, 1000);
                //mScrollView.smoothScrollTo(0, 0);
                //mScrollView.invokeOverScrollBy();

//                ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//                {
//
//                    private IntEvaluator evaluator = new IntEvaluator();
//                    private AccelerateInterpolator interpolator = new AccelerateInterpolator();
//
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation)
//                    {
//                        int currentValue = (Integer) animation.getAnimatedValue();
//                        float fraction = currentValue / 100f;
//                        float fraction_trans = interpolator.getInterpolation(fraction);
//                        mScrollView.setScrollY(evaluator.evaluate(fraction_trans, -200, 0));
//                        mScrollView.invalidate();
//                    }
//                });
//                valueAnimator.setDuration(1000).start();
            }
        }
    }

    private class MyThread extends Thread
    {
        @Override
        public void run()
        {
            int duration = 15;
            for (int i = 1; i <= duration; i++)
            {
                synchronized (this)
                {
                    try
                    {
                        wait(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            myHandler.sendEmptyMessage(1);
        }
    }
}
