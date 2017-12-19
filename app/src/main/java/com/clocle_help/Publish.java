
package com.clocle_help;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.Base_activity;
import com.adapter.Picked_photo_adapter;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.constant.Constant;
import com.databindingbean.Clocle_help_publish;
import com.facebook.drawee.view.SimpleDraweeView;
import com.function.Dynamic_publish;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aliyun.AliOss;
import tool.Progress_dialog;
import tool.ShowToast;
import tool.ToolbarHelper;
import tool.Utils;


/**
 * 圈圈帮的发表悬赏页面
 * Created by Administrator on 2016/7/26.
 */
public class Publish extends Base_activity{
    private Button addImage;
    private Picked_photo_adapter adapter;
    private GridView mgridView;
    private Button addTag;
    private PopupWindow tagWindow;
    private boolean isHasTag=false;
    private View popview;
    private Button confirmBt;//确认标签
    private EditText tag;
    private Toolbar toolbar;
    private ToolbarHelper toolbarHelper=new ToolbarHelper();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_layout);
        initView();
        toolbarHelper.setToolBar(false,this,toolbar,null, R.mipmap.ic_menu_back);
        initEvent();
    }

    private void initEvent() {
        toolbarHelper.setActivityTask(new ToolbarHelper.ActivityTask() {
            @Override
            public void action(View v) {
                if(v.getId()==R.id.toolbar_bt){
                    //如果点击的发布按钮，后台通信
                    //TODO
                    //发布前检查
                    //check();
                    //TODO
                    //doHttpTask
                    ShowToast.showToast(Publish.this,"正在发布。。。");
                }
            }
        });
    }


    private void initView() {
        addImage= (Button) findViewById(R.id.addImage);
        addTag= (Button) findViewById(R.id.addTag);
        toolbar= (Toolbar) findViewById(R.id.common_toolbar);
        mgridView= (GridView) findViewById(R.id.pick_image);
        addImage.setOnClickListener(this);
        addTag.setOnClickListener(this);
        adapter=new Picked_photo_adapter(this);
        mgridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addImage:
                //添加图片
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(3)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.addTag:
                //添加标签
                Utils.lightOff(this);
                if(tagWindow==null||!tagWindow.isShowing())
                popTag();
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Log.i("publish", "返回图片地址: "+selectList.get(0).getPath());
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setSelectList(selectList);
                    break;
            }
        }
    }

    /**
     * 弹出标签选择popupwindow
     */
    public void popTag(){
        if(tagWindow==null) {
            popview = getLayoutInflater().inflate(R.layout.addtag, null);
            tag=popview.findViewById(R.id.tag);
            confirmBt=popview.findViewById(R.id.confirmTag);
            confirmBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加了自定义标签
                    if(tag!=null&&tag.getText().toString()==""){
                        ShowToast.showToast(Publish.this,"还没有输入标签哦");
                        return;
                    }
                    else{
                        isHasTag=true;
                        tagWindow.dismiss();
                        addTag.setText(tag.getText().toString());
                    }
                }
            });
            tagWindow = new PopupWindow(popview, (int) ((int)Utils.getDeviceWH(this).get("width")*0.7), (int) ((int)Utils.getDeviceWH(this).get("height")*0.7));
            tagWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Utils.lightOn(Publish.this);
                }
            });
            tagWindow.setTouchable(true);
            tagWindow.setFocusable(true);
            tagWindow.setOutsideTouchable(true);
            //没有这句点击外部不会消失
            tagWindow.setBackgroundDrawable(new BitmapDrawable());
            tagWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0,0);
            //tagWindow.setAnimationStyle(R.style.popup_window_anim);
        }
        else {
            tagWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //当标签的popupwindow在显示时，点击返回可以关闭
        if(keyCode==KeyEvent.KEYCODE_BACK&&tagWindow!=null&&tagWindow.isShowing()){
            tagWindow.dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
