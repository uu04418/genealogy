package cn.com.gene.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Lucre;
import cn.com.gene.queryvo.VipCustomer;
import cn.com.gene.service.VipService;

@Controller
public class VipCountController {
	
	@Autowired HttpServletRequest request;
	@Autowired VipService vipService;
	
	/**
	 * 查询我的钱包
	 * **/
	@RequestMapping("/vip/searchmybure")
	@ResponseBody
	public ResultMap searchmybure () {
		VipCustomer searchresult = new VipCustomer();
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid<0) {searchresult.setIntecount(0L);}
		searchresult = vipService.searchmybure(userid);
		return ResultMap.IS_200(searchresult);
	}
	
	/**
	 * 查询我的金币明细
	 * **/
	@RequestMapping ("/vip/jinbimx")
	@ResponseBody
	public ResultMap searchjinbimx(@RequestParam(defaultValue = "0") String currentpage,
			   @RequestParam(defaultValue = "8")int pagesize ,String state){
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400, "未知用户");}
		// 校验state
		if ("".equals(state) || state == null  ) {return ResultMap.build(400, "输入金币类型");}
		try {
			int infocount = vipService.searchjinbimxCount(userid ,state);
			PageQuery pagequery = new PageQuery();
			pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
			List<VipCustomer> jinbimx = vipService.searchjinbimx(userid ,state, pagequery);
			Map<String ,Object> map = new HashMap<>();
			map.put("pagequery", pagequery);
			map.put("jinbimx", jinbimx);
			return ResultMap.IS_200(map);
		} catch (Exception e) {
			return ResultMap.build(400, "state 非合法数据");
		}
		
	}
	
	
	
	/** 查询充值记录 和消费
	 * @param type : 1 - 消费记录   2 - 充值记录   1,2 - 充值和消费记录
	 * 
	 * **/
	@RequestMapping("/consume/recharge")
	@ResponseBody
	public ResultMap searchrecharge(@RequestParam(defaultValue = "0") String currentpage, 
			@RequestParam(defaultValue = "8")	int pagesize ,String type) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {
			return ResultMap.build(400, "登录空");
		}
		try {
			int infocount = vipService.searchrechargeCount(userid ,type);
			PageQuery pagequery = new PageQuery();
			pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
			List<VipCustomer> consumeList = vipService.searchrecharge(userid ,type ,pagequery);
			Map<String,Object> map = new HashMap<>();
			map.put("consumeList", consumeList);
			map.put("pagequery", pagequery);
			return ResultMap.IS_200(map);
		} catch (Exception e) {
			return ResultMap.build(400, "type 类型错误");
		}
		
	}
	
	
	
	// 查询我的收益记录
	@RequestMapping("/vip/mylurnce")
	@ResponseBody
	public ResultMap mylurnce( 
			@RequestParam(defaultValue = "0") String currentpage, 
			@RequestParam(defaultValue = "100")	int pagesize) {
	
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400,"未知用户");}
		
		int infocount = vipService.mylurnceCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		Map<String,Object> map = new HashMap<>();
		List<Lucre> USERS = vipService.mylurnce(userid,pagequery );
		map.put("mylurnce", USERS);
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
	}
	
	
	
	

}
