package com;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Base_activity extends AppCompatActivity implements View.OnClickListener {
    public Context mcontext=this;
    /**
     * 初始化状态栏相关，
     * PS: 设置全屏需要在调用super.onCreate(arg0);之前设置setIsFullScreen(true);否则在Android 6.0下非全屏的activity会出错;
     * SDK19：可以设置状态栏透明，但是半透明的SYSTEM_BAR_BACKGROUNDS会不好看；
     * SDK21：可以设置状态栏颜色，并且可以清除SYSTEM_BAR_BACKGROUNDS，但是不能设置状态栏字体颜色（默认的白色字体在浅色背景下看不清楚）；
     * SDK23：可以设置状态栏为浅色（SYSTEM_UI_FLAG_LIGHT_STATUS_BAR），字体就回反转为黑色。
     * 为兼容目前效果，仅在SDK23才显示沉浸式。
     */
    private void initStatusBar() {
        Window win = getWindow();
        if (false) {
            win.requestFeature(Window.FEATURE_NO_TITLE);
            win.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
            win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 保持屏幕高亮
        } else {
            //KITKAT也能满足，只是SYSTEM_UI_FLAG_LIGHT_STATUS_BAR（状态栏字体颜色反转）只有在6.0才有效
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
                // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
                win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                // 部分机型的statusbar会有半透明的黑色背景
                win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                win.setStatusBarColor(Color.TRANSPARENT);// SDK21

               Boolean isStatusBarTranslate = true;
            }
        }
    }
    protected void hideStatus(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
