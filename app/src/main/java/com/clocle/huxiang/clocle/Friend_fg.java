package com.clocle.huxiang.clocle;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/7/7.
 */
public class Friend_fg extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<String> titles;//viewpager的标题
    private ArrayList<Fragment> fragments;//装载进viewpager的fragment
    private View view;
    private RecyclerView coversationlistRv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.friend_fg,container,false);

        return view;
    }


}
