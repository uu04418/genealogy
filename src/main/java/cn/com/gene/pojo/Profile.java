package cn.com.gene.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Profile {
    /** 用户信息表ID*/
    private Long profileid;

    /** user表主键*/
    private Long userid;

    /** */
    private BigDecimal longitude;

    /** */
    private BigDecimal latitude;

    /** 用户头像*/
    private String avatar;

    /** 用户昵称*/
    private String username;

    /** 证件号码*/
    private String identity;

    /** 真实姓名*/
    private String realname;

    /** 用户性别*/
    private String sex;

    /** 1-没有实名，2已经实名*/
    private Integer signstate;

    /** 微信账号*/
    private String weixinnub;

    /** 属相*/
    private String zodiac;

    /** 文化程度*/
    private Long educationid;

    /** 工作单位*/
    private String workunit;

    /** 职位*/
    private Long positionid;

    /** 生平简介*/
    private String introduction;

    /** 兄弟姐妹排行*/
    private Integer rankings;

    /** 更新时间*/
    private Date updatetime;

    /** */
    private Date birthday;

    public Long getProfileid() {
        return profileid;
    }

    public void setProfileid(Long profileid) {
        this.profileid = profileid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getSignstate() {
        return signstate;
    }

    public void setSignstate(Integer signstate) {
        this.signstate = signstate;
    }

    public String getWeixinnub() {
        return weixinnub;
    }

    public void setWeixinnub(String weixinnub) {
        this.weixinnub = weixinnub == null ? null : weixinnub.trim();
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac == null ? null : zodiac.trim();
    }

    public Long getEducationid() {
        return educationid;
    }

    public void setEducationid(Long educationid) {
        this.educationid = educationid;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit == null ? null : workunit.trim();
    }

    public Long getPositionid() {
        return positionid;
    }

    public void setPositionid(Long positionid) {
        this.positionid = positionid;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getRankings() {
        return rankings;
    }

    public void setRankings(Integer rankings) {
        this.rankings = rankings;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}