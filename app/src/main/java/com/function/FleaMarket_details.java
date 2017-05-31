package com.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Base_activity;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/16.
 */
public class FleaMarket_details extends Base_activity {
    public LinearLayout imgsLiner;
    private SimpleDraweeView photo;
    private TextView nickname;
    private ImageView msex;
    private TextView mcontent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flea_market_details_layout);
        initviews();
        Intent intent = getIntent();

        String photourl = intent.getStringExtra("photourl");
        photo.setImageURI(photourl);

        String name = intent.getStringExtra("username");
        nickname.setText(name);

        String sex = intent.getStringExtra("sex");
       /* if(sex.equals("男")){
            msex.setImageResource(R.mipmap.man);
        }
        else {
            msex.setImageResource(R.mipmap.woman);
        }*/

        String content=intent.getStringExtra("content");
        mcontent.setText(content);

        ArrayList<String> imgs=intent.getStringArrayListExtra("imgs");
        imgsLiner = (LinearLayout) findViewById(R.id.flea_imgs_details);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SimpleDraweeView imgsview;
        for (int i = 0; i < imgs.size(); i++) {
            imgsview = new SimpleDraweeView(this);
            imgsview.setImageURI(imgs.get(i));
            imgsview.setLayoutParams(lp);
            imgsLiner.addView(imgsview);
        }

    }

    private void initviews() {
        photo = (SimpleDraweeView) findViewById(R.id.flea_details_photo);
        nickname = (TextView) findViewById(R.id.flea_details_nickname);
        msex = (ImageView) findViewById(R.id.flea_detais_sex);
        mcontent= (TextView) findViewById(R.id.flea_details_content);
    }
}
