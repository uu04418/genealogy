package cn.com.gene.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.queryvo.MessCustomer;
import cn.com.gene.queryvo.MessReply;
import cn.com.gene.queryvo.MessVo;

public interface MessCustomerMapper {
	
	// 查询消息总数
	int messarryCount( @Param("otherid")Long otherid, @Param("type")Integer type);
	List<MessCustomer> messarry(MessVo messVo);
	
	//获取回复的内容
	List<MessReply> getreply(Long intt);
}