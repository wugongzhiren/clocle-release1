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
        Toast.makeText(mcontext,"成功",Toast.LENGTH_SHORT).show();
        this.mcontext = mcontext;

    }

    /**
     * 此方法是在用户登录过一次的情况，将账号信息存在本地
     */
    public void saveUserinfo(String username,String password){
        //获取SharedPreferences对象，并且设置生成文件的权限为“本应用读取”
        share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        editor=share.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        Toast.makeText(mcontext,"用户信息已经保存",Toast.LENGTH_SHORT);
    }
    /**
     * 获取本地用户账号信息
     */
    public Map<String,String> getUserinfo(){
        Map<String,String> infomap=new HashMap<String, String>();
        share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        editor=share.edit();
        SharedPreferences share=mcontext.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        //share.getString 第二个参数是默认值
        infomap.put("user_name",share.getString("username",null));
        infomap.put("user_password",share.getString("password",null));
        return infomap;
    }
    /**
     * 判断用户有没有登录
     */
    public  Boolean isLogin(){
        Map<String,String> infomap=getUserinfo();
        if((infomap.get("user_name")!=null)||(infomap.get("password")!=null)){
            return true;
        }
        else{
            return false;
        }

    }

}
