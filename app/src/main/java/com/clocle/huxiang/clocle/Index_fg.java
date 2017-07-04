package com.clocle.huxiang.clocle;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.LazyFrament;
import com.adapter.Dynamic_Rv_Adapter;
import com.bean.Dynamic;
import com.bean.Message;
import com.bean.Messages;
import com.constant.Constant;
import com.function.Dynamic_Detail;
import com.function.Dynamic_publish;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.view.EmptyRecleView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import tool.Myadpter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by Administrator on 2016/7/6.
 */
public class Index_fg extends LazyFrament{
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    public   List<Dynamic> messages= new ArrayList<Dynamic>();;//动态的数据
    public  Myadpter myadapter;
    private EmptyRecleView recyclerView;
    private FloatingActionButton dynamic_action_bt;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fg, container, false);
        dynamic_action_bt= (FloatingActionButton) view.findViewById(R.id.dynamic_action_bt);
        dynamic_action_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dynamic_publish.class);
                startActivity(intent);
            }
        });
        /*linearLayout= (LinearLayout) view.findViewById(R.id.dynamic_content);
        linearLayout.setOnClickListener(this);*/
        recyclerView= (EmptyRecleView) view.findViewById(R.id.main_rv);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.VERTICAL ); //两列，纵向排列

        recyclerView.setLayoutManager(mLayoutManager) ;
        //XXX初始化view的各控件
        isPrepared = true;
        lazyLoad();



        return view;



    }

    private void initData() {
        BmobQuery<Dynamic> query=new BmobQuery<>();
        query.include("user");
        query.order("-createdAt").findObjects(new FindListener<Dynamic>() {
            @Override
            public void done(List<Dynamic> list, BmobException e) {
                if(e==null){
                    messages.addAll(list);
                    recyclerView.setAdapter(new Dynamic_Rv_Adapter(getActivity()));
                }
            }
        });
    }


    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        //填充各控件的数据
        initData();
    }
}









