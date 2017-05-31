package test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;
import com.constant.Constant;
import com.facebook.drawee.view.SimpleDraweeView;

import com.httpThread.Image_Loader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class Test3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
       // ListView listView= (ListView) findViewById(R.id.testlistview);
        List list=new ArrayList();
        for(int i=0;i<40;i++){
            list.add(i);
        }
       // listView.setAdapter(new Adapter(list,this));
       /* Uri uri = Uri.parse("http://sqimg.qq.com/qq_product_operations/im/2016/pc/ay/mb65_b.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);*/
     /*   ImageView imageView= (ImageView) findViewById(R.id.imgs);
       // new Image_Loader().loadImagebyAsyncTask(imageView,"http://192.168.1.110:8080/clocle/user_photo/myphoto.png");
        //imageView.setImageBitmap();
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.t9)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage("http://192.168.1.110:8080/clocle/user_photo/reg.jpg", imageView, options);
    }*/
        RecyclerView view= (RecyclerView) findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
       // new Clocle_help_AsyncTask(null,null, this, view).execute(Constant.GET_HELP_JSON);
    }
    class Adapter extends BaseAdapter{
        private List list;
        private Context context;
        public Adapter(List list,Context context){
            this.list=list;
            this.context=context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view= LayoutInflater.from(context).inflate(R.layout.testlistview,parent,false);
            TextView textView= (TextView) view.findViewById(R.id.test_listview);
            textView.setText(getItem(position).toString());
            return view;
        }
    }
}