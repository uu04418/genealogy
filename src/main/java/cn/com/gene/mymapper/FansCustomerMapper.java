package cn.com.gene.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.queryvo.UserCustomer;

public interface FansCustomerMapper {
	
	// 查询赞的总数
	int zanarryCount(Long userid);
	List<UserCustomer> zanarry(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
	
	// 查询浏览量的 总数
	int broarryCount(Long userid);
	List<UserCustomer> broarry(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
	
	// 查询粉丝的总数
	int fansarryCount(Long userid);
	List<UserCustomer> fansarry(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
	
	//查询关注的列表
	List<UserCustomer> guanarry(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
	int guanarryCount(Long userid);
	
	// 查询我的收藏
	int findMyEnshListCount(Long userid);
	List<UserCustomer> findMyEnshList(@Param("userid")Long userid, @Param("pagequery")PageQuery pagequery);
}