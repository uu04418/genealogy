package cn.com.gene.service;

import java.util.List;

import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Detailaddress;
import cn.com.gene.queryvo.AreaCustomer;

public interface AreaService {
	
	/**
	 * 通过cityname 获取citycode
	 * **/
	Long getcodebycity(String cityname);
	
	/**
	 * 根据code 获取下级区域
	 * **/
	List<AreaCustomer> getnextareabycode(String[] str);
	
	
	/**
	 * 获取详情地址的ID
	 * **/
	Long getdetailid(Detailaddress detailaddress);
	
	/**
	 * 插入姓氏
	 * @param string : 姓氏名称
	 * **/ 
	void insersurname(String string);
	
	/**
	 * 更换姓氏未知
	 * @param  surnameid : 姓氏未知
	 * @param  changename ： 修改的姓氏名称
	 * **/
	ResultMap changesurname(Long surnameid, String changename);

}
