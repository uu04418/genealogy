package cn.com.gene.pojo;

import java.util.Date;

public class Enshrine {
    /** 收藏表主键*/
    private Long enshrineid;

    /** 被收藏人ID*/
    private Long followuserid;

    /** 用户ID*/
    private Long userid;

    /** 收藏物品主键*/
    private Long otherid;

    /** 标题【图片】*/
    private String title;

    /** 1-房源，2-出租，3-出售，等等*/
    private Integer type;

    /** 1-已经收藏，2-没有收藏*/
    private Integer state;

    /** 创建时间*/
    private Date createtime;

    /** */
    private Date updatetime;

    public Long getEnshrineid() {
        return enshrineid;
    }

    public void setEnshrineid(Long enshrineid) {
        this.enshrineid = enshrineid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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