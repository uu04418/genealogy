package cn.com.gene.pojo;

public class Commoninte {
    /** */
    private Integer commonid;

    /** */
    private String typename;

    /** */
    private Long typecount;

    /** */
    private String typeword;

    public Integer getCommonid() {
        return commonid;
    }

    public void setCommonid(Integer commonid) {
        this.commonid = commonid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Long getTypecount() {
        return typecount;
    }

    public void setTypecount(Long typecount) {
        this.typecount = typecount;
    }

    public String getTypeword() {
        return typeword;
    }

    public void setTypeword(String typeword) {
        this.typeword = typeword == null ? null : typeword.trim();
    }
}