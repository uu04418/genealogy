package cn.com.gene.pojo;

import java.math.BigDecimal;

public class Detailaddress {
    /** 详情地址ID*/
    private Long detailid;

    /** */
    private BigDecimal latitude;

    /** 经度*/
    private BigDecimal longitude;

    /** 详情地址*/
    private String detailes;

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDetailes() {
        return detailes;
    }

    public void setDetailes(String detailes) {
        this.detailes = detailes == null ? null : detailes.trim();
    }
}