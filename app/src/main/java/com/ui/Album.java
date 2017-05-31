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
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


/**
 * 查看相册界面（包括个人和他人）
 * Created by Administrator on 2016/11/28.
 */

public class Album extends Base_activity {
    private RecyclerView albumRv;
    private Button button;
    protected static Uri tempUri;
    private ArrayList<PhotoInfo> selectedImgs;
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback=new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            selectedImgs=new ArrayList<>();
            Intent intent=new Intent(Album.this,AddPhoto.class);
            selectedImgs.addAll(resultList);
            intent.putExtra("selectImgs",selectedImgs);
            startActivity(intent);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }
    };
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
                FunctionConfig config = new FunctionConfig.Builder()
                        .setMutiSelectMaxSize(9)
                        .setSelected(selectedImgs)
                        .setEnablePreview(true)
                        .setEnableCamera(true)
                        .build();
                GalleryFinal.openGalleryMuti(1001, config, mOnHanlderResultCallback);
            }
        });
    }


}
