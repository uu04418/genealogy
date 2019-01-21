package cn.com.gene.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Messages;
import cn.com.gene.pojo.Messagesreply;
import cn.com.gene.queryvo.MessCustomer;
import cn.com.gene.queryvo.MessReply;
import cn.com.gene.queryvo.MessVo;
import cn.com.gene.service.InteService;
import cn.com.gene.service.MessService;

@Controller
public class MessController {
	
	@Autowired MessService messService;
	@Autowired HttpServletRequest request;
	@Autowired InteService inteService;
	
	
	/**
	 * 添加留言
	 */
	@RequestMapping(value = "/mess/addmess/{type}")
	@ResponseBody
	public ResultMap addMessages(Messages mess, @PathVariable Integer type) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400,"未知用户");}
		if (mess == null) {return ResultMap.build(400,"数据不全");}
		mess.setType(type);
		ResultMap resultMap =  messService.addmess(mess ,userid);
		
		// 添加留言成功加金币
		if (resultMap.getCode() == 200) { inteService.managebranch(6, userid);}
		
		return resultMap;
	}
	
	
	/**
	 * 用户之间回复
	 */
	@RequestMapping(value = "/mess/reply")
	@ResponseBody
	public ResultMap messagereply(Messagesreply messagesreply) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400,"未知用户");}
		if (messagesreply == null) {return ResultMap.build(400,"数据不全");}
		// 根据留言ID查询用户
		ResultMap resultMap = messService.messagereply(messagesreply , userid);
		// 添加留言成功加金币
		if (resultMap.getCode() == 200) { inteService.managebranch(6, userid);}
		return resultMap;
	}
	
	
	/**
	 * 房屋详情显示留言列表
	 */
	@RequestMapping(value = "/mess/messarry/{type}")
	@ResponseBody
	public ResultMap findMessagesByHouse(Long otherid, @RequestParam(defaultValue = "0") String currentpage,
			@RequestParam(defaultValue = "5") int pagesize, @PathVariable Integer type) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		Map<String,Object> map = new HashMap<>();
		PageQuery pagequery = new PageQuery();
		MessVo messVo = new MessVo();
		int infocount = messService.messarryCount(otherid, type);
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		messVo.setPagequery(pagequery);
		messVo.setOtherid(otherid);
		messVo.setMesstype(type);
		List<MessCustomer> list = messService.messarry(messVo);
		List<Long> mess = new ArrayList<>();
		if (!list.isEmpty() && list.size() > 0) {
			for (MessCustomer reply : list) {
				boolean flag = reply.getUserid() - userid == 0 ;
				reply.setDeleteflag(flag);
				reply.setUserid(5201314L);
				Long messid = reply.getMessagesid();
				mess.add(messid);
				if (!mess.isEmpty() && mess.size() > 0) {
					List<MessReply> replyarry = new ArrayList<>();
					for (Long intt : mess) {
						// 获取回复的集合
						 replyarry = messService.getreply(intt);
						if (!replyarry.isEmpty() && replyarry.size() > 0) {
							reply.setReplyarry(replyarry);
						} else {
							reply.setReplyarry(replyarry);
						}
					}

				}
			}
		}
		map.put("messarry", list);
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
	}
	
	
	// 删除一级留言
	@RequestMapping("/mess/deletemessall")
	@ResponseBody
	public ResultMap deletemessall(Long messid) {
		
		//通过一级留言查出二级留言ID
		List<Messagesreply> reply = messService.getmessreply(messid);
		if (!reply.isEmpty() && reply.size() >0) {
			for (Messagesreply mess : reply) {
				//删除二级留言
				if (mess.getMessagesreplyid() !=null) {
					messService.deletemess(mess.getMessagesreplyid());
				}
			}
		}
		//删除一级留言
		messService.deletemessall(messid);
		return ResultMap.IS_200();
	}

	
	// 删除二级留言
	@RequestMapping("/mess/deletemess")
	@ResponseBody
	public ResultMap deletemess(Long replyid) {
		messService.deletemess(replyid);
		return ResultMap.IS_200();
	}



}
