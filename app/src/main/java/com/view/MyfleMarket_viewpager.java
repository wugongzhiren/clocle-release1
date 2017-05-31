package com.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MyfleMarket_viewpager extends ViewPager {
    private boolean isCanScroll = true;

    public MyfleMarket_viewpager(Context context) {
        this(context,null);
    }

    public MyfleMarket_viewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {

        this.isCanScroll = noScroll;

    }

    @Override
    public void scrollTo(int x, int y) {

        super.scrollTo(x, y);

    }

    @Override

    public boolean onTouchEvent(MotionEvent arg0) {

        if (isCanScroll) {

            return false;

        } else {

            return super.onTouchEvent(arg0);
        }

    }

    @Override

    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        if (isCanScroll) {

            return false;

        } else {

            return super.onInterceptTouchEvent(arg0);

        }

    }

    @Override
    public void setCurrentItem(int item) {

        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
