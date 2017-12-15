package com.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 1.加载时显示加载中；2.加载失败显示加载失败；3.没加载到数据显示什么也没有
 * Created by Hodo on 2017/12/12.
 */

public class EmptyLayout extends FrameLayout {
    /**
     * 无数据
     */
    public final static int TYPE_EMPTY = 1;
    /**
     * 加载中
     */
    public final static int TYPE_LOADING = 2;
    /**
     * 加载失败
     */
    public final static int TYPE_ERROR = 3;

    public static int CURRENT_TYPE=0;

    public EmptyLayout(Context context) {
        super(context);
    }

    /**
     * 设置当前需要显示的类型
     * @param type
     */
    public void setType(int type){
        setVisibility(VISIBLE);
        if(type==1) {
            CURRENT_TYPE = TYPE_EMPTY;
            requestLayout();
            return;
        }
        if(type==2){
            CURRENT_TYPE=TYPE_LOADING;
            requestLayout();
            return;
        }
        if(type==3){
            CURRENT_TYPE=TYPE_ERROR;
            requestLayout();
            return;
        }
        else{
            CURRENT_TYPE=0;
            return;
        }
    }


    public EmptyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 注意：EmptyLaout XML中定义的：index 0：loading ;index 1:empty;index 2:error
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count=getChildCount();

            if(CURRENT_TYPE==TYPE_EMPTY){
                getChildAt(1).setVisibility(VISIBLE);
                getChildAt(0).setVisibility(GONE);
                getChildAt(2).setVisibility(GONE);
                return;
            }
            if(CURRENT_TYPE==TYPE_LOADING){
                getChildAt(0).setVisibility(VISIBLE);
                getChildAt(1).setVisibility(GONE);
                getChildAt(2).setVisibility(GONE);
                return;
            }
            if(CURRENT_TYPE==TYPE_ERROR){
                getChildAt(2).setVisibility(VISIBLE);
                getChildAt(0).setVisibility(GONE);
                getChildAt(1).setVisibility(GONE);
                return;
            }

    }
}
