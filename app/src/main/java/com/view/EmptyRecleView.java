package com.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/6/27.
 */

public class EmptyRecleView extends RecyclerView {
    private EmptyLayout emptyView;
    private static final String TAG = "EmptyRecleView";

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.i(TAG, "onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public EmptyRecleView(Context context) {
        super(context);
    }

    public EmptyRecleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecleView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 是否需要显示emptyView,在这里只会出现一种情况，就是加载到空数据，其他的情况在外面处理
     */
    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            //设置为什么也没有视图
            emptyView.setType(1);
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }

    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        //checkIfEmpty();
    }

    public void setEmptyView(EmptyLayout emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

}
