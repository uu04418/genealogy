package cn.com.gene.mymapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.Genemessconn;
import cn.com.gene.pojo.Surname;
import cn.com.gene.queryvo.GenemessCustomer;
import cn.com.gene.queryvo.GenemessVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;

public interface GeneCustomerMapper {
	
	//查询所有的姓氏
	List<Surname> searchsurname(Surname realname);
	
	// 更新配偶
	void updatewife(@Param("profileid")Long profileid, @Param("spouseid")  Long spouseid);
	
	// 查询兄弟姐妹的父ID
	Long getfatheridbybrotherids( @Param ("idsList") String[] brotherids);
	
	//同步所有的兄弟姐妹一个父亲
	void updatefatherbyuserids( @Param ("idsList") String[] brotherids, @Param ("fatherid") Long fatherid);
	
	
	// 更新父亲
	void updatefatherbyuserid(@Param("profileid") Long profileid,@Param("fatherid") Long fatherid);
	
	// 查询第一代的数据
	List<UserCustomer> firstgene(Long genemessid);

	List<UserCustomer> searchnextgene(  @Param ("idsList") List<Long> array);
	
	// 根据userid 查询数据
	UserCustomer searchuserbyuserid(Long userid);
	
	//根据家族ID 查询家族基本信
	GenemessCustomer searchgenemess(GenemessCustomer search);
	
	// 查询氏族成员
	List<UserCustomer> searchgeneuser(Long genemessid);
	
	// 氏族列表页面
	List<GenemessCustomer> genearray(GenemessVo genemessVo);
	int genearrayCount(GenemessVo genemessVo);

	List<Surname> surnames(GenemessVo genemessVo);
	int surnamesCount(GenemessVo genemessVo);
	
	//我加入的家族列表页面
	List<GenemessCustomer> mygenearray(@Param("genemessids") String genemessids ,@Param("pagequery") PageQuery pagequery);
	int mygenearrayCount( @Param("genemessids") String gennemessids);
	
	//校验是否是氏族成员
	Genemessconn checkgeneuser(@Param("profileid") Long profileid , @Param("genemessid") Long genemessid );


	List<UserCustomer> checkbrother(@Param("userid") String alluserids,
			@Param("realname")String realname,  @Param("rankings") Integer rankings ,@Param("myid") Long userid);


	List<UserCustomer> searchgenesignuser(GenemessVo genemessVo);
	int searchgenesignuserCount(GenemessVo genemessVo);

	// 查询学历的统计
	List<PosCustomer> honorpostype(@Param("fatherid")Long fatherid ,@Param("genemessid") Long genemessid);
	
	//查询职位的统计
	List<PosCustomer> honoredutype(Long genemessid);
	
	//改变成员在家族中的地位
	void changegeneusersate(Genemessconn checkgene);

	void synchuserid(@Param("userid")Long userid,@Param("newuserid") Long newuserid);


	List<String> searchhonorposname(@Param("positionid")Long positionid,@Param("genemessid") Long genemessid);

	List<String> searchhonoreduname(@Param("educationid")Long educationid, @Param("genemessid") Long genemessid);
	
	// 先查首级职称
	List<PosCustomer> honorpostfatherype(Long genemessid);
	
	// 判断用户是否已经有氏族
	Genemess checkaddgene(@Param("profileid") Long profileid,@Param("likenameone") String likenameone, 
			@Param("likenametwo")	String likenametwo);
	
	// 获取当前用户 的基本信息
	UserCustomer searchcheckduser(Long userid);
	
	// 地图找 家族成员
	List<UserCustomer> searchgeneusermap (Long genemessid);

	
	
}