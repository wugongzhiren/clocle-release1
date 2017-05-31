package com.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import tool.DensityUtil;

/**
 * 动态计算宽高的recycleview
 * Created by Administrator on 2016/12/25.
 */

public class DynamicHWRecycleview extends RecyclerView {

    public DynamicHWRecycleview(Context context) {
        this(context,null);
    }

    public DynamicHWRecycleview(Context context, AttributeSet attrs) {
        super(context, attrs);
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
