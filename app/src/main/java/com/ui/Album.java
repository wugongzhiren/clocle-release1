package com.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.Base_activity;
import com.adapter.Album_Rv_ItemAdapter;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;



/**
 * 查看相册界面（包括个人和他人）
 * Created by Administrator on 2016/11/28.
 */

public class Album extends Base_activity {
    private RecyclerView albumRv;
    private Button button;
    protected static Uri tempUri;
   // private ArrayList<PhotoInfo> selectedImgs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatus();
        setContentView(R.layout.album_layout);
        Bmob_UserBean bean= BmobUser.getCurrentUser(Bmob_UserBean.class);
        button= (Button) findViewById(R.id.dynamic_publish);

        albumRv= (RecyclerView) findViewById(R.id.album_rv);
        albumRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       // albumRv.setAdapter(new Album_Rv_ItemAdapter(null ,this));
        albumRv.setAdapter(new Album_Rv_ItemAdapter(bean.getAlbumUrl(),this));
        //打开图片选择器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
