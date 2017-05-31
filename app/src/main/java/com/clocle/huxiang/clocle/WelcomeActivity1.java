package com.clocle.huxiang.clocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tool.GetuserInfo;

/**
 * Created by Administrator on 2016/7/4.
 */
public class WelcomeActivity1 extends Activity {
    private final long SPLASH_LENGTH = 1000;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        //假如延迟的时间非常短，还没判断完就跳转到了loginacticity怎么办
        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转

            public void run() {
                GetuserInfo getuserInfo=new GetuserInfo(WelcomeActivity1.this);

                //Toast.makeText(WelcomeActivity1.this,infomap.get("user_name"),Toast.LENGTH_SHORT).show();
                if(getuserInfo.isLogin()){
                    Intent intent = new Intent(WelcomeActivity1.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent1 = new Intent(WelcomeActivity1.this, Login.class);
                    startActivity(intent1);
                }

                finish();
            }
        }, SPLASH_LENGTH);//1秒后跳转至应用主界面MainActivity

    }
}
