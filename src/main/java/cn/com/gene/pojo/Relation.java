package cn.com.gene.pojo;

public class Relation {
    /** */
    private Long relationid;

    /** 关系名称*/
    private String relationname;

    public Long getRelationid() {
        return relationid;
    }

    public void setRelationid(Long relationid) {
        this.relationid = relationid;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname == null ? null : relationname.trim();
    }
}