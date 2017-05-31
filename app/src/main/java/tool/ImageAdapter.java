package tool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ImageAdapter extends BaseAdapter {
    private String mDirPath;
    private List<String> mImgPaths;
    private LayoutInflater minflater;
    private Button button;

    private static Set<String> mSelectedImg = new HashSet<String>();
private List<String> list;





    public ImageAdapter(List<String> imgsUrl ,Context context, List<String> mDatas, String dirPath) {
        this.mDirPath = dirPath;
        this.mImgPaths = mDatas;
        minflater = LayoutInflater.from(context);
       this.list=imgsUrl;

    }

    @Override
    public int getCount() {
        return mImgPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.id_item_image);
            viewHolder.mSelect = (ImageButton) convertView.findViewById(R.id.id_item_select);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //重置状态
        viewHolder.mImg.setImageResource(R.mipmap.ic_launcher);
        viewHolder.mSelect.setImageResource(R.mipmap.select);
        viewHolder.mImg.setColorFilter(null);
        ImageLoder.getInstance(3, ImageLoder.Type.LIFO)
                .loadImage(mDirPath + "/" + mImgPaths.get(position), viewHolder.mImg);
        final String filePath = mDirPath + "/" + mImgPaths.get(position);

        if (list.size()>3){
            viewHolder.mImg.setClickable(false);
        }
        if(list.size()<4) {
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //已经被选择
                    if (mSelectedImg.contains(filePath)) {
                        if (list.contains(filePath)) {
                            list.remove(filePath);

                        }

                        mSelectedImg.remove(filePath);
                        viewHolder.mImg.setColorFilter(null);
                        viewHolder.mSelect.setImageResource(R.mipmap.select);
                    } else {//未被选择
                        //从imageview获取bitmap
                        //Bitmap bmp=((BitmapDrawable)viewHolder.mImg.getDrawable()).getBitmap();
                        list.add(filePath);

                        mSelectedImg.add(filePath);
                        viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
                        viewHolder.mSelect.setImageResource(R.mipmap.selected);
                    }
                    // notifyDataSetChanged();
                }
            });
        }
        /*if (mSelectedImg.contains(filePath)) {
            viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
            viewHolder.mSelect.setImageResource(R.mipmap.selected);
        }*/
        return convertView;
    }

    private class ViewHolder {
        ImageView mImg;
        ImageButton mSelect;
    }
}
