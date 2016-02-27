package com.example.enzo.practicedemos.Activities;

import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.example.enzo.practicedemos.R;

public class PropertyAnimationActivity extends AppCompatActivity {

    private Button btn_value_animator, btn_property_animator, btn_customized_animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        btn_value_animator = (Button) findViewById(R.id.btn_value_animator);
        btn_value_animator.setOnClickListener(new ValueAnimatorOnClickListener());
        Log.i("Width", "---->" + btn_value_animator.getLayoutParams().width);

        btn_property_animator = (Button) findViewById(R.id.btn_property_animator);
        btn_property_animator.setOnClickListener(new PropertyAnimatorOnClickListener());

        btn_customized_animator = (Button) findViewById(R.id.btn_customized_animator);
        btn_customized_animator.setOnClickListener(new CustomizedAnimatorOnClickListener());

    }

    private class ValueAnimatorOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            btn_value_animator.setEnabled(false);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                private IntEvaluator evaluator = new IntEvaluator();
                private AccelerateInterpolator interpolator = new AccelerateInterpolator();

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (Integer) animation.getAnimatedValue();
                    float fraction = currentValue / 100f;
                    float fraction_trans = interpolator.getInterpolation(fraction);
                    btn_value_animator.getLayoutParams().width = evaluator.evaluate
                            (fraction_trans, 300, 800);
                    btn_value_animator.requestLayout();
                }
            });
            valueAnimator.setDuration(2000).start();
            btn_value_animator.setEnabled(true);
        }
    }

    private class PropertyAnimatorOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            btn_property_animator.setEnabled(false);
            ViewWrapper wrapper = new ViewWrapper(btn_property_animator);
            int init_width = wrapper.getWidth();
            Log.i("Width", "----->" + init_width);
            ObjectAnimator animator1 = ObjectAnimator.ofInt(wrapper, "width", init_width, 500);
            animator1.setDuration(2000);
            ObjectAnimator animator2 = ObjectAnimator.ofInt(wrapper, "width", 500, init_width);
            animator2.setDuration(2000);
            AnimatorSet set = new AnimatorSet();
            set.play(animator1).before(animator2);
            set.start();
            btn_property_animator.setEnabled(true);
        }
    }

    private class CustomizedAnimatorOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(android.view.View v) {

        }
    }


    private class ViewWrapper {
        private View target;

        public ViewWrapper(View target) {
            this.target = target;
        }

        public int getWidth() {
            return target.getLayoutParams().width;
        }

        public void setWidth(int width) {
            target.getLayoutParams().width = width;

            /*
             * When a view is added, there are some methods will be invoke in its lifecycle:
             * -- parent calls addView() --
             * onAttachedToWindow()
             * measure()
             * onMeasure()
             * layout()
             * onLayout()
             * dispatchDraw()
             * draw()
             * onDraw()
             * { invalidate() or requestLayout() }
             *
             * The difference between these two methods is invalidate() recalls dispatchDraw()
             * and requestLayout() restarts from measure()
             *
             */
            target.requestLayout();
        }
    }

}
