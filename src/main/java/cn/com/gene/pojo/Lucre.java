package cn.com.gene.pojo;

import java.util.Date;

public class Lucre {
    /** 收益主键*/
    private Long lucreid;

    /** 收益描述*/
    private String descname;

    /** 收益金额*/
    private Double account;

    /** 收益人ID*/
    private Long userid;

    /** 收益时间*/
    private Date updatetime;

    public Long getLucreid() {
        return lucreid;
    }

    public void setLucreid(Long lucreid) {
        this.lucreid = lucreid;
    }

    public String getDescname() {
        return descname;
    }

    public void setDescname(String descname) {
        this.descname = descname == null ? null : descname.trim();
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}