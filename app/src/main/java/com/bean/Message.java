package com.bean;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/9.
 * 这个类是首页动态的bean类
 */
public class Message {
    private String name;//昵称
    private String message;//消息内容
    private int pic;//头像


    private String self_info;//时间+学校


    public String getSelf_info() {
        return self_info;
    }

    public void setSelf_info(String self_info) {
        this.self_info = self_info;
    }


    public Message(String name, String message, int pic, String self_info) {
        this.name = name;
        this.message = message;
        this.pic = pic;
        this.self_info = self_info;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }


}
