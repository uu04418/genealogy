package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.Genemessconn;
import cn.com.gene.pojo.Profile;
import cn.com.gene.pojo.Relation;
import cn.com.gene.pojo.Surname;
import cn.com.gene.queryvo.GenemessCustomer;
import cn.com.gene.queryvo.GenemessVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;

public interface GeneService {
	
	// 创建家族
	ResultMap creategene(Genemess genemess ,Long detailid);
	
	//根据真实姓名查询对应的姓氏
	List<Surname> searchsurname(String realname);
	
	// 查询添加人的关系
	List<Relation> searchrelation();
	
	// 添加氏族成员
	ResultMap addrelation(Profile profile, Long telephone ,Long relationid,Long genemessid ,Long adduserid ,String code,Long userid);
	
	//查询家族代数关系
	List<UserCustomer> generelation(List<UserCustomer> userlist, 
			Long genemessid ,List<UserCustomer> searchlist,Integer index);
	
	// 根据家族ID 查询家族的基本信息
	GenemessCustomer searchgenemess(Long genemessid, Integer type);
	
	// 查询氏族成员
	List<UserCustomer> searchgeneuser(Long genemessid);
	
	// 家族列表页面
	List<GenemessCustomer> genearray(GenemessVo genemessVo);
	int genearrayCount(GenemessVo genemessVo);

	List<Surname> surnames(GenemessVo genemessVo);
	int surnamesCount(GenemessVo genemessVo);
	
	// 家族签到
	ResultMap usersign(Long genemessid , Integer type,Long userid);
	
	// 校验用户是否有氏族
	Genemessconn checkgeneflag(Long genemessid, Long userid);

	// 加入氏族
	ResultMap joingene(Long genemessid ,Integer state);
	
	// 查询氏族成员的信息
	ResultMap geneuserdetail(Long userid);
	
	// 查询我加入的家族的列表页面
	List<GenemessCustomer> mygenearray(Long userid ,PageQuery pagequery);
	int mygenearrayCount(Long userid);
	
	// 查询通讯录
	List<UserCustomer> searchphone(Long genemessin);
	
	// 查询签到的 结合
	int searchgenesignuserCount(GenemessVo genemessVo);
	List<UserCustomer> searchgenesignuser(GenemessVo genemessVo);
	
	// 查询学历的统计
	List<PosCustomer> honorpostype(Long genemessid);
	
	// 查询职业的统计
	List<PosCustomer> honoredutype(Long genemessid);
	
	// 校验用户发布家族时候是否已经有家族
	Genemess checkaddgene(Long userid, String likenameone, String likenametwo);
	
	/**地图找家族成员
	 * @param genemessid : 家族ID
	 * **/
	List<UserCustomer> searchgeneusermap (Long genemessid );

	
	

}
