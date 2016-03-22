package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.enzo.practicedemos.Core.Toolkit.TextInflator;
import com.example.enzo.practicedemos.R;

public class OverScrollerActivity extends AppCompatActivity
{
    private ScrollView scrollView;
    private Button btn_up, btn_down;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_scroller);

        txtview = (TextView) findViewById(R.id.txtview);
        txtview.setText(TextInflator.inflate());

        scrollView = (ScrollView) findViewById(R.id.scrollview);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_down.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
