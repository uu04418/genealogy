package cn.com.gene.pojo;

import java.util.Date;

public class Code {
    /** 验证码id*/
    private Long codeid;

    /** 手机号码*/
    private Long telephone;

    /** 验证码 */
    private String code;

    /** 开始时间*/
    private Date createtime;

    /** 结束时间*/
    private Date updatetime;

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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