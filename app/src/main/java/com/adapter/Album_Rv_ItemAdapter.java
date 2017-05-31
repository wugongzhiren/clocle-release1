package com.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Album_Rv_ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<String> imgs;
    public Context mcontext;
    private List<Integer> mHeights;

    public Album_Rv_ItemAdapter(List<String> imgs, Context mcontext) {
        this.imgs = imgs;
        this.mcontext = mcontext;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < imgs.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
        Log.i("tag", "onCreateViewHolder"+imgs.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(mcontext).inflate(R.layout.single_img, parent, false);
        Log.i("tag", "onCreateViewHolder"+imgs.size());
        return new Rv_single_imgs_holder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("tag", "singonBindViewHolder在这显示");

        if (holder instanceof Rv_single_imgs_holder) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) ((Rv_single_imgs_holder) holder).img.getLayoutParams();
            lp.height = mHeights.get(position);

            ((Rv_single_imgs_holder) holder).img.setLayoutParams(lp);
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
            img = (SimpleDraweeView) itemView.findViewById(R.id.item_single_img);
            Log.i("tag", "实例化");
        }
    }
}
