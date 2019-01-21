package cn.com.gene.queryvo;

import cn.com.gene.pojo.Messagesreply;

public class MessReply extends Messagesreply {
	
	private String uname;
	
	private String fname;
	
	private Integer uid;
	
	private Integer fid;
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	

}
