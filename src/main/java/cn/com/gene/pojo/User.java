package cn.com.gene.pojo;

import java.util.Date;

public class User {
    /** 用户ID*/
    private Long userid;

    /** 电话号码*/
    private Long telephone;

    /** 登录验证token*/
    private String accesstoken;

    /** 手机物理地址*/
    private String phyAddress;

    /** 用户状态1-正常，2-已经死去*/
    private Integer state;

    /** 创建用户时间*/
    private Date createtime;

    /** 更新用户时间*/
    private Date updatetime;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    public String getPhyAddress() {
        return phyAddress;
    }

    public void setPhyAddress(String phyAddress) {
        this.phyAddress = phyAddress == null ? null : phyAddress.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}