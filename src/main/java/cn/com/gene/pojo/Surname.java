package cn.com.gene.pojo;

public class Surname {
    /** */
    private Long surnameid;

    /** */
    private String detailname;

    public Long getSurnameid() {
        return surnameid;
    }

    public void setSurnameid(Long surnameid) {
        this.surnameid = surnameid;
    }

    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname == null ? null : detailname.trim();
    }
}