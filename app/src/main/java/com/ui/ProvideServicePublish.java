package com.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bean.Clocle_pro_service;
import com.clocle.huxiang.clocle.R;


/**
 * Created by Administrator on 2017/1/10.
 */

public class ProvideServicePublish extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化view
        setContentView( R.layout.provide_service_publish);
        Clocle_pro_service bean=new Clocle_pro_service();

    }

    @Override
    public void onClick(View v) {

    }
}
