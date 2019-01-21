package cn.com.gene.pojo;

import java.util.Date;

public class Inteconn {
    /** 用户ID*/
    private Long userid;

    /** 金币总数*/
    private Long count;

    /** */
    private Date updatetime;

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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}