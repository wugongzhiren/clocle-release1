package com.bean;

/**
 * messages包含连接用户表的用户信息
 */
public class Messages {
    private int help_id;
    private int user_id;
    private String name;// 昵称
    private String message;// 消息内容
    private String pic;// 头像的网络路径
    private String school;// 学校
    private String publish_time;//发表时间
    private String sex;//性别
    private String tag;//标签
    private int reward;//报酬
    private String img1;//图片1路径
    private String img2;//图片2路径
    private String img3;//图片3路径
    private int CommentCount;//评论数

    public Messages(int help_id, int user_id, String name, String message, String pic, String school, String publish_time, String sex, String tag, int reward, String img1, String img2, String img3, int commentCount) {
        this.help_id = help_id;
        this.user_id = user_id;
        this.name = name;
        this.message = message;
        this.pic = pic;
        this.school = school;
        this.publish_time = publish_time;
        this.sex = sex;
        this.tag = tag;
        this.reward = reward;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        CommentCount = commentCount;
    }

    public int getHelp_id() {
        return help_id;
    }

    public void setHelp_id(int help_id) {
        this.help_id = help_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }
}
