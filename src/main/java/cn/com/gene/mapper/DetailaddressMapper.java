package cn.com.gene.mapper;

import cn.com.gene.pojo.Detailaddress;
import cn.com.gene.pojo.DetailaddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetailaddressMapper {
    int countByExample(DetailaddressExample example);

    int deleteByExample(DetailaddressExample example);

    int deleteByPrimaryKey(Long detailid);

    int insert(Detailaddress record);

    int insertSelective(Detailaddress record);

    List<Detailaddress> selectByExample(DetailaddressExample example);

    Detailaddress selectByPrimaryKey(Long detailid);

    int updateByExampleSelective(@Param("record") Detailaddress record, @Param("example") DetailaddressExample example);

    int updateByExample(@Param("record") Detailaddress record, @Param("example") DetailaddressExample example);

    int updateByPrimaryKeySelective(Detailaddress record);

    int updateByPrimaryKey(Detailaddress record);
}