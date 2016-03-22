package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.enzo.practicedemos.Core.Toolkit.TextInflator;
import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.SlowScrollView;

public class OverScrollerActivity extends AppCompatActivity
{
    private SlowScrollView scrollView;
    private Button btn_up, btn_down;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_scroller);

        txtview = (TextView) findViewById(R.id.txtview);
        txtview.setText(TextInflator.inflate(800));

        scrollView = (SlowScrollView) findViewById(R.id.scrollview);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                scrollView.slowScrollTo(0, scrollView.getScrollY() - 700, 500);
            }
        });

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_down.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                scrollView.slowScrollTo(0, scrollView.getScrollY() + 700, 500);
            }
        });
    }
}
