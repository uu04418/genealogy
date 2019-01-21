package cn.com.gene.service;

import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Integral;

public interface InteService {
	
	/**处理获得的积分
	 * @param type : 对应的获得类型
	 * @param userid : 当前用户id
	 * 
	 * **/
	public String managebranch(int type, Long userid);
	
	/**处理扣除的积分 的查询
	 * @param type : 对应的消费类型
	 * @param userid : 当前用户ID
	 * **/
	public ResultMap deletesearch(int type, Long userid);
	
	/**处理扣除的积分操作
	 * @param type : 对应的消费类型
	 * @param userid : 当前用户ID
	 * **/
	public ResultMap deletebrach(int type, Long userid);
	
	/**查询删除后返回的金币
	 * @
	 * **/
	public Long delelteafter(int type ,Long userid) ;
	
	/**
	 * 金币充值回调处理
	 * **/
	public String chongzhirenturn(Integral integral);

}
