package cn.com.gene.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gene.comm.ResultMap;
import cn.com.gene.mapper.DetailaddressMapper;
import cn.com.gene.mapper.SurnameMapper;
import cn.com.gene.mymapper.AreaCustomerMapper;
import cn.com.gene.pojo.Detailaddress;
import cn.com.gene.pojo.DetailaddressExample;
import cn.com.gene.pojo.Surname;
import cn.com.gene.pojo.SurnameExample;
import cn.com.gene.queryvo.AreaCustomer;
import cn.com.gene.service.AreaService;

@Service
public class AreaServiceImpl  implements AreaService{
	
	@Autowired AreaCustomerMapper areaCustomerMapper;
	@Autowired DetailaddressMapper detailaddressMapper;
	@Autowired SurnameMapper surnameMapper;

	@Override
	public Long getcodebycity(String cityname) {
		Long code = areaCustomerMapper.getcodebycity(cityname);
		return code;
	}

	@Override
	public List<AreaCustomer> getnextareabycode(String[] code) {
		return areaCustomerMapper.getnextareabycode(code);
	}

	@Override
	public Long getdetailid(Detailaddress detailaddress) {
		Long detailid = checkeDetailaAddressExists(detailaddress);
		if (detailid < 0 ) {
			detailaddressMapper.insertSelective(detailaddress);
			detailid = detailaddress.getDetailid();
		}
		return detailid;
	}
	
	
	// 检查详情地址是否存在
	public Long checkeDetailaAddressExists(Detailaddress detaileaddress) {
		DetailaddressExample example = new DetailaddressExample();
		DetailaddressExample.Criteria criteria = example.createCriteria();
		if (!"".equals(detaileaddress.getDetailes()) && detaileaddress.getDetailes() != null) {
			criteria.andDetailesEqualTo(detaileaddress.getDetailes());
		}
		if (!"".equals(detaileaddress.getLatitude()) && detaileaddress.getLatitude() != null) {
			criteria.andLatitudeEqualTo(detaileaddress.getLatitude());
		}
		if (!"".equals(detaileaddress.getLongitude()) && detaileaddress.getLongitude() != null) {
			criteria.andLongitudeEqualTo(detaileaddress.getLongitude());
		}
		List<Detailaddress> selectByExample = detailaddressMapper.selectByExample(example);
		if (!selectByExample.isEmpty() || selectByExample.size() > 0) {
			Detailaddress detail_search = selectByExample.get(0);
			return detail_search.getDetailid();
		} else {
			return -1L;
		}

	}

	@Override
	public void insersurname(String string) {
		if (string !=null && !"".equals(string)) {
			Surname insert = new Surname();
			insert.setDetailname(string);
			surnameMapper.insertSelective(insert);
		}
		
	}

	@Override
	public ResultMap changesurname(Long surnameid, String changename) {
		
		// 查询更换的名字的位置
		SurnameExample example = new SurnameExample();
		SurnameExample.Criteria criteria = example.createCriteria();
		criteria.andDetailnameEqualTo(changename);
		List<Surname> chanlist = surnameMapper.selectByExample(example);
		Surname change = null;
		if (chanlist.size() == 1) {
			change = chanlist.get(0);
		}else {
			return ResultMap.build(400, "两个");
		}
		
		changename = change.getDetailname();
		
		// 查询到想要更换的位置
		Surname search = surnameMapper.selectByPrimaryKey(surnameid);
		String searchname = search.getDetailname();
		
		// 两个更新
		search.setDetailname(changename);
		surnameMapper.updateByPrimaryKeySelective(search);
		
		// 
		change.setDetailname(searchname);
		surnameMapper.updateByPrimaryKeySelective(change);
		
		return ResultMap.IS_200();
	}

}
