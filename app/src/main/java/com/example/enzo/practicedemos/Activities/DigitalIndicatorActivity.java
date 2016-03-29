package com.example.enzo.practicedemos.Activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.enzo.practicedemos.R;

public class DigitalIndicatorActivity extends AppCompatActivity {
    private ImageView img;
    private AnimatorSet in, out;
    private AnimatorSet set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_indicator);

        img = (ImageView) findViewById(R.id.image_view);

        in = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out);
        out = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in);

        in.setTarget(img);
        in.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                img.setImageResource(R.drawable.ic_number_green_5);
                out.setTarget(img);
                out.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set = new AnimatorSet();
        set.play(in).before(out);
        set.setTarget(img);



        Button btn = (Button) findViewById(R.id.btn_digital_indicator_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //img.setImageResource(R.drawable.ic_number_green_1);
                in.start();
            }
        });

        Button btn_reset = (Button) findViewById(R.id.btn_digital_indicator_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.ic_number_green_1);
            }
        });
    }
}
