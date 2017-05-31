package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/1/11.
 */

public class Clocle_pro_service extends BmobObject{
    private String imgUrl;
    private Bmob_UserBean bean;
    private String tag;
    private String rewardType;
    private Integer rewardNum;
    private String content;
private String contentDetail;

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Bmob_UserBean getBean() {
        return bean;
    }

    public void setBean(Bmob_UserBean bean) {
        this.bean = bean;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(Integer rewardNum) {
        this.rewardNum = rewardNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
