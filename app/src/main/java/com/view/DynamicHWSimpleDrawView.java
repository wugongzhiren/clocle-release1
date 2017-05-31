package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;

import tool.DensityUtil;

/**
 * 动态计算simpleDrawview宽高的simpleDrawView
 * Created by Administrator on 2016/12/12.
 */

public class DynamicHWSimpleDrawView extends SimpleDraweeView {
    private int screenwidth;
    private int screenwidthDP;
    private Context mcontext;
    public DynamicHWSimpleDrawView(Context context, AttributeSet attrs) {
        //super(context);
        super(context, attrs);
        this.mcontext=context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth = outMetrics.widthPixels;//获取屏幕宽度的像素值
        screenwidthDP = DensityUtil.px2dip(context,screenwidth);
    }

    public DynamicHWSimpleDrawView(Context context) {

        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(screenwidthDP/3, widthMeasureSpec);
        int height = getMySize(screenwidthDP/3, heightMeasureSpec);
        setMeasuredDimension(DensityUtil.dip2px(mcontext,width), DensityUtil.dip2px(mcontext,height));
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {

                mySize = defaultSize;
                break;
            }
            //精确模式
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
        }
        return mySize;
    }
}
