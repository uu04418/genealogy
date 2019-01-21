package cn.com.gene.pojo;

import java.util.Date;

public class Genemess {
    /** 家族主键*/
    private Long genemessid;

    /** */
    private Long surnameid;

    /** 发布人ID*/
    private Long profileid;

    /** 家谱名称*/
    private String genename;

    /** 图片*/
    private String picture;

    /** 地区编号*/
    private Long code;

    /** 家谱描述*/
    private String descname;

    /** 详情地址ID*/
    private Long detailid;

    /** 创建时间*/
    private Date createtime;

    /** 更新时间*/
    private Date updatetime;

    public Long getGenemessid() {
        return genemessid;
    }

    public void setGenemessid(Long genemessid) {
        this.genemessid = genemessid;
    }

    public Long getSurnameid() {
        return surnameid;
    }

    public void setSurnameid(Long surnameid) {
        this.surnameid = surnameid;
    }

    public Long getProfileid() {
        return profileid;
    }

    public void setProfileid(Long profileid) {
        this.profileid = profileid;
    }

    public String getGenename() {
        return genename;
    }

    public void setGenename(String genename) {
        this.genename = genename == null ? null : genename.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescname() {
        return descname;
    }

    public void setDescname(String descname) {
        this.descname = descname == null ? null : descname.trim();
    }

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
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