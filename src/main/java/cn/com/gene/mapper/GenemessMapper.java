package cn.com.gene.mapper;

import cn.com.gene.pojo.Genemess;
import cn.com.gene.pojo.GenemessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenemessMapper {
    int countByExample(GenemessExample example);

    int deleteByExample(GenemessExample example);

    int deleteByPrimaryKey(Long genemessid);

    int insert(Genemess record);

    int insertSelective(Genemess record);

    List<Genemess> selectByExample(GenemessExample example);

    Genemess selectByPrimaryKey(Long genemessid);

    int updateByExampleSelective(@Param("record") Genemess record, @Param("example") GenemessExample example);

    int updateByExample(@Param("record") Genemess record, @Param("example") GenemessExample example);

    int updateByPrimaryKeySelective(Genemess record);

    int updateByPrimaryKey(Genemess record);
}