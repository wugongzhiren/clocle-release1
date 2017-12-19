package com.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

import tool.DensityUtil;

/**
 * 根据屏幕动态宽高的imageview,针对一行三列的图片，扩展时，可以加个一行多少列的参数
 * Created by Administrator on 2016/11/13.
 */

public class DynamicHWimageview extends AppCompatImageView {
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
        Log.i("DynamicHWimageview", "屏幕宽度的像素: "+screenwidth);
        screenwidthDP = DensityUtil.px2dip(context,screenwidth);

    }

    public DynamicHWimageview(Context context) {
        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //26=padding+gridView间隔
        int width = (screenwidthDP-26)/3;
        Log.i(getClass().getName(), "设置宽度: "+width);
        setMeasuredDimension(DensityUtil.dip2px(mcontext,width),DensityUtil.dip2px(mcontext,width));
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
