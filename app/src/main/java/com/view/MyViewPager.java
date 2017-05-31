package com.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/10/29.
 */
public class MyViewPager extends ViewPager {
    // 滑动距离及坐标 归还父控件焦点

    private boolean isInterruptFlag = false;
    private float xDistance, yDistance, xLast, yLast,mLeft;
    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //当为true时，则阻止父view不拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public int getCurrentItem() {

        return super.getCurrentItem();
    }
}
