package com.clocle.huxiang.clocle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.adapter.Picked_photo_adapter;
import com.adapter.Picked_photo_withAddBt_Adapter;
import com.bean.Clocle_help;
import com.bean.MyPhotoInfo;
import com.clocle.huxiang.clocle.databinding.PublishLayoutBinding;
import com.common_tool.ImageFactory;
import com.constant.Constant;
import com.databindingbean.Clocle_help_publish;
import com.facebook.drawee.view.SimpleDraweeView;
import com.function.Dynamic_publish;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aliyun.AliOss;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tool.Progress_dialog;
import tool.ShowToast;
import tool.Utils;

/**
 * 圈圈帮的发表悬赏页面（还需要优化）
 * Created by Administrator on 2016/7/26.
 */
public class Publish extends Activity implements View.OnClickListener {
    private List<MyPhotoInfo> radioPhotoUrl = new ArrayList<>();//压缩后图片的临时路径
    private RecyclerView recyclerView;
    private Picked_photo_withAddBt_Adapter picked_photo_adapter;
    private List<PhotoInfo> pickedUrl = new ArrayList<>();//记住的图片url
    private PublishLayoutBinding binding;
    //选择好图片时回调
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, final List<PhotoInfo> resultList) {
            if (resultList.size() != 0) {
                pickedUrl.clear();
                //pickedUrl.addAll(resultList);
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        for (int i = 0; i < resultList.size(); i++) {
                            subscriber.onNext(resultList.get(i).getPhotoPath());
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.newThread())
                        //指定为IO线程
                        .observeOn(Schedulers.io())
                        .map(new Func1<String, String[]>() {
                            @Override
                            public String[] call(String s) {
                                //图片压缩，并且返回压缩完成后临时图片路径
                                Bitmap bm = ImageFactory.ratio(s, 480, 800);
                                String filename = UUID.randomUUID() + ".png";
                                //filenames.add(filename);
                                ImageFactory.savePhoto(bm, Environment
                                        .getExternalStorageDirectory().getAbsolutePath().toString() + "/clocle/clocle_help/temp_img/", filename);
                                return new String[]{filename, ImageFactory.savePhoto(bm, Environment
                                        .getExternalStorageDirectory().getAbsolutePath().toString() + "/clocle/clocle_help/temp_img/", filename)};

                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String[]>() {
                            @Override
                            public void onCompleted() {
                                pickedUrl.addAll(radioPhotoUrl);
                                //在末尾加个addphoto
                                pickedUrl.add(new MyPhotoInfo());
                                picked_photo_adapter.notifyDataSetChanged();
                                ShowToast.showToast(Publish.this, "添加图片成功");
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onNext(String[] s) {
                                MyPhotoInfo info = new MyPhotoInfo();
                                info.setPhotoPath(s[1]);
                                info.setFilename(s[0]);
                                radioPhotoUrl.add(info);


                                //表示用户已经选择了图片

                            }
                        });
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.publish_layout);
        Toast.makeText(this, "测试2", Toast.LENGTH_SHORT).show();
        Clocle_help_publish bean = new Clocle_help_publish();
        bean.setText4("安徽中医药大学");
        bean.setText5("人数");
        bean.setText3("标签");
        bean.setText1("请描述你想要获取什么帮助？来自databingding的设值");
        //dataBinding绑定
        binding.setBean(bean);
        binding.setClickListener(this);
        pickedUrl.add(new PhotoInfo());
        picked_photo_adapter = new Picked_photo_withAddBt_Adapter(mOnHanlderResultCallback, this, pickedUrl);
        initviews();
    }

    private void initviews() {

        recyclerView = (RecyclerView) findViewById(R.id.picked_photo);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(picked_photo_adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_button://发表
                //ShowToast.showToast(this,"databimgd");
                //首先上传照片
                final Bmob_UserBean bean = BmobUser.getCurrentUser(Bmob_UserBean.class);
                //页面输入项检查
                if (binding.publishText.getText().toString().equals("")) {
                    ShowToast.showToast(this, "请输入求助内容");
                    return;
                }
                if (binding.publishMoney.getText().toString().equals("")) {
                    ShowToast.showToast(this, "打赏后小伙伴更乐意帮助你哦！");
                    return;
                }
                if (binding.publishPeople.getText().toString().equals("")) {
                    ShowToast.showToast(this, "参与人数必须输入");
                    return;
                }
                final String publishcontent = binding.publishText.getText().toString();//获取发表内容
                final int money = Integer.parseInt(binding.publishMoney.getText().toString());//获取悬赏金额
                final int people = Integer.parseInt(binding.publishPeople.getText().toString());
                //选择了图片，上传
                final Dialog mydialog = new Progress_dialog(Publish.this).createLoadingDialog("111");
                mydialog.show();
                    /*BmobFile.uploadBatch(urlArr, new UploadBatchListener() {
                        @Override
                        public void onSuccess(List<BmobFile> list, List<String> list1) {
                            if (list1.size() == urlArr.length) {
                                Toast.makeText(Publish.this, "图片上传成功", Toast.LENGTH_SHORT).show();

                                //mydialog.show();
                                //插入数据到圈圈帮帖子表
                                Clocle_help help = new Clocle_help();
                                help.setContent(publishcontent);
                                help.setImgs(list1);
                                help.setPeopleNum(people);
                                help.setTag("tag");
                                help.setSum_clocle_money(money);
                                help.setBmob_userBean(bean);
                                help.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            mydialog.dismiss();
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
                            Toast.makeText(Publish.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }*/
                //用户没有选择添加图片
                /*else {
                    Clocle_help help = new Clocle_help();
                    help.setContent(publishcontent);
                    help.setPeopleNum(people);
                    help.setTag("羽毛球");
                    help.setSum_clocle_money(money);
                    help.setBmob_userBean(bean);
                    final Dialog mydialog = new Progress_dialog(Publish.this).createLoadingDialog("111");
                    mydialog.show();
                    help.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                mydialog.dismiss();
                            }
                        }
                    });
                }*/


                //  Pulish_bean bean = new Pulish_bean(0, userid,
                //  //          publishtext, date, money, null, null, null);
                //  Obeject_toJson obeject_toJson = new Obeject_toJson();
                //   request_string = obeject_toJson.publish_tojson(bean);//将bean对象转化为json字符串
                //点击button开启一个线程，线程中的网络请求又是一个异步请求，但是这个网络请求的线程非系统的主线程
                //所以必须通过Asynctask或者handle，或者runonuithread的方式来进行UI操作
            /*    new Thread() {
                    @Override
                    public void run() {


                        //final Publish_request publish_request = new Publish_request(Publish.this, request_string, req_progressBar,intent);
                        Handler handler = new Handler(getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Publish.this, "请求", Toast.LENGTH_SHORT).show();
                                mydialog = new Progress_dialog(Publish.this).createLoadingDialog("111");
                                mydialog.show();
                                if (imgCount == 1) {
                                    publish_requestwithOnephoto();
                                }
                                if (imgCount == 2) {
                                    publish_requestwithTwophoto();
                                }
                                if (imgCount == 3) {
                                    publish_requestwithThreephoto();
                                }

                                // goTo_indexc_fg();
                            }
                        });


                    }
                }.start();*/
//测试阿里云oss
                final ArrayList<String> imgs=new ArrayList<>();

                final AliOss aliOss = new AliOss(this);
                Observable.create(new Observable.OnSubscribe<List<MyPhotoInfo>>() {

                    @Override
                    public void call(Subscriber<? super List<MyPhotoInfo>> subscriber) {

                        subscriber.onNext(radioPhotoUrl);

                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.newThread())
                        //指定为IO线程
                        .observeOn(Schedulers.io())
                        .map(new Func1<List<MyPhotoInfo>, String>() {
                            @Override
                            public String call(List<MyPhotoInfo> s) {

                                //上传图片，更新后端信息
                                for (int i = 0; i < s.size(); i++) {
                                    //返回成功还是失败
                                    String resultTemp = aliOss.UploadToOssSync(s.get(i).getFilename(), s.get(i).getPhotoPath());
                                    if (!resultTemp.equals(Constant.FAIL)) {
                                        imgs.add(resultTemp);
                                        continue;
                                    }
                                    else {
                                        return Constant.FAIL;
                                    }


                                }
                                return Constant.SUCCESS;
                            }
                        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //更新到Bmob后端
                        Clocle_help help = new Clocle_help();
                        help.setContent(publishcontent);
                        help.setImgs(imgs);
                        help.setPeopleNum(people);
                        help.setTag("tag");
                        help.setSum_clocle_money(money);
                        help.setBmob_userBean(bean);
                        help.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    mydialog.dismiss();
                                    ShowToast.showToast(Publish.this, "发布成功");
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals(Constant.FAIL)) {
                            this.onError(new Throwable());
                        }

                    }
                });



                break;

            default:
                break;
        }

    }


}



