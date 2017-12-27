package com.clocle_help.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 热门服务用户的adapter
 * Created by Hodo on 2017/12/21.
 */

public class HotUserAdapter extends RecyclerView.Adapter<HotUserAdapter.HotUserHolder>{
    private RequestOptions options=new RequestOptions();
    List<User> users=new ArrayList<>();
    private Context context;
    public HotUserAdapter(Context context){
        this.context=context;
        options.transform(new CircleCrop());
    }
    @Override
    public HotUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hotuser_item,parent,false);
        return new HotUserHolder(view);
    }

    @Override
    public void onBindViewHolder(HotUserHolder holder, int position) {
        Glide.with(context).load(users.get(position).getPhotoUrl()).apply(options).into(holder.avatar);
        holder.nickname.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public void setData(List<User> users){
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }
    class HotUserHolder extends RecyclerView.ViewHolder{
        public ImageView avatar;
        public TextView nickname;

        public HotUserHolder(View itemView){
            super(itemView);
            avatar=itemView.findViewById(R.id.avatar);
            nickname=itemView.findViewById(R.id.nickname);
        }
    }
}
