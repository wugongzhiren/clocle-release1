package com.clocle.huxiang.clocle;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;


public class Bmob_UserBean extends BmobUser {
   private BmobFile userphoto;
    private String photoUrl;
    private String sex;
    private String school;
    private String birthdate;
    private String signature;
    private Integer score;
    private ArrayList<String> albumUrl;//相册图片路径

    public ArrayList<String> getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(ArrayList<String> albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getClocleMoney() {
        return clocleMoney;
    }

    public void setClocleMoney(Integer clocleMoney) {
        this.clocleMoney = clocleMoney;
    }

    private Integer clocleMoney;

    public String getphotoUrl() {
        return photoUrl;
    }

    public void setphotoUrl(String photoUrl) {
        this.photoUrl =photoUrl;
    }

    public BmobFile getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(BmobFile userphoto) {
        this.userphoto = userphoto;
    }
}
