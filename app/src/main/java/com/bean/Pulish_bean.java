package com.bean;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Pulish_bean {


    private int help_id;//自增id
    private int userid;//对应用户表的id


    private String help_text;//发表的内容
    private String publish_date;//发表日期

    private int reward_money;//赏金

    public Pulish_bean(int help_id, int userid,
                        String help_text,
                       String publish_date,
                       int reward_money, String photo1,
                       String photo2, String photo3) {
        this.help_id = help_id;
        this.userid = userid;

        this.help_text = help_text;
        this.publish_date = publish_date;

        this.reward_money = reward_money;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public int getReward_money() {
        return reward_money;
    }
    public void setReward_money(int reward_money) {
        this.reward_money = reward_money;
    }
    private String photo1;//附带图片1
    private String photo2;//附带图片2
    private String photo3;//附带图片3

    public String getPhoto1() {
        return photo1;
    }
    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    public String getPhoto2() {
        return photo2;
    }
    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }
    public String getPhoto3() {
        return photo3;
    }
    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }
    public int getHelp_id() {
        return help_id;
    }
    public void setHelp_id(int help_id) {
        this.help_id = help_id;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }


    public String getHelp_text() {
        return help_text;
    }
    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }
    public String getPublish_date() {
        return publish_date;
    }
    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }


}
