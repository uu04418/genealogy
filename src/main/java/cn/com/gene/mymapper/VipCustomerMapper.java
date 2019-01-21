package cn.com.gene.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.pojo.Lucre;
import cn.com.gene.queryvo.VipCustomer;

public interface VipCustomerMapper {
	
	/**
	 * 统计金币时候判断当天类型发布的长度
	 * **/
	Integer searchcurrntlength(@Param("type")int type,@Param("userid") Long userid);
	
	/**
	 * 查询我的钱包
	 * ***/
	VipCustomer searchmybure(Long userid);
	
	/**
	 * 查询金币明细
	 * **/
	int searchjinbimxCount(@Param("userid")Long userid, @Param("state")String state);
	List<VipCustomer> searchjinbimx(@Param("userid")Long userid, @Param("state")String state,@Param("pagequery") PageQuery pagequery);
	
	/**
	 * 获取consuem 的最大订单号
	 * **/
	Long consumeorderNum();
	
	/**
	 * 查询充值记录 和消费记录
	 * **/
	int searchrechargeCount(@Param("userid")Long userid, @Param("type")String type);
	List<VipCustomer> searchrecharge(@Param("userid")Long userid,
			@Param("type")String type ,@Param("pagequery") PageQuery pagequery);
	
	/**
	 * 查询收益记录
	 * **/
	List<Lucre> mylurnce(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
	int mylurnceCount(@Param("userid")Long userid);

}