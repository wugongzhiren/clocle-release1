package com.bean;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Index_list_bean {
    public Index_list_bean(int icon, String menuName) {
        this.icon = icon;
        this.menuName = menuName;
    }

    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    private String menuName;
}
