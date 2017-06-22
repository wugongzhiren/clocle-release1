package com.function;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Base_activity;
import com.adapter.Picked_photo_adapter;
import com.application.App;
import com.bean.Dynamic;
import com.bean.ImageInfo;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.constant.Constant;
import com.http.repository.DynamicManage;
import com.imageselector.MultiImageSelectorActivity;
import com.imageselector.view.StartImage;

import java.util.ArrayList;
import java.util.List;

import aliyun.AliOss;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tool.GetuserInfo;
import tool.ShowToast;

/**
 * 分享新鲜事的发表页面
 * Created by Administrator on 2016/11/12.
 */

public class Dynamic_publish extends Base_activity {
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private EditText dynamic_ed;
    private List<String> mPhotoList;//已经选择图片
    private List<ImageInfo> radioPhoto = new ArrayList<>();//压缩后图片的信息
    private Picked_photo_adapter picked_photo_adapter;
    private Button button;
private Retrofit retrofit;
    private DynamicManage dynamicManage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoList = new ArrayList<>();
        setContentView(R.layout.dynamic_publish_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_choose_photo);
        //发表按钮
        button = (Button) findViewById(R.id.dynamic_publish);
        dynamic_ed = (EditText) findViewById(R.id.dynamic_edit);
        //发表
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhotoList.size() == 0) {
                    Toast.makeText(Dynamic_publish.this, "请添加要分享的图片", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    AliOss.getAliOss().startUploadTasks(radioPhoto, new AliOss.UploadListener() {
                        @Override
                        public void success(List<ImageInfo> results) {
ShowToast.showToast(mcontext,"全部上传完成");
                        }

                        @Override
                        public void fail() {

                        }
                    });
//上传到服务器，加密传输
                    retrofit= App.getRetrofit();
                    dynamicManage=retrofit.create(DynamicManage.class);
                    //Call<String> call = dynamicManage.publish(new GetuserInfo(mcontext).getUserinfo(),dynamic_ed.getText().toString(),);
                }
            }
        });
        gridView = (GridView) findViewById(R.id.gv_photo_choose);
        mPhotoList = new ArrayList<>();
        //picked_photo_adapter = new Picked_photo_adapter(this, mPhotoList);
        gridView.setAdapter(null);
        //打开图片选择器
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    "请求权限",
                    101);
        } else {
            Intent intent = new Intent(mcontext, MultiImageSelectorActivity.class);
            startActivityForResult(intent, Constant.IMAGESELECTOR);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle("权限")
                    .setMessage(rationale)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Dynamic_publish.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.IMAGESELECTOR) {
            if (resultCode == Activity.RESULT_OK) {
                mPhotoList = data.getStringArrayListExtra("select_result");
                // ShowToast.showToast(mcontext,mPhotoList.toString());
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        for (int i = 0; i < mPhotoList.size(); i++) {
                            subscriber.onNext(mPhotoList.get(i));
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.newThread())
                        //指定为IO线程
                        .observeOn(Schedulers.io())
                        .map(new Func1<String, ImageInfo>() {
                            @Override
                            public ImageInfo call(String s) {
                                //图片压缩，并且返回压缩完成后临时图片路径
                                return ImageFactory.ratio(s, 480, 800);
                            }
                        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ImageInfo>() {
                    @Override
                    public void onCompleted() {
                        picked_photo_adapter = new Picked_photo_adapter(mcontext, radioPhoto);
                        //picked_photo_adapter.notifyDataSetChanged();
                        gridView.setAdapter(picked_photo_adapter);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ShowToast.showToast(mcontext, "图片解析错误");
                    }

                    @Override
                    public void onNext(ImageInfo s) {
                        if (!radioPhoto.contains(s)) {
                            radioPhoto.add(s);

                        }
                    }
                });
            }
        }
    }
}
