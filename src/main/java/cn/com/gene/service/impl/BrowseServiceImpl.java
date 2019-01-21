package cn.com.gene.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gene.mapper.BrowseMapper;
import cn.com.gene.mapper.DetailcontentMapper;
import cn.com.gene.mapper.GenemessMapper;
import cn.com.gene.mapper.ProfileMapper;
import cn.com.gene.pojo.Browse;
import cn.com.gene.pojo.BrowseExample;
import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.Profile;
import cn.com.gene.service.BrowseService;
@Service
public class BrowseServiceImpl implements BrowseService{
	
	@Autowired BrowseMapper browseMapper;
	@Autowired GenemessMapper genemessMapper; 
	@Autowired DetailcontentMapper detailcontentMapper;
	@Autowired ProfileMapper profileMapper;

	@Override
	public void addBrowse(Long otherid, Integer type, Long userid) {
		
		// 校验浏览量是否存在
		Browse searchchcek  = this.checkbrowseexist(otherid , type , userid);
		
		// 获取流量的 followuserid  和 title
		Browse customer = this.getbrowsetitle(otherid , type );
		if (searchchcek != null) {
			Date cuntTime = new Date();
			Long cunt = cuntTime.getTime();
			Long old = searchchcek.getUpdatetime().getTime() + 3600000;
			if (cunt > old) {
				Integer count = searchchcek.getBcount();
				count++;
				searchchcek.setBcount(count);
				searchchcek.setTitle( customer.getTitle()   );
				searchchcek.setUpdatetime(new Date());
				searchchcek.setStatue(1);
				browseMapper.updateByPrimaryKeySelective(searchchcek);
			}
		} else {
			searchchcek = new Browse();
			searchchcek.setUserid(userid);
			searchchcek.setOtherid(otherid);
			searchchcek.setUpdatetime(new Date());
			searchchcek.setBcount(1);
			searchchcek.setType(type);
			searchchcek.setStatue(1);
			searchchcek.setTitle(customer.getTitle());
			searchchcek.setFollowuserid(customer.getFollowuserid());
			browseMapper.insert(searchchcek);
		}
		
	}
	
	// 获取被浏览物品的主键和发布人ID
	private Browse getbrowsetitle(Long otherid, Integer type) {
		String title = "" ;
		Long  followuserid = null;
		// 说明 这里是家谱的 
		if (type == 1) {
			Genemess genemess = genemessMapper.selectByPrimaryKey(otherid);
			if (genemess !=null) {
				Profile profile = profileMapper.selectByPrimaryKey(genemess.getProfileid());
				followuserid = profile.getUserid();
				if (genemess.getPicture() != null && !"".equals(genemess.getPicture())) {
					title = genemess.getPicture().split(",")[0];
				} else {
					title = genemess.getGenename();
				}
			}
			
		}
		
		//这里说明浏览的是氏族发布详情
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
		returnbro.setTitle(title);
		returnbro.setFollowuserid(followuserid);
		return returnbro;
	}

	private Browse checkbrowseexist(Long otherid, Integer type ,Long userid) {
		BrowseExample example = new BrowseExample();
		BrowseExample.Criteria criteria = example.createCriteria();
		criteria.andOtheridEqualTo(otherid);
		criteria.andTypeEqualTo(type);
		criteria.andUseridEqualTo(userid);
		List<Browse> browses =browseMapper.selectByExample(example);
		if ( !browses.isEmpty() && browses.size() > 0) {
			return browses.get(0);
		}
		return null;
	}


}
