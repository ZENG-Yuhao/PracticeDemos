package com.example.enzo.practicedemos.Activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.DigitalIndicatorLayout;

public class DigitalIndicatorActivity extends AppCompatActivity {
    private ImageView img;
    private AnimatorSet in, out;
    private AnimatorSet set;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_indicator);

    }
}
