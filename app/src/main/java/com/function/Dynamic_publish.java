package com.function;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.adapter.Picked_photo_adapter;
import com.bean.Dynamic;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 分享新鲜事的发表页面
 * Created by Administrator on 2016/11/12.
 */

public class Dynamic_publish extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private EditText dynamic_ed;
    private List<PhotoInfo> mPhotoList;//已经选择图片
    private List<String> radioPhotoUrl=new ArrayList<>();//压缩后图片的临时路径
    private Picked_photo_adapter picked_photo_adapter;
    private Button button;
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.clear();
                mPhotoList.addAll(resultList);
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        for(int i=0;i<mPhotoList.size();i++) {
                            subscriber.onNext(mPhotoList.get(i).getPhotoPath());
                        }
                    }
                }).subscribeOn(Schedulers.newThread())
                        //指定为IO线程
                        .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //图片压缩，并且返回压缩完成后临时图片路径
                        Bitmap bm=ImageFactory.ratio(s,480,800);
                        return ImageFactory.savePhoto(bm,Environment
                                .getExternalStorageDirectory().getAbsolutePath().toString() + "/clocle/temp_img/",String
                                .valueOf(System.currentTimeMillis()));


                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //TODO
                        picked_photo_adapter = new Picked_photo_adapter(Dynamic_publish.this, mPhotoList);
                        //picked_photo_adapter.notifyDataSetChanged();
                        gridView.setAdapter(picked_photo_adapter);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        radioPhotoUrl.add(s);
                        Log.i("tag",s);
                    }
                });





                   // Bitmap bm=ImageFactory.ratio(mPhotoList.get(0).getPhotoPath(),480,800);




            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(Dynamic_publish.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoList = new ArrayList<>();
        setContentView(R.layout.dynamic_publish_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_choose_photo);
        button= (Button) findViewById(R.id.dynamic_publish);
        dynamic_ed= (EditText) findViewById(R.id.dynamic_edit);
        //发表
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPhotoList.size()==0){
                    Toast.makeText(Dynamic_publish.this,"请添加要分享的图片",Toast.LENGTH_SHORT).show();
                return;
                }
                //1.压缩图片保存在临时文件夹
                /*try {
                    ImageFactory.compressAndGenImage(mPhotoList.get(0).getPhotoPath(),Environment
                            .getExternalStorageDirectory().getAbsolutePath().toString() + "/clocle/temp_img/",500,false);
                    Toast.makeText(Dynamic_publish.this,"完成l",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //2.上传图片
               final String[] Urlarr=new String[mPhotoList.size()];
                for(int i=0;i<Urlarr.length;i++){
                    Urlarr[i]=mPhotoList.get(i).getPhotoPath();
                }
                BmobFile.uploadBatch(Urlarr, new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list, List<String> list1) {
                        if(list1.size()==Urlarr.length) {
                            Dynamic dynamic=new Dynamic();
                            dynamic.setDynamicContent(dynamic_ed.getText().toString());
                            dynamic.setCommentCount(0);
                            dynamic.setViews(1);
                            dynamic.setUser(BmobUser.getCurrentUser(Bmob_UserBean.class));
                            dynamic.setImgs(list1);
                            dynamic.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(Dynamic_publish.this,"发布成功",Toast.LENGTH_SHORT).show();
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

                //3.保存到数据库
            }
        });
        gridView = (GridView) findViewById(R.id.gv_photo_choose);
        mPhotoList=new ArrayList<>();
        //picked_photo_adapter = new Picked_photo_adapter(this, mPhotoList);
        gridView.setAdapter(null);
        //打开图片选择器
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionConfig config = new FunctionConfig.Builder()
                        .setMutiSelectMaxSize(9)
                        .setSelected(mPhotoList)
                        .setEnablePreview(true)
                        .setEnableCamera(true)
                        .build();
                GalleryFinal.openGalleryMuti(1001, config, mOnHanlderResultCallback);
            }
        });
    }
}
