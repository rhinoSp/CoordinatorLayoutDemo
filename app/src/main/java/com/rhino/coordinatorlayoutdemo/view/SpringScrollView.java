package com.rhino.coordinatorlayoutdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.rhino.coordinatorlayoutdemo.utils.ScreenUtils;


public class SpringScrollView extends NestedScrollView {

    private float startDragY;
    private SpringAnimation springAnim;
    private SpringAnimation springAnim1;
    private ScrollViewListener scrollViewListener = null;

    public SpringScrollView(Context context) {
        this(context, null);
    }

    public SpringScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDrawingCacheBackgroundColor(Color.TRANSPARENT);
        springAnim = new SpringAnimation(this, SpringAnimation.TRANSLATION_Y, 0);
        //刚度 默认1200 值越大回弹的速度越快
        springAnim.getSpring().setStiffness(1000.0f);
        //阻尼 默认0.5 值越小，回弹之后来回的次数越多
        springAnim.getSpring().setDampingRatio(0.35f);


        springAnim1 = new SpringAnimation(this, SpringAnimation.TRANSLATION_Y, ScreenUtils.dip2px(getContext(), 60));
        //刚度 默认1200 值越大回弹的速度越快
        springAnim1.getSpring().setStiffness(10000.0f);
        //阻尼 默认0.5 值越小，回弹之后来回的次数越多
        springAnim1.getSpring().setDampingRatio(1f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (getScrollY() <= 0) {
                    //顶部下拉
                    if (startDragY == 0) {
                        startDragY = e.getRawY();
                    }
                    if (e.getRawY() - startDragY >= 0) {
                        setTranslationY((e.getRawY() - startDragY) / 3);
                        return super.onTouchEvent(e);
                    } else {
                        startDragY = 0;
                        springAnim.cancel();
                        setTranslationY(0);
                    }
                }
                /*else if ((getScrollY() + getHeight()) >= getChildAt(0).getMeasuredHeight()) {
                    //底部上拉
                    if (startDragY == 0) {
                        startDragY = e.getRawY();
                    }
                    if (e.getRawY() - startDragY <= 0) {
                        setTranslationY((e.getRawY() - startDragY) / 3);
                        return true;
                    } else {
                        startDragY = 0;
                        springAnim.cancel();
                        setTranslationY(0);
                    }
                }*/
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (getTranslationY() != 0) {
                    springAnim.start();
                }
                startDragY = 0;
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener {
        void onScrollChanged(SpringScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public void setOnAnimationEndListener(DynamicAnimation.OnAnimationEndListener mOnAnimationEndListener) {
        springAnim.addEndListener(mOnAnimationEndListener);
    }

    public void startAnim() {
        springAnim.start();
    }


    public void startAnim1() {
        springAnim1.start();
    }


}