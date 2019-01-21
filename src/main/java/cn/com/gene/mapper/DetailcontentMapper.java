package cn.com.gene.mapper;

import cn.com.gene.pojo.Detailcontent;
import cn.com.gene.pojo.DetailcontentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetailcontentMapper {
    int countByExample(DetailcontentExample example);

    int deleteByExample(DetailcontentExample example);

    int deleteByPrimaryKey(Long detailcontentid);

    int insert(Detailcontent record);

    int insertSelective(Detailcontent record);

    List<Detailcontent> selectByExample(DetailcontentExample example);

    Detailcontent selectByPrimaryKey(Long detailcontentid);

    int updateByExampleSelective(@Param("record") Detailcontent record, @Param("example") DetailcontentExample example);

    int updateByExample(@Param("record") Detailcontent record, @Param("example") DetailcontentExample example);

    int updateByPrimaryKeySelective(Detailcontent record);

    int updateByPrimaryKey(Detailcontent record);
}