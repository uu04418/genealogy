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
import cn.com.gene.pojo.Detailaddress;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.Genemessconn;
import cn.com.gene.pojo.Profile;
import cn.com.gene.pojo.Relation;
import cn.com.gene.pojo.Surname;
import cn.com.gene.queryvo.GenemessCustomer;
import cn.com.gene.queryvo.GenemessVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.AreaService;
import cn.com.gene.service.BrowseService;
import cn.com.gene.service.FanService;
import cn.com.gene.service.GeneService;
import cn.com.gene.service.InteService;

@Controller
public class GeneController {
	
	@Autowired GeneService geneService;
	@Autowired AreaService areaService;
	@Autowired HttpServletRequest request;
	@Autowired BrowseService browseService;
	@Autowired FanService fanService;
	@Autowired InteService inteService;
	

	
	// 创建家族
	@RequestMapping("/gene/creategene")
	@ResponseBody
	public ResultMap creategene(Genemess genemess ,Detailaddress detailaddress) {
		// 检查是否输入经纬度
		if (detailaddress.getLatitude() == null || detailaddress.getLongitude() == null
				|| detailaddress.getDetailes() == null || "".equals(detailaddress.getDetailes())) 
			{ return ResultMap.build(400, "请检查定位!"); }
		if (genemess.getPicture() == null || "".equals(genemess.getPicture())) {
			return ResultMap.build(400, "插入图片");
		}
		if (genemess.getSurnameid() == null ) {return ResultMap.build(400, "检查姓氏ID");}
		Long detailid = areaService.getdetailid(detailaddress);
		ResultMap resultMap = geneService.creategene(genemess ,detailid);
		return resultMap;
	}
	
	// 根据真实姓名调出对应的姓氏
	@RequestMapping("/gene/searchsurname")
	@ResponseBody
	public ResultMap searchsurname (String realname) {
		List<Surname> surnames  = geneService.searchsurname(realname); ;
		return ResultMap.IS_200(surnames);
	}
	
	
	// 查询添加的人的关系
	@RequestMapping("/gene/searchrelation")
	@ResponseBody
	public ResultMap searchrelation () {
		List<Relation> relaiton = geneService.searchrelation();
		return ResultMap.IS_200(relaiton);
	}
	
	
	/**添加家族成员
	 * @param adduserid : 相当 profileid 给哪个成员添加
	 * 
	 * **/ 
	@RequestMapping("/gene/addrelation")
	@ResponseBody
	public ResultMap addrelation(Profile profile ,Long telephone ,Long  relationid,Long genemessid,Long adduserid 
			,String code ) {
		
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400,"请先登录");}
		
		// 在添加氏族成员之前先校验用户积分是否充足
		ResultMap checkmap = inteService.deletesearch(5, userid);
		if (checkmap.getCode() !=200) {return checkmap;}
		if (relationid == null) {return ResultMap.build(400, "检查人物关系");}
		if (profile.getSex() == null || "".equals(profile.getSex())) {return ResultMap.build(400,"输入性别");}
		if (profile.getRankings() == null) { return ResultMap.build(400,"输入排行");}
		String tel = telephone == null ?"" : telephone+"";
		tel = IDUtils.checkphone(tel);
		telephone = tel == "" ? null :Long.valueOf(tel);
		ResultMap resultMap = geneService.addrelation(profile , telephone ,relationid ,genemessid ,adduserid ,code,userid);
		//如果发布成功需要做积分扣除处理
		if (resultMap.getCode() == 200) {
			inteService.deletebrach(5, userid);
		}
		return resultMap;
	}
	
	// 查询代数关系
	@RequestMapping("/gene/generelation")
	@ResponseBody
	public ResultMap generelation (Long genemessid) {
		if (genemessid == null) {return ResultMap.build(400, "选择家族");}
		Long deletejinbi = -1L;
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid > 0 ) { 
			deletejinbi = inteService.delelteafter(7, userid);
		}
		Map<Integer, List<UserCustomer>> skuIdMap = new HashMap<>();
		List<UserCustomer> searchlist =   geneService.generelation(new ArrayList<>(), genemessid ,new ArrayList<>(),1);
		for (UserCustomer skuVo : searchlist) {
	 		List<UserCustomer> tempList = skuIdMap.get(skuVo.getIndex());
	 		if (!"".equals(skuVo.getRealname())) {
		        if (tempList == null) {
		            tempList = new ArrayList<>();
		            tempList.add(skuVo);
		            skuIdMap.put(skuVo.getIndex(), tempList);
		        }
		        else {
		            tempList.add(skuVo);
		        }
	 		}
	     }
		 Map<String,Object> map = new HashMap<>();
		 map.put("deletejinbi", deletejinbi);
		 map.put("skuIdMap", skuIdMap);
	     return ResultMap.IS_200(map);
	}
	
	
	// 家族详情页
	@RequestMapping("/gene/genedetail/{type}")
	@ResponseBody
	public ResultMap genedetail(Long genemessid ,@PathVariable Integer type ) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		//设置刚开始是没有关注状态
		Integer fanstate = 2; 
		//判断是否是氏族成员
		Integer genestate = -1;
		if (userid > 0) {  
			browseService.addBrowse( genemessid ,type ,userid); 
			// 当用户登录时候校验是否关注
			fanstate = fanService.checkguanzhu(userid  , genemessid , type);
			// 校验是否是氏族成员
			
			Genemessconn checkgene = checkgeneflag (genemessid , userid);
			if (checkgene !=null) { genestate = checkgene.getState();	}
		}
		Map<String,Object> map = new HashMap<>();
		// 查询家族的基本信息
		GenemessCustomer genemess = geneService.searchgenemess(genemessid ,type);
		// 查询氏族成员
		List<UserCustomer> geneuser = geneService.searchgeneuser(genemessid);
		map.put("fanstate", fanstate);
		map.put("genemess", genemess);
		map.put("geneuser", geneuser);
		map.put("genestate", genestate);
		return ResultMap.IS_200(map);
	}
	
	private Genemessconn checkgeneflag(Long genemessid, Long userid) {
		Genemessconn checkgene = geneService.checkgeneflag(genemessid , userid);
		return checkgene ;
	}

	// 家族列表页面
	@RequestMapping("/gene/genearray")
	@ResponseBody
	public ResultMap genearray (GenemessVo genemessVo , String city ,
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		genemessVo = genemessVo == null ? new GenemessVo(): genemessVo;
		GenemessCustomer genemessCustomer = genemessVo.getGenemessCustomer() == null ? new GenemessCustomer():genemessVo.getGenemessCustomer();
		Map<String, Object> map = new HashMap<>();
		if (genemessCustomer.getLikecode() == null || "".equals(genemessCustomer.getLikecode())) {
			Long searchcode = areaService.getcodebycity(city);
			map.put("citycode", searchcode);
			genemessCustomer.setLikecode(searchcode+"");
		}
		genemessVo.setGenemessCustomer(genemessCustomer);
		int infocount = geneService.genearrayCount(genemessVo);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		genemessVo.setPagequery(pagequery);
		List<GenemessCustomer> list = geneService.genearray(genemessVo);
		map.put("pagequery", pagequery);
		map.put("genearray", list);
		return ResultMap.IS_200(map);
	}
	
	
	// 我的家族的列表页面
	@RequestMapping("/gene/mygenearray")
	@ResponseBody
	public ResultMap mygenearray ( @RequestParam(defaultValue = "1")String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid< 0) {return ResultMap.build(400, "未知用户");}
		int infocount = geneService.mygenearrayCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		Map<String, Object> map = new HashMap<>();
		List<GenemessCustomer> list = geneService.mygenearray(userid ,pagequery);
		map.put("mygenearray", list);
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
	}
	
	
	
	// 查询所有的姓氏
	@RequestMapping("/gene/surnames")
	@ResponseBody
	public ResultMap surnames (GenemessVo genemessVo , 
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		genemessVo = genemessVo == null ? new GenemessVo(): genemessVo;
		Map<String, Object> map = new HashMap<>();
		int infocount = geneService.surnamesCount(genemessVo);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		genemessVo.setPagequery(pagequery);
		List<Surname> list = geneService.surnames(genemessVo);
		map.put("pagequery", pagequery);
		map.put("surnames", list);
		return ResultMap.IS_200(map);
	}
	
	
	// 家族签到
	@ResponseBody
	@RequestMapping("/sing/usersign/{type}")
	public ResultMap usersign(Long genemessid ,@PathVariable Integer type) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400, "未知用户");}
		ResultMap resultMap = geneService.usersign(genemessid , type ,userid);
		if (resultMap.getCode() == 200) {
			inteService.managebranch(3, userid);
		}
		return resultMap;
	}
	
	// 加入氏族
	@RequestMapping ("/gene/joingene") 
	@ResponseBody
	public ResultMap joingene (Long genemessid ,Integer state ) {
		ResultMap resultMap = geneService.joingene(genemessid ,state);
		return resultMap;
	}
	
	
	// 进入氏族成员的详情页面
	@RequestMapping("/gene/userdetail")
	@ResponseBody
	public ResultMap userdetail (Long profileid) {
		// 先判断用户是否已经正常登录
		Long sessionid = IDUtils.searchuseridbyrequest(request);
		if (sessionid < 0) {return ResultMap.build(400, "请先登录");}
		if (profileid == null ) {return ResultMap.build(400, "选择氏族成员");}
		//查询氏族积分是否充足
		ResultMap checkmap  = inteService.deletesearch(7, sessionid);
		if (checkmap.getCode() !=200) {return checkmap;}
		ResultMap resultMap = geneService.geneuserdetail(profileid);
		//如果操作成功做积分扣除操作
		if (resultMap.getCode() ==200) {inteService.deletebrach(7, sessionid);}
		return resultMap;
	}
	
	
	// 获取通讯录
	@RequestMapping("/gene/searchphone")
	@ResponseBody
	public ResultMap searchphone(Long genemessid) {
		if (genemessid == null) {return ResultMap.build(400, "输入家族");}
		List<UserCustomer> phones= geneService.searchphone(genemessid);
		return ResultMap.IS_200( phones );
	}
	
	
	
	// 查询氏族签到榜单
	@RequestMapping("/gene/searchgenesignuser")
	@ResponseBody
	public ResultMap searchgenesignuser (GenemessVo genemessVo , 
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		genemessVo = genemessVo == null ? new GenemessVo(): genemessVo;
		if (genemessVo.getGenemessid() == null) {return ResultMap.build(400, "输入氏族ID");}
		Map<String, Object> map = new HashMap<>();
		int infocount = geneService.searchgenesignuserCount(genemessVo);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		genemessVo.setPagequery(pagequery);
		List<UserCustomer> list = geneService.searchgenesignuser(genemessVo);
		map.put("pagequery", pagequery);
		map.put("genesign", list);
		return ResultMap.IS_200(map);
	}
	
	
	// 查询荣恩录的分类
	@RequestMapping("/gene/honorstype")
	@ResponseBody
	public ResultMap honors (Long genemessid) {
		if (genemessid == null) {return ResultMap.build(400, "输入氏族ID");}
		Map<Integer, List<PosCustomer>> skuIdMap = new HashMap<>();
		// 查询学历的分类
		List<PosCustomer> poslist =  geneService.honorpostype(genemessid); 
		// 查询职业的分离
		List<PosCustomer> edulist =  geneService.honoredutype(genemessid);
		skuIdMap.put(1, poslist);
		skuIdMap.put(2, edulist);
		return ResultMap.IS_200(skuIdMap);
	}
	
	// 地图找家族成员
	@RequestMapping("/gene/mapuser")
	@ResponseBody
	public ResultMap mapuser (Long genemessid) {
		if (genemessid == null) {return ResultMap.build(400, "输入氏族ID");}
		List<UserCustomer> mapuser =geneService.searchgeneusermap(genemessid);
		return ResultMap.IS_200(mapuser);
	}
	
	
	
	

	

}
