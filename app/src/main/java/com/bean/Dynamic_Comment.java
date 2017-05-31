package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import cn.bmob.v3.BmobObject;

/**
 * 动态评论bean
 * Created by Administrator on 2016/11/21.
 */

public class Dynamic_Comment extends BmobObject {
    private Bmob_UserBean commentuser;

    public String getDynamicID() {
        return dynamicID;
    }

    public void setDynamicID(String dynamicID) {
        this.dynamicID = dynamicID;
    }

    private String dynamicID;
    private String comment;
    private Integer likes;

    public Bmob_UserBean getCommentuser() {
        return commentuser;
    }

    public void setCommentuser(Bmob_UserBean commentuser) {
        this.commentuser = commentuser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
