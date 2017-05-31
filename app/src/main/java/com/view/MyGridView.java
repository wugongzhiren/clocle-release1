package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决嵌套gridview时Item不显示的问题
 * Created by Administrator on 2016/12/11.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyGridView(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
