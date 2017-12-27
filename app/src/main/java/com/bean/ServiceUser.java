package com.bean;

import com.clocle.huxiang.clocle.Bmob_UserBean;

import java.util.List;


/**圈圈帮的个人服务信息bean
 * Created by Administrator on 2016/9/28.
 */
public class ServiceUser  {
    private User user;//服务者
    private String serviceContent;//服务内容
    private List<String> imgs;//服务图片
    private String tags;//标签
    private List<String> judge;//评价
    private String judgeStar;//评价好评率

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getJudge() {
        return judge;
    }

    public void setJudge(List<String> judge) {
        this.judge = judge;
    }

    public String getJudgeStar() {
        return judgeStar;
    }

    public void setJudgeStar(String judgeStar) {
        this.judgeStar = judgeStar;
    }
}
