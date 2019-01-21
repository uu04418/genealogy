package cn.com.gene.queryvo;

import java.util.Date;

import cn.com.gene.pojo.Vipcount;

public class VipCustomer extends Vipcount{
	
	private Long intecount ;

	private String event ;
	
	private Date updatetime;
	
	private Integer state;
	
	private Double monetary;
	
	
	public Double getMonetary() {
		return monetary;
	}

	public void setMonetary(Double monetary) {
		this.monetary = monetary;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Long getIntecount() {
		return intecount;
	}

	public void setIntecount(Long intecount) {
		this.intecount = intecount;
	}
	
	

}
