package com.adapter;

/**
 * Created by Administrator on 2016/10/17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/1 下午8:42
 */
public class ChoosePhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PhotoInfo> mList;
    private LayoutInflater mInflater;
    private int mScreenWidth;
    private int type0 = 0;//图片
    private int type1 = 1;//添加图片
    private GalleryFinal.OnHanlderResultCallback callback;

    public ChoosePhotoListAdapter(Activity activity, List<PhotoInfo> list,GalleryFinal.OnHanlderResultCallback callback) {
        this.mList = list;
        this.mInflater = LayoutInflater.from(activity);
        this.mScreenWidth = DeviceUtils.getScreenPix(activity).widthPixels;
        this.callback=callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == type0) {
            View itemview = mInflater.inflate(R.layout.picked_photo_item, parent, false);

            return new ViewHolder(itemview);
        } else {
            View itemview = mInflater.inflate(R.layout.addphoto, parent, false);
            return new ViewHolderWithAddPhoto(itemview);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        if (holder instanceof ViewHolder) {


            ((ViewHolder) holder).mimageButton.setVisibility(View.VISIBLE);

            //RxJava异步压缩图片
            //1.创建被观察者
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    //发出事件,即将图片URL发给订阅者
                    subscriber.onNext(mList.get(position).getPhotoPath());
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

                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onNext(Bitmap bitmap) {
                            //设置图片到view
                            ((ViewHolder) holder).pickedphotoView.setImageBitmap(bitmap);
                        }
                    });

           // ((ViewHolder) holder).pickedphotoView.setImageURI("file://" + Uri.parse(mList.get(position).getPhotoPath()));
        } else if (holder instanceof ViewHolderWithAddPhoto) {
            ((ViewHolderWithAddPhoto) holder).mimageview.setImageResource(R.mipmap.addphoto);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (mList.size() - 1)) {
            return type1;
        } else {
            return type0;
        }
    }

   /*
        SimpleDraweeView ivPhoto = (SimpleDraweeView) mInflater.inflate(R.layout.single_img, null);
        setHeight(ivPhoto);

        PhotoInfo photoInfo = mList.get(position);
        ivPhoto.setImageURI("file://" + Uri.parse(photoInfo.getPhotoPath()));
        return ivPhoto;


    private void setHeight(final View convertView) {
        int height = mScreenWidth / 3 - 8;
        convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }*/

    class ViewHolderWithAddPhoto extends RecyclerView.ViewHolder {
        private ImageView mimageview;

        public ViewHolderWithAddPhoto(View itemView) {
            super(itemView);
            mimageview = (ImageView) itemView.findViewById(R.id.addphoto);
            //int width = mimageview.getLayoutParams().width = (getResources().getDisplayMetrics().widthPixels - 15) / 4;
           // mimageview.getLayoutParams().height = width;
            mimageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FunctionConfig config = new FunctionConfig.Builder()
                            .setMutiSelectMaxSize(8)
                            .setEnablePreview(true)
                            .setEnableCamera(true)

                    .setSelected(mList)
                            .build();
                    GalleryFinal.openGalleryMuti(1001, config, callback);
//                    Intent intent = new Intent(Publish.this, Test_imageloader.class);
  //                  startActivityForResult(intent, 401);
                }
            });

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pickedphotoView;
        public ImageButton mimageButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            pickedphotoView = (ImageView) itemView.findViewById(R.id.picked_photo_item);
            //设置pickedphotoView的宽高一样，适应屏幕大小
            //int photoW;
            //photoW = pickedphotoView.getLayoutParams().width = (getResources().getDisplayMetrics().widthPixels - 15) / 4;
           // pickedphotoView.getLayoutParams().height = photoW;
            // Log.i("width" ,mcontext.getResources().getDisplayMetrics().widthPixels+"");
            //marginleft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mcontext.getResources().getDisplayMetrics());
            // Log.i("width1" ,marginleft+"");
            mimageButton = (ImageButton) itemView.findViewById(R.id.photo_clear);
            mimageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove((int) itemView.getTag());
                    notifyDataSetChanged();
                }
            });
        }
    }
}