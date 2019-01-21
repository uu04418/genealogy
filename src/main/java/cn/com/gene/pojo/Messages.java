package cn.com.gene.pojo;

import java.util.Date;

public class Messages {
    /** 消息ID*/
    private Long messagesid;

    /** 房屋id*/
    private Long otherid;

    /** 用户id*/
    private Long userid;

    /** 1-房源，2-出租，3-出售*/
    private Integer type;

    /** 消息状态1-正常，2移除*/
    private Integer state;

    /** 留言内容*/
    private String messcontent;

    /** */
    private Date createtime;

    /** */
    private Date updatetime;

    public Long getMessagesid() {
        return messagesid;
    }

    public void setMessagesid(Long messagesid) {
        this.messagesid = messagesid;
    }

    public Long getOtherid() {
        return otherid;
    }

    public void setOtherid(Long otherid) {
        this.otherid = otherid;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMesscontent() {
        return messcontent;
    }

    public void setMesscontent(String messcontent) {
        this.messcontent = messcontent == null ? null : messcontent.trim();
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