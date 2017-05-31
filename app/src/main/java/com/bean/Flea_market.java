package com.bean;

import android.text.Editable;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 跳蚤市场model，和数据库字段对应
 * Created by Administrator on 2016/10/12.
 */
public class Flea_market extends BmobObject {
    private Bmob_UserBean flea_user;//发布者
    private String flea_content;//附带说明内容
    private List<String> flea_imgs;//二手货图片
    private String flea_type;//分类
    private Integer views;//浏览数
    private Integer follows;//评论人数
    private Integer flea_money;//报价
    private Integer viewType;//标识listview的布局类型

    public Bmob_UserBean getFlea_user() {
        return flea_user;
    }

    public String getFlea_type() {
        return flea_type;
    }

    public void setFlea_type(String flea_type) {
        this.flea_type = flea_type;
    }

    public void setFlea_user(Bmob_UserBean flea_user) {
        this.flea_user = flea_user;
    }

    public String getFlea_content() {
        return flea_content;
    }

    public void setFlea_content(String flea_content) {
        this.flea_content = flea_content;
    }

    public List<String> getFlea_imgs() {
        return flea_imgs;
    }

    public void setFlea_imgs(List<String> flea_imgs) {
        this.flea_imgs = flea_imgs;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getFlea_money() {
        return flea_money;
    }

    public void setFlea_money(Integer flea_money) {
        this.flea_money = flea_money;
    }
}
