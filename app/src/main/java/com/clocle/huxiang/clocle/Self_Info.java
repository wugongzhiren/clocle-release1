
package com.clocle.huxiang.clocle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import tool.Pop_activity;


/**
 * Created by Administrator on 2016/7/18.
 */

public class Self_Info extends AppCompatActivity {
    private List<com.bean.Self_Info> self_infos;
    public ListView selflistView;
    private Button saveinfo;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_info);




    }
    }




