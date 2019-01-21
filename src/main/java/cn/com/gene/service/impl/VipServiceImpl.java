package cn.com.gene.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.ConsumeMapper;
import cn.com.gene.mapper.InviteMapper;
import cn.com.gene.mapper.LucreMapper;
import cn.com.gene.mapper.UserMapper;
import cn.com.gene.mapper.VipcountMapper;
import cn.com.gene.mymapper.VipCustomerMapper;
import cn.com.gene.pojo.Consume;
import cn.com.gene.pojo.Invite;
import cn.com.gene.pojo.InviteExample;
import cn.com.gene.pojo.Lucre;
import cn.com.gene.pojo.User;
import cn.com.gene.pojo.Vipcount;
import cn.com.gene.queryvo.VipCustomer;
import cn.com.gene.service.VipService;
@Service
public class VipServiceImpl implements VipService {
	
	@Autowired VipCustomerMapper vipCustomerMapper;
	@Autowired ConsumeMapper consumeMapper;
	@Autowired InviteMapper inviteMapper;
	@Autowired UserMapper userMapper;
	@Autowired VipcountMapper vipcountMapper;
	@Autowired LucreMapper lucreMapper;
	
	// 查询用户的钱包
	public VipCustomer searchmybure(Long userid) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.searchmybure(userid);
	}

	// 查询金币明细
	public List<VipCustomer> searchjinbimx(Long userid, String state, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.searchjinbimx(userid ,state ,pagequery  );
	}

	// 查询金币明细
	public int searchjinbimxCount(Long userid, String state) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.searchjinbimxCount(userid,state);
	}

	// 获取最大订单号
	public Long ordernum() {
		// TODO Auto-generated method stub
		return vipCustomerMapper.consumeorderNum();
	}

	//插入充值记录
	public void addConsume(Consume consume) {
		// TODO Auto-generated method stub
		consumeMapper.insertSelective(consume);
	}

	// 查询充值记录COUNT
	public int searchrechargeCount(Long userid ,String type) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.searchrechargeCount(userid ,type);
	}

	// 查询充值记录
	public List<VipCustomer> searchrecharge(Long userid,String type, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.searchrecharge(userid,type, pagequery);
	}

	// 这里作收益记录操作
	public ResultMap addinvitebalance(Long userid, double account) {
		// 查询谁邀请了我
		InviteExample example = new InviteExample();
		InviteExample.Criteria criteria = example.createCriteria();
		criteria.andFollowuseridEqualTo(userid);// 当前的我是followuserid
		List<Invite> firstfloors = inviteMapper.selectByExample(example);
		// 说明这个是推荐我的人
		if (firstfloors.size() == 0) {
			// 说明没有人推荐我进来
			return ResultMap.build(400, "我的上级不存在");
		}
		// 遍历所有的上级。
		Double ticheng = 0.0d;
		String descname = "充值" + account + "元获取收益:";
		for (Invite invi : firstfloors) {
			// 这里是指数上级提成 20%
			if (invi.getLevel() == 1) {
				ticheng = 0.2;
				setaddaccount(invi, userid, account * ticheng, descname);
			}
			// 如果是间接上级提成是5%
			if (invi.getLevel() == 2) {
				ticheng = 0.05;
				setaddaccount(invi, userid, account * ticheng, descname);
			}

		}
		return ResultMap.IS_200();
	}
	
	
	
	private void setaddaccount(Invite invite, Long userid, Double account, String descname) {
		// 查询充值用户信息
		User user = userMapper.selectByPrimaryKey(userid);
		// 第一步同步邀请的金额
		invite.setAccount(invite.getAccount() + account);
		inviteMapper.updateByPrimaryKeySelective(invite);
		// 第二步同步钱包收益余额
		Vipcount vipcount = vipcountMapper.selectByPrimaryKey(invite.getUserid());
		vipcount.setAccount(vipcount.getAccount() + account);
		vipcountMapper.updateByPrimaryKeySelective(vipcount);
		// 充值记录插入一条记录
		Lucre lurce = new Lucre();
		lurce.setUpdatetime(new Date());
		lurce.setAccount(account);
		String telp = (user.getTelephone() + "").substring(0, 4) + "**";
		descname = "队员" + telp + descname;
		lurce.setDescname(descname);
		lurce.setUserid(invite.getUserid()); // 这里记录的是收益人
		lucreMapper.insertSelective(lurce);
	}

	//查询收益记录
	public List<Lucre> mylurnce(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.mylurnce(userid,pagequery);
	}

	@Override
	public int mylurnceCount(Long userid) {
		// TODO Auto-generated method stub
		return vipCustomerMapper.mylurnceCount(userid);
	}

	@Override
	public void updateVipSelective(Vipcount vipcount) {
		// TODO Auto-generated method stub
		vipcountMapper.updateByPrimaryKeySelective(vipcount);
	}


	

}
