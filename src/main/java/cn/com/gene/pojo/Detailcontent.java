package cn.com.gene.pojo;

import java.util.Date;

public class Detailcontent {
    /** 发布详情的ID*/
    private Long detailcontentid;

    /** 发布类型的ID*/
    private Integer detailtypeid;

    /** 详情地址ID*/
    private Long detailid;

    /** 发布人 */
    private Long userid;

    /** 家族ID*/
    private Long genemessid;

    /** 1-正常，2-置顶没有支付，3-移除*/
    private Integer state;

    /** 发布内容*/
    private String detailcontent;

    /** 发布标题*/
    private String title;

    /** 图片*/
    private String picture;

    /** 创建时间*/
    private Date createtime;

    /** 更新时间*/
    private Date updatetime;

    public Long getDetailcontentid() {
        return detailcontentid;
    }

    public void setDetailcontentid(Long detailcontentid) {
        this.detailcontentid = detailcontentid;
    }

    public Integer getDetailtypeid() {
        return detailtypeid;
    }

    public void setDetailtypeid(Integer detailtypeid) {
        this.detailtypeid = detailtypeid;
    }

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGenemessid() {
        return genemessid;
    }

    public void setGenemessid(Long genemessid) {
        this.genemessid = genemessid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDetailcontent() {
        return detailcontent;
    }

    public void setDetailcontent(String detailcontent) {
        this.detailcontent = detailcontent == null ? null : detailcontent.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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