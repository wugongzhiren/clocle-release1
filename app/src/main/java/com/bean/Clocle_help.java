package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Clocle_help extends BmobObject{
    private String content;
    private Integer peopleNum;
    private Integer sum_clocle_money;
    private Bmob_UserBean bmob_userBean;
    private List<String > imgs;
    private String tag;

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

    public Bmob_UserBean getBmob_userBean() {
        return bmob_userBean;
    }

    public void setBmob_userBean(Bmob_UserBean bmob_userBean) {
        this.bmob_userBean = bmob_userBean;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
