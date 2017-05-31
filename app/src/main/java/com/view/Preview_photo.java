package com.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.ArrayList;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * 图片预览界面
 * Created by Administrator on 2016/9/11.
 */
public class Preview_photo extends Activity {
    private ViewPager viewPager;
    private TextView index_tv;
    private ArrayList<String> urlList;
    private PhotoDraweeView photoDraweeView;
    private PhotoDraweeView[] photoDraweeViews;
public int imgPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_photo_layout);
        index_tv= (TextView) findViewById(R.id.preview_index);
        Intent intent=getIntent();
       urlList= intent.getStringArrayListExtra("urlList");
        imgPosition=intent.getIntExtra("position",0);
        index_tv.setText(imgPosition+1+"/"+urlList.size());
        photoDraweeViews = new PhotoDraweeView[urlList.size()];
        viewPager = (ViewPager) findViewById(R.id.preview_photo);
        //viewpager滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index_tv.setText(position+1+"/"+urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urlList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                photoDraweeView = new PhotoDraweeView(Preview_photo.this);
                PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
                controller.setUri(Uri.parse(urlList.get(position)));
                //controller.setOldController(photoDraweeView.getController());
                controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        if (imageInfo == null || photoDraweeView == null) {
                            return;
                        }
                        photoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
                    }
                });
                photoDraweeView.setController(controller.build());

                photoDraweeViews[position] = photoDraweeView;
                container.addView( photoDraweeViews[position]);
                return  photoDraweeViews[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(photoDraweeViews[position]);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        viewPager.setCurrentItem(imgPosition);
    }

}
