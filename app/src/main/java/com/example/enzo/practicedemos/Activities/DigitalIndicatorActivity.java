package com.example.enzo.practicedemos.Activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
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
        Animator alphaAnim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
        alphaAnim.setDuration(0);

        Animator rotationXAnim = ObjectAnimator.ofFloat(img, "rotationX", -90f, 0f);
        rotationXAnim.setDuration(250);
        rotationXAnim.setInterpolator(new DecelerateInterpolator());

        Animator rotationXAnim2 = ObjectAnimator.ofFloat(img, "rotationX", 0f, 90f);
        rotationXAnim2.setDuration(250);
        rotationXAnim2.setInterpolator(new DecelerateInterpolator());

        Animator alphaAnim2 = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        alphaAnim2.setDuration(125);


        Animator alphaAnim3 = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
        alphaAnim2.setDuration(125);

        in = new AnimatorSet();
        in.play(rotationXAnim2).with(alphaAnim3);
        out = new AnimatorSet();
        out.play(alphaAnim).with(rotationXAnim).with(alphaAnim2);


//        in = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out);
//        out = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in);
//
//        in.setTarget(img);
        in.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                img.setImageResource(R.drawable.ic_number_green_5);
                out.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        set = new AnimatorSet();
//        set.play(in).before(out);
//        set.setTarget(img);



        Button btn = (Button) findViewById(R.id.btn_digital_indicator_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.ic_number_green_1);
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
