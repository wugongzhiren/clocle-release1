package com.bean;

import java.util.List;


/**
 * 圈圈求助内容bean
 * Created by Administrator on 2016/9/19.
 */
public class Clocle_help {
    private String content;//发布内容
    private Integer peopleNum;//限制人数
    private Integer sum_clocle_money;//悬赏总额
    private User user;//发布用户
    private List<String> imgs;//图片路径
    private String tag;//标签

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getSum_clocle_money() {
        return sum_clocle_money;
    }

    public void setSum_clocle_money(Integer sum_clocle_money) {
        this.sum_clocle_money = sum_clocle_money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
