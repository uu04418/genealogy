package cn.com.gene.queryvo;

import cn.com.gene.pojo.Detailcontent;

public class DetailCustomer extends Detailcontent{
	
	private String detailes;
	
	private String username;
	
	private String genename;
	
	private String realname;
	
	private String avatar;
	
	private String telephone;
	
	private Integer brocount ;
	
	private Integer messcount;
	
	private Integer zancount;
	
	private String typename;
	
	
	
	
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGenename() {
		return genename;
	}

	public void setGenename(String genename) {
		this.genename = genename;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getMesscount() {
		return messcount;
	}

	public void setMesscount(Integer messcount) {
		this.messcount = messcount;
	}

	public Integer getZancount() {
		return zancount;
	}

	public void setZancount(Integer zancount) {
		this.zancount = zancount;
	}

	public Integer getBrocount() {
		return brocount;
	}

	public void setBrocount(Integer brocount) {
		this.brocount = brocount;
	}

	public String getDetailes() {
		return detailes;
	}

	public void setDetailes(String detailes) {
		this.detailes = detailes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
