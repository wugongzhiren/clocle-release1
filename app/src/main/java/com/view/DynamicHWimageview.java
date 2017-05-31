package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

import tool.DensityUtil;

/**
 * 根据屏幕动态宽高的imageview,针对一行三列的图片，扩展时，可以加个一行多少列的参数
 * Created by Administrator on 2016/11/13.
 */

public class DynamicHWimageview extends ImageView {
    private int screenwidth;
    private int screenwidthDP;
    private Context mcontext;
    public DynamicHWimageview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext=context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth = outMetrics.widthPixels;//获取屏幕宽度的像素值
        screenwidthDP = DensityUtil.px2dip(context,screenwidth);

    }

    public DynamicHWimageview(Context context) {
        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(screenwidthDP/3-6, widthMeasureSpec);
        int height = getMySize(screenwidthDP/3-6, heightMeasureSpec);
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
            case MeasureSpec.EXACTLY: {
                mySize = defaultSize;
                break;
            }
        }
        return mySize;
    }
}
