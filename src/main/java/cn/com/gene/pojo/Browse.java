package cn.com.gene.pojo;

import java.util.Date;

public class Browse {
    /** 主键*/
    private Long browseid;

    /** 被浏览人ID*/
    private Long followuserid;

    /** 浏览人ID*/
    private Long userid;

    /** 被浏览物品ID*/
    private Long otherid;

    /** 浏览总次数*/
    private Integer bcount;

    /** */
    private Integer type;

    /** */
    private Date updatetime;

    /** 1,正常，2停用*/
    private Integer statue;

    /** */
    private String title;

    public Long getBrowseid() {
        return browseid;
    }

    public void setBrowseid(Long browseid) {
        this.browseid = browseid;
    }

    public Long getFollowuserid() {
        return followuserid;
    }

    public void setFollowuserid(Long followuserid) {
        this.followuserid = followuserid;
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

    public Integer getBcount() {
        return bcount;
    }

    public void setBcount(Integer bcount) {
        this.bcount = bcount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}