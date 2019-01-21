package cn.com.gene.queryvo;

import java.util.List;

import cn.com.gene.pojo.Messages;

public class MessCustomer extends Messages {
	
	private String avatar;
	
	private String username;
	
	private Boolean deleteflag;
	
	
	public Boolean getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private List<MessReply> replyarry;

	public List<MessReply> getReplyarry() {
		return replyarry;
	}

	public void setReplyarry(List<MessReply> replyarry) {
		this.replyarry = replyarry;
	}
	
	
	
	


}
