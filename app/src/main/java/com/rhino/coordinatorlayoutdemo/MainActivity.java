package com.rhino.coordinatorlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.rhino.coordinatorlayoutdemo.view.SpringScrollView;


public class MainActivity extends AppCompatActivity {

    private SpringScrollView mSpringScrollView;
    private LinearLayout mLinearLayout;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        shake = AnimationUtils.loadAnimation(this, R.anim.anim_top_bottom_shake);

        mSpringScrollView = findViewById(R.id.SpringScrollView);
        mLinearLayout = findViewById(R.id.LinearLayout);

        findViewById(R.id.TextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayout.startAnimation(shake);
            }
        });


        mSpringScrollView.startAnim1();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSpringScrollView.startAnim();
            }
        }, 800);
        mSpringScrollView.setScrollViewListener(new SpringScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(SpringScrollView scrollView, int x, int y, int oldx, int oldy) {
                Log.d("RHINO", "x = " + x + ", y = " + y + ", oldx = " + oldx + ", oldy = " + oldy);

            }
        });

        mSpringScrollView.setOnAnimationEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
//                mSpringScrollView.startAnimation(shake);
            }
        });

    }
}
