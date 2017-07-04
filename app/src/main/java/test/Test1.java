package test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;
import com.view.rv.NumAdapter;
import com.view.rv.SWPullRecyclerLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/31.
 */
public class Test1 extends Activity{
    private SWPullRecyclerLayout recycler;
    private View header;
    private View footer;
    private AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        //启动会话界面
        //初始化列表页面
       /* recycler = (SWPullRecyclerLayout) findViewById(R.id.recycler);
        //recycler.setItemDivider(true);
        appBarLayout = (AppBarLayout) findViewById(R.id.layout_appbar);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + 1 + "");
        }
        //初始化head和footerview
        header =  LayoutInflater.from(this).inflate(R.layout.header, null);
        footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        //footer.setT;
        //将head和foot添加到listview
        recycler.addHeaderView(header, 100);
        recycler.addFooterView(footer, 100);
        NumAdapter adapter = new NumAdapter(this, list);
        recycler.setMyRecyclerView(new LinearLayoutManager(this), adapter);*/
        //recycler.addOnTouchUpListener(this);
      /*//  ImageView myimag= (ImageView) findViewById(R.id.imgs);
        Bitmap map= BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory().getAbsolutePath().toString()+"/clocle_img/"+"myphoto.png");
       // myimag.setImageBitmap(map);
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient  = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        final String url = "http://192.168.1.104:8080/clocle/servlet/File_Upload";
        File file = new File(Environment
                .getExternalStorageDirectory().getAbsolutePath().toString()+"/clocle_img/"+"myphoto.png");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "myphoto.png", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "uploadMultiFile() e=" + e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "uploadMultiFile() response=" + response.body().string());
            }
        });
    }*/
}}
