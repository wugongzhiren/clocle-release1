package com.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;
import com.view.ninegrid.NineGridTestLayout;

/**
 * 求帮助的holder
 * Created by Administrator on 2017/11/20.
 */

public class Clocle_help_Holder extends RecyclerView.ViewHolder{
    public ImageView avartarImage;//头像
    public TextView contentText;//发布内容
    public TextView nicknameText;//昵称
    public NineGridTestLayout images;//可选图片
    public TextView moneyText;//显示金额
    public Clocle_help_Holder(View itemView) {
        super(itemView);

        avartarImage= (ImageView) itemView.findViewById(R.id.user_photo);
        contentText= (TextView) itemView.findViewById(R.id.clocle_content);
        nicknameText= (TextView) itemView.findViewById(R.id.nickname);
        images= (NineGridTestLayout) itemView.findViewById(R.id.imgs);
        moneyText= (TextView) itemView.findViewById(R.id.money);

    }
}
