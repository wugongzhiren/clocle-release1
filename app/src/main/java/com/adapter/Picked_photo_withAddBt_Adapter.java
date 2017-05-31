package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bean.MyPhotoInfo;
import com.clocle.huxiang.clocle.Publish;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.facebook.drawee.view.SimpleDraweeView;
import com.view.DynamicHWSimpleDrawView;
import com.view.DynamicHWimageview;

import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * 附带添加图片的图片选择器的adapter
 * Created by Administrator on 2016/12/28.
 */

public class Picked_photo_withAddBt_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;

    private int marginleft;
    private int photoW;
    private int type0 = 0;//图片
    private int type1 = 1;//添加图片
    private List<PhotoInfo> urlList;//已经选择的图片信息
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback;

    public Picked_photo_withAddBt_Adapter(GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback, Context context, List<PhotoInfo> urlList) {
        this.urlList = urlList;
        this.mOnHanlderResultCallback = mOnHanlderResultCallback;
        inflater = LayoutInflater.from(context);
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).mimageButton.setVisibility(View.VISIBLE);
            //再次异步对临时图片进行压缩
            ((ViewHolder) holder).pickedphotoView.setImageBitmap(ImageFactory.getBitmap(urlList.get(position).getPhotoPath()));
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
        private DynamicHWimageview mimageview;

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

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public DynamicHWimageview pickedphotoView;
        public ImageButton mimageButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            pickedphotoView = (DynamicHWimageview) itemView.findViewById(R.id.picked_photo_item);
            //设置pickedphotoView的宽高一样，适应屏幕大小
            // Log.i("width" ,mcontext.getResources().getDisplayMetrics().widthPixels+"");
            //marginleft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mcontext.getResources().getDisplayMetrics());
            // Log.i("width1" ,marginleft+"");
            mimageButton = (ImageButton) itemView.findViewById(R.id.photo_clear);
            mimageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    urlList.remove((int) itemView.getTag());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
