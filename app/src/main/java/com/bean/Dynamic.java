package com.bean;

import java.util.Date;

/**
 * 首页动态
 * Created by Administrator on 2017/7/11.
 */
public class Dynamic {
    private Long id;//动态ID
    private Long userID;//发表用户ID
    private String userName;//发表用户名
    private int sex;//发表用户性别
    private  String avatarUrl;//用户头像URL
    private String imgHttpUrlJson;//所有图片外链URLJSON字符串（最多9张）
    private  String content;//动态内容
    private int dFlag;//动态类型；1.文字，2.图文，3.广告。4.带视频。。。等
    private int commentCount;//评论数
    private int viewCount;//浏览数

    private int totalpages;//由服务器传回内容共多少页
    private String creatTime;//创建日期
    private String updateTime;//更新日期

    public Dynamic() {
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public Long getId() {
        return id;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getImgHttpUrlJson() {
        return imgHttpUrlJson;
    }

    public void setImgHttpUrlJson(String imgHttpUrlJson) {
        this.imgHttpUrlJson = imgHttpUrlJson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getdFlag() {
        return dFlag;
    }

    public void setdFlag(int dFlag) {
        this.dFlag = dFlag;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
