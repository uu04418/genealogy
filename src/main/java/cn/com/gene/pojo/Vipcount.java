package cn.com.gene.pojo;

import java.util.Date;

public class Vipcount {
    /** 关联User表的ID一对一*/
    private Long userid;

    /** 统计可以预览房源的记录默认是5次*/
    private Integer count;

    /** 1表示VIP，0表示普通用户*/
    private Integer isvip;

    /** */
    private Date createtime;

    /** 可用余额*/
    private Double balance;

    /** 收益总额*/
    private Double account;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }
}