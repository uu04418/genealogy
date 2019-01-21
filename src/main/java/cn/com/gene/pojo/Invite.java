package cn.com.gene.pojo;

public class Invite {
    /** */
    private Long inviteid;

    /** 当前用户ID*/
    private Long userid;

    /** 我邀请的人的ID*/
    private Long followuserid;

    /** 收益*/
    private Double account;

    /** 层级关系1-直接推荐2间接推荐*/
    private Integer level;

    public Long getInviteid() {
        return inviteid;
    }

    public void setInviteid(Long inviteid) {
        this.inviteid = inviteid;
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

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}