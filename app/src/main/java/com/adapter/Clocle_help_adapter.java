package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ViewHolder.Clocle_help_Holder;
import com.bean.Clocle_help;
import com.bumptech.glide.Glide;
import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求帮助的适配器
 * Created by Administrator on 2017/10/30.
 */

public class Clocle_help_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mcontext;
    private List<Clocle_help> list = new ArrayList<>();


    //对外设置item点击的接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View itemview, int position);
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private OnItemClickListener mOnItemClickLitener;

    public Clocle_help_adapter(Context context) {
        this.mcontext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.clocle_gethelp_item, parent, false);
        return new Clocle_help_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //短按
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //长按
                    mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
        if (holder instanceof Clocle_help_Holder) {
            //加载头像
            //.transform(new GlideCircleTransform(mcontext))
            Glide.with(mcontext).load(list.get(position).getUser().getPhotoUrl())
                    .into(((Clocle_help_Holder) holder).avartarImage);
            //加载内容
            ((Clocle_help_Holder) holder).contentText.setText(list.get(position).getContent());
            //显示昵称
            ((Clocle_help_Holder) holder).nicknameText.setText(list.get(position).getUser().getUsername());
            //显示赏金
            ((Clocle_help_Holder) holder).moneyText.setText("赏"+list.get(position).getSum_clocle_money()+"元");
            //显示附加图片
            ((Clocle_help_Holder) holder).images.setUrlList((String[]) list.get(position).getImgs().toArray());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDatas(List<Clocle_help> datas) {
        list.clear();
        list.addAll(datas);
        notifyDataSetChanged();

    }


}
