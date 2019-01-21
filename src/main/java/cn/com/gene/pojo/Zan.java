package cn.com.gene.pojo;

import java.util.Date;

public class Zan {
    /** 主键ID*/
    private Long zanid;

    /** user表ID*/
    private Long userid;

    /** */
    private Long followuserid;

    /** qiuzu表ID*/
    private Long otherid;

    /** 1-房源，2-出租，3-出售，4-其他*/
    private Integer type;

    /** 1:点赞;0:取消点赞*/
    private Integer state;

    /** */
    private String title;

    /** 创建时间*/
    private Date createtime;

    /** 修改时间*/
    private Date updatetime;

    public Long getZanid() {
        return zanid;
    }

    public void setZanid(Long zanid) {
        this.zanid = zanid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFollowuserid() {
        return followuserid;
    }

    public void setFollowuserid(Long followuserid) {
        this.followuserid = followuserid;
    }

    public Long getOtherid() {
        return otherid;
    }

    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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