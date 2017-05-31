package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**公开的个人服务信息
 * Created by Administrator on 2016/9/28.
 */
public class ServiceUser extends BmobObject {
    private Bmob_UserBean bmob_userBean;
    private BmobFile bmobFile;
    private String serviceContent;

    public Bmob_UserBean getBmob_userBean() {
        return bmob_userBean;
    }

    public void setBmob_userBean(Bmob_UserBean bmob_userBean) {
        this.bmob_userBean = bmob_userBean;
    }

    public BmobFile getBmobFile() {
        return bmobFile;
    }

    public void setBmobFile(BmobFile bmobFile) {
        this.bmobFile = bmobFile;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }
}
