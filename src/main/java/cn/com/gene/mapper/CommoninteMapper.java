package cn.com.gene.mapper;

import cn.com.gene.pojo.Commoninte;
import cn.com.gene.pojo.CommoninteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommoninteMapper {
    int countByExample(CommoninteExample example);

    int deleteByExample(CommoninteExample example);

    int deleteByPrimaryKey(Integer commonid);

    int insert(Commoninte record);

    int insertSelective(Commoninte record);

    List<Commoninte> selectByExample(CommoninteExample example);

    Commoninte selectByPrimaryKey(Integer commonid);

    int updateByExampleSelective(@Param("record") Commoninte record, @Param("example") CommoninteExample example);

    int updateByExample(@Param("record") Commoninte record, @Param("example") CommoninteExample example);

    int updateByPrimaryKeySelective(Commoninte record);

    int updateByPrimaryKey(Commoninte record);
}