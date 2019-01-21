package cn.com.gene.pojo;

import java.util.Date;

public class Consume {
    /** 充值或者消费ID*/
    private Long consumeid;

    /** 记录的用户ID即是VIP的ID */
    private Long userid;

    /** 1消费， 2 充值 */
    private Integer type;

    /** 充值或者消费金额*/
    private Double monetary;

    /** 描述*/
    private String descname;

    /** 记录消费*/
    private Date updatetime;

    /** 记录充值时间*/
    private Date createtime;

    public Long getConsumeid() {
        return consumeid;
    }

    public void setConsumeid(Long consumeid) {
        this.consumeid = consumeid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getMonetary() {
        return monetary;
    }

    public void setMonetary(Double monetary) {
        this.monetary = monetary;
    }

    public String getDescname() {
        return descname;
    }

    public void setDescname(String descname) {
        this.descname = descname == null ? null : descname.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}