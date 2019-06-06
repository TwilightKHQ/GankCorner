package com.gankcorner.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {

    private boolean CanScroll = true;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置ViewPager能否滑动换页
     *
     * @param CanScroll false 不能滑动， true 可以滑动
     */
    public void setCanScroll(boolean CanScroll) {
        this.CanScroll = CanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return CanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return CanScroll && super.onTouchEvent(ev);
    }

}
