package cn.com.gene.queryvo;

import cn.com.gene.pojo.Genemess;

public class GenemessCustomer extends Genemess {
	
	private Integer type;
	
	private Integer totalcount; 
	
	private Integer  pastcount;
	
	private String detailes;
	
	private String likecode;
	
	
	public String getLikecode() {
		return likecode;
	}

	public void setLikecode(String likecode) {
		this.likecode = likecode;
	}

	public String getDetailes() {
		return detailes;
	}

	public void setDetailes(String detailes) {
		this.detailes = detailes;
	}

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public Integer getPastcount() {
		return pastcount;
	}

	public void setPastcount(Integer pastcount) {
		this.pastcount = pastcount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	} 
	
	

}
