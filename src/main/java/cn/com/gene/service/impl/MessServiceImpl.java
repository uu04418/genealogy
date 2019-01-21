package cn.com.gene.service.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.MessagesMapper;
import cn.com.gene.mapper.MessagesreplyMapper;
import cn.com.gene.mymapper.MessCustomerMapper;
import cn.com.gene.pojo.Messages;
import cn.com.gene.pojo.Messagesreply;
import cn.com.gene.pojo.MessagesreplyExample;
import cn.com.gene.queryvo.MessCustomer;
import cn.com.gene.queryvo.MessReply;
import cn.com.gene.queryvo.MessVo;
import cn.com.gene.service.MessService;

@Service
public class MessServiceImpl implements MessService{
	
	@Autowired HttpServletRequest request;
	@Autowired MessagesMapper messmapper;
	@Autowired MessagesreplyMapper replymapper;
	@Autowired MessCustomerMapper messCustomerMapper;
	
	
	// 添加留言
	public ResultMap addmess(Messages mess, Long userid) {
		
		if (mess.getOtherid()  == null) {return ResultMap.build(400,"选择留言的物品");}
		if (mess.getMesscontent() == null || "".equals(mess.getMesscontent())) {return ResultMap.build(400,"输入留言内容");}
		mess.setState(1);
		mess.setUserid(userid);
		mess.setUpdatetime(new Date());
		mess.setCreatetime(new Date());
		messmapper.insertSelective(mess);
		return ResultMap.build(200, "留言成功");
	}

	// 回复留言
	public ResultMap messagereply(Messagesreply reply ,Long userid) {
		if (reply.getMessagesid() == null) {
			return ResultMap.build(400, "回复留言不存在");
		}
		if (reply.getReply() == null || "".equals(reply.getReply())) {return ResultMap.build(400, "输入回复内容");}
		//校验回复的消息是否存在
		Messages checkmess = messmapper.selectByPrimaryKey(reply.getMessagesid());
		if (checkmess == null || checkmess.getState() != 1) {return ResultMap.build(400,"回复的对应不存在或者删除");}
		reply.setFollowuserid(checkmess.getUserid());
		reply.setUserid(userid);
		reply.setState(1);
		reply.setUpdatetime( new Date());
		replymapper.insertSelective(reply);
		return ResultMap.build(200, "回复成功");
	}

	// 查询消息总数
	public int messarryCount(Long otherid, Integer type) {
		// TODO Auto-generated method stub
		return messCustomerMapper.messarryCount(otherid,type );
	}

	@Override
	public List<MessCustomer> messarry(MessVo messVo) {
		// TODO Auto-generated method stub
		return messCustomerMapper.messarry(messVo);
	}

	@Override
	public List<MessReply> getreply(Long intt) {
		// TODO Auto-generated method stub
		return messCustomerMapper.getreply(intt);
	}

	//查询出二级留言
	public List<Messagesreply> getmessreply(Long messagesid) {
		MessagesreplyExample example = new MessagesreplyExample();
		MessagesreplyExample.Criteria criteria = example.createCriteria();
		criteria.andMessagesidEqualTo(messagesid);
		return replymapper.selectByExample(example);
	}

	@Override
	public void deletemessall(Long messagesid) {
		messmapper.deleteByPrimaryKey(messagesid);
	}

	@Override
	public void deletemess(Long messagesreplyid) {
		replymapper.deleteByPrimaryKey(messagesreplyid);
	}

	@Override
	public Messages searchcheck(Long messid) {
		return messmapper.selectByPrimaryKey(messid);
	}

}
