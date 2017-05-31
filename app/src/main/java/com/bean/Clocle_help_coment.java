package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Clocle_help_coment extends BmobObject {
    private String coment;//评论内容
    private Bmob_UserBean commentUser;//评论者


    private String clocle_help_id;

    public Clocle_help_coment(String coment,Bmob_UserBean user,String clocle_help_id){
        this.coment=coment;
        this.commentUser=user;
        this.clocle_help_id=clocle_help_id;
    }
    public String getClocle_help_id() {
        return clocle_help_id;
    }

    public void setClocle_help_id(String clocle_help_id) {
        this.clocle_help_id = clocle_help_id;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Bmob_UserBean getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(Bmob_UserBean commentUser) {
        this.commentUser = commentUser;
    }
}
