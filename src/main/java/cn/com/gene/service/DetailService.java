package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.Detailtype;
import cn.com.gene.pojo.Education;
import cn.com.gene.queryvo.DetailCustomer;
import cn.com.gene.queryvo.DetailVo;
import cn.com.gene.queryvo.PosCustomer;

public interface DetailService {
	
	// 根据type类型查询发布的类型
	List<Detailtype> searchdetailtype(Integer type ,String liketype);
	
	/**发布详情
	 * @param userid : 发布人userid
	 * */
	ResultMap adddetailcontent(Long detailid, Detailcontent detailcontent,Long userid);
	
	// 根据条件查询发布的详情
	List<DetailCustomer> searchdetail(DetailVo detailVo);
	int searchdetailCount(DetailVo detailVo);
	
	
	// 查询发布的详情
	DetailCustomer detailcontentbyid(Long detailcontentid);
	
	// 查询教育水平
	List<Education> searcheduc();
	
	//查询职位职称
	List<PosCustomer> searchpostion();
	
	// 查询相册
	int searchpicCount(Long genemessid);
	List<DetailCustomer> searchpic(Long genemessid, PageQuery pagequery);
	
	//查询个人中心发布详情
	int searchsendcontentCount(Long userid);
	List<DetailCustomer> searchsendcontent(Long userid, PageQuery pagequery);
	
	/**移除发布内容
	 * @param userid : sessionuserid
	 * @param detailcontentid : 发布详情ID
	 * **/
	ResultMap removedetail(Long userid, Long detailcontentid);
	
	/**
	 * 编辑的查询
	 * **/
	Detailcontent searcheditdetail(Long detailcontentid);
	/**
	 * 编辑发布
	 * **/
	ResultMap editdetail(Long userid, Detailcontent detailcontent);
	
	/**
	 * 查询我的动态
	 * @param userid : 当前用户
	 * @param pagequery : 分页参数
	 * @param searchwhere : 搜索框的条件
	 * **/
	List<DetailCustomer> aboutmydynamic(Long userid, PageQuery pagequery,String searchwhere );
	int aboutmydynamicCount(Long userid ,String searchwhere);
	
	

}
