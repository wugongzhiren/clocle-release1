package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bean.ServiceUser;
import com.clocle.huxiang.clocle.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class Clocle_help_userservice extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ServiceUser> list;
    private LayoutInflater inflater;
    private Context mcontext;
    public Clocle_help_userservice(Context context,List<ServiceUser> list){
        this.mcontext=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=null;
        view=inflater.inflate(R.layout.user_service_item,parent,false);
        return new UserserviceHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
if(holder instanceof UserserviceHolder){
    ((UserserviceHolder) holder).serviceContent.setText("提供的服务：陪跑步，陪上自习，羽毛球缺人也可以找我哦");
}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class UserserviceHolder extends RecyclerView.ViewHolder{
public TextView serviceContent;

        public UserserviceHolder(View itemView) {
            super(itemView);
            serviceContent= (TextView) itemView.findViewById(R.id.cloclehelp_content);
        }
    }
}
