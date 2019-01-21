package cn.com.gene.queryvo;

import cn.com.gene.comm.PageQuery;

public class MessVo {
	
	private PageQuery pagequery;
	
	private Long otherid ;
	
	private Integer messtype;
	
	

	public Integer getMesstype() {
		return messtype;
	}

	public void setMesstype(Integer messtype) {
		this.messtype = messtype;
	}

	public Long getOtherid() {
		return otherid;
	}

	public void setOtherid(Long otherid) {
		this.otherid = otherid;
	}

	public PageQuery getPagequery() {
		return pagequery;
	}

	public void setPagequery(PageQuery pagequery) {
		this.pagequery = pagequery;
	}
	
	

}
