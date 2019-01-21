package cn.com.gene.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.Detailtype;
import cn.com.gene.queryvo.DetailCustomer;
import cn.com.gene.queryvo.DetailVo;
import cn.com.gene.queryvo.PosCustomer;

public interface DetailCustomerMapper {
	
	// 根据条件查询发布的列表
	List<DetailCustomer> searchdetail(DetailVo detailVo);
	int searchdetailCount(DetailVo detailVo);
	
	//查询发布的详情
	DetailCustomer detailcontentbyid(Long detailcontentid);
	
	// 查询发布类型
	List<Detailtype> searchdetailtype(@Param("type")String type, @Param("liketype") String liketype);
	
	// 查询职称
	List<PosCustomer> searchposbyfatherid(Long fatherid);
	
	
	// 查询相册
	int searchpicCount(Long genemessid);
	List<DetailCustomer> searchpic(@Param("genemessid")Long genemessid,@Param("pagequery") PageQuery pagequery);
	
	//根据userid 查询氏族ID
	Long  getgenemessbyuserid(Long userid);
	
	//个人中心发布详情
	int searchsendcontentCount(Long userid);
	List<DetailCustomer> searchsendcontent(@Param("userid")Long userid,@Param("pagequery") PageQuery pagequery);
	
	// 编辑的查询
	Detailcontent searcheditdetail(Long detailcontentid);
	
	/**
	 * 查询我的动态
	 * @param userid : 当前用户
	 * @param pagequery : 分页参数
	 * **/
	List<DetailCustomer> aboutmydynamic(@Param("userid") Long userid,
			@Param("profileid")Long profileid , @Param("pagequery") PageQuery pagequery 
			,@Param("searchwhere") String searchwhere);
	int aboutmydynamicCount(@Param("userid")Long userid ,
			@Param("profileid")Long profileid,@Param("searchwhere") String searchwhere);

}