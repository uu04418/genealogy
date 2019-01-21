package cn.com.gene.mapper;

import cn.com.gene.pojo.Inteconn;
import cn.com.gene.pojo.InteconnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InteconnMapper {
    int countByExample(InteconnExample example);

    int deleteByExample(InteconnExample example);

    int deleteByPrimaryKey(Long userid);

    int insert(Inteconn record);

    int insertSelective(Inteconn record);

    List<Inteconn> selectByExample(InteconnExample example);

    Inteconn selectByPrimaryKey(Long userid);

    int updateByExampleSelective(@Param("record") Inteconn record, @Param("example") InteconnExample example);

    int updateByExample(@Param("record") Inteconn record, @Param("example") InteconnExample example);

    int updateByPrimaryKeySelective(Inteconn record);

    int updateByPrimaryKey(Inteconn record);
}