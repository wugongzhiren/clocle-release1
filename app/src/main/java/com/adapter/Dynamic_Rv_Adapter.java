package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bean.Dynamic;
import com.clocle.huxiang.clocle.Other_Self_infos;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.function.Dynamic_Detail;

import java.util.List;

/**
 * 首页动态适配器
 * Created by Administrator on 2016/11/17.
 */

public class Dynamic_Rv_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Dynamic> list;
    private LayoutInflater inflater;
    private Context mcontext;



    public Dynamic_Rv_Adapter(Context context, List<Dynamic> dynamicList) {
        this.list = dynamicList;
        inflater = LayoutInflater.from(context);
        this.mcontext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.index_rv_item, parent, false);
        return new DynamicVH(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(list.get(position));
       if(holder instanceof DynamicVH){
           ((DynamicVH) holder).nameTV.setText(list.get(position).getUser().getUsername());
           ((DynamicVH) holder).indexImg.setImageURI(list.get(position).getImgs().get(0));
           ((DynamicVH) holder).indexPhoto.setImageURI(list.get(position).getUser().getphotoUrl());
           ((DynamicVH) holder).indexPhoto.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //跳转到查看他人资料页
                   Intent intent=new Intent(mcontext, Other_Self_infos.class);
                   intent.putExtra("userbean",list.get(position).getUser());
                   mcontext.startActivity(intent);
               }
           });
           ((DynamicVH) holder).contentTv.setText(list.get(position).getDynamicContent());
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DynamicVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SimpleDraweeView indexImg;
        private SimpleDraweeView indexPhoto;
        private TextView contentTv;
        private TextView nameTV;

        public DynamicVH(View itemView) {
            super(itemView);
            indexImg = (SimpleDraweeView) itemView.findViewById(R.id.index_rv_img);
            contentTv = (TextView) itemView.findViewById(R.id.index_rv_content);
            indexPhoto = (SimpleDraweeView) itemView.findViewById(R.id.index_rv_photo);
            nameTV = (TextView) itemView.findViewById(R.id.dynamic_name_tv);
            contentTv.setOnClickListener(this);
            indexPhoto.setOnClickListener(this);
            indexImg.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.index_rv_content:
                    //跳转到详情页
                    Intent intent=new Intent(mcontext, Dynamic_Detail.class);
                    intent.putExtra("dynamic",(Dynamic)itemView.getTag());
                    mcontext.startActivity(intent);
            }
        }
    }
}
