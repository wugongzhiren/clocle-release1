package com.common_tool;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.ui.UpdateSelfInfo;

import tool.ShowToast;

/**
 * 安卓6.0以上系统危险权限管理类
 * Created by Administrator on 2016/12/8.
 */

public class PermissionManage {
    private static int MY_PERMISSIONS_REQUEST_CAMERA=0;
    /**
     * 判断是否具有某权限
     * 返回true:已经授权，false:没有授权
     */
    public static Boolean checkPermisson(Context context,String permission){
        int permissionCheck =ContextCompat.checkSelfPermission(context,
                permission);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            return true;
        }
       return false;
    }

    /**
     * 申请授权
     */
    public static void requestPermission(final Context context,String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,permission)) {
            //Show an expanation to the user asynchronously.说明请求原因,之前被用户拒绝过
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle("应用需要得到您的允许才能使用照相机奥！").setPositiveButton("我同意!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }).setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ShowToast.showToast(context, "相机开启失败");
                }
            }).show();
        } else {
            //第一次申请时，请求授权
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_CAMERA);
    }
}
