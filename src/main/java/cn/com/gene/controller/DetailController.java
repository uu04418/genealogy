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
import cn.com.gene.pojo.Detailaddress;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.Detailtype;
import cn.com.gene.pojo.Education;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.queryvo.DetailCustomer;
import cn.com.gene.queryvo.DetailVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.AreaService;
import cn.com.gene.service.BrowseService;
import cn.com.gene.service.DetailService;
import cn.com.gene.service.FanService;
import cn.com.gene.service.GeneService;
import cn.com.gene.service.InteService;
import cn.com.gene.service.UserService;
@Controller
public class DetailController {
	
	@Autowired DetailService  detailService;
	@Autowired AreaService areaService;
	@Autowired HttpServletRequest request;
	@Autowired BrowseService browseService;
	@Autowired FanService fanService;
	@Autowired InteService inteService;
	@Autowired GeneService geneService;
	@Autowired UserService userService;
	
	/**查询需要发布的类型
	 * @param type : 分类的类型 1-氏族类
	 * @param liketype : 分类的主键 集合 类似 1,2,3
	 * **/ 
	@RequestMapping("/detail/indexsearchdetailtype/{type}")
	@ResponseBody
	public ResultMap searchdetailtype (@PathVariable Integer type ,String liketype ) {
		boolean creategeneflage = true ;
		Long userid = IDUtils.searchuseridbyrequest(request);
		// 说明用户登录了
		if (userid > 0) {
			UserCustomer userCustomer = userService.searchcommoninfo(userid);
			if (userCustomer !=null) {
				if (userCustomer.getRealname() !=null && !"".equals(userCustomer.getRealname())) {
					String likenameone = userCustomer.getRealname().substring(0, 1);
					String likenametwo = userCustomer.getRealname().substring(0, 2);
					Genemess check = geneService.checkaddgene(userid, likenameone, likenametwo);
					// 这里说明当前用户有氏族了。如果要控制发布家族按钮隐藏 设置flase
					if (check !=null) {
						creategeneflage = true;
					}
				}
			}
		}
		List<Detailtype> detailtypes = detailService.searchdetailtype(type , liketype);
		Map<String,Object> map = new HashMap<>();
		map.put("creategeneflage", creategeneflage);
		map.put("detailtypes", detailtypes);
		return ResultMap.IS_200(map);
	}
	
	/**查询需要发布的类型
	 * @param type : 分类的类型 1-氏族类
	 * @param liketype : 分类的主键 集合 类似 1,2,3
	 * **/
	@RequestMapping("/detail/searchdetailtype/{type}")
	@ResponseBody
	public ResultMap indexsearchdetailtype (@PathVariable Integer type ,String liketype ) {
		List<Detailtype> detailtypes = detailService.searchdetailtype(type , liketype);
		return ResultMap.IS_200(detailtypes);
	}
	
	
	/**发布 氏族 图片 , 活动  , 等等
	 * @param Latitude  Longitude 经纬度
	 * @param details : 详情地址名称
	 * @param title : 发布的标题
	 * @param detailcontent : 发布内容
	 * @param picture : 发布的图片
	 * **/ 
	@RequestMapping ("/detail/adddetailcontent") 
	@ResponseBody
	public ResultMap adddetailcontent (Detailaddress detailaddress , Detailcontent detailcontent) {
		// 获取session userid
		Long userid = IDUtils.searchuseridbyrequest(request);
		// 先校验数据的完整性
		if (userid < 0 ) {return ResultMap.build(400,"未知的用户!");}
		// 检查是否输入经纬度
		if (detailaddress.getLatitude() == null || detailaddress.getLongitude() == null
				|| detailaddress.getDetailes() == null || "".equals(detailaddress.getDetailes())) 
				{ return ResultMap.build(400, "请检查定位!"); }
		Long detailid = areaService.getdetailid(detailaddress);
		ResultMap resultMap = detailService.adddetailcontent(detailid,detailcontent,userid);
		// 说明发布成功 需要做+ 积分处理
		if (resultMap.getCode() == 200) {
			inteService.managebranch(2, userid);
		}
		
		return resultMap;
	}
	
	/**根据条件查询发布内容 的列表页面
	 * 
	 * **/ 
	@RequestMapping ("/detail/searchdetail") 
	@ResponseBody
	public ResultMap searchdetail (DetailVo detailVo ,
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize ) {
		Map<String,Object>  map   =  new HashMap<>();
		int infocount = detailService.searchdetailCount(detailVo);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		detailVo.setPagequery(pagequery);
		List<DetailCustomer> details = detailService.searchdetail(detailVo);
		map.put("details", details);
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
	}
	
	// 进入发布的详情
	@RequestMapping ("/detail/detailcontentbyid/{type}")
	@ResponseBody
	public ResultMap detailcontentbyid (Long detailcontentid ,@PathVariable Integer type) {
		if (type !=2) {return ResultMap.build(200, "type类型不对");}
		if (detailcontentid == null) {return ResultMap.build(400, "选择物品!");}
		Map<String ,Object> map = new HashMap<>();
		Long userid = IDUtils.searchuseridbyrequest(request);
		// 判断留言是否可以删除
		Integer zanstate = 2;   // 默认是没有赞
		Integer enshstate = 2;  // 默认是没有收藏
		if (userid > 0) {
			// 这里处理浏览量
			browseService.addBrowse( detailcontentid ,type ,userid);
			// 校验赞的状态
			zanstate = fanService.checkzan(userid, detailcontentid, type);
			//校验收藏的状态
			enshstate = fanService.checkensh(userid, detailcontentid, type);
		}
		// 查询发布的详情
		DetailCustomer detailCustomer = detailService.detailcontentbyid(detailcontentid);
		boolean deleteflag = detailCustomer.getUserid() - userid == 0 ;
		map.put("zanstate", zanstate);
		map.put("enshstate", enshstate);
		map.put("deleteflag", deleteflag);
		map.put("detailcontent", detailCustomer);
		return ResultMap.IS_200(map);
	}
	
	
	/**
	 * 查询教育水平 列表页面
	 * **/ 
	@RequestMapping ("/educ/searcheduc")
	@ResponseBody
	public ResultMap searcheduc () {
		List<Education> educ = detailService.searcheduc();
		return ResultMap.IS_200(educ);
		
	}
	
	
	/**
	 * 查询职称 列表页面
	 * **/ 
	@RequestMapping ("/educ/searchpostion")
	@ResponseBody
	public ResultMap searchpostion () {
		List<PosCustomer> pos = detailService.searchpostion();
		return ResultMap.IS_200(pos);
	}
	
	
	/** 查询家族相册
	 * @param genemessid : 家族ID
	 * @param currentpage : 当前页面
	 * @param pagesize : 每页查询的数目
	 * **/
	@RequestMapping("/detail/searchpic")
	@ResponseBody
	public ResultMap searchpic(Long genemessid ,
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		if (genemessid == null) {return ResultMap.build(400, "输入家族");}
		Map<String ,Object> map = new HashMap<>();
		int infocount = detailService.searchpicCount(genemessid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<DetailCustomer> phones= detailService.searchpic(genemessid ,pagequery);
		String pics = "";
		if (!phones.isEmpty() && phones.size() > 0) {
			for (DetailCustomer search : phones) {
				pics +=search.getPicture() +",";
			}
		}
		if (!"".equals(pics)) {
			pics = pics.substring(0,pics.length()-1);
		}
		
		map.put("pagequery", pagequery);
		map.put("pic", pics);
		
		return ResultMap.IS_200( map );
	}
	
	
	// 根据个人ID 查询发布信息
	@RequestMapping("/user/searchsendcontent")
	@ResponseBody
	public ResultMap searchsendcontent (Long userid ,
			@RequestParam(defaultValue = "0") String currentpage, @RequestParam(defaultValue = "8")int pagesize) {
		Long sessionuserid = IDUtils.searchuseridbyrequest(request);
		if (userid ==null) { userid = sessionuserid; }
		if (userid < 0 ) {return ResultMap.build(400, "未知用户信息");}
		
		int infocount = detailService.searchsendcontentCount(userid);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<DetailCustomer> details = detailService.searchsendcontent(userid , pagequery);
		Map<String,Object> map = new HashMap<>();
		map.put("pagequery", pagequery);
		map.put("details", details);
		return ResultMap.IS_200(map);
	}
	
	/**
	 * 移除发布的content
	 * **/
	@ResponseBody
	@RequestMapping ("/detail/removedetail")
	public ResultMap removedetail(Long detailcontentid) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400,"未知用户");}
		ResultMap resultMap = detailService.removedetail(userid , detailcontentid);
		return resultMap;
	}
	
	
	/**
	 * 编辑内容的查询
	 * **/
	@ResponseBody
	@RequestMapping ("/detail/searcheditdetail")
	public ResultMap searcheditdetail(Long detailcontentid) {
		if (detailcontentid == null) {return ResultMap.build(400, "选择编辑");}
		Detailcontent detailcontent = detailService.searcheditdetail(detailcontentid);
		return ResultMap.IS_200(detailcontent);
	}
	
	/**
	 * 编辑发布
	 * **/
	@ResponseBody
	@RequestMapping ("/detail/editdetail")
	public ResultMap searcheditdetail(Detailcontent detailcontent) {
		if (detailcontent.getDetailcontentid() == null) {return ResultMap.build(300, "选择编辑");}
		if (detailcontent.getTitle() == null || "".equals(detailcontent.getTitle())) {return ResultMap.build(300, "选择标题");}
		if (detailcontent.getDetailcontent() == null || "".equals(detailcontent.getDetailcontent())) {
			return ResultMap.build(400, "发布内容");
		}
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400, "用户信息空");}
		ResultMap resultMap = detailService.editdetail(userid , detailcontent);
		return resultMap;
	}
	
	/**
	 * 查询关注的动态
	 * **/
	@ResponseBody
	@RequestMapping("/detail/aboutmydynamic")
	public ResultMap aboutmydynamic (@RequestParam(defaultValue = "0") String currentpage,
			@RequestParam(defaultValue = "8")int pagesize , String searchwhere) {
		// 先进行用户登录校验
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0) {return ResultMap.build(400,"未知用户");}
		Map<String,Object> map = new HashMap<>();
		int infocount = detailService.aboutmydynamicCount(userid ,searchwhere);
		PageQuery pagequery = new PageQuery();
		pagequery.setPageParams(infocount, pagesize, Integer.parseInt(currentpage));
		List<DetailCustomer> mydynamic = detailService.aboutmydynamic (userid , pagequery ,searchwhere);
		map.put("mydynamic", mydynamic);
		map.put("pagequery", pagequery);
		return ResultMap.IS_200(map);
		
	}


}
