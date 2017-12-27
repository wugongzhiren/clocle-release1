package com.clocle_help.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.ServiceUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.common_tool.SystemHelper;

import java.util.List;

/**
 * 最新的发布者数据适配器
 * Created by Hodo on 2017/12/26.
 */

public class LatestDatasAdapter extends BaseQuickAdapter<ServiceUser,BaseViewHolder> {
    private Context mcontext;
    private int screenW;
    public LatestDatasAdapter(int layoutResId, @Nullable List<ServiceUser> data, Context context) {
        super(layoutResId, data);
        this.mcontext=context;
        screenW= SystemHelper.getScreenWidthPX(context);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ServiceUser item) {
        Glide.with(mcontext).asBitmap().load(item.getImgs().get(0)).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                ImageFactory.setNewHWImage((ImageView) helper.getView(R.id.contentImg),resource.getWidth(),resource.getHeight(),screenW);
                helper.setImageBitmap(R.id.contentImg,resource);
            }
        });
        //设置服务内容
        helper.setText(R.id.contentText,item.getServiceContent());
        //设置昵称
        helper.setText(R.id.nickname,item.getUser().getUsername());
        //设置头像
        Glide.with(mcontext).load(item.getUser().getPhotoUrl()).into((ImageView) helper.getView(R.id.avatar));

    }



}

