package com.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.Base_activity;
import com.adapter.Picked_photo_adapter;
import com.adapter.Selected_Photo_Adapter;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.Publish;
import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import tool.ShowToast;

/**
 * Created by Administrator on 2016/12/11.
 */

public class AddPhoto extends Base_activity  {
    private  RecyclerView mrecycleView;
    private List<PhotoInfo> selectImg;
    private String[] uploadurl;
    private Selected_Photo_Adapter picked_photo_adapter;
    private ArrayList<String> url=new ArrayList<>();//要上传照片的URL
    private Button button;
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback=new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }
    };
    private static final String addphotourl = "res://com.clocle.huxiang.clocle/" + Uri.parse(R.mipmap.addphoto + "");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectImg= (List<PhotoInfo>) getIntent().getSerializableExtra("selectImgs");
        setContentView(R.layout.album_layout);
        uploadurl=new String[selectImg.size()];
        for(int i=0;i<selectImg.size();i++){
            url.add(selectImg.get(i).getPhotoPath());
            uploadurl[i]=selectImg.get(i).getPhotoPath();
        }
        url.add(addphotourl);
        mrecycleView= (RecyclerView) findViewById(R.id.album_rv);
        button= (Button) findViewById(R.id.dynamic_publish);
        button.setOnClickListener(this);
        mrecycleView.setLayoutManager(new GridLayoutManager(this, 3));

        picked_photo_adapter = new Selected_Photo_Adapter(this, url,mOnHanlderResultCallback);
        mrecycleView.setAdapter(picked_photo_adapter);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.dynamic_publish:
        BmobFile.uploadBatch(uploadurl, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> list, List<String> list1) {
                if(list1.size()==uploadurl.length){
                    final Bmob_UserBean newuser = new Bmob_UserBean();
                    newuser.setAlbumUrl((ArrayList<String>) list1);
                    Bmob_UserBean userBean = BmobUser.getCurrentUser(Bmob_UserBean.class);
                    newuser.update(userBean.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                ShowToast.showToast(AddPhoto.this,"成功");
                            }
                        }
                    });
                }
            }

            @Override
            public void onProgress(int i, int i1, int i2, int i3) {

            }

            @Override
            public void onError(int i, String s) {

            }
        });
        break;
    default:
        break;
}
    }
    private  void UploadFile(){

    }
}
