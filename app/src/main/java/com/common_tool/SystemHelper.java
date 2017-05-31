package com.common_tool;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import tool.DensityUtil;

/**
 * 系统帮助工具类
 * Created by Administrator on 2016/12/25.
 */

public class SystemHelper {

    /**
     * 获取设备宽度px单位
     * @param context
     * @return
     */
    public static int getScreenWidthPX(Context context)   {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int screenwidth = outMetrics.widthPixels;//获取屏幕宽度的像素值
        //int screenwidthDP = DensityUtil.px2dip(context,screenwidth);
        return screenwidth;
    }
    /**
     * 获取设备宽度dp单位
     * @param context
     * @return
     */
    public static int getScreenWidthDP(Context context)   {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int screenwidth = outMetrics.widthPixels;//获取屏幕宽度的像素值
        int screenwidthDP = DensityUtil.px2dip(context,screenwidth);
        return screenwidthDP;
    }
}
