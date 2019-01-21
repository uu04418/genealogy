 package cn.com.gene.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gene.comm.HttpSign;
import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.CodeMapper;
import cn.com.gene.mapper.FansMapper;
import cn.com.gene.mapper.GenemessconnMapper;
import cn.com.gene.mapper.InteconnMapper;
import cn.com.gene.mapper.InviteMapper;
import cn.com.gene.mapper.ProfileMapper;
import cn.com.gene.mapper.UserMapper;
import cn.com.gene.mapper.UserconnMapper;
import cn.com.gene.mapper.VipcountMapper;
import cn.com.gene.mymapper.userCustomerMapper;
import cn.com.gene.pojo.Code;
import cn.com.gene.pojo.CodeExample;
import cn.com.gene.pojo.Fans;
import cn.com.gene.pojo.FansExample;
import cn.com.gene.pojo.Inteconn;
import cn.com.gene.pojo.InteconnExample;
import cn.com.gene.pojo.Invite;
import cn.com.gene.pojo.Profile;
import cn.com.gene.pojo.User;
import cn.com.gene.pojo.UserExample;
import cn.com.gene.pojo.Userconn;
import cn.com.gene.pojo.UserconnExample;
import cn.com.gene.pojo.Vipcount;
import cn.com.gene.pojo.VipcountExample;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired CodeMapper codeMapper;
	@Autowired userCustomerMapper userCustomerMapper;
	@Autowired UserMapper userMapper;
	@Autowired ProfileMapper profileMapper;
	@Autowired GenemessconnMapper genemessconnMapper;
	@Autowired UserconnMapper userconnMapper;
	@Autowired HttpServletRequest request;
	@Autowired FansMapper fansMapper;
	@Autowired VipcountMapper vipcountMapper;
	@Autowired InteconnMapper inteconnMapper;
	@Autowired InviteMapper inviteMapper;
	/**
	 * 通过短信验证码登录
	 * @param telephone : 登录电话号码
	 * @param code : 发达的短信验证码
	 * **/
	public ResultMap codelogin(String telephone, String code ,Long rephone) {
		
		Long userid = -100L;
		// 获取验证码 type =3表示更改手机号码
		if (telephone == null || "".equals(telephone)) {
			return ResultMap.build(100,"输入电话号码");
		}
		Code code_search = getcheckcode(Long.valueOf(telephone));
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
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebytelephone(telephone);
		if (userCustomer !=null) {
			//为了登录安全重置登录安全码
			userCustomer.setAccesstoken(String.valueOf(UUID.randomUUID()+telephone.substring(5, 11)));
			if (userCustomer.getState() == 2) {
				userid = userCustomer.getUserid();
			}
			userCustomer.setState(1);
			userCustomerMapper.updateaccesstokenbytelephone(userCustomer);
		} else {
			userCustomer =  createuserbytelephone( telephone);
			userid = userCustomer.getUserid();
		}
		// 这里要控制VIP表数据
		checkVipANDInte(userCustomer.getUserid());
		userCustomer.setUserid(userid);
		return ResultMap.IS_200(userCustomer);
		
	}
	
	
	




	private void checkVipANDInte(Long userid) {
		VipcountExample example = new VipcountExample();
		VipcountExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Vipcount> vips =vipcountMapper.selectByExample(example);
		if (vips.isEmpty() || vips.size() == 0) {
			// 说明VIP 表中无数据
			Vipcount invip = new Vipcount();
			invip.setAccount(0.0D);
			invip.setBalance(0.0D);
			invip.setCount(0);
			invip.setUserid(userid);
			invip.setCreatetime(new Date());
			invip.setIsvip(0);
			vipcountMapper.insertSelective(invip);
		}
		
		InteconnExample intexample = new InteconnExample();
		InteconnExample.Criteria intecriteria = intexample.createCriteria();
		intecriteria.andUseridEqualTo(userid);
		List<Inteconn> intes = inteconnMapper.selectByExample(intexample);
		if (intes.isEmpty() || intes.size() == 0) {
			Inteconn inteconn = new Inteconn();
			inteconn.setCount(100L);
			inteconn.setUpdatetime(new Date());
			inteconn.setUserid(userid);
			inteconnMapper.insertSelective(inteconn);
		}
		
	}




	/**
	 * 根据电话号码校验验证码
	 * @param telephone : 电话号码
	 * **/
	public Code getcheckcode(Long telephone) {
		Date current = new Date();
		Date before = new Date(current.getTime() - 100000000);
		Date after = new Date(current.getTime() + 100000000);
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


	/**根据电话号码创建一个新的用户对象
	 * @param telehphone : 电话号码
	 * 
	 * **/
	public UserCustomer createuserbytelephone(String telephone ) {
		//第一步,创建userid
		User insert = new User();
		insert.setAccesstoken(String.valueOf(UUID.randomUUID()+telephone.substring(5, 11)));
		insert.setCreatetime(new Date());
		insert.setPhyAddress("4d76087378d5f71bd9f994668e342e92e7894d80");
		insert.setState(1);
		insert.setTelephone(Long.valueOf(telephone));
		insert.setUpdatetime(new Date());
		userMapper.insertSelective(insert);
		
		//第二步创建profile
		Profile profile = new Profile();
		profile.setAvatar("http://ou1sxnil3.bkt.clouddn.com/d7b6b65a-5ee1-4d6f-9f18-5a6fbc589387");
		profile.setEducationid(1L);
		profile.setUserid(insert.getUserid());
		profile.setPositionid(5L);
		profile.setRankings(0);
		profile.setRealname("");
		profile.setSex("男");
		profile.setIdentity("");
		profile.setIntroduction("");
		profile.setSignstate(1); //设置没有实名
		profile.setUpdatetime(new Date());
		profile.setUsername("JP"+telephone.substring(7, 11));
		profile.setWeixinnub("");
		profile.setWorkunit("");
		profile.setZodiac("蛇");
		profile.setIntroduction("");
		profileMapper.insert(profile);
		
		
		
		// 查询返回的信息
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebytelephone(telephone);
		
		return userCustomer;
	}


	public UserCustomer getUserMessagebytelephone(Long telephone) {
		return userCustomerMapper.getUserMessagebytelephone(telephone+"");
	}

	/**发送验证码时候。同步code表中数据
	 * @param telehpne : 电话号码
	 * @param code : 发送的验证码
	 * 
	 * **/
	public ResultMap insertcheckcode(String code, Long telephone) {
		Code insert_code = new Code();
		insert_code.setUpdatetime(new Date());
		insert_code.setCreatetime(new Date());
		insert_code.setTelephone(telephone);
		insert_code.setCode(code);
		CodeExample example = new CodeExample();
		CodeExample.Criteria criteria = example.createCriteria();
		criteria.andTelephoneEqualTo(telephone);
		List<Code> code_search = codeMapper.selectByExample(example);
		if (code_search.size() > 0 && !code_search.isEmpty()) {
			insert_code.setCodeid(code_search.get(0).getCodeid());
			codeMapper.updateByPrimaryKeySelective(insert_code);
			return ResultMap.IS_200();
		}
		codeMapper.insertSelective(insert_code);
		return ResultMap.IS_200();
	}


	public ResultMap signuser(Profile profile) {
		
		//校验用户是否实名过
		UserCustomer userCustomer = userCustomerMapper.getcommonusermess(profile.getUserid());
		if (userCustomer == null) {return ResultMap.build(900, "用户不存在!");}
		if (userCustomer.getSignstate() == 2) {return ResultMap.build(200,"已实名");}
		//判断最近一次实名实名是否是最近
		boolean flag  =  HttpSign.checkuserrealmessage(profile.getRealname(), profile.getIdentity());
		String message = "实名失败!";
		Integer code = 300;
		UserCustomer updatesearch = null;
		if (flag == true) { 
			code = 200; message = "实名成功!"; 
			profile.setSignstate(2);
			profile.setProfileid(userCustomer.getProfileid());
			profileMapper.updateByPrimaryKeySelective(profile);
			updatesearch = userCustomerMapper.getUserMessagebytelephone(userCustomer.getTelephone() +"");
		}
		return ResultMap.build(code, message ,updatesearch);
	}


	public Long getuseridbytoken(String accesstoken) {
		if (accesstoken == null || "".equals(accesstoken)) {return -1L;}
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andAccesstokenEqualTo(accesstoken);
		List<User> user = userMapper.selectByExample(example);
		if (user.size() >0 && !user.isEmpty()) {
			return user.get(0).getUserid();
		}
		return -1L;
	}





	@Override
	public UserCustomer searchuser(Long relationid, Long profileid) {
		Long getuserid = IDUtils.searchuseridbyrequest(request);
		if (getuserid < 0 )  {
			return null;
		}
		if (profileid ==null) {
			UserCustomer searchuser = userCustomerMapper.getUserMessagebyuserid(getuserid);
			profileid = searchuser.getProfileid();
		}
		UserCustomer searchuser = null;
		Long fatherid = null;
		Long motherid = null;
		Long grandfatherid =null;
		Long grandmotherid =null;
		Long zenfatherid =null;
		Long zenmotherid =null;
		Long spouceid = null;
		Userconn myself = searchuserconn(profileid);
		if (myself !=null) { // userid -->我
			fatherid = myself.getFatherid(); // 这里拿到父亲ID
			spouceid = myself.getSpouseid() ; // 这里拿到配偶ID
			if (fatherid !=null) { // userid -->父亲
				Userconn searchfather = searchuserconn(fatherid);
				if (searchfather !=null) {
					motherid = searchfather.getSpouseid();  // 母亲ID
					grandfatherid =searchfather.getFatherid();  // 祖父ID
					if (grandfatherid !=null) { // userid -->祖父
						Userconn searchgrandfather = searchuserconn(grandfatherid);
						if (searchgrandfather !=null) {
							grandmotherid = searchgrandfather.getSpouseid(); // 祖母ID
							zenfatherid = searchgrandfather.getFatherid();  // 曾祖父ID
							if (zenfatherid !=null) {
								Userconn zenfather = searchuserconn(zenfatherid);
								if (zenfather !=null) {
									zenmotherid = zenfather.getSpouseid(); //曾祖母
								}
							}
						}
					}
				}
			}
		}
		if (relationid == null) {return searchuser;}
		if (relationid == 1) {profileid = null;} // 这里是查询兄弟
		if (relationid == 10) {profileid =  null;}
		if (relationid == 2) {profileid = fatherid;} // 这里是查询父亲
		if (relationid == 3) {profileid = motherid;} // 这里是查询母亲
		if (relationid == 4) {profileid = grandfatherid;} // 这里是查询祖父
		if (relationid == 5) {profileid = grandmotherid;}
		if (relationid == 6) {profileid = zenfatherid;}
		if (relationid == 7) {profileid = zenmotherid;}
		if (relationid == 8 ) {profileid = spouceid;}
		if (profileid !=null) {
			searchuser = userCustomerMapper.searchupdatemess(profileid);
		}
		return searchuser;
	}





	private Userconn searchuserconn(Long profileid) {
		UserconnExample example = new UserconnExample();
		UserconnExample.Criteria criteria = example.createCriteria();
		criteria.andProfileidEqualTo(profileid);
		List<Userconn> check = userconnMapper.selectByExample(example);
		if (! check.isEmpty() && check.size() > 0) {return check.get(0);}
		return null;
	}





	/**
	 * 进入用户个人中心
	 * @param userid : 用户ID
	 * **/
	public UserCustomer searchinfo(Long userid) {
		// TODO Auto-generated method stub
		return userCustomerMapper.searchinfo(userid);
	}




	/**
	 * @param userid : 当前用户userid
	 * @param sessionuserid : 会话中userid
	 * **/
	public Integer findIsFans(Long userid, Long sessionuserid) {
		Integer state = 2;
		if (sessionuserid < 0 ) {return state ;}
		// 查询控制表数据
		FansExample example = new FansExample();
		FansExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(sessionuserid);
		criteria.andOtheridEqualTo(userid);
		criteria.andTypeEqualTo(11);
		List<Fans> fans = fansMapper.selectByExample(example);
		if (! fans.isEmpty() && fans.size() > 0 ) {
			state = fans.get(0).getState();
		}
		return state;
	}




	@Override
	public UserCustomer searchcommoninfo(Long userid) {
		// TODO Auto-generated method stub
		UserCustomer search = userCustomerMapper.getUserMessagebyuserid(userid);
		return userCustomerMapper.searchupdatemess(search.getProfileid());
	}




	@Override
	public ResultMap updatecommoninfo(Long userid, UserCustomer userCustomer ,String code) {
		// 先校验用户是否登录
		if (userCustomer.getTelephone() == null) {
			return ResultMap.build(404, "请输入手机号码！");
		}
		// 首先根据用户的USERID 查询用户表中基本信息
		UserCustomer user = userCustomerMapper.getUserMessagebyuserid(userid);
		Profile profile = profileMapper.selectByPrimaryKey(user.getProfileid());
		if (profile == null || user == null) {
			return ResultMap.build(400, "你修改的账号不存在");
		}
		// 这里做修改手机号码处理
		if (!(user.getTelephone() + "").equals(userCustomer.getTelephone() + "")) {
			// 校验本系统中是否已经有该手机号的注册
			User checkphone = checktelephoneexixt(userCustomer.getTelephone());
	
			if (checkphone != null) {
				return ResultMap.build(400, "该手机号已经注册，无法修改。");
			}
			// 获取验证码 type =3表示更改手机号码
			Code searchcode = getcheckcode( userCustomer.getTelephone());
			if (searchcode == null) {
				return ResultMap.build(400, "验证码失效");
			}
			String code_get = "";
			if (code != null && !"".equals(code)) {
				code_get = getBASE64(code);
			}
			if (!searchcode.getCode().equals(code_get)) {
				return ResultMap.build(500, "验证码错误");
			}
			User updateuser = new User();
			updateuser.setUserid(userid);
			updateuser.setTelephone(userCustomer.getTelephone());
			userMapper.updateByPrimaryKeySelective(updateuser);
		}
	
		if (userCustomer.getSex() != null && !"".equals(userCustomer.getSex())) {
			profile.setSex(userCustomer.getSex());
		}
		if (userCustomer.getUsername() != null && !"".equals(userCustomer.getUsername())) {
			profile.setUsername(userCustomer.getUsername());
		}
		if (userCustomer.getAvatar() != null && !"".equals(userCustomer.getAvatar())) {
			profile.setAvatar(userCustomer.getAvatar());
		}
		if (userCustomer.getWeixinnub() !=null && !"".equals(userCustomer.getWeixinnub())) {
			profile.setWeixinnub(userCustomer.getWeixinnub());
		}
		if (userCustomer.getZodiac() !=null && !"".equals(userCustomer.getZodiac())) {
			profile.setZodiac(userCustomer.getZodiac());
		}
		if (userCustomer.getWorkunit() !=null && !"".equals(userCustomer.getWorkunit())) {
			profile.setWorkunit(userCustomer.getWorkunit());
		}
		if (userCustomer.getBirthday() !=null) {
			profile.setBirthday(userCustomer.getBirthday());
		}
		
	
		profileMapper.updateByPrimaryKeySelective(profile);
		return ResultMap.build(200, "编辑成功");
	}
	
	
	public User checktelephoneexixt(Long telephone) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andTelephoneEqualTo(telephone);
		List<User> user = userMapper.selectByExample(example);
		if (user.size() > 0 && !user.isEmpty()) {
			return user.get(0);
		}
		return null;
	}







	@Override
	public Map<String, Object> addconnectionlevel(Long userid, Long rephone) {
		// 这里判断是不是别人邀请过来的 如果是建立一级关系
		Map<String, Object> map = new HashMap<>();
		String returnstr = "old";
		Long preuserid = 0L;  // 当前推荐人的上级
		// 如果没有推荐人直接返回 || 获取是老用户
		if (rephone ==null || rephone < 0   || userid < 0 ) {
			map.put("mess", returnstr);
			return map;
		}
		// 这里查询推荐人信息
		UserCustomer user = this.getUserMessagebytelephone(rephone);
		if (user == null) {
			map.put("mess", returnstr);
			return map;
		}
		Long reuserid = user.getUserid() ;  // 推荐人用户ID
		// 这里是查询的推荐人的level ---最高级别的上级
		Invite invite_search = userCustomerMapper.getmaxuserlevel(user.getUserid());
		// 说明推荐的用户就是第一级用户。需要和新用户建立一级关系就可以了。
		Integer level = invite_search.getLevel();
		if (level == 0) {
			// 这里直接和新用户建立一级绑定关系
			Invite invite = new Invite();
			invite.setAccount(0.00);
			invite.setFollowuserid(userid); // 新用户
			invite.setUserid(reuserid); // 推荐人ID
			invite.setLevel(1); // 建立一级关系
			inviteMapper.insertSelective(invite);
			returnstr = "new";
			map.put("mess", returnstr);
			map.put("reuserid", reuserid); 
			return map;
		}
		// 说明推荐的用户是第二级用户。需要和他上面的用户和新用户建立关系
		if (level == 1) {
			// 这里先和新用户建立一级关系
			Invite invite = new Invite();
			invite.setAccount(0.00);
			invite.setFollowuserid(userid); // 新用户
			invite.setUserid(user.getUserid()); // 推荐人ID
			invite.setLevel(1); // 建立一级关系
			inviteMapper.insertSelective(invite);

			// 除此之外还要和推荐人上级形成二级关系
			Invite follow = new Invite();
			follow.setAccount(0.0);
			follow.setFollowuserid(userid); // 新用户
			follow.setUserid(invite_search.getUserid()); // 当前推荐人的上一级
			follow.setLevel(2); // 新用户和推荐人的上一级形成二级关系
			inviteMapper.insertSelective(follow);
			returnstr = "erjinew";
			preuserid = invite_search.getUserid(); // 当前推荐人的上一级
			map.put("mess", returnstr);
			map.put("preuserid", preuserid);
			map.put("reuserid", reuserid);
			return map;
		}
		// 说明是第三级用户需要和下面的用户建立level为3的绑定都没有收益
		if (level == 2 || level == 3) {
			// 这里先和新用户建立一级关系
			Invite invite = new Invite();
			invite.setAccount(0.00);
			invite.setFollowuserid(userid); // 新用户
			invite.setUserid(user.getUserid()); // 推荐人ID
			invite.setLevel(3); // 建立三级级关系没有收益
			inviteMapper.insertSelective(invite);
			returnstr = "erjinew";
			preuserid = invite_search.getUserid(); //推荐人的上一级
			map.put("reuserid", reuserid);
			map.put("preuserid", preuserid);
			map.put("mess", returnstr);
			return map;
		}
		return map;
	}







	/**
	 * 查询我的团队
	 * **/ 
	public List<UserCustomer> myteam(Long userid, PageQuery pagequery ,Integer level) {
		String searchstr = "" ;
		if (level == 1) {searchstr = "1";}
		if (level == 2 || level == 3) {searchstr = "2,3";}
		return  userCustomerMapper.myteam(userid , pagequery , searchstr );
	}
	@Override
	public int myteamCount(Long userid, Integer level) {
		// TODO Auto-generated method stub
		return userCustomerMapper.myteamCount(userid , level);
	}






	@Override
	public Invite searchminlevel(Long userid) {
		// TODO Auto-generated method stub
		return userCustomerMapper.searchminlevel(userid);
	}







	@Override
	public List<UserCustomer> myteamson(Long userid, Long sessionid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return userCustomerMapper.myteamson(userid, sessionid, pagequery);
	}







	@Override
	public int myteamsonCount(Long userid, Long sessionid) {
		// TODO Auto-generated method stub
		return userCustomerMapper.myteamsonCount( userid,  sessionid);
	}







	@Override
	public boolean checktoken(String telephone, String accesstoken) {
		// TODO Auto-generated method stub
		UserCustomer checktoken = userCustomerMapper.checktoken(telephone ,accesstoken );
		if (checktoken == null) {
			return false;
		}
		return true;
	}







	@Override
	public ResultMap synclatandlog(Profile profile, Long sessionid) {
		// TODO Auto-generated method stub
		UserCustomer searchuser = userCustomerMapper.getUserMessagebyuserid(sessionid);
		Long profileid = searchuser.getProfileid();
		Profile update = profileMapper.selectByPrimaryKey(profileid);
		if (profile.getLatitude() !=null && profile.getLongitude() !=null) {
			update.setLatitude(profile.getLatitude());
			update.setLongitude(profile.getLongitude());
			profileMapper.updateByPrimaryKey(update);
		}
		return ResultMap.IS_200();
	}












	
	

}
