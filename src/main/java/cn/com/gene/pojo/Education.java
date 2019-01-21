package cn.com.gene.pojo;

public class Education {
    /** */
    private Integer educationid;

    /** */
    private String educationname;

    public Integer getEducationid() {
        return educationid;
    }

    public void setEducationid(Integer educationid) {
        this.educationid = educationid;
    }

    public String getEducationname() {
        return educationname;
    }

    public void setEducationname(String educationname) {
        this.educationname = educationname == null ? null : educationname.trim();
    }
}