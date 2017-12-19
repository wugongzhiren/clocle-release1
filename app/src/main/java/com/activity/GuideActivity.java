package com.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.view.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 向导页，app首次启动的时候才显示
 */
public class GuideActivity extends AppCompatActivity {
private NoSlideViewPager viewPager;
private LinearLayout layout_viewpager_indicator;//指示器
    private FrameLayout frameLayout;
    private int currentPage;//当前显示页，记录指示器的位置
    private List<ImageView> indicatorImageview=new ArrayList<>();
private int[] drawables=new int[]{R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager= (NoSlideViewPager) findViewById(R.id.content);
        layout_viewpager_indicator= (LinearLayout) findViewById(R.id.layout_viewpager_indicator);//指示器
        frameLayout= (FrameLayout) findViewById(R.id.layout_viewager_content);//父容器
        viewPager.setAdapter(new pagerAdapter());
        //去掉滑到边缘的光晕效果
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicatorImageview.get(currentPage).setBackgroundResource(R.drawable.icon_point);
                indicatorImageview.get(position).setBackgroundResource(R.drawable.icon_point_pre);
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置指示器
        initIndicator();
    }

    /**
     * viewpager指示器
     */
    private void initIndicator() {
        currentPage=0;
for(int i=0;i<drawables.length;i++){
    //创建指示器的Imageview
ImageView imageView=new ImageView(this);
    indicatorImageview.add(imageView);
    if(i==0){
        imageView.setBackgroundResource(R.drawable.icon_point_pre);
    }
    else {
        imageView.setBackgroundResource(R.drawable.icon_point);
    }
    layout_viewpager_indicator.addView(imageView);
    //imageView.set
}
    }

    private class pagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return drawables.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(position!=(drawables.length-1)) {
                ImageView view = ImageFactory.getImageViewWithImage(GuideActivity.this, drawables[position]);
                container.addView(view);
                return view;
            }
            else{
                RelativeLayout layout =new RelativeLayout(GuideActivity.this);
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ImageView view = ImageFactory.getImageViewWithImage(GuideActivity.this, drawables[position]);
                Button button=new Button(GuideActivity.this);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(320,100);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.setMargins(0,0,0,70);
                button.setLayoutParams(lp);
                button.setText("开启圈圈之旅");
                //button.setGravity(Gravity.BOTTOM);
                layout.addView(view);
                layout.addView(button);
                container.addView(layout);
                return layout;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
