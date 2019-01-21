package cn.com.gene.pojo;

import java.util.Date;

public class Genemessconn {
    /** 家族ID*/
    private Long genemessid;

    /** 家族成员用户*/
    private Long profileid;

    /** 1-家族创建人，2-族谱人，3-外部人员*/
    private Integer state;

    /** 更新时间*/
    private Date updatetime;

    public Long getGenemessid() {
        return genemessid;
    }

    public void setGenemessid(Long genemessid) {
        this.genemessid = genemessid;
    }

    public Long getProfileid() {
        return profileid;
    }

    public void setProfileid(Long profileid) {
        this.profileid = profileid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}