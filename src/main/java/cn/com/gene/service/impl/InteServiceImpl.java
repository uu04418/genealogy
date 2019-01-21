package cn.com.gene.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.CommoninteMapper;
import cn.com.gene.mapper.InteconnMapper;
import cn.com.gene.mapper.IntegralMapper;
import cn.com.gene.mymapper.VipCustomerMapper;
import cn.com.gene.pojo.Commoninte;
import cn.com.gene.pojo.Inteconn;
import cn.com.gene.pojo.Integral;
import cn.com.gene.service.InteService;


@Service
public class InteServiceImpl implements InteService {

	@Autowired InteconnMapper inteconnMapper;
	@Autowired IntegralMapper integralMapper;
	@Autowired VipCustomerMapper vipCustomerMapper;
	@Autowired CommoninteMapper commMapper;
	
	//这里处理扣除积分操作
	public ResultMap deletesearch(int type, Long userid) {
		Long count = 0l ;
		Commoninte search = commMapper.selectByPrimaryKey(type);
		if (search !=null) {count = search.getTypecount();}
		if (count < 0) {return ResultMap.build(400, "参数类型错误");}
		// 控制积分总数
		Inteconn inteconn_search = inteconnMapper.selectByPrimaryKey(userid);
		//如果积分不足返回
		if (inteconn_search.getCount() - count < 0) {
			return ResultMap.build(700, "金币不足");
		}
		return ResultMap.IS_200();
	}

	
	

	// 处理获得的积分
	public String managebranch(int type, Long userid) {
		String event = "";
		Long count = 0L;
		Integer currentlength = vipCustomerMapper.searchcurrntlength(type ,userid );
		
		Commoninte search = commMapper.selectByPrimaryKey(type);
		
		if (search ==null) {
			return "";
		}
		
		if (currentlength<20) {
			event = search.getTypename();
			count = search.getTypecount();
		}
		if (!"".equals(event)) {
			// 统计积分事件
			Integral integral = new Integral();
			integral.setUpdatetime(new Date());
			integral.setUserid(userid);
			integral.setType(type);
			integral.setState(1);
			integral.setEvent(event);
			integral.setCount(count);
			integralMapper.insertSelective(integral);
			// 控制积分总数
			Inteconn inteconn_search = inteconnMapper.selectByPrimaryKey(userid);
			inteconn_search.setUpdatetime(new Date());
			inteconn_search.setCount(inteconn_search.getCount() + count);
			inteconnMapper.updateByPrimaryKeySelective(inteconn_search);
		}
		
		return "";
	
	}




	/**处理积分扣除操作
	 * @param type : 积分扣除类型
	 * @param userid : 扣除的用户
	 * 
	 * **/
	public ResultMap deletebrach(int type, Long userid) {
		String event = "";
		Long count = 0L;
		Commoninte search = commMapper.selectByPrimaryKey(type);
		if (search !=null) {
			count = search.getTypecount();
			event = search.getTypename();
		}
		if (!"".equals(event)) {
			// 统计积分事件
			Integral integral = new Integral();
			integral.setUpdatetime(new Date());
			integral.setUserid(userid);
			integral.setType(type);
			integral.setState(2);
			integral.setEvent(event);
			integral.setCount(count);
			integralMapper.insertSelective(integral);
			
			// 控制积分总数
			Inteconn inteconn_search = inteconnMapper.selectByPrimaryKey(userid);
			inteconn_search.setUpdatetime(new Date());
			inteconn_search.setCount(inteconn_search.getCount() - count);
			inteconnMapper.updateByPrimaryKeySelective(inteconn_search);
		}
		
		return ResultMap.IS_200();
	}




	@Override
	public Long delelteafter(int type, Long userid) {
		Long count = -1L ;
		Commoninte search = commMapper.selectByPrimaryKey(type);
		if (search !=null) {
			count = search.getTypecount();
		}
		if (count < 0) {return count;}
		// 控制积分总数
		Inteconn inteconn_search = inteconnMapper.selectByPrimaryKey(userid);
		return inteconn_search.getCount() - count;
	}




	@Override
	public String chongzhirenturn(Integral integral) {
		// 先插入一条数据
		integralMapper.insertSelective(integral);
		Inteconn inteconn_search = inteconnMapper.selectByPrimaryKey(integral.getUserid());
		inteconn_search.setUpdatetime(new Date());
		inteconn_search.setCount(inteconn_search.getCount() + integral.getCount());
		inteconnMapper.updateByPrimaryKeySelective(inteconn_search);
		return "success";
	}

	

	

	
	

	


}
