package cn.com.gene.mapper;

import cn.com.gene.pojo.Genemessconn;
import cn.com.gene.pojo.GenemessconnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenemessconnMapper {
    int countByExample(GenemessconnExample example);

    int deleteByExample(GenemessconnExample example);

    int insert(Genemessconn record);

    int insertSelective(Genemessconn record);

    List<Genemessconn> selectByExample(GenemessconnExample example);

    int updateByExampleSelective(@Param("record") Genemessconn record, @Param("example") GenemessconnExample example);

    int updateByExample(@Param("record") Genemessconn record, @Param("example") GenemessconnExample example);
}