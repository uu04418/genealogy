 package cn.com.gene.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import cn.com.gene.comm.DateUtil;
import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.CodeMapper;
import cn.com.gene.mapper.GenemessMapper;
import cn.com.gene.mapper.GenemessconnMapper;
import cn.com.gene.mapper.ProfileMapper;
import cn.com.gene.mapper.RelationMapper;
import cn.com.gene.mapper.SurnameMapper;
import cn.com.gene.mapper.UserMapper;
import cn.com.gene.mapper.UserconnMapper;
import cn.com.gene.mapper.UsersignMapper;
import cn.com.gene.mymapper.GeneCustomerMapper;
import cn.com.gene.mymapper.userCustomerMapper;
import cn.com.gene.pojo.Code;
import cn.com.gene.pojo.CodeExample;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.GenemessExample;
import cn.com.gene.pojo.Genemessconn;
import cn.com.gene.pojo.GenemessconnExample;
import cn.com.gene.pojo.Profile;
import cn.com.gene.pojo.Relation;
import cn.com.gene.pojo.Surname;
import cn.com.gene.pojo.User;
import cn.com.gene.pojo.UserExample;
import cn.com.gene.pojo.Userconn;
import cn.com.gene.pojo.UserconnExample;
import cn.com.gene.pojo.Usersign;
import cn.com.gene.pojo.UsersignExample;
import cn.com.gene.queryvo.GenemessCustomer;
import cn.com.gene.queryvo.GenemessVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.GeneService;

@Service
public class GeneServiceImpl implements GeneService{

	@Autowired HttpServletRequest request;
	@Autowired userCustomerMapper userCustomerMapper;
	@Autowired SurnameMapper surnameMapper;
	@Autowired GenemessMapper genemessMapper;
	@Autowired GenemessconnMapper genemessconnMapper;
	@Autowired GeneCustomerMapper geneCustomerMapper;
	@Autowired RelationMapper relationMapper;
	@Autowired UserMapper userMapper;
	@Autowired ProfileMapper profileMapper;
	@Autowired UserconnMapper userconnMapper;
	@Autowired UsersignMapper usersignMapper;
	@Autowired CodeMapper codeMapper;
	public ResultMap creategene(Genemess genemess ,Long detailid) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) 
			{return ResultMap.build(400,"未知用户！");}
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		if (userCustomer == null )
			{return ResultMap.build(400,"未知用户");}
		if (userCustomer.getSignstate() !=2) 
			{return ResultMap.build(400, "此操作需要实名！"); }
		if (genemess.getCode() == null) 
			{return ResultMap.build(400, "请选择区域！");}
		if (genemess.getGenename() == null || "".equals(genemess.getGenename())) 
			{return ResultMap.build(400, "输入家族名称!");}
		if (genemess.getPicture() == null || "".equals(genemess.getPicture())) 
			{ genemess.setPicture("");}
		if (genemess.getDescname() == null || "".equals(genemess.getDescname())) 
			{genemess.setDescname("");}
		if (genemess.getSurnameid() == null) 
			{ return ResultMap.build(400,"选择姓氏");}
		//校验同区域下是否占用家族名称
		Genemess check = checkGenemessbyCode (genemess.getCode() , genemess.getGenename());
		if (check!=null) {return ResultMap.build(400, "家族占用");}
		String likenameone = userCustomer.getRealname().substring(0,1);
		String likenametwo = userCustomer.getRealname().substring(0, 2);
		Genemess checkgene = this.checkaddgene(userCustomer.getProfileid(),likenameone ,likenametwo);
		if (checkgene !=null) {return ResultMap.build(400, "已有氏族！");}
		genemess.setCreatetime(new Date());
		genemess.setUpdatetime(new Date());
		genemess.setDetailid(detailid);
		genemess.setProfileid(userCustomer.getProfileid());
		genemessMapper.insertSelective(genemess);
		
		// 建立和家族的关系
		Genemessconn geneconn = new Genemessconn();
		geneconn.setGenemessid(genemess.getGenemessid());
		geneconn.setState(1);
		geneconn.setUpdatetime(new Date());
		geneconn.setProfileid(userCustomer.getProfileid());
		genemessconnMapper.insertSelective(geneconn);
		
		//判断人物关系表是否有数据
		UserconnExample example = new UserconnExample();
		UserconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(userCustomer.getProfileid());
		List<Userconn> search = userconnMapper.selectByExample(example);
		if (search.isEmpty() || search.size() == 0) {
			Userconn inserconn = new Userconn();
			inserconn.setProfileid(userCustomer.getProfileid());
			userconnMapper.insertSelective(inserconn);
		} else {
			Userconn chekcspo = search.get(0);
			Long spouceid = chekcspo.getSpouseid();
			if (spouceid !=null) {
				Genemessconn inspo = new Genemessconn();
				inspo.setGenemessid(genemess.getGenemessid());
				inspo.setState(3);
				inspo.setUpdatetime(new Date());
				inspo.setProfileid(spouceid);
				genemessconnMapper.insertSelective(inspo);
			}
		}
		
		
		return ResultMap.build(200, "创建成功" ,genemess.getGenemessid());
	}
	public Genemess checkaddgene(Long profileid, String likenameone, String likenametwo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.checkaddgene(profileid,likenameone,likenametwo);
	}
	private Genemess checkGenemessbyCode(Long code, String genename) {
		GenemessExample example = new GenemessExample();
		GenemessExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		criteria.andGenenameEqualTo(genename);
	    List<Genemess> list = genemessMapper.selectByExample(example);
	    if (list.size() >0 && !list.isEmpty()) {return list.get(0);}
		return null;
	}
	@Override
	public List<Surname> searchsurname(String realname) {
		if (realname !=null && !"".equals(realname)) {
			realname = realname.substring(0,1);
		}
		Surname search = new Surname();
		search.setDetailname(realname);
		return geneCustomerMapper.searchsurname(search);
	}
	@Override
	public List<Relation> searchrelation() {
		return relationMapper.selectByExample(null);
	}
	
	/**添加氏族成员
	 *  对应的应该是profileid
	 * **/ 
	public ResultMap addrelation(Profile profile, Long telephone ,Long relationid,Long genemessid ,Long profileid,
			@RequestParam("") String code ,Long userid) {
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		if (userCustomer == null) {return ResultMap.build(400, "查无用户");}
		if (genemessid == null) {return ResultMap.build(400, "选择家族！");}
		// 判断当前登录用户是否是氏族成员
		Genemessconn myself = geneCustomerMapper.checkgeneuser(userCustomer.getProfileid() , genemessid); 
		if (myself == null) {return ResultMap.build(300,"非氏族成员");}
		if (profile.getRankings() < 0) {return ResultMap.build(400, "请输入有效排行！");}
		if (profile.getLatitude() == null || profile.getLongitude() == null) {
			return ResultMap.build(400, "请检查成员定位!");
		}
		// 如果验证码不为空需要做验证码校验
		if (!"".equals(code) &&code !=null) {
			 // 这里需要做验证码校验
			Code code_search = getcheckcode(telephone);
			if (code_search == null) {
				return ResultMap.build(101, "验证码失效");
			}
			String code_get = "";
			if (code != null && !"".equals(code)) {
				code_get = getBASE64(code);
			}
			if (!code_search.getCode().equals(code_get)) {
				return ResultMap.build(102,"验证码错误");
			}
		} 
		// 获取当前登录用户的 profileid
		Long addprofileid = userCustomer.getProfileid();
		// 说明是帮助别人建人物关系 / 判断当事人是否是氏族成员
		if (profileid !=null ) { 
			addprofileid = profileid;
			Genemessconn checkconn = geneCustomerMapper.checkgeneuser(addprofileid , genemessid); 
			if (checkconn == null) {return ResultMap.build(300,"非氏族成员");}
		}
		 
		
		// 建立人物关系
		ResultMap resultMap = createuserconn(profile,telephone,addprofileid,relationid ,genemessid ,code);
		return resultMap;
	}
	
	

	
	/**@param profile : 传入的 用户基本信息
	 * @param user : 主要封装传入的电话号码
	 * @param addprofileid : 最终和这个用户建立关系
	 * @param realtionid : 关系主键
	 * @param genemessid : 家族ID
	 * @param code : 验证码
	 * 
	 * **/
	private ResultMap createuserconn(Profile profile, Long telephone, Long addprofileid, 
			Long relationid,Long genemessid,String code) {
	
		// 获取当前用户的基本信息 /或者当事人的基本信息 
		UserCustomer userCustomer = geneCustomerMapper.searchcheckduser(addprofileid);
		
		if (relationid != 9) {
			if (userCustomer.getRankings() == 0) {return ResultMap.build(400,"先发布本人信息");}
		}
		/**校验是否有重复的排行和 姓名
		UserCustomer checknameandraking = userCustomerMapper.checknameandraking(profile.getRankings() ,profile.getRealname(),userid ,expertid);
		if (checknameandraking !=null) {
			return ResultMap.build(400, "排行/姓名重复");
		}
		**/ 
		Long fatherid = userCustomer.getFatherid();
		// 说明没有父亲
		if (fatherid == null ) { 
			fatherid = createunknowuser("男",genemessid); 
			// 这里要建立和父亲的关联
			geneCustomerMapper.updatefatherbyuserid(addprofileid, fatherid);
			// 人物控制表也需要设置
			Userconn userconn = new Userconn();
			userconn.setProfileid(fatherid);
			userconnMapper.insertSelective(userconn);
		}
		
		//  添加 外祖母
		if (relationid == 7) {
			// 校验祖父是否存在
			Userconn grandfather = checkfather(fatherid);  
			if (grandfather == null) {
				//创建祖父
				Long grandfatherid =createunknowuser("男", genemessid);
				// 建立父亲的关联
				createfatherconn(fatherid,grandfatherid);
				// 创建外祖父
				Long waigrandfatherid = createunknowuser("男", genemessid);
				createfatherconn(grandfatherid,waigrandfatherid);
				// 创建外祖母
				profile.setSex("女");
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				Long waimother = createnewuser(profile , telephone ,genemessid,"no");
				createwiferconn(waigrandfatherid ,waimother);
			} else {
				// 拿到祖父ID
				Long grandfatherid = grandfather.getFatherid();
				// 校验祖父是否存在 【处理外祖父】
				Userconn waigrandfather = checkfather(grandfatherid);  
				if (waigrandfather == null) {
					// 创建外祖父
					Long waigrandfatherid = createunknowuser("男", genemessid);
					createfatherconn(grandfatherid,waigrandfatherid);
					// 创建外祖母
					profile.setSex("女");
					User check = checktelephone(telephone);
					if (check !=null) {return ResultMap.build(300, "电话占用") ;};
					Long waimother = createnewuser(profile , telephone,genemessid ,"no" );
					createwiferconn(waigrandfatherid ,waimother);
				}else {
					Long waigrandfatherid = waigrandfather.getFatherid();
					Userconn waigrandmother = checkwifer(waigrandfatherid);
					if (waigrandmother == null) {
						// 创建外祖母
						profile.setSex("女");
						User check = checktelephone(telephone);
						if (check !=null) {return ResultMap.build(300, "电话占用") ;};
						Long waimother = createnewuser(profile , telephone ,genemessid,"no");
						createwiferconn(waigrandfatherid ,waimother);
					} else {
						Long waigrandmotherid = waigrandmother.getSpouseid();
						// 更新外祖母
						profile.setProfileid(waigrandmotherid);
						profile.setSex("女");
						updateprofile(profile);
					}
				}
			}
		}
		
		// 如果添加的是外祖父
		if (relationid == 6) {
			// 通过父亲的 ID 校验 父亲 的父亲是否存在
			Userconn grandfather = checkfather(fatherid);  
			if (grandfather == null) {
				//创建祖父
				Long grandfatherid = createunknowuser("男", genemessid);
				// 建立 祖父 和 父亲的关联
				createfatherconn(fatherid,grandfatherid);
				// 创建外祖父
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				profile.setSex("男");
				Long waigrandfatherid = createnewuser(profile , telephone ,genemessid ,"yes");
				createfatherconn(grandfatherid,waigrandfatherid);
			} else {
				// 拿到祖父ID
				Long grandfatherid = grandfather.getFatherid();
				// 校验祖父是否存在 【处理外祖父】
				Userconn waigrandfather = checkfather(grandfatherid);  
				if (waigrandfather == null) {
					// 创建外祖父
					User check = checktelephone(telephone);
					if (check !=null) {return ResultMap.build(300, "电话占用") ;};
					Long waigrandfatherid = createnewuser(profile , telephone , genemessid ,"yes");
					createfatherconn(grandfatherid,waigrandfatherid);
				}else {
					Long waigrandfatherid = waigrandfather.getFatherid();
					profile.setSex("男");
					profile.setProfileid(waigrandfatherid);
					updateprofile(profile);
				}
			}
		}
		
		// 如果添加的是祖母
		if (relationid == 5) {
			// 校验祖父是否存在 【处理祖父】
			Userconn grandfather = checkfather(fatherid);  
			if (grandfather == null) {
				//创建祖父
				Long grandfatherid = createunknowuser("男", genemessid);
				// 建立父亲的关联
				createfatherconn(fatherid,grandfatherid);
				// 创建祖母
				profile.setSex("女");
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				Long grandmotherid = createnewuser(profile , telephone ,genemessid ,"no");
				createwiferconn(grandfatherid ,grandmotherid);
			} else {
				Long grandfatherid = grandfather.getFatherid();
				// 校验祖母
				Userconn grandmother = checkwifer(grandfatherid);  
				if (grandmother == null) {
					// 创建祖母
					profile.setSex("女");
					User check = checktelephone(telephone);
					if (check !=null) {return ResultMap.build(300, "电话占用") ;};
					Long grandmotherid = createnewuser(profile , telephone ,genemessid,"no" );
					createwiferconn(grandfatherid ,grandmotherid );
				} else {
					Long grandmotherid = grandmother.getSpouseid();
					// 更新外祖母
					profile.setProfileid(grandmotherid);
					updateprofile(profile);
				}
			}
		}
		
		
		// 如果添加的是祖父
		if (relationid == 4) {
			// 先判断当前用户是否有父亲     [ 处理父亲 ]
			profile.setSex("男");
			// 校验祖父是否存在 【处理祖父】
			Userconn grandfather = checkfather(fatherid);  
			if (grandfather == null) {
				//创建祖父
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				Long grandfatherid = createnewuser(profile , telephone ,genemessid ,"yes");
				// 建立父亲的关联
				createfatherconn(fatherid,grandfatherid);
			} else {
				Long grandfatherid = grandfather.getFatherid();
				profile.setProfileid(grandfatherid);
				updateprofile(profile);
			}
			
		}
		
		
		// 如果添加的是母亲
		if (relationid == 3) {
			Userconn mother = checkwifer(fatherid);
			if (mother == null) {
				// 创建母亲
				profile.setSex("女");
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				Long motherid = createnewuser(profile , telephone ,genemessid,"no");
				createwiferconn(fatherid ,motherid);
			}else {
				Long motehrid = mother.getSpouseid();
				profile.setProfileid(motehrid);
				updateprofile(profile);
			}
			
		}
		
		//如果添加的父亲
		if (relationid == 2) {
			profile.setProfileid(fatherid);
			profile.setSex("男");
			updateprofile(profile);
		}
		
		
		// 创建配偶
		if (relationid == 8) {
			// 先判断当事人 性别
			if (profile.getSex().equals(userCustomer.getSex())) {
				return ResultMap.build(400,"配偶性别冲突！");
			}
			Userconn wife = checkwifer(addprofileid);
			if (wife == null) {
				//创建配偶
				User check = checktelephone(telephone);
				if (check !=null) {return ResultMap.build(300, "电话占用") ;};
				Long wifeid = createnewuser(profile , telephone ,genemessid,"no");
				createwiferconn(addprofileid ,wifeid);
			} else {
				Long wifeid = wife.getSpouseid();
				profile.setProfileid(wifeid);
				updateprofile(profile);
			}
		}
		
		//添加兄弟姐妹
		if (relationid == 1) {
			User check = checktelephone(telephone);
			if (check !=null) {return ResultMap.build(300, "电话占用") ;};
			Long brotherid = createnewuser(profile , telephone ,genemessid,"yes");
			createbrotherconn(brotherid ,fatherid);
		}
		
		// 添加本人资料 [处理一下更新操作]
		if (relationid == 9) {
			if (!"".equals(code)) {
				// 判断此用户在系统中是否有注册
				UserCustomer usercheck =userCustomerMapper.getUserMessagebytelephone(telephone+"");
				// 说明在本系统中，已经有数据了。
				if (usercheck !=null) {
					// 先校验实名 
					if (usercheck.getSignstate() !=2) {
						return ResultMap.build(400, "此操作需要实名!");
					}
					if (! profile.getRealname().equals(usercheck.getRealname())) {
						return ResultMap.build(400,"姓名不一致");
					}
				}
			}
			profile.setProfileid(addprofileid);
			updateprofile(profile);
		}
		
		//添加儿女
		if (relationid == 10) {
			// 校验当前是否是父亲
			if ("女".equals(userCustomer.getSex().trim())) {
				if (userCustomer.getSpouseid() == null) 
				{return ResultMap.build(400,"先完善配偶信息!");}
				addprofileid = userCustomer.getSpouseid();
			}
			User check = checktelephone(telephone);
			if (check !=null) {return ResultMap.build(300, "电话占用") ;};
			Long sonid = createnewuser(profile , telephone ,genemessid ,"yes");
			//建立儿女关系
			createsonconn(addprofileid ,sonid );
		}
		
		return ResultMap.build(200, "添加成功");
	}
	
	
	
	private Userconn getcurrentuserfatheruserid(Long profileid) {
		UserconnExample userconn = new UserconnExample();
		UserconnExample.Criteria criteria = userconn.createCriteria();
		criteria.andProfileidEqualTo(profileid);
		List<Userconn> searchcurrent = userconnMapper.selectByExample(userconn);
		return searchcurrent.get(0);
	}
	// userid : 当事人ID  sonid ：儿女ID 
	private void createsonconn(Long profileid, Long sonid ) {
		//建立和父亲的关联
		Userconn inrelation = new Userconn();
		inrelation.setFatherid(profileid);
		inrelation.setProfileid(sonid);
		inrelation.setSpouseid(null);
		userconnMapper.insertSelective(inrelation);
	}
	// 创建兄弟姐妹时候和父亲
	private void createbrotherconn(Long brotherid,Long fatherid) {
		Userconn userconn = new Userconn();
		userconn.setProfileid(brotherid);
		userconn.setFatherid(fatherid);
		userconnMapper.insertSelective(userconn);
	}
	private Userconn checkwifer(Long waigrandfatherid) {
		UserconnExample example = new UserconnExample();
		UserconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(waigrandfatherid);
		criteria.andSpouseidIsNotNull();
		userconnMapper.selectByExample(example);
		List <Userconn> wife = userconnMapper.selectByExample(example);
		if ( !wife.isEmpty() && wife.size() >0) {
			return wife.get(0);
		}
		return null;
	}
	private void createwiferconn(Long profileid, Long spouseid) {
		
		//判断当前userid 在控制表是否有数据
		UserconnExample check = new UserconnExample();
		UserconnExample.Criteria criterion = check.createCriteria();
		criterion.andProfileidEqualTo(profileid);
		List<Userconn> reutn = userconnMapper.selectByExample(check);
		if (reutn.isEmpty()) {
			Userconn userconn = new Userconn();
			userconn.setProfileid(profileid);
			userconn.setSpouseid(spouseid);
			userconnMapper.insertSelective(userconn);
		} else {
			geneCustomerMapper.updatewife(profileid , spouseid);
		}
		
		// 判断配偶在数据中是否有数据
		UserconnExample checkspouce = new UserconnExample();
		UserconnExample.Criteria criteriaspouce = checkspouce.createCriteria();
		criteriaspouce.andSpouseidEqualTo(profileid);
		List<Userconn> spoucecheck = userconnMapper.selectByExample(checkspouce);
		if (spoucecheck.isEmpty()) {
			Userconn inwife = new Userconn();
			inwife.setProfileid(spouseid);
			inwife.setSpouseid(profileid);
			userconnMapper.insertSelective(inwife);
		}else {
			geneCustomerMapper.updatewife(spouseid , profileid);
		}  
	}
	private void createfatherconn(Long profileid, Long fatherid) {
		UserconnExample check = new UserconnExample();
		UserconnExample.Criteria criteria = check.createCriteria();
		criteria.andProfileidEqualTo(profileid);
		List<Userconn> chekcs = userconnMapper.selectByExample(check);
		if (! chekcs.isEmpty() && chekcs.size() > 0 ) {
			// 这里做更新
			geneCustomerMapper.updatefatherbyuserid(profileid, fatherid);
		}else {
			Userconn userconn = new Userconn();
			userconn.setFatherid(fatherid);
			userconn.setProfileid(profileid);
			userconnMapper.insertSelective(userconn);
		}
		
		// 判断父亲在控制表是否有数据
		UserconnExample checkfahter = new UserconnExample();
		UserconnExample.Criteria fathercri = checkfahter.createCriteria();
		fathercri.andProfileidEqualTo(fatherid);
		List<Userconn> checkfas  = userconnMapper.selectByExample(checkfahter);
		if (checkfas.isEmpty() || checkfas.size() == 0 ) {
			Userconn userconn = new Userconn();
			userconn.setProfileid(fatherid);
			userconnMapper.insertSelective(userconn);
		}
		
	}
	
	public Long createunknowuser(String sex ,Long genemessid ) {
		Profile inprofile = new Profile();
		// 第二步创建profile
		inprofile.setAvatar("http://ou1sxnil3.bkt.clouddn.com/d7b6b65a-5ee1-4d6f-9f18-5a6fbc589387");
		inprofile.setEducationid(1L);
		//inprofile.setOpenid("");
		inprofile.setPositionid(5L);
		inprofile.setRankings(1);
		inprofile.setRealname("");
		inprofile.setSex(sex);
		inprofile.setIntroduction("");
		inprofile.setIdentity("");
		inprofile.setSignstate(1); //设置没有实名
		inprofile.setUpdatetime(new Date());
		inprofile.setUsername("JP_001");
		inprofile.setWeixinnub("");
		inprofile.setWorkunit("");
		inprofile.setZodiac("蛇");
		profileMapper.insert(inprofile);
		
		
		// 建立和家族直接的关系
		Genemessconn createrelation = new Genemessconn();
		createrelation.setState(2);
		createrelation.setUpdatetime(new Date());
		createrelation.setProfileid(inprofile.getProfileid());
		createrelation.setGenemessid(genemessid);
		genemessconnMapper.insertSelective(createrelation);
		return inprofile.getProfileid();
		
	}
	
	private Long createnewuser(Profile profile, Long telephone,Long genemessid,String createconn) {
		
		// 如果传入了电话号码 创建一个新的用户
		Long userid = null;
		if (telephone !=null) {
			User inuser = new User();
			inuser.setAccesstoken(String.valueOf(UUID.randomUUID()));
			inuser.setCreatetime(new Date());
			inuser.setPhyAddress("4d76087378d5f71bd9f994668e342e92e7894d80");
			inuser.setState(1);
			inuser.setTelephone(telephone);
			inuser.setUpdatetime(new Date());
			userMapper.insertSelective(inuser);
			userid = inuser.getUserid();
		}
		
		
		Profile inprofile = new Profile();
		// 第二步创建profile
		if (profile.getAvatar() == null || "".equals(profile.getAvatar())) {
			inprofile.setAvatar("http://ou1sxnil3.bkt.clouddn.com/d7b6b65a-5ee1-4d6f-9f18-5a6fbc589387");
		}else {
			inprofile.setAvatar(profile.getAvatar());
		}
		
		inprofile.setEducationid(profile.getEducationid()==null ? 1L:profile.getEducationid());
		//inprofile.setOpenid(profile.getOpenid()==null?"":profile.getOpenid());
		inprofile.setPositionid(profile.getPositionid() ==null ? 5L:profile.getPositionid());
		inprofile.setRankings(profile.getRankings()==null?1:profile.getRankings());
		inprofile.setRealname(profile.getRealname()==null?"":profile.getRealname());
		inprofile.setSex(profile.getSex()==null?"男":profile.getSex());
		inprofile.setIdentity(profile.getIdentity()==null?"":profile.getIdentity());
		inprofile.setSignstate(1); //设置没有实名
		inprofile.setUpdatetime(new Date());
		inprofile.setUserid(userid);
		inprofile.setUsername("JP_001");
		inprofile.setIntroduction(profile.getIntroduction() == null?"": profile.getIntroduction());
		inprofile.setWeixinnub(profile.getWeixinnub()==null?"":profile.getWeixinnub());
		inprofile.setWorkunit(profile.getWorkunit()==null?"":profile.getWorkunit());
		inprofile.setZodiac(profile.getZodiac()==null?"":profile.getZodiac());
		inprofile.setIntroduction(profile.getIntroduction()==null ? "": profile.getIntroduction());
		profileMapper.insert(inprofile);
		
		Genemessconn genemessconn = new Genemessconn();
		//是否和家族建立关系
		Integer state = 3;
		if ("yes".equals(createconn)) {
			// 家族控制表建立关联关系
			state = 2;
		}
		genemessconn.setGenemessid(genemessid);
		genemessconn.setState(state);
		genemessconn.setUpdatetime(new Date());
		genemessconn.setProfileid(inprofile.getProfileid());
		genemessconnMapper.insertSelective(genemessconn);
		return inprofile.getProfileid();
	}
	private Userconn checkfather(Long profileid) {
		UserconnExample example = new UserconnExample();
		UserconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(profileid);
		criteria.andFatheridIsNotNull();
		List<Userconn> list = userconnMapper.selectByExample(example);
		if (! list.isEmpty() && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	private void updateprofile(Profile profile) {
		profile.setUpdatetime(new Date());
		// 判断有没有实名
		Profile usercheck = profileMapper.selectByPrimaryKey(profile.getProfileid());
		if (usercheck.getSignstate() == 2) {profile.setRealname(null);}
		profileMapper.updateByPrimaryKeySelective(profile);
		
	}
	
	private User checktelephone(Long telephone) {
		if (telephone == null) {
				return null;
		}
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andTelephoneEqualTo(telephone);
		List<User> user = userMapper.selectByExample(example);
		if (!user.isEmpty() && user.size() > 0) {
			return user.get(0);
		}
		return null;
	}
	
	
	// 查询家族代数关系
	public List<UserCustomer> generelation( List<UserCustomer> userlist, 
			Long genemessid ,List<UserCustomer> searchlist,Integer index) {
		// 首先查询第一代
		String realname = "";
		if (userlist.isEmpty() || userlist.size() == 0) {
			userlist = geneCustomerMapper.firstgene(genemessid);
			if (userlist.isEmpty() || userlist.size() == 0 ) {return searchlist ; }
			for (UserCustomer user: userlist) {
				realname = user.getRealname();
				user.setIndex(index);
				user.setAddflag(true);
				// 判断 是否有配偶
				if (user.getSpouseid() !=null) {
					UserCustomer spouce = geneCustomerMapper.searchuserbyuserid(user.getSpouseid());
					if (spouce !=null) {
						spouce.setAddflag(false);
						spouce.setIndex(index);
						searchlist.add(spouce);
					}
					
				}
			}
			searchlist.addAll(userlist);
			// 判断是否有外祖母
		} else {
			List<Long> searcharr = new ArrayList<>();
			for (UserCustomer search : userlist) {
				searcharr.add(search.getProfileid());
				if (search.getSpouseid() !=null) {
					searcharr.add(search.getSpouseid());
				}
			}
			userlist = geneCustomerMapper.searchnextgene(searcharr);
			if (! userlist.isEmpty() && userlist.size() > 0) {
				for (UserCustomer user: userlist) {
					user.setIndex(index);
					realname = user.getRealname();
					user.setAddflag(true);
					// 判断 是否有配偶
					if (user.getSpouseid() !=null) {
						UserCustomer spouce = geneCustomerMapper.searchuserbyuserid(user.getSpouseid());
						if (spouce !=null) {
							spouce.setIndex(index);
							spouce.setAddflag(false);
							searchlist.add(spouce);
						}
					}
				}
				searchlist.addAll(userlist);
			}else {
				return searchlist;
			}
		}
		if (!"".equals(realname)) {  index ++;  }
		generelation(userlist,  genemessid ,searchlist ,index);
		return searchlist;
	}
	
	//根据家族ID 查询家族基本信息
	public GenemessCustomer searchgenemess(Long genemessid ,Integer type) {
		GenemessCustomer search = new GenemessCustomer();
		search.setGenemessid(genemessid);
		search.setType(type);
		return geneCustomerMapper.searchgenemess(search);
	}
	
	// 查询氏族成员
	public List<UserCustomer> searchgeneuser(Long genemessid) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.searchgeneuser(genemessid);
	}
	@Override
	public List<GenemessCustomer> genearray(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.genearray(genemessVo);
	}
	@Override
	public int genearrayCount(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.genearrayCount(genemessVo);
	}
	
	
	// 查询所有的姓氏
	public List<Surname> surnames(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.surnames(genemessVo);
	}
	 
	// 查询所有的姓氏
	public int surnamesCount(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.surnamesCount(genemessVo);
	}
	
	
	// 家族签到
	public ResultMap usersign(Long genemessid ,Integer type,Long userid) {
		if (genemessid == null) {return ResultMap.build(400,"家族不存在");}
		if (type == null || type <0 || type >11) {return ResultMap.build(400, "type类型不对");}
		// 校验家族中是否有该成员
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		GenemessconnExample example = new GenemessconnExample();
		GenemessconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(userCustomer.getProfileid());
		criteria.andGenemessidEqualTo(genemessid);
		List<Genemessconn> checkgene = genemessconnMapper.selectByExample(example);
		if (checkgene.isEmpty() || checkgene.size() == 0) {
			return ResultMap.build(400, "请加入家族");
		}
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = "yyyy-MM-dd HH:mm:ss";
		Date date1 = DateUtil.strToDate(format, sdf.format(date) + " 00:00:01");
		Date date2 = DateUtil.strToDate(format, sdf.format(date) + " 23:59:58");
		// 检查签到表是否有记录
		Usersign usersign = checkusersign(genemessid, userid ,type);
		if (usersign == null) {
			Usersign usersign_insert = new Usersign();
			usersign_insert.setSingcount(1);
			usersign_insert.setType(type);
			usersign_insert.setUpdate_time(new Date());
			usersign_insert.setOtherid(genemessid);
			usersign_insert.setUserid(userid);
			usersignMapper.insertSelective(usersign_insert);
			return ResultMap.build(200, "签到成功");
		}else {
			if (date1.before(usersign.getUpdate_time()) && usersign.getUpdate_time().before(date2)) {
				return ResultMap.build(400, "今天已经签到过");
			}
			Integer count = usersign.getSingcount();
			count++;
			usersign.setSingcount(count);
			usersign.setUpdate_time(new Date());
			usersignMapper.updateByPrimaryKeySelective(usersign);
			return ResultMap.build(200, "签到成功");
		}
	}
	
	
	private Usersign checkusersign( Long genemessid, Long userid ,Integer type) {
		UsersignExample example = new UsersignExample();
		UsersignExample.Criteria criteria = example.createCriteria();
		criteria.andOtheridEqualTo(genemessid);
		criteria.andTypeEqualTo(type);
		criteria.andUseridEqualTo(userid);
		List<Usersign> usersigns = usersignMapper.selectByExample(example);
		if (!usersigns.isEmpty() && usersigns.size() > 0) {
			return usersigns.get(0);
		}
		return null;
	}
	@Override
	public Genemessconn checkgeneflag(Long genemessid, Long userid) {
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		GenemessconnExample example = new GenemessconnExample();
		GenemessconnExample.Criteria criteria = example.createCriteria();
		criteria.andGenemessidEqualTo(genemessid);
		criteria.andProfileidEqualTo(userCustomer.getProfileid());
		List<Genemessconn> genes = genemessconnMapper.selectByExample(example);
		if (!genes.isEmpty() && genes.size() >0) {
			return genes.get(0);
		}
		return null;
	}

	// 加入氏族
	public ResultMap joingene(Long genemessid ,Integer state) {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(400, "未知用户");}
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		if (userCustomer == null) {return ResultMap.build(400, "未知用户");}
		if (genemessid == null) {return ResultMap.build(400, "选择氏族");}
		
		// 判断氏族控制表是否有数据
		Genemessconn checkgene = checkgeneflag(genemessid , userid);
		if (checkgene != null) {
			if (checkgene.getState() == 1 || checkgene.getState() ==2) {return ResultMap.build(300, "已经是氏族成员");}
			checkgene.setState(state);
			geneCustomerMapper.changegeneusersate(checkgene);
			return ResultMap.build(200, "操作成功");
			
		}
		Genemessconn ingene = new Genemessconn();
		ingene.setGenemessid(genemessid);
		ingene.setState(5); // 普通成员
		ingene.setUpdatetime(new Date());
		ingene.setProfileid(userCustomer.getProfileid());
		genemessconnMapper.insertSelective(ingene);
		
		return ResultMap.build(200, "加入成功");
	}
	
	// 查询氏族成员的userid
	public ResultMap geneuserdetail(Long profileid) {
		Map<Integer, List<UserCustomer>> skuIdMap = new HashMap<>();
		Userconn userconn = this.getcurrentuserfatheruserid(profileid);
		// 第一步 查询自己的信息
		UserCustomer  search =userCustomerMapper.searchgenemyself(profileid);
		if (search == null) {return null;}
		List<UserCustomer> bros = new ArrayList<>();
		if (userconn.getFatherid() !=null) {
			bros = userCustomerMapper.searchmybrothers(profileid , userconn.getFatherid());
		}
		
		if (! bros.isEmpty() && bros.size() > 0) {
			for (UserCustomer userbro :bros) {
				// 这里是兄 和 姐
				String brostr = IDUtils.foematInteger(userbro.getRankings());
				if (userbro.getRankings() < search.getRankings()) {
					userbro.setRankrelation(userbro.getSex().equals("男") ?brostr+"哥":brostr+"姐");
				}
				if (userbro.getRankings() > search.getRankings()) {
					userbro.setRankrelation(userbro.getSex().equals("男") ?brostr+"弟":brostr+"妹");
				}
			}
		}
		
		// 查询配偶的基本信息
		List<UserCustomer> searchwife = new ArrayList<>();
		if (search.getSpouseid() !=null) {
			UserCustomer wife  = userCustomerMapper.searchgenemyself(search.getSpouseid());
			if (wife !=null) {
				searchwife.add(wife);
				searchwife.get(0).setRankrelation("配偶");
			}
		}
		
		
		
		List<UserCustomer> searchfather = new ArrayList<>();
		UserCustomer searchmother = new UserCustomer();
		
		List<UserCustomer> grandfather = new ArrayList<>();
		UserCustomer grandmother = new UserCustomer();
		// 查询父亲的信息
		if (search.getFatherid() !=null) {
			searchfather = userCustomerMapper.searchgeneuser(search.getFatherid());
			if (!searchfather.isEmpty() && searchfather.size() > 0 ) {
				searchfather.get(0).setRankrelation("父亲");
				// 这里查询母亲的 信息
				if (searchfather.get(0).getSpouseid() !=null) {
					List<UserCustomer> mothers = userCustomerMapper.searchgeneuser(searchfather.get(0).getSpouseid());
					if ( !mothers.isEmpty() && mothers.size() > 0 ) {
						searchmother = mothers.get(0);
						searchmother.setRankrelation("母亲");
					}
					
				}
				
				// 这里查询祖父信息
				if (searchfather.get(0).getFatherid() !=null) {
					grandfather =  userCustomerMapper.searchgeneuser(searchfather.get(0).getFatherid());
					if (!grandfather.isEmpty() && grandfather.size() > 0 ) {
						grandfather.get(0).setRankrelation("祖父");						
						// 这里查祖母
						if (grandfather.get(0).getSpouseid() !=null) {
							List<UserCustomer> grandmothers =  userCustomerMapper.searchgeneuser(grandfather.get(0).getSpouseid());
							if (! grandmothers.isEmpty() && grandmothers.size() > 0) {
								grandmother = grandmothers.get(0);
								grandmother.setRankrelation("祖母");
							}
						}
						
					}
					
				}
			}
		}
		Long wifeid = search.getSpouseid();
		List<Long > sonarry = new ArrayList<>();  // 查询儿子
		List<UserCustomer> sons = new ArrayList<>(); 
		sonarry.add(profileid);
		if (wifeid !=null) {sonarry.add(wifeid);}
		sons =userCustomerMapper.searchmysons(sonarry);
		
		
		List<UserCustomer> sunzi = new ArrayList<>();  // 查询孙子
		sonarry.clear();
		if (!sons.isEmpty() && sons.size() > 0) {
			for (UserCustomer son :sons) {
				sonarry.add(son.getUserid());
			}
		}
		
		if (! sonarry.isEmpty() && sonarry.size() > 0) {
			sunzi = userCustomerMapper.searchmysons(sonarry);
		}
		List<UserCustomer> zensun = new ArrayList<>();
		sonarry.clear();
		if (! sunzi.isEmpty() && sunzi.size() > 0) {
			for (UserCustomer sunziid : sunzi) {
				sonarry.add(sunziid.getUserid());
			}
		}
		if (!sonarry.isEmpty() && sonarry.size() > 0) {
			zensun = userCustomerMapper.searchmysons(sonarry);
		}
		
		
		
		
		Map<String ,Object> map = new HashMap<>();
		searchfather.add(searchmother);
		grandfather.add(grandmother);
		skuIdMap.put(1, searchwife);    // 妻子
		skuIdMap.put(2, searchfather); // 父亲
		skuIdMap.put(3, grandfather);  // 祖父
		skuIdMap.put(4, bros);  // 兄弟姐妹
		skuIdMap.put(5, sons);  // 儿子
		skuIdMap.put(6, sunzi);  // 儿子
		skuIdMap.put(7, zensun);  // 儿子
		map.put("searchuser", search);
		map.put("skumap", skuIdMap);
		return ResultMap.IS_200(map);
	}
	@Override
	public List<GenemessCustomer> mygenearray(Long userid ,PageQuery pagequery) {
		List<GenemessCustomer> genes = new ArrayList<>();
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		// 查询用户对应的家族集合
		String gennemessids = searchgenemess(userCustomer.getProfileid());
		if ("".equals(gennemessids)) {return genes;}
		genes =  geneCustomerMapper.mygenearray(gennemessids , pagequery );
		return genes;
	}
	@Override
	public int mygenearrayCount(Long userid ) {
		Integer count = 0;
		// 查询用户对应的家族集合
		String gennemessids = searchgenemess(userid);
		if ("".equals(gennemessids)) {return count;}
		count =  geneCustomerMapper.mygenearrayCount(gennemessids );
		return count;
	}
	
	private String searchgenemess(Long userid) {
		String returnstr = "";
		GenemessconnExample example = new GenemessconnExample();
		GenemessconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(userid);
		List<Genemessconn> genes = genemessconnMapper.selectByExample(example);
		if (! genes.isEmpty() && genes.size() > 0) {
			for (Genemessconn gene :genes) {
				returnstr +=gene.getGenemessid() + ",";
			}
		}
		if (! "".equals(returnstr)) {
			returnstr = returnstr.substring(0, returnstr.length() - 1);
		}
		return returnstr;
	}
	@Override
	public List<UserCustomer> searchphone(Long genemessid) {
		// TODO Auto-generated method stub
		return userCustomerMapper.searchphone(genemessid);
	}
	@Override
	public int searchgenesignuserCount(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.searchgenesignuserCount(genemessVo);
	}
	@Override
	public List<UserCustomer> searchgenesignuser(GenemessVo genemessVo) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.searchgenesignuser(genemessVo);
	}
	@Override
	public List<PosCustomer> honorpostype(Long genemessid) {
		// 先查首级职业
		List<PosCustomer> fatherpos = geneCustomerMapper.honorpostfatherype (genemessid);
		if (!fatherpos.isEmpty() && fatherpos.size() > 0 ){
			for (PosCustomer father: fatherpos) {
				List<PosCustomer> post =  geneCustomerMapper.honorpostype(father.getTypeid() ,genemessid);
				if (!post.isEmpty() && post.size() > 0 ) {
					father.setChild(post);
					for (PosCustomer pos : post) {
						List<String> searchname = geneCustomerMapper.searchhonorposname(pos.getTypeid() , genemessid);
						pos.setUsername(searchname);
					}
				}
			}
		}
		
		return fatherpos ;
	}
	@Override
	public List<PosCustomer> honoredutype(Long genemessid) {
		// TODO Auto-generated method stub
		List<PosCustomer> edus =  geneCustomerMapper.honoredutype(genemessid);
		if (!edus.isEmpty() && edus.size() > 0) {
			for (PosCustomer edu :edus) {
				List<String> searchname = geneCustomerMapper.searchhonoreduname(edu.getTypeid(), genemessid);
				edu.setUsername(searchname);
			}
		} 
		return edus;
	}
	
	
	public Code getcheckcode(Long telephone) {
		Date current = new Date();
		Date before = new Date(current.getTime() - 1000000);
		Date after = new Date(current.getTime() + 1000000);
		CodeExample example = new CodeExample();
		CodeExample.Criteria criteria = example.createCriteria();
		criteria.andTelephoneEqualTo(telephone);
		criteria.andUpdatetimeBetween(before, after);
		List<Code> code_search = codeMapper.selectByExample(example);
		if (code_search.size() > 0 && !code_search.isEmpty()) {
			return code_search.get(0);
		}
		return null;
	}
	
	// 将 s 进行 BASE64 编码
	@SuppressWarnings("restriction")
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	@Override
	public List<UserCustomer> searchgeneusermap(Long genemessid) {
		// TODO Auto-generated method stub
		return geneCustomerMapper.searchgeneusermap(genemessid);
	}
	
}
