package com.bean;

/**用户信息类，字段和数据库中对应
 * Created by Administrator on 2016/7/16.
 */
public class User {
    private String nickname;//昵称
    private String password;
    private String phonenumber;//用手机号码登录
    private String school;//学校
    private String birth;
    private String sex;
    private String signature;//个性签名
    private String starsign;//星座

    public User(String nickname, String password, String phonenumber, String school,
                String birth, String sex, String signature, String starsign) {
        this.nickname = nickname;
        this.password = password;
        this.phonenumber = phonenumber;
        this.school = school;
        this.birth = birth;
        this.sex = sex;
        this.signature = signature;
        this.starsign = starsign;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStarsign() {
        return starsign;
    }

    public void setStarsign(String starsign) {
        this.starsign = starsign;
    }
}
