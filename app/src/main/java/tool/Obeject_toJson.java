package tool;

import com.bean.Pulish_bean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Obeject_toJson {
    private Gson mgson=new Gson();
    /**
     * 将发表的内容实体转化为json字符串
     */
    public String publish_tojson(Pulish_bean pulish_bean){
        String publish_Json_String =mgson.toJson(pulish_bean);
        return publish_Json_String;

    }
}
