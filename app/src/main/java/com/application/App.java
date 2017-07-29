package com.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;


/*import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.wxlib.util.SysUtil;*/
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.constant.Constant;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.http.ToStringConverterFactory;






import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/26.
 */
public class App extends Application {
    private OkHttpClient okHttpClient;
    private static Context context;
    private static Bmob_UserBean bean;
private static Gson gson;
String testtoken="0GxyEvHDQqKuu9T+21VAScouCHXzYZWucMXTL4Cxm0sdLCBYt4wPAcjEhK+6xfzKUJ55QYfUThDvGO6JlAERqQkK28hF1GpB";
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        //阿里OSS初始化
        //AliOss.init(context);
        //Configuration config = new Configuration.Builder().build();
              //  .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
              ////  .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
              //  .connectTimeout(10) // 链接超时。默认10秒
              //  .responseTimeout(60) // 服务器响应超时。默认60秒
             //   .recorder(recorder)  // recorder分片上传时，已上传片记录器。默认null
             //   .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
              //  .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
             //   .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        //UploadManager uploadManager = new UploadManager(config);
/*RongIM.connect(testtoken, new RongIMClient.ConnectCallback() {
    @Override
    public void onTokenIncorrect() {

    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(getApplicationContext(),"连接融云服务器成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(RongIMClient.ErrorCode errorCode) {

    }
});*/
     /*   bean = new Bmob_UserBean();
        bean.setObjectId("fa02b47eb3");*/
        context = getApplicationContext();
        //fresco初始化
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(this, config);


        //ImageLoad配置
      /*  ImageLoaderConfiguration config1 = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config1);*/
    }

    public static Context getContext() {
        return context;
    }

    public static Bmob_UserBean getuser() {
        return bean;
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(Constant.BASEURL)
                .build();
        return retrofit;
    }
    public static Gson getGson(){
        if(gson==null){
            return new Gson();
        }
        else {
            return gson;
        }
    }

}
