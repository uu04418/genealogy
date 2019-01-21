package cn.com.gene.queryvo;

import java.util.List;

import cn.com.gene.pojo.Position;

public class PosCustomer extends Position{
	
	private List<PosCustomer> child ;
	
	private Integer typestate ;
	
	private String typename;
	
	private Integer totalcount;
	
	private List<String> username;
	
	private Long typeid;
	
	
	public List<String> getUsername() {
		return username;
	}

	public void setUsername(List<String> username) {
		this.username = username;
	}

	public Integer getTypestate() {
		return typestate;
	}

	public void setTypestate(Integer typestate) {
		this.typestate = typestate;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public List<PosCustomer> getChild() {
		return child;
	}

	public void setChild(List<PosCustomer> child) {
		this.child = child;
	}
	
	
}
