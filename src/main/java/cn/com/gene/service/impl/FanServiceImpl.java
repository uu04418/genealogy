package cn.com.gene.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.DetailcontentMapper;
import cn.com.gene.mapper.EnshrineMapper;
import cn.com.gene.mapper.FansMapper;
import cn.com.gene.mapper.ZanMapper;
import cn.com.gene.mymapper.FansCustomerMapper;
import cn.com.gene.pojo.Browse;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.Enshrine;
import cn.com.gene.pojo.EnshrineExample;
import cn.com.gene.pojo.Fans;
import cn.com.gene.pojo.FansExample;
import cn.com.gene.pojo.Zan;
import cn.com.gene.pojo.ZanExample;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.FanService;

@Service
public class FanServiceImpl implements FanService{

	@Autowired FansMapper fansMapper;
	@Autowired HttpServletRequest request;
	@Autowired ZanMapper zanMapper;
	@Autowired EnshrineMapper enshrineMapper;
	@Autowired DetailcontentMapper detailcontentMapper;
	@Autowired FansCustomerMapper fansCustomerMapper;
	
	// 校验是否已经关注
	public Integer checkguanzhu(Long userid, Long otherid, Integer type) {
		Integer state = 2;
		FansExample example = new FansExample();
		FansExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Fans> list = fansMapper.selectByExample(example);
		if (! list.isEmpty() && list.size() > 0) {
			state = list.get(0).getState();
		}
		return state;
	}
	
	// 检查赞的状态
	public Integer checkzan(Long userid, Long otherid, Integer type) {
		Integer state = 2; // 默认是没有赞
		ZanExample example = new ZanExample();
		ZanExample.Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Zan> list = zanMapper.selectByExample(example);
		if (! list.isEmpty() && list.size() > 0) {
			state = list.get(0).getState();
		}
		return state;
	}
	
	//检查收藏的状态
	public Integer checkensh(Long userid, Long otherid, Integer type) {
		Integer state = 2;
		EnshrineExample example = new EnshrineExample();
		EnshrineExample.Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Enshrine> list = enshrineMapper.selectByExample(example);
		if (! list.isEmpty() && list.size() > 0) {
			state = list.get(0).getState();
		}
		
		return state;
	}

	@Override
	public ResultMap changefanstate(Long otherid, Integer state, Integer type) {
		// 先拿到userid 
		String message = "首次关注";
		Long userid  = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 )  {return ResultMap.build(404,"未知用户！");} 
		if (otherid == null ) {return ResultMap.build(400, "请选择操作对象!");}
		if (type == null ||  type < 0 || type > 12) {return ResultMap.build(400, "type类型不对 ");}
		if (state < 1 || state > 2) {return ResultMap.build(400, "参数错误！");}
		
		// 先校验当前事物和当前userid 是否存在关联
		FansExample example = new FansExample();
		FansExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Fans> checkfans = fansMapper.selectByExample(example);
		if (! checkfans.isEmpty() && checkfans.size() > 0 ) {
			// 说明已经建立关系 只需要更新就可以
			Fans search = checkfans.get(0);
			search.setState(state);
			search.setUpdatetime(new Date());
			fansMapper.updateByPrimaryKeySelective(search);
			message  = "操作成功";
		}else {
			Fans infans = new Fans();
			infans.setOtherid(otherid);
			infans.setState(state);
			infans.setUserid(userid);
			infans.setUpdatetime(new Date());
			infans.setType(type);
			fansMapper.insertSelective(infans);
		}
		return ResultMap.build(200, message ,state);
	}

	private Browse checkgood(Integer type, Long otherid) {
		// 表示氏族发布的详情
		Long followuserid = null;
		String title = "" ;
		if (type == 2) {
			Detailcontent content = detailcontentMapper.selectByPrimaryKey(otherid);
			if (content !=null) {
				followuserid = content.getUserid();
				if (content.getPicture() !=null &&!"".equals(content.getPicture())) {
					title = content.getPicture().split(",")[0];
				}else {
					title = content.getTitle();
				}
			}
		}
		Browse returnbro = new Browse();
		if (followuserid !=null) {
			returnbro.setTitle(title);
			returnbro.setFollowuserid(followuserid);
		}
		return returnbro;
	}

	@Override
	public ResultMap changezanstate(Long otherid, Integer state, Integer type) {
		Long userid  = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 )  {return ResultMap.build(404,"未知用户！");} 
		if (otherid == null ) {return ResultMap.build(400, "请选择操作对象!");}
		if (type == null ||  type < 0 || type > 11) {return ResultMap.build(400, "type类型不对 ");}
		if (state < 1 || state > 2) {return ResultMap.build(400, "参数错误！");}
		
		// 判断关注的物品是否存在
		Browse checkgood = checkgood(type , otherid ) ;
		if (checkgood == null) {return ResultMap.build(400, "点赞的物品不存在");}
		
		// 先校验当前事物和当前userid 是否存在关联
		ZanExample example = new ZanExample();
		ZanExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Zan> checkzans = zanMapper.selectByExample(example);
		if (! checkzans.isEmpty() && checkzans.size() > 0 ) {
			// 说明已经建立关系 只需要更新就可以
			Zan search = checkzans.get(0);
			search.setState(state);
			search.setTitle(checkgood.getTitle());
			search.setUpdatetime(new Date());
			zanMapper.updateByPrimaryKeySelective(search);
		}else {
			Zan inzan = new Zan();
			inzan.setOtherid(otherid);
			inzan.setState(state);
			inzan.setUserid(userid);
			inzan.setCreatetime(new Date());
			inzan.setTitle(checkgood.getTitle());
			inzan.setFollowuserid(checkgood.getFollowuserid());
			inzan.setUpdatetime(new Date());
			inzan.setType(type);
			zanMapper.insertSelective(inzan);
		}
		return ResultMap.build(200, "操作成功" ,state);
	}


	
	// 改变收藏的状态
	public ResultMap changeenshstate(Long otherid, Integer state, Integer type) {
		Long userid  = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 )  {return ResultMap.build(404,"未知用户！");} 
		if (otherid == null ) {return ResultMap.build(400, "请选择操作对象!");}
		if (type == null ||  type < 0 || type > 11) {return ResultMap.build(400, "type类型不对 ");}
		if (state < 1 || state > 2) {return ResultMap.build(400, "参数错误！");}
		
		// 判断收藏的物品是否存在
		Browse checkgood = checkgood(type , otherid ) ;
		if (checkgood == null) {return ResultMap.build(400, "收藏的物品不存在");}
		// 先校验当前事物和当前userid 是否存在关联
		EnshrineExample example = new EnshrineExample();
		EnshrineExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		List<Enshrine> checkenshs = enshrineMapper.selectByExample(example);
		if (! checkenshs.isEmpty() && checkenshs.size() > 0 ) {
			// 说明已经建立关系 只需要更新就可以
			Enshrine search = checkenshs.get(0);
			search.setState(state);
			search.setTitle(checkgood.getTitle());
			search.setUpdatetime(new Date());
			enshrineMapper.updateByPrimaryKeySelective(search);
		}else {
			Enshrine inensh = new Enshrine();
			inensh.setOtherid(otherid);
			inensh.setState(state);
			inensh.setUserid(userid);
			inensh.setCreatetime(new Date());
			inensh.setTitle(checkgood.getTitle());
			inensh.setFollowuserid(checkgood.getFollowuserid());
			inensh.setUpdatetime(new Date());
			inensh.setType(type);
			enshrineMapper.insertSelective(inensh);
		}
		return ResultMap.build(200, "操作成功" ,state);
	}

	@Override
	public int zanarryCount(Long userid) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.zanarryCount(userid);
	}

	@Override
	public List<UserCustomer> zanarry(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.zanarry(userid ,pagequery);
	}

	@Override
	public int broarryCount(Long userid) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.broarryCount(userid);
	}

	@Override
	public List<UserCustomer> broarry(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.broarry(userid ,pagequery);
	}

	@Override
	public int fansarryCount(Long userid) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.fansarryCount(userid);
	}

	@Override
	public List<UserCustomer> fansarry(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.fansarry(userid ,pagequery);
	}

	@Override
	public int guanarryCount(Long userid) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.guanarryCount(userid);
	}

	@Override
	public List<UserCustomer> guanarry(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.guanarry(userid ,pagequery);
	}

	//查询我的收藏
	public int findMyEnshListCount(Long userid) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.findMyEnshListCount(userid);
	}

	//查询我的收藏
	public List<UserCustomer> findMyEnshList(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return fansCustomerMapper.findMyEnshList(userid,pagequery);
	}
	
	
	





}
