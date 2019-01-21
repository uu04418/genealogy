package cn.com.gene.service;

public interface BrowseService {
	
	// 添加浏览量
	void addBrowse(Long otherid, Integer type, Long userid);

}
