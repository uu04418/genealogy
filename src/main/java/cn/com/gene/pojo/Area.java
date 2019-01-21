package cn.com.gene.pojo;

public class Area {
    /** 主ID*/
    private Integer id;

    /** 城市编号唯一标识*/
    private Long code;

    /** 上级code*/
    private Long parentId;

    /** 地址名称*/
    private String name;

    /** 层级1-4*/
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}