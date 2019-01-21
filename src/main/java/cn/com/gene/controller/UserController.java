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
import cn.com.gene.pojo.Invite;
import cn.com.gene.pojo.Profile;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.InteService;
import cn.com.gene.service.UserService;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired HttpServletRequest request; 
	@Autowired InteService inteService;
	
	
	// 验证token是否zhengq
	@RequestMapping("/user/checktoken")
	@ResponseBody
	public ResultMap checktoken (String telephone , String accesstoken) {
		boolean flag = false ;
		if (! "".equals(telephone) && telephone !=null && !"".equals(accesstoken) && accesstoken !=null) {
			flag = userService.checktoken(telephone ,accesstoken );
		}
		return ResultMap.IS_200(flag);
	}
	
	// 用户通过短信验证码登录
	@RequestMapping("/user/loginbycode")
	@ResponseBody
	public ResultMap loginbycode (String telephone,String code ,Long rephone  ) {
		ResultMap resultMap = userService.codelogin(telephone , code , rephone);
		if (resultMap.getCode() !=200) {return resultMap;}
		UserCustomer userCustomer = (UserCustomer) resultMap.getObj();
		// 创建推荐人 的关系
		Map<String,Object> searchmap = userService.addconnectionlevel(userCustomer.getUserid(), rephone);
		// 如果是推荐人推荐过来的
		if ("new".equals(searchmap.get("mess"))) {
			Long reuserid = (Long )searchmap.get("reuserid"); // 推荐人userid
			inteService.managebranch(9, reuserid);
		}
		if ("erjinew".equals(searchmap.get("mess"))) {
			Long reuserid = (Long )searchmap.get("reuserid"); // 推荐人userid
			inteService.managebranch(9, reuserid);  //先建立一级用户收益
			// 获取上一级userid
			Long leveluserid = (Long)searchmap.get("preuserid");
			inteService.managebranch(10, leveluserid);  //先建立一级用户收益
		}
		userCustomer.setUserid(null);
		return ResultMap.IS_200(userCustomer);
	}
	
	// 通过真实姓名和身份证号实名认证
	@RequestMapping("/user/signuser")
	@ResponseBody
	public ResultMap signuser (Profile profile) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 || profile == null ) {return ResultMap.build(400,"未知用户");}
		if (profile.getIdentity() == null || "".equals(profile.getIdentity())) {return ResultMap.build(400, "输入证件号码");}
		if (profile.getRealname() ==null || "".equals(profile.getRealname())) {return ResultMap.build(400,"输入真实姓名");}
		profile.setUserid(userid);
		ResultMap resultMap = userService.signuser(profile);
		//实名认证成功加金币
		if (resultMap.getCode() == 200) {
			inteService.managebranch(8, userid);
		}
		return resultMap;
	}
	
	
	// 根据userid 回显发布资料
	@RequestMapping ("/user/searchupdteuser")
	@ResponseBody
	public ResultMap searchuser (Long relationid , Long profileid) {
		UserCustomer searchuser = userService.searchuser (relationid , profileid);
		return ResultMap.IS_200(searchuser);
	}
	
	/**
	 * 编辑资料查询
	 * 
	 * **/
	@RequestMapping("/user/searchcommoninfo")
	@ResponseBody
	public ResultMap searchupdateuser () {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400, "未知用户");}
		UserCustomer searchupdate = userService.searchcommoninfo(userid);
		searchupdate.setUserid(null);
		searchupdate.setRealname(null);
		return ResultMap.IS_200(searchupdate);
	}
	
	/**
	 * 个人中心修改资料
	 * **/
	@RequestMapping("/user/updatecommoninfo")
	@ResponseBody
	public ResultMap updatecommoninfo(UserCustomer userCustomer ,String code) {
		Long userid =IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400, "");}
		ResultMap resultMap = userService.updatecommoninfo (userid , userCustomer ,code);
		return resultMap;
	}
	
	
	// 用户个人中心
	@RequestMapping("/user/searchinfo")
	@ResponseBody
	public ResultMap searchinfo (Long userid) {
		Map<String,Object> map = new HashMap<>();
		Long sessionuserid = IDUtils.searchuseridbyrequest(request);
		
		Integer  fanstate = 2 ; //表示没有关注
		if (userid !=null) {
			// 查询是否是关注的状态
			fanstate = userService.findIsFans(userid , sessionuserid); // 先判断是否关注
			if (sessionuserid - userid==0) {fanstate = 3;} //表示是自己的个人中心
		} else {
			if (sessionuserid > 0) {fanstate = 3;}      // 表示是自己的个 人中心
			userid = sessionuserid;
		}
		if (userid < 0 ) {return ResultMap.build(400, "未知用户");} 
		UserCustomer userCustomer = userService.searchinfo (userid);
		map.put("userinfo", userCustomer);
		map.put("fanstate", fanstate);
		return ResultMap.IS_200(map);
	}
	
	
	
	// 查询我的团队
	@RequestMapping("/user/myteam")
	@ResponseBody
	public ResultMap myteam(@RequestParam(defaultValue = "0") String currentpage,
			@RequestParam(defaultValue = "8")int pagesize) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400, "未知用户");}
		Invite checkuser = userService.searchminlevel(userid);
		if (checkuser == null) {return ResultMap.build(400, "无队员");}
		// 1,在判断当前用户的地位
		Integer level = checkuser.getLevel();
		int infocount = userService.myteamCount(userid ,level);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> USERS = userService.myteam(userid ,pagequery ,level);
		Map<String ,Object> map  = new HashMap<>();
		map.put("myteam", USERS);
		map.put("level", level) ;
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
	}

	/**查询我的团队**/
	@RequestMapping("/user/myteamson")
	@ResponseBody
	public ResultMap myteamson(Long userid ,@RequestParam(defaultValue = "0") String currentpage,
			@RequestParam(defaultValue = "8")int pagesize) {
		Long sessionid = IDUtils.searchuseridbyrequest(request);
		if (sessionid < 0 ) {return ResultMap.build(400, "未知用户");}
		int infocount = userService.myteamsonCount(userid ,sessionid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<UserCustomer> USERS = userService.myteamson(userid , sessionid ,pagequery);
		Map<String,Object> map = new HashMap<>();
		map.put("pagequery", pagequery);
		map.put("myteamson", USERS);
		return ResultMap.IS_200(map);
	}
	
	// 同步用户 经纬度
	@RequestMapping("/user/synclatandlog")
	@ResponseBody
	public ResultMap synclatandlog (Profile profile) {
		Long sessionid = IDUtils.searchuseridbyrequest(request);
		if (sessionid < 0 ) {return ResultMap.build(400, "未知用户");}
		ResultMap resultMap = userService.synclatandlog(profile , sessionid);
		return resultMap;
		
	}
	

	


}
