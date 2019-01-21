package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.queryvo.UserCustomer;

public interface FanService {
	
	// 检查关注状态
	Integer checkguanzhu(Long userid, Long genemessid, Integer type);
	
	// 更改关注状态
	ResultMap changefanstate(Long otherid, Integer state, Integer type);
	
	// 检查赞的状态
	Integer checkzan(Long userid, Long detailcontentid, Integer type);
	
	//检查收藏的状态
	Integer checkensh(Long userid, Long detailcontentid, Integer type);
	
	// 更改zan的状态
	ResultMap changezanstate(Long otherid, Integer state, Integer type);
	
	//改变收藏的状态
	ResultMap changeenshstate(Long otherid, Integer state, Integer type);
	
	//查询zan 的总数
	int zanarryCount(Long userid);
	List<UserCustomer> zanarry(Long userid, PageQuery pagequery);
	
	// 查询浏览量的总数
	int broarryCount(Long userid);
	List<UserCustomer> broarry(Long userid, PageQuery pagequery);
	
	//查询粉丝的总数
	int fansarryCount(Long userid);
	List<UserCustomer> fansarry(Long userid, PageQuery pagequery);
	
	//查询关注的列表
	int guanarryCount(Long userid);
	List<UserCustomer> guanarry(Long userid, PageQuery pagequery);
	
	// 查询我的收藏
	int findMyEnshListCount(Long userid);
	List<UserCustomer> findMyEnshList(Long userid, PageQuery pagequery);
	
	
	

}
