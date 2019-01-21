package cn.com.gene.service.impl;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gene.comm.PageQuery;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.DetailaddressMapper;
import cn.com.gene.mapper.DetailcontentMapper;
import cn.com.gene.mapper.DetailtypeMapper;
import cn.com.gene.mapper.EducationMapper;
import cn.com.gene.mapper.GenemessconnMapper;
import cn.com.gene.mapper.PositionMapper;
import cn.com.gene.mymapper.DetailCustomerMapper;
import cn.com.gene.mymapper.userCustomerMapper;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.DetailcontentExample;
import cn.com.gene.pojo.Detailtype;
import cn.com.gene.pojo.Education;
import cn.com.gene.queryvo.DetailCustomer;
import cn.com.gene.queryvo.DetailVo;
import cn.com.gene.queryvo.PosCustomer;
import cn.com.gene.queryvo.UserCustomer;
import cn.com.gene.service.DetailService;
@Service
public class DetailServiceImpl implements DetailService{
	@Autowired DetailaddressMapper detailaddressMapper;
	@Autowired DetailtypeMapper detailtypeMapper;
	@Autowired HttpServletRequest request;
	@Autowired DetailcontentMapper detailcontentMapper;
	@Autowired GenemessconnMapper genemessconnMapper;
	@Autowired DetailCustomerMapper detailCustomerMapper;
	@Autowired EducationMapper educationMapper;
	@Autowired PositionMapper positionMapper;
	@Autowired userCustomerMapper userCustomerMapper;
	@Override
	public List<Detailtype> searchdetailtype(Integer type ,String searchdetailtype) {
		if (type == null || type < 0 || type > 5) {
			return null;
		}
		List<Detailtype> detailtypes = detailCustomerMapper.searchdetailtype(type+"" ,searchdetailtype);
		return detailtypes;
	}

	// 发布详情
	public ResultMap adddetailcontent(Long detailid , Detailcontent detailcontent,Long userid) {
		
		// 校验是否输入发布的标题
		if (detailcontent.getTitle() == null || "".equals(detailcontent.getTitle())) 
				{ return ResultMap.build(400, "输入标题！"); }
		// 校验是否输入发布的内容
		if (detailcontent.getDetailcontent() == null || "".equals(detailcontent.getDetailcontent())) 
				{ detailcontent.setDetailcontent(""); }
		// 校验是否选择了发布类型
		if (detailcontent.getDetailtypeid() == null) 
				{ return ResultMap.build(400, "请选择发布类型！"); }
		// 校验是否插入图片
		if (detailcontent.getPicture() == null || "".equals(detailcontent.getPicture())) 
				{detailcontent.setPicture("");}
		
		boolean falg = checkcontentrepeat(detailid , userid,detailcontent.getTitle());
		// 说明是重复发布
		if (falg == true) {return ResultMap.build(400, "勿频繁操作!");}
	
		detailcontent.setState(1);                  //设置发布状态为正常
		detailcontent.setUserid(userid);           // 设置发布人ID 
		detailcontent.setDetailid(detailid);       // 设置发布的详情ID
		detailcontent.setUpdatetime(new Date());  
		detailcontent.setCreatetime(new Date());
		
		// 先判断发布的类型是不是图文
		if (detailcontent.getDetailtypeid() == 2 || detailcontent.getDetailtypeid()==3) {
			// 在判断是否传入 家族ID
			if (detailcontent.getGenemessid() != null) {
				detailcontentMapper.insertSelective(detailcontent);
				return ResultMap.build(200, "发布成功");
			}
			Long genemess_searchid = getgenemessbyuserid (userid);
			if (genemess_searchid == null) {return ResultMap.build(400, "选择家族");}
			detailcontent.setGenemessid(genemess_searchid);
			detailcontentMapper.insertSelective(detailcontent);
			return ResultMap.build(200, "发布成功");
		} else {
			if (detailcontent.getGenemessid() == null) {
				return ResultMap.build(400, "请选择家族");
			}
			detailcontentMapper.insertSelective(detailcontent);
		}
		return ResultMap.build(200, "发布成功");
	}

	private boolean checkcontentrepeat(Long detailid, Long userid, String title) {
		DetailcontentExample example = new DetailcontentExample();
		DetailcontentExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andTitleEqualTo(title);
		criteria.andDetailidEqualTo(detailid);
		Date current = new Date();
		Date before = new Date(current.getTime() - 100000);
		Date after = new Date(current.getTime() + 100000);
		criteria.andUpdatetimeBetween(before, after);
		List<Detailcontent> search = detailcontentMapper.selectByExample(example);
		if (!search.isEmpty() && search.size() > 0) {return true;}
		return false;
	}

	private Long getgenemessbyuserid(Long userid) {
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		return  detailCustomerMapper.getgenemessbyuserid(userCustomer.getProfileid());
		
	}

	@Override
	public List<DetailCustomer> searchdetail(DetailVo detailVo) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchdetail(detailVo);
	}

	@Override
	public int searchdetailCount(DetailVo detailVo) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchdetailCount(detailVo);
	}

	// 查询发布的详情
	public DetailCustomer detailcontentbyid(Long detailcontentid) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.detailcontentbyid(detailcontentid);
	}

	@Override
	public List<Education> searcheduc() {
		
		return educationMapper.selectByExample(null);
	}

	@Override
	public List<PosCustomer> searchpostion() {
		// 先查一级职位
		Long fatherid = 0L;
		List<PosCustomer> firstarry = detailCustomerMapper.searchposbyfatherid(fatherid);
		for (PosCustomer pos : firstarry) {
			List<PosCustomer> search = detailCustomerMapper.searchposbyfatherid(pos.getPositionid());
			pos.setChild(search);
		}
		return firstarry;
	}

	@Override
	public int searchpicCount(Long genemessid) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchpicCount(genemessid);
	}

	@Override
	public List<DetailCustomer> searchpic(Long genemessid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchpic(genemessid , pagequery);
	}

	@Override
	public int searchsendcontentCount(Long userid) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchsendcontentCount (userid);
	}

	@Override
	public List<DetailCustomer> searchsendcontent(Long userid, PageQuery pagequery) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searchsendcontent(userid,pagequery);
	}

	@Override
	public ResultMap removedetail(Long userid, Long detailcontentid) {
		// TODO Auto-generated method stub
		if (detailcontentid ==null) {return ResultMap.build(400, "选择移除的对象");}
		Detailcontent search = detailcontentMapper.selectByPrimaryKey(detailcontentid);
		if (search == null) {return ResultMap.build(400, "该内容不存在");}
		if (!(userid+"").equals(search.getUserid()+"")) {return ResultMap.build(400, "不是自己的发布");}
		search.setState(2); // 设置移除状态
		search.setUpdatetime(new Date());
		detailcontentMapper.updateByPrimaryKeySelective(search);
		return ResultMap.build(200, "移除成功");
	}

	// 编辑的查询
	public Detailcontent searcheditdetail(Long detailcontentid) {
		// TODO Auto-generated method stub
		return detailCustomerMapper.searcheditdetail(detailcontentid);
	}

	@Override
	public ResultMap editdetail(Long userid, Detailcontent detailcontent) {
		Detailcontent search = detailcontentMapper.selectByPrimaryKey(detailcontent.getDetailcontentid());
		if (search == null) {return ResultMap.build(400, "该内容不存在");}
		if (!(userid+"").equals(search.getUserid()+"")) {return ResultMap.build(400, "不是自己的发布");}
		search.setTitle(detailcontent.getTitle());
		search.setDetailcontent(detailcontent.getDetailcontent());
		search.setPicture(detailcontent.getPicture());
		detailcontentMapper.updateByPrimaryKeySelective(search);
		return ResultMap.build(200, "编辑成功");
	}

	@Override
	public List<DetailCustomer> aboutmydynamic(Long userid, PageQuery pagequery ,String searchwhere) {
		// TODO Auto-generated method stub
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		return detailCustomerMapper.aboutmydynamic(userid ,userCustomer.getProfileid(),pagequery ,searchwhere);
	}

	@Override
	public int aboutmydynamicCount(Long userid , String searchwhere) {
		// TODO Auto-generated method stub
		UserCustomer userCustomer = userCustomerMapper.getUserMessagebyuserid(userid);
		return detailCustomerMapper.aboutmydynamicCount(userid ,userCustomer.getProfileid() ,searchwhere);
	}
	
	
	

}
