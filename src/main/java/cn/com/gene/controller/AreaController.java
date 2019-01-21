package cn.com.gene.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gene.comm.ResultMap;
import cn.com.gene.queryvo.AreaCustomer;
import cn.com.gene.service.AreaService;

@Controller
public class AreaController {
	
	@Autowired AreaService areaService;
	
	//通过城市的code获取城市的信息
	@RequestMapping(value = "/area/getareabycode")
	@ResponseBody
	public ResultMap getareabycode(@RequestParam(defaultValue = "0") String code,String cityname) {
		if (!"".equals(cityname) && cityname !=null) {
			code = areaService.getcodebycity(cityname)+"";
		}
		String[] str = code.split(",");
		List<AreaCustomer> arealist = areaService.getnextareabycode(str);
		return ResultMap.IS_200(arealist);
	}
	
	
	// 插入姓氏
	@ResponseBody
	@RequestMapping("/gene/insersurname")
	public ResultMap insersurname (String keyword) {
		String [] str = keyword.split(" ");
		for (int i = 0 ;i<str.length;i++) {
			areaService.insersurname(str[i]);
		}
		return ResultMap.IS_200();
	}
	
	/**更换姓氏
	 * @param surnameid : 更换的位置
	 * @param changename : 更换的名称
	 * **/ 
	@RequestMapping("/gene/changesurname")
	@ResponseBody
	public ResultMap changesurname (Long surnameid ,String changename) {
		ResultMap resultMap = areaService.changesurname( surnameid , changename);
		return resultMap ;
	}

}
