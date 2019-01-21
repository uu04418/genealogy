package cn.com.gene.pojo;

import java.util.Date;

public class Integral {
    /** 积分主键*/
    private Long integralid;

    /** 用户ID*/
    private Long userid;

    /** 获得积分的数目*/
    private Long count;

    /** 积分事件*/
    private String event;

    /** 更新时间*/
    private Date updatetime;

    /** 类型 2-消耗 ， 1-收入*/
    private Integer state;

    /** 积分事件类型*/
    private Integer type;

    public Long getIntegralid() {
        return integralid;
    }

    public void setIntegralid(Long integralid) {
        this.integralid = integralid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
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