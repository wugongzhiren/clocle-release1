package com.clocle_help.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.ServiceUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.common_tool.SystemHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 最新的服务用户列表
 * Created by Hodo on 2017/12/25.
 */

public class LatestUserAdapter extends RecyclerView.Adapter<LatestUserAdapter.LatestUserHolder> {
    private int screenW;
    private List<ServiceUser> users=new ArrayList<>();
    private Context mcontext;
    public LatestUserAdapter(Context context){
        this.mcontext=context;
        screenW= SystemHelper.getScreenWidthPX(context);
    }
    @Override
    public LatestUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.cloclehelp_latestuseritem,parent,false);
        return new LatestUserHolder(view);
    }

    @Override
    public void onBindViewHolder(final LatestUserHolder holder, int position) {
        //设置服务图片
        if(users.size()==0)
            return;
        Glide.with(mcontext).asBitmap().load(users.get(position).getImgs().get(0)).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                ImageFactory.setNewHWImage(holder.contentImg,resource.getWidth(),resource.getHeight(),screenW);
                holder.contentImg.setImageBitmap(resource);
            }
        });
        //设置服务内容
        holder.contentText.setText(users.get(position).getServiceContent());
        //设置昵称
        holder.nickname.setText(users.get(position).getUser().getUsername());
        //设置头像
        Glide.with(mcontext).load(users.get(position).getUser().getPhotoUrl()).into(holder.avatar);
        //设置标签
        //TODO
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public void setDatas(List<ServiceUser> datas){
        this.users.clear();
        this.users.addAll(datas);
        notifyDataSetChanged();
    }
    class LatestUserHolder extends RecyclerView.ViewHolder{
        public ImageView contentImg;//服务图片
        public TextView contentText;//服务内容
        public ImageView avatar;//用户头像
        public TextView nickname;//用户昵称
        public LatestUserHolder(View itemView) {
            super(itemView);
            contentImg=itemView.findViewById(R.id.contentImg);
            contentText=itemView.findViewById(R.id.contentText);
            avatar=itemView.findViewById(R.id.avatar);
            nickname=itemView.findViewById(R.id.nickname);
        }
    }
}
