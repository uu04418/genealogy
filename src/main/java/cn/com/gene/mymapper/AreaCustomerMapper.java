package cn.com.gene.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gene.queryvo.AreaCustomer;

public interface AreaCustomerMapper {
	
	/**
	 * 通过cityname 获取citycode
	 * **/
	Long getcodebycity(String cityname);
	
	/**
	 * 根据code 获取
	 * **/
	List<AreaCustomer> getnextareabycode(@Param ("idsList")String[] code);
   
}