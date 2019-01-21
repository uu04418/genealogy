package cn.com.gene.pojo;

import java.util.Date;

public class Fans {
    /** 粉丝ID*/
    private Long fansid;

    /** 用户ID*/
    private Long userid;

    /** 被关注人/事物*/
    private Long otherid;

    /** 修改时间*/
    private Date updatetime;

    /** 1:关注,0:取消关注*/
    private Integer state;

    /** 1- 氏族*/
    private Integer type;

    public Long getFansid() {
        return fansid;
    }

    public void setFansid(Long fansid) {
        this.fansid = fansid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getOtherid() {
        return otherid;
    }

    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}