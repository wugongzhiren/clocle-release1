package com.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 * Created by Hodo on 2017/12/18.
 */

public class SharedPreferencesUtils {
    private Context mcontext;
    SharedPreferences share;
    SharedPreferences.Editor editor;
    public SharedPreferencesUtils(Context mcontext) {
        this.mcontext = mcontext;
    }
    /**
     * 此方法是在用户登录过一次的情况，将账号ID存在本地
     */
    public void saveUserinfo(long userID){
        //获取SharedPreferences对象，并且设置生成文件的权限为“本应用读取”
        share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        editor=share.edit();
        editor.putLong("userID",userID);
        //editor.putString("password",password);
        editor.commit();
    }
    /**
     * 获取本地用户账号
     */
    public long getUserinfo(){
        share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        editor=share.edit();
        //SharedPreferences share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        //share.getString 第二个参数是默认值
        return share.getLong("userID",100l);
    }
    /**
     * 判断用户有没有登录
     */
    public  Boolean isLogin(){
        long id=getUserinfo();
        if(id!=100l){
            return true;
        }
        else{
            return false;
        }

    }
}
