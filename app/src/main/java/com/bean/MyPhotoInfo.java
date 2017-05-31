package com.bean;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MyPhotoInfo extends PhotoInfo {
    private String filename;//包括后缀名

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
