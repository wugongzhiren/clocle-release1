package com.bean;

/**
 * 图片实体类
 * Created by Administrator on 2017/6/20.
 */

public class ImageInfo  {
    public String path;//图片路径
    public String name;//图片名(不包括后缀名)
    public int height;
    public int width;
    public String url;

    public ImageInfo() {
    }

    public ImageInfo(String path, String name, int height, int width) {
        this.path = path;
        this.name = name;
        this.height = height;
        this.width = width;
        this.url=path+name+".png";
    }

}
