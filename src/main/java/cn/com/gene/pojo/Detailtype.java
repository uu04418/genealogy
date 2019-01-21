package cn.com.gene.pojo;

public class Detailtype {
    /** */
    private Integer detailtypeid;

    /** 发布类型名称*/
    private String typename;

    /** 对应的loge图标*/
    private String typeurl;

    /** 当前类型*/
    private Integer type;

    public Integer getDetailtypeid() {
        return detailtypeid;
    }

    public void setDetailtypeid(Integer detailtypeid) {
        this.detailtypeid = detailtypeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getTypeurl() {
        return typeurl;
    }

    public void setTypeurl(String typeurl) {
        this.typeurl = typeurl == null ? null : typeurl.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}