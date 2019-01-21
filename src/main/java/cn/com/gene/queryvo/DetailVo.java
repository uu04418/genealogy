package cn.com.gene.queryvo;

import cn.com.gene.comm.PageQuery;

public class DetailVo {
	
	private PageQuery pagequery;
	
	private DetailTypeCustomer detailTypeCustomer;
	
	private DetailContentCustomer detailContentCustomer;
	
	private GenemessCustomer genemessCustomer;
	

	public GenemessCustomer getGenemessCustomer() {
		return genemessCustomer;
	}

	public void setGenemessCustomer(GenemessCustomer genemessCustomer) {
		this.genemessCustomer = genemessCustomer;
	}

	public DetailContentCustomer getDetailContentCustomer() {
		return detailContentCustomer;
	}

	public void setDetailContentCustomer(DetailContentCustomer detailContentCustomer) {
		this.detailContentCustomer = detailContentCustomer;
	}

	public DetailTypeCustomer getDetailTypeCustomer() {
		return detailTypeCustomer;
	}

	public void setDetailTypeCustomer(DetailTypeCustomer detailTypeCustomer) {
		this.detailTypeCustomer = detailTypeCustomer;
	}

	public PageQuery getPagequery() {
		return pagequery;
	}

	public void setPagequery(PageQuery pagequery) {
		this.pagequery = pagequery;
	}
	
	

}
