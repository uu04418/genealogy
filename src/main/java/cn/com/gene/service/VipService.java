package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Consume;
import cn.com.gene.pojo.Lucre;
import cn.com.gene.pojo.Vipcount;
import cn.com.gene.queryvo.VipCustomer;

public interface VipService {
	
	/**查询钱包对应的信息
	 *  @param userid : 当前用户的IID
	 * **/
	VipCustomer searchmybure(Long userid);

	/**查询金币明细
	 * @param userid : 当前用户ID
	 * @param state :1-收入，2-支出， 1,2 收入和支出
	 * @param pagequery : 分页条件
	 * **/
	List<VipCustomer> searchjinbimx(Long userid, String state, PageQuery pagequery);
	int searchjinbimxCount(Long userid, String state);
	
	/**
	 * 获取充值记录表的订单号
	 * **/
	Long ordernum();
	
	/**
	 * 创建充值订单
	 * **/
	void addConsume(Consume consume);
	
	/**
	 * 充值记录
	 * */
	int searchrechargeCount(Long userid ,String type);
	List<VipCustomer> searchrecharge(Long userid,String type ,  PageQuery pagequery);
	
	/**
	 *这里作收益操作
	 *@param userid : 充值 的用户ID
	 *@param account : 充值的金额 
	 * **/
	ResultMap addinvitebalance(Long userid, double account);

	
	/**
	 * 查询我的收益记录
	 *@param userid : 当前用户ID
	 *@param pagequery :  分页参数
	 * **/
	List<Lucre> mylurnce(Long userid, PageQuery pagequery);
	int mylurnceCount(Long userid);
	
	/**
	 *更新余额表记录 
	 * **/
	void updateVipSelective(Vipcount vipcount);


}
