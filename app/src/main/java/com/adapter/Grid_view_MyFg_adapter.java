package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.BaseViewHolder;
import com.clocle.huxiang.clocle.R;

/**
 * Created by Administrator on 2016/12/11.
 */

public class Grid_view_MyFg_adapter extends BaseAdapter {
    private Context context;
    public String[] img_text = { "分享", "圈圈帮", "跳蚤市场", "我要充值", "积分中心", "反馈申诉"
            };
    public int[] imgs = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    public Grid_view_MyFg_adapter(Context context){
        super();
        this.context = context;
    }
    @Override
    public int getCount() {
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.grid_view_item_myfg, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);

        tv.setText(img_text[position]);
        return convertView;
    }
}
