package com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */

public class MyfragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> title;
    private List<Fragment> views;

    public MyfragmentPagerAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
        super(fm);
        this.title = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
