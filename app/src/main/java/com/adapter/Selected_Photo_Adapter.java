package com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.view.DynamicHWSimpleDrawView;
import com.view.DynamicHWimageview;

import java.util.ArrayList;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 已经选择的图片，多个添加图片的图片
 * Created by Administrator on 2016/12/12.
 */

public class Selected_Photo_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private int type0 = 0;//图片
    private int type1 = 1;//添加图片
    public GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback;
public ArrayList<String> urlList=new ArrayList<>();
    public Selected_Photo_Adapter(Context context, ArrayList<String> urlList,GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback) {
        //urlList=new ArrayList<>();
        this.urlList=urlList;
        inflater = LayoutInflater.from(context);
        this.mOnHanlderResultCallback=mOnHanlderResultCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == type0) {
            View itemview = inflater.inflate(R.layout.picked_photo_item, parent, false);

            return new ViewHolder(itemview);
        } else {
            View itemview = inflater.inflate(R.layout.addphoto, parent, false);
            return new ViewHolderWithAddPhoto(itemview);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        if (holder instanceof ViewHolder) {


            ((ViewHolder) holder).mimageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    urlList.remove(position);
                    notifyDataSetChanged();
                }
            });
            //RxJava异步压缩图片
            //1.创建被观察者

            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    //发出事件,即将图片URL发给订阅者
                    subscriber.onNext(urlList.get(position));
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
                            ((ViewHolder) holder).pickedphotoView.setImageBitmap(bitmap);
                            ((ViewHolder) holder).mimageButton.setVisibility(View.VISIBLE);
                        }
                    });
            //((ViewHolder) holder).pickedphotoView.setImageURI(urlList.get(position));
        } else if (holder instanceof ViewHolderWithAddPhoto) {
            ((ViewHolderWithAddPhoto) holder).mimageview.setImageResource(R.mipmap.addphoto);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (urlList.size() - 1)) {
            return type1;
        } else {
            return type0;
        }
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }


class ViewHolderWithAddPhoto extends RecyclerView.ViewHolder {
    public DynamicHWimageview mimageview;

    public ViewHolderWithAddPhoto(View itemView) {
        super(itemView);
        mimageview = (DynamicHWimageview) itemView.findViewById(R.id.addphoto);
        mimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionConfig config = new FunctionConfig.Builder()
                        .setMutiSelectMaxSize(9)
                        .setSelected(urlList)
                        .setEnablePreview(true)
                        .setEnableCamera(true)
                        .build();
                GalleryFinal.openGalleryMuti(1001, config, mOnHanlderResultCallback);
            }
        });
        //int width = mimageview.getLayoutParams().width = (getResources().getDisplayMetrics().widthPixels - 15) / 4;


    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public DynamicHWimageview pickedphotoView;
    public ImageButton mimageButton;

    public ViewHolder(final View itemView) {
        super(itemView);
        pickedphotoView = (DynamicHWimageview) itemView.findViewById(R.id.picked_photo_item);
        mimageButton = (ImageButton) itemView.findViewById(R.id.photo_clear);

    }
}
}

