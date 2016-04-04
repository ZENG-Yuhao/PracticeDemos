package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.DigitalIndicatorLayout;

public class DigitalIndicatorActivity extends AppCompatActivity {

    private DigitalIndicatorLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_indicator);

        indicator = (DigitalIndicatorLayout) findViewById(R.id.indicator);

        Button btn12306 = (Button) findViewById(R.id.btn12306);
        btn12306.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.setNumber(12306);
            }
        });

        Button btn15308 = (Button) findViewById(R.id.btn15308);
        btn15308.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.setNumber(15308);
            }
        });


        Button btn_change = (Button) findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editor = (EditText) findViewById(R.id.editText);
                int number = Integer.valueOf(editor.getText().toString());
                indicator.setNumber(number);
            }
        });
    }

}
