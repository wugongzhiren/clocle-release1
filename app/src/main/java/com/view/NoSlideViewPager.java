package com.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置Viewpage是否可以滑动
 * Created by Hodo on 2017/12/7.
 */

public class NoSlideViewPager extends ViewPager{
    private boolean isCanSlide=true;//初始化能滑动
    public NoSlideViewPager(Context context) {
        this(context,null);
    }

    public NoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否可以滑动
     * @param isCanSlide
     */
    public void setCanSlide(boolean isCanSlide){
        this.isCanSlide=isCanSlide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanSlide)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanSlide)
            return super.onTouchEvent(ev);
        else
            return false;
    }
}

