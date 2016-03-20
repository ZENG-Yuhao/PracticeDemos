package com.example.enzo.practicedemos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.enzo.practicedemos.R;

public class TransitionActivity2 extends AppCompatActivity
{
    private Button btn_page_jumping;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        overridePendingTransition(R.anim.activity_translate_in_r2l, R.anim.activity_translate_out_r2l);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);

        btn_page_jumping = (Button) findViewById(R.id.btn_page_jumping);
        btn_page_jumping.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TransitionActivity2.this, TransitionActivity1.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.activity_translate_in_l2r, R.anim.activity_translate_out_l2r);
    }
}
