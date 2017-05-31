package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**动态类
 * Created by Administrator on 2016/7/16.
 */
public class Dynamic extends BmobObject{
    private Bmob_UserBean user;
    private Integer commentCount;//评论数
    private Integer views;//浏览数
    private List<String> imgs;//附带图

    public Bmob_UserBean getUser() {
        return user;
    }

    public void setUser(Bmob_UserBean user) {
        this.user = user;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    private String dynamicContent;//发表内容
}
