package cn.com.gene.service;

import java.util.List;
import java.util.Map;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Invite;
import cn.com.gene.pojo.Profile;
import cn.com.gene.queryvo.UserCustomer;

public interface UserService {
	
	/**
	 * 通过电话号码和短信登录
	 * @param telephone : 当前用户的电话号码
	 * @param code : 验证码
	 * @param rephone : 推荐人电话号码
	 * **/
	ResultMap codelogin(String telephone, String code ,Long rephone);
	
	/**
	 * 根据电话号码创建新的user对象
	 * @param telephone : 用户电话号码
	 * **/
	UserCustomer createuserbytelephone(String telephone );
	
	/**根据电话号码查询用户信息
	 * @param telephone : 用户电话号码
	 * */
	UserCustomer getUserMessagebytelephone(Long telephone);
	
	/**
	 * 插入短信验证码
	 * @param code : 接收的验证码
	 * @param telephone : 电话号码
	 * **/
	ResultMap insertcheckcode(String code, Long telephone);
	
	/**
	 * 实名认证
	 * @param realname : 用户的真实姓名
	 * **/
	ResultMap signuser(Profile profile);
	
	/**
	 * 通过token 获取userid
	 * **/
	Long getuseridbytoken(String accesstoken);
	
	/**
	 * 添加氏族时候查询资料
	 * @param relationid : 人家关系主键
	 * @param userid : 用户id
	 * **/
	UserCustomer searchuser(Long relationid, Long userid);
	
	/**
	 * 进入用户个人中心
	 * @param userid : 用户的唯一主键
	 * **/
	UserCustomer searchinfo(Long userid);
	
	/**校验是否是粉丝
	 *  @param userid : 当前用户userid
	 *  @param sessionuserid : session的用户ID
	 * 
	 * **/
	Integer findIsFans(Long userid, Long sessionuserid);
	
	/**
	 * 更新用户信息时候根据userid 查询用户基本信息
	 * @param userid :session 用户ID
	 * **/
	UserCustomer searchcommoninfo(Long userid);
	
	
	/**修改用户信息
	 * @param userid : session 用户ID
	 * @param code : 验证码
	 * @param userCustomer :  封装的用户基本信息资料 
	 * **/
	ResultMap updatecommoninfo(Long userid, UserCustomer userCustomer ,String code);
	
	/**
	 * 创建新用户和推荐人的关系
	 * @param userid : 当前用户ID
	 * @param rephone : 推荐人电话
	 * **/
	Map<String, Object> addconnectionlevel(Long userid, Long rephone);
	
	/**
	 * 查询我的团队
	 * @param userid : 当前用户ID
	 * @author hyg
	 * @param level : 当前用户的级别
	 * **/
	List<UserCustomer> myteam(Long userid, PageQuery pagequery ,Integer level);
	int myteamCount(Long userid, Integer level);
	
	Invite searchminlevel(Long userid);
	/**查询二级队员
	 * @param userid : 传过来的userid
	 * @param sessionid : 通过accesstoken 获取的userid 也就是当前用户ID
	 * @param pagequery : 分页参数
	 * **/
	List<UserCustomer> myteamson(Long userid, Long sessionid, PageQuery pagequery);
	int myteamsonCount(Long userid, Long sessionid);

	/**校验TOKEN 是否正确
	 * @param telephone : 电话号码
	 * @param accesstoken : 传入的token
	 * **/
	boolean checktoken(String telephone, String accesstoken);
	
	/**同步用户经纬度
	 * @param profile : 接收经纬度
	 * @param sessionid : 登录用户
	 * **/
	ResultMap synclatandlog(Profile profile, Long sessionid);
	

}
