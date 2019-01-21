package cn.com.gene.controller;

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
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.FanService;
import cn.com.gene.service.InteService;

@Controller
public class FansController {
	
   @Autowired FanService fanService;
   @Autowired HttpServletRequest request;
   @Autowired InteService inteService;
   
   /**改变粉丝的状态/关注的状态
    * @param type : 粉丝 的类型
    * @param otherid :人、物品主键  [被关注的那个人]
    * @param state :粉丝状态 1-已经关注，2-没有关注
    * **/ 
   @RequestMapping("/user/changefanstate/{type}")
   @ResponseBody
   public ResultMap changefanstate(@PathVariable Integer type , Long otherid ,Integer state  ) {
	   ResultMap resultMap = fanService.changefanstate(otherid ,state , type );
	   if (resultMap.getCode()==200 && type==11) {
		   if ("首次关注".equals(resultMap.getMsg())) {
			   inteService.managebranch(4, otherid);
		   }
	   }
	   return resultMap;
   }
   
   /**改变赞的状态
    * @param type :赞的类型
    * @param otherid :赞的物品主键
    * @param state : 1-已经赞，2没有赞
    * **/
   @RequestMapping("/user/changezanstate/{type}")
   @ResponseBody
   public ResultMap changezanstate(@PathVariable Integer type , Long otherid ,Integer state  ) {
	   ResultMap resultMap = fanService.changezanstate(otherid ,state , type );
	   return resultMap;
   }
   
   /**改变收藏的状态
    * @param type : 收藏的类型
    * @param otherid : 收藏的物品主键
    * @param state : 收藏的状态 1-已经收藏，2没有收藏
    * **/
   @RequestMapping("/user/changeenshstate/{type}")
   @ResponseBody
   public ResultMap changeenshstate(@PathVariable Integer type , Long otherid ,Integer state  ) {
	   ResultMap resultMap = fanService.changeenshstate(otherid ,state , type );
	   return resultMap;
   }
   
   /**
    * 查询赞的列表页面
    * @param currentpage : 当前页面
    * @param pagesiez : 每页数据
    * @param userid : 传入的userid
    * **/
   @RequestMapping ("/zan/zanarry")
   @ResponseBody
   public ResultMap zanarry (@RequestParam(defaultValue = "0") String currentpage,
		   @RequestParam(defaultValue = "8")int pagesize,Long userid) {
	    Long sessionuserid = IDUtils.searchuseridbyrequest(request);
	    if (userid == null) {userid = sessionuserid;} // 如果进入别人的个人中心需要传入对应的别人的用户ID 
	    Map<String ,Object> map = new HashMap<>();
	    int infocount = fanService.zanarryCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> zanarry = fanService.zanarry(userid , pagequery);
		map.put("pagequery", pagequery);
		map.put("returnarry", zanarry);
	    return ResultMap.IS_200(map);
   }
   
   
   /**
    * 查询浏览量的列表页面
    * @param currentpage : 当前页面
    * @param pagesiez : 每页数据
    * @param userid : 传入的userid
    * **/
   @RequestMapping ("/zan/broarry")
   @ResponseBody
   public ResultMap broarry (@RequestParam(defaultValue = "0") String currentpage,
		   @RequestParam(defaultValue = "8")int pagesize,Long userid) {
	    Long sessionuserid = IDUtils.searchuseridbyrequest(request);
	    if (userid == null) {userid = sessionuserid;} // 如果进入别人的个人中心需要传入对应的别人的用户ID 
	    Map<String ,Object> map = new HashMap<>();
	    int infocount = fanService.broarryCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> broarry = fanService.broarry(userid , pagequery);
		map.put("pagequery", pagequery);
		map.put("returnarry", broarry);
	    return ResultMap.IS_200(map);
   }
   
   
	/**
	 * 查询粉丝
	 *    * @param currentpage : 当前页面
    *       @param pagesiez : 每页数据
    *       @param userid : 传入的userid
	 * **/ 
	@RequestMapping(value = "/fans/fansarry")
	@ResponseBody
	public ResultMap fansarry(@RequestParam(defaultValue = "0") String currentpage,
			   @RequestParam(defaultValue = "8")int pagesize,Long userid) {
		 Long sessionuserid = IDUtils.searchuseridbyrequest(request);
	    if (userid == null) {userid = sessionuserid;} // 如果进入别人的个人中心需要传入对应的别人的用户ID 
	    Map<String ,Object> map = new HashMap<>();
	    int infocount = fanService.fansarryCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> broarry = fanService.fansarry(userid , pagequery);
		map.put("pagequery", pagequery);
		map.put("returnarry", broarry);
		return ResultMap.IS_200(map);
	}
	
	
	/**
	 *  	查询关注
	 *    * @param currentpage : 当前页面
    *       @param pagesiez : 每页数据
    *       @param userid : 传入的userid
	 * **/ 
	@RequestMapping(value = "/fans/guanarry")
	@ResponseBody
	public ResultMap guanarry(@RequestParam(defaultValue = "0") String currentpage,
			   @RequestParam(defaultValue = "8")int pagesize,Long userid) {
		 Long sessionuserid = IDUtils.searchuseridbyrequest(request);
	    if (userid == null) {userid = sessionuserid;} // 如果进入别人的个人中心需要传入对应的别人的用户ID 
	    Map<String ,Object> map = new HashMap<>();
	    int infocount = fanService.guanarryCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> broarry = fanService.guanarry(userid , pagequery);
		map.put("pagequery", pagequery);
		map.put("returnarry", broarry);
		return ResultMap.IS_200(map);
	}
	
	
	
	// 查询我的收藏
	@RequestMapping("/ensh/myenshlist")
	@ResponseBody
	public ResultMap myenshList( @RequestParam(defaultValue = "0") String currentpage, 
			@RequestParam(defaultValue = "20")	int pagesize) {
		
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400, "未知登录");}
		int infocount = fanService.findMyEnshListCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> enshList = fanService.findMyEnshList(userid ,pagequery);
		Map<String,Object> map = new HashMap<>();
		map.put("enshList", enshList);
		map.put("pagequery", pagequery);
		
		return ResultMap.IS_200(map);
	}
 
}
