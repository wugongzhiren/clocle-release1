package com.clocle.huxiang.clocle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.Base_activity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ui.UpdateSelfInfo;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import okhttp3.Call;
import tool.Utils;

/**
 * 个人中心界面
 * Created by Administrator on 2016/7/16.
 */
public class Self_manager extends Base_activity {
    private ImageView change_photo;
    protected static Uri tempUri;
    private String userPhotoUrl;
    private Intent index_fg_intent;
    private android.support.v7.widget.Toolbar mtoolbar;
    private TextView toolBarTitle;
    private SimpleDraweeView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideStatus();
        setContentView(R.layout.self_manager);
        initViews();
        photo.setImageURI(Bmob_UserBean.getCurrentUser(Bmob_UserBean.class).getphotoUrl());
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

Intent intent =new Intent(Self_manager.this, UpdateSelfInfo.class);
                //intent.putExtra("user",BmobUser.getCurrentUser(Bmob_UserBean.class));
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        //头像
        photo= (SimpleDraweeView) findViewById(R.id.self_manage_photo);
    }
}



