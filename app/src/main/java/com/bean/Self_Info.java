package com.bean;

/**
 * Created by Administrator on 2016/7/18.
 */
public class Self_Info {
    private String leftinfo;
    private String centerinfo;

    public Self_Info(String leftinfo, String centerinfo) {
        this.leftinfo = leftinfo;
        this.centerinfo = centerinfo;
    }

    public String getLeftinfo() {
        return leftinfo;
    }

    public void setLeftinfo(String leftinfo) {
        this.leftinfo = leftinfo;
    }

    public String getCenterinfo() {
        return centerinfo;
    }

    public void setCenterinfo(String centerinfo) {
        this.centerinfo = centerinfo;
    }
}
