package cn.com.gene.pojo;

import java.util.Date;

public class Usersign {
    /** 签到主键*/
    private Long signid;

    /** 用户id*/
    private Long userid;

    /** 部落ID */
    private Long otherid;

    /** 签到总次数*/
    private Integer singcount;

    /** 最后一次签到时间*/
    private Date update_time;

    /** */
    private Integer type;

    public Long getSignid() {
        return signid;
    }

    public void setSignid(Long signid) {
        this.signid = signid;
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

    public Integer getSingcount() {
        return singcount;
    }

    public void setSingcount(Integer singcount) {
        this.singcount = singcount;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}