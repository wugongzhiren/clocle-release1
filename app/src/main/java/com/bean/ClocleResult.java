package com.bean;

import java.util.List;

/**
 * http统一回复体
 * Created by Administrator on 2017/11/16.
 */

public class ClocleResult<T> {
    private String msg;
    private int code;
    private List<T> t;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getT() {
        return t;
    }

    public void setT(List<T> t) {
        this.t = t;
    }
}
