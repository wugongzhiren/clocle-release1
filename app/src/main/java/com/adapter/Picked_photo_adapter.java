
package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.facebook.drawee.view.SimpleDraweeView;
import com.view.DynamicHWimageview;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 通用图片选择后展示已选图片的adapter
 * Created by Administrator on 2016/9/7.
 */

public class Picked_photo_adapter extends BaseAdapter {
    private List<PhotoInfo> photoInfoList;
    private LayoutInflater inflater;

    public Picked_photo_adapter(Context mcontext, List<PhotoInfo> infoList) {
        this.inflater = LayoutInflater.from(mcontext);
        this.photoInfoList = infoList;
    }

    @Override
    public int getCount() {
        return photoInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SinglePhotoVH holder;
        if (convertView == null) {
convertView=inflater.inflate(R.layout.picked_photo_item,parent,false);
            holder=new SinglePhotoVH();
            holder.imgview= (DynamicHWimageview) convertView.findViewById(R.id.picked_photo_item);
        holder.imageButton= (ImageButton) convertView.findViewById(R.id.photo_clear);
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoInfoList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else {
            holder= (SinglePhotoVH) convertView.getTag();
        }

        //RxJava异步压缩图片
       //1.创建被观察者

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //发出事件,即将图片URL发给订阅者
                subscriber.onNext(photoInfoList.get(position).getPhotoPath());
            }
            //将压缩操作切换到新的线程
        }).subscribeOn(Schedulers.newThread())
                //指定为IO线程
                .observeOn(Schedulers.io())
                //链式调用，变换，将url转化为Bitmap
                .map(new Func1<String,Bitmap>() {
                    @Override
                    public Bitmap call(String url) {
                        //自定义方法，根据路径进行压缩
                        return ImageFactory.getBitmap(url);
                    }
                    //将线程环境切换到主线程，进行UI更新操作
                }).observeOn(AndroidSchedulers.mainThread())
                //创建观察者，对结果进行处理
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
Log.i("TAG","晚餐晚餐");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        //设置图片到view
                        holder.imgview.setImageBitmap(bitmap);
                        holder.imageButton.setVisibility(View.VISIBLE);
                    }
                });
       return convertView;
    }

    class SinglePhotoVH {
        private DynamicHWimageview imgview;
        private ImageButton imageButton;
    }
}

