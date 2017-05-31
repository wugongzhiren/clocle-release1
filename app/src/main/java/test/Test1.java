package test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.clocle.huxiang.clocle.R;



/**
 * Created by Administrator on 2016/7/31.
 */
public class Test1 extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        //启动会话界面

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
