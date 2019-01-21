package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Messages;
import cn.com.gene.pojo.Messagesreply;
import cn.com.gene.queryvo.MessCustomer;
import cn.com.gene.queryvo.MessReply;
import cn.com.gene.queryvo.MessVo;

public interface MessService {
	
	/**
	 * 添加留言
	 * **/ 
	ResultMap addmess(Messages mess , Long userid);
	
	
	/**
	 * 回复留言
	 * **/
	ResultMap messagereply(Messagesreply messagesreply ,Long userid);

	/**
	 * 查询消息的总数
	 * **/ 
	int messarryCount(Long otherid, Integer type);
	List<MessCustomer> messarry(MessVo messVo);

	/**
	 * 获取回复的内容
	 * **/ 
	List<MessReply> getreply(Long intt);

	/**
	 * 查询出 二级留言
	 * @param messagesid : 一级留言ID
	 * **/ 
	List<Messagesreply> getmessreply(Long messagesid);

	/**
	 * 删除一级留言
	 * @param messagesid : 一级留言ID
	 * **/
	void deletemessall(Long messagesid);

	/**
	 * 删除二级留言
	 * @param messagesreplyid : 二级留言ID
	 * **/ 
	void deletemess(Long messagesreplyid);


	Messages searchcheck(Long messid);

}
