package com.function;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import com.bean.ImageInfo;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.constant.Constant;
import com.http.repository.DynamicManage;
import com.imageselector.MultiImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import aliyun.AliOss;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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
    private List<String> httpImgUrl = new ArrayList<>();//图片外链路径
    private List<ImageInfo> radioPhoto = new ArrayList<>();//压缩后图片的信息
    private Picked_photo_adapter picked_photo_adapter;
    private Button button;
    private Retrofit retrofit;
    private DynamicManage dynamicManage;
    private CompositeDisposable s = new CompositeDisposable();//Rxjava的Disposable

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
                    //异步文件上传
                    Observable.create(new ObservableOnSubscribe<ImageInfo>() {
                        @Override
                        public void subscribe(ObservableEmitter<ImageInfo> e) throws Exception {
                            for (int i = 0; i < radioPhoto.size(); i++) {
                                e.onNext(radioPhoto.get(i));
                            }
                            //事件完成
                            e.onComplete();
                        }
                    }).map(new Function<ImageInfo, String>() {
                        @Override
                        public String apply(ImageInfo imageInfo) throws Exception {
                            //图片上传，并且返回上传结果
                            return AliOss.getAliOss().UploadToOssSync(imageInfo.name + ".png", imageInfo.url);
                        }
                    }).subscribeOn(Schedulers.io())//在IO线程发事件
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>()
                    {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //d.add();
                            s.add(d);
                        }
                        @Override
                        public void onNext(String value) {
                            if (Constant.FAIL.equals(value)) {
                                ShowToast.showToast(mcontext, "图片上传失败");
                                //取消订阅
                                s.clear();
                            } else {
                                httpImgUrl.add(value);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            s.clear();
                        }

                        @Override
                        public void onComplete() {
                            retrofit = App.getRetrofit();
                            dynamicManage = retrofit.create(DynamicManage.class);
                            Call call = dynamicManage.publish(new GetuserInfo(mcontext).getUserinfo(), dynamic_ed.getText().toString(), httpImgUrl);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    if (response.body().equals(Constant.SUCCESS)) {
                                        ShowToast.showToast(mcontext, "发布成功");
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    ShowToast.showToast(mcontext, "服务器开小差啦");
                                }
                            });
                        }
                    });
                    //回到主线程处理结果
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
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; i < mPhotoList.size(); i++) {
                            e.onNext(mPhotoList.get(i));
                        }
                        e.onComplete();
                    }
                })
                        //指定为IO线程
                        .subscribeOn(Schedulers.io())
                        .map(new Function<String, ImageInfo>() {
                            @Override
                            public ImageInfo apply(String s) throws Exception {
                                return ImageFactory.ratio(s, 480, 800);
                            }
                        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ImageInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        s.add(d);
                    }

                    @Override
                    public void onNext(ImageInfo s) {
                        if (!radioPhoto.contains(s)) {
                            radioPhoto.add(s);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShowToast.showToast(mcontext, "图片解析错误");
                        s.clear();
                    }

                    @Override
                    public void onComplete() {
                        picked_photo_adapter = new Picked_photo_adapter(mcontext, radioPhoto);
                        //picked_photo_adapter.notifyDataSetChanged();
                        gridView.setAdapter(picked_photo_adapter);
                    }
                });
            }
        }
    }
}
