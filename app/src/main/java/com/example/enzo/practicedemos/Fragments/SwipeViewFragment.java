package com.example.enzo.practicedemos.Fragments;

import android.os.Bundle;
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
    SpringBackScrollView mScrollView;

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

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);

        mScrollView = (SpringBackScrollView) view.findViewById(R.id
                .scrollView);
        mScrollView.setSmoothScrollingEnabled(true);
        mScrollView.setTopView(header_cover);
        mScrollView.setOutsideTopView(imageView);
        mScrollView.scrollTo(0, 200);
        //mScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        Button btn = (Button) view.findViewById(R.id.btn_profile);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mScrollView.setButtonClicked(true);
                Toast.makeText(getActivity(), "setButtonClicked - true", Toast.LENGTH_LONG).show();
            }
        });
        return view;
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
