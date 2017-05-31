package com.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.common_tool.PermissionManage;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import tool.Popwindow;
import tool.ShowToast;


/**
 * 个人资料修改页面
 * Created by Administrator on 2016/12/6.
 */

public class UpdateSelfInfo extends AppCompatActivity implements View.OnClickListener{
    private SimpleDraweeView photo;
    protected static Uri tempUri;
    private LinearLayout nickname;
    private LinearLayout signature;
    private Popwindow mpopwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Bmob_UserBean bean= (Bmob_UserBean) getIntent().getSerializableExtra("user");
setContentView(R.layout.updateselfinfo);
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.update_self_line);
        photo= (SimpleDraweeView) findViewById(R.id.updateselfinfo_photo);
        nickname= (LinearLayout) findViewById(R.id.nicknameLine);

        signature= (LinearLayout) findViewById(R.id.signatureLine);
        photo.setImageURI(Bmob_UserBean.getCurrentUser(Bmob_UserBean.class).getphotoUrl());
        photo.setOnClickListener(this);
        nickname.setOnClickListener(this);
        signature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nicknameLine:
                //打开修改昵称的popwindow
                mpopwindow = new Popwindow(UpdateSelfInfo.this,0);
                mpopwindow.setAnimationStyle(R.style.mypopwindow_anim);
                mpopwindow.showAsDropDown(nickname,0,0);
                //mpopwindow.showAtLocation(linearLayout,0,0);
                lightOff();
                mpopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        lightOn();
                    }
                });
                // mpopwindow.update();
        break;
            case R.id.signatureLine:
                //打开修改昵称的popwindow
                mpopwindow = new Popwindow(UpdateSelfInfo.this,1);
                mpopwindow.setAnimationStyle(R.style.mypopwindow_anim);
                mpopwindow.showAsDropDown(signature,0,0);
                //mpopwindow.showAtLocation(linearLayout,0,0);
                lightOff();
                mpopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        lightOn();
                    }
                });
                break;
            case R.id.updateselfinfo_photo:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("更新头像");
                String[] items = {"选择照片", "拍照"};
                builder.setNegativeButton("取消", null);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://从本地选取
                                Intent openAlbumIntent = new Intent(
                                        Intent.ACTION_GET_CONTENT);
                                openAlbumIntent.setType("image/*");
                                  startActivityForResult(openAlbumIntent, 0);
                                break;
                            case 1: // 拍照
                                //判断是否授权
                                if(PermissionManage.checkPermisson(UpdateSelfInfo.this,Manifest.permission.CAMERA)){
                                    Intent openCameraIntent = new Intent(
                                            MediaStore.ACTION_IMAGE_CAPTURE);
                                    tempUri = Uri.fromFile(new File(Environment
                                            .getExternalStorageDirectory(), "myphoto.jpg"));
                                    // 指定照片保存路径（SD卡），myphoto.jpg为一个临时文件，每次拍照后这个图片都会被替换
                                    openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                                    startActivityForResult(openCameraIntent, 1);
                                    break;
                                }
                                else {
                                    //去请求权限
                                    PermissionManage.requestPermission(UpdateSelfInfo.this,Manifest.permission.CAMERA);
                                    break;
                                    //Toast.makeText(PermissionActivity.this, "no this permission", Toast.LENGTH_SHORT).show();
                                }

                        }
                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }
    }

    //裁剪图片
    protected void startPhotoZoom(Uri uri) {
        //Log.i("tag", "裁剪");
        if (uri == null) {
            //Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case 1:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case 0:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case 2:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
    /**
     * 保存裁剪之后的图片数据,将裁剪后的照片设置在图像页面
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        //Log.i("tag", "保存裁剪");
        Bundle extras = data.getExtras();
        if (extras != null) {

            final Bitmap bMphoto = extras.getParcelable("data");
            String imagePath = ImageFactory.savePhoto(bMphoto, Environment
                    .getExternalStorageDirectory().getAbsolutePath().toString() + "/clocle/myphoto/", "myphoto");
            photo.setImageURI("file://"+Uri.parse(imagePath));
            //头像上传更新
            File file = new File(imagePath);

            final BmobFile bmobFile = new BmobFile(file);
            bmobFile.uploadblock(new UploadFileListener() {

                @Override
                public void done(BmobException e) {
                    if(e==null){
                        //ShowToast.showToast(UpdateSelfInfo.this,);
                        String url=bmobFile.getFileUrl();//返回的上传文件的完整地址
                        final Bmob_UserBean newuser = new Bmob_UserBean();
                        newuser.setphotoUrl(url);
                        Bmob_UserBean userBean = BmobUser.getCurrentUser(Bmob_UserBean.class);
                        //toast("上传文件成功:" + bmobFile.getFileUrl());
                        newuser.update(userBean.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    ShowToast.showToast(UpdateSelfInfo.this,"头像更换成功");
                                }
                            }
                        });
                    }else{
                        //toast("上传文件失败：" + e.getMessage());
                    }

                }

                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });






/*
            newuser.setUsername("糖糖没有果2");
            newuser.setphotoUrl("http://bmob-cdn-6342.b0.upaiyun.com/2016/09/18/f7451a7915a94788bd73c68f8e7e4bc7.png");

            Bmob_UserBean userBean = BmobUser.getCurrentUser(Bmob_UserBean.class);
            newuser.update(userBean.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(Self_manager.this, "头像更换成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Self_manager.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

          /*  new Thread() {
                @Override
                public void run() {


                    uploadPic(photo);//上传到服务器


                }
            }.start();*/

        }
    }
    //内容区域哦变亮
    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);

    }
    //内容区域哦变暗
    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent openCameraIntent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    tempUri = Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), "myphoto.jpg"));
                    // 指定照片保存路径（SD卡），myphoto.jpg为一个临时文件，每次拍照后这个图片都会被替换
                    openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    PermissionManage.requestPermission(UpdateSelfInfo.this,Manifest.permission.CAMERA);
                }

            }
        }
    }
}
