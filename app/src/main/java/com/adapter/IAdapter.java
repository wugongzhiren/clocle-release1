package com.adapter;

/**
 * Created by Administrator on 2016/12/1.
 */

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public interface IAdapter {
    int getCount();

    View getView(View convertView, ViewGroup parent, int position);
}