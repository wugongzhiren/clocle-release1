package com.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.clocle.huxiang.clocle.R;
import com.common_tool.SystemHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.view.DynamicHWSimpleDrawView;

import java.util.List;

import tool.DensityUtil;

/**
 *
 * Created by Administrator on 2016/10/12.
 */
public class Rv_single_imgs_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<String> imgs;
private int viewtype=0;
    private int viewtype1=1;
    private int screenWidth;
    @Override
    public int getItemViewType(int position) {
        if(imgs.size()==1){
            return viewtype;
        }
        else{
            return viewtype1;
        }
    }

    public Context mcontext;
    public interface OnItemOnclickListener{
        void onItemClick(View view ,int pos);
    }
    private OnItemOnclickListener onItemOnclickListener;

    public void setOnItemOnclickListener(OnItemOnclickListener onItemOnclickListener) {
        this.onItemOnclickListener = onItemOnclickListener;
    }

    public Rv_single_imgs_adapter(List<String> imgs, Context mcontext) {
        this.imgs = imgs;
        this.mcontext = mcontext;
        screenWidth= SystemHelper.getScreenWidthPX(mcontext);
        Log.i("tag", "onCreateViewHolder"+imgs.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemview = LayoutInflater.from(mcontext).inflate(R.layout.single_img, parent, false);
        Log.i("tag", "onCreateViewHolder"+imgs.size());
        if(viewType==0) {
            //单图模式，模拟自适应图片的宽高
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(600,450);
            itemview.setLayoutParams(lp);
        }
        if(viewType==1){
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(screenWidth/3- DensityUtil.dip2px(mcontext,6), screenWidth/3-DensityUtil.dip2px(mcontext,6));
            itemview.setLayoutParams(lp);
        }

        return new Rv_single_imgs_holder(itemview);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(onItemOnclickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemOnclickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        Log.i("tag", "singonBindViewHolder在这显示");
        if (holder instanceof Rv_single_imgs_holder) {
            ((Rv_single_imgs_holder) holder).img.setImageURI(Uri.parse(imgs.get(position)));
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return imgs.size();
    }

    class Rv_single_imgs_holder extends RecyclerView.ViewHolder {
        public SimpleDraweeView img;

        public Rv_single_imgs_holder(View itemView) {
            super(itemView);

            img = (SimpleDraweeView) itemView;
            Log.i("tag", "实例化");
        }
    }
}
