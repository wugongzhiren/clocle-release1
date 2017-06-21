package tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**获取和保存用户登录信息
 * Created by Administrator on 2016/7/11.
 */
public class GetuserInfo {
    private Context mcontext;
    SharedPreferences share;
    SharedPreferences.Editor editor;
    public GetuserInfo(Context mcontext) {
        //Toast.makeText(mcontext,"成功",Toast.LENGTH_SHORT).show();
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
        Toast.makeText(mcontext,"用户信息已经保存",Toast.LENGTH_SHORT);
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
