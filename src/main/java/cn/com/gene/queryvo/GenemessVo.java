package cn.com.gene.queryvo;

import cn.com.gene.comm.PageQuery;

public class GenemessVo  {
	
	private PageQuery pagequery ;
	
	private GenemessCustomer genemessCustomer;
	
	private SurNameCustomer surNameCustomer;
	
	private AreaCustomer areaCustomer;
	
	private String hortorderby ;
	
	private Long genemessid;
	
	
	
	
	public Long getGenemessid() {
		return genemessid;
	}

	public void setGenemessid(Long genemessid) {
		this.genemessid = genemessid;
	}

	public String getHortorderby() {
		return hortorderby;
	}

	public void setHortorderby(String hortorderby) {
		this.hortorderby = hortorderby;
	}

	public AreaCustomer getAreaCustomer() {
		return areaCustomer;
	}

	public void setAreaCustomer(AreaCustomer areaCustomer) {
		this.areaCustomer = areaCustomer;
	}

	public SurNameCustomer getSurNameCustomer() {
		return surNameCustomer;
	}

	public void setSurNameCustomer(SurNameCustomer surNameCustomer) {
		this.surNameCustomer = surNameCustomer;
	}

	public PageQuery getPagequery() {
		return pagequery;
	}

	public void setPagequery(PageQuery pagequery) {
		this.pagequery = pagequery;
	}

	public GenemessCustomer getGenemessCustomer() {
		return genemessCustomer;
	}

	public void setGenemessCustomer(GenemessCustomer genemessCustomer) {
		this.genemessCustomer = genemessCustomer;
	}
	
	

}
