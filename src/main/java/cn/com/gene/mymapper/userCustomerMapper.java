package cn.com.gene.mymapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.pojo.Invite;
import cn.com.gene.queryvo.UserCustomer;


public interface userCustomerMapper {
   
	/**
	 * 根据电话号码查询用户的基本信息
	 * **/
	UserCustomer getUserMessagebytelephone(String telephone);
	
	/**
	 * 根据电话号码重置登录安全码
	 * **/
	void updateaccesstokenbytelephone(UserCustomer userCustomer);
	
	/**
	 * 根据userid 获取用户基本信息
	 * **/
	UserCustomer getcommonusermess(Long userid);
	
	/**
	 * 根据userid查询用户的基本信息
	 * **/
	UserCustomer getUserMessagebyuserid(Long userid);
	
	/**
	 * 查询氏族中自己的基本信息
	 * **/
	UserCustomer searchgenemyself(Long profileid);

	List<UserCustomer> searchgeneuser(Long profileid);


	
	// 这里查询所有的孙子 和孙女

	List<UserCustomer> searchmysons(@Param("idlist")List<Long> sonarry);
	
	
	//查询编辑的资料
	UserCustomer searchupdatemess(Long userid);
	
	/// 获取通讯录
	List<UserCustomer> searchphone(Long genemessid);
	
	// 同步fahterid
	void sysnfatherid(@Param("userid")Long userid,@Param("newuserid") Long userid2);
	
	// 同步userid
	void sysnuserid(@Param("userid")Long userid,@Param("newuserid") Long userid2);
	
	//同步配偶ID
	void sysnspouceid(@Param("userid")Long userid,@Param("newuserid") Long userid2);
	
	//查询用户基本资料【个人中心】
	UserCustomer searchinfo(Long userid);
	
	/**
	 * 获取推荐人的最高级别
	 * **/
	Invite getmaxuserlevel(Long userid);
	
	/**
	 * 查询我的队员
	 * **/
	List<UserCustomer> myteam(@Param("userid")Long userid, 
		@Param("pagequery")	PageQuery pagequery,@Param("searchwhere") String searchstr);
	int myteamCount(@Param("userid")Long userid, @Param("searchwhere") Integer level);
	/**
	 * 查询我的队员
	 * **/
	List<UserCustomer> myteamson(@Param("userid")Long userid, @Param("sessionid")Long sessionid,
		@Param("pagequery")	PageQuery pagequery);
	int myteamsonCount(@Param("userid")Long userid, @Param("sessionid")Long sessionid);
	
	/**
	 * 查询 队员表中 最小级别level 也就是最大级别
	 * **/
	Invite searchminlevel(Long userid);
	
	
	/**
	 * 查询当前用户的兄弟姐妹
	 * **/
	List<UserCustomer> searchmybrothers(@Param("profileid")Long profileid, @Param("fatherid")Long fatherid);
	
	/**
	 * @param expertid   排除自己的ID
	 * @param userid 直接ID
	 * @param rankings 排行
	 * @param realname 真实姓名
	 * **/
	UserCustomer checknameandraking(@Param("rankings")Integer rankings, 
			@Param("realname")String realname,@Param("userid") Long userid,@Param("expertid") Long expertid);

	UserCustomer checktoken(@Param("telephone") String telephone, @Param("accesstoken")String accesstoken);

	



	}