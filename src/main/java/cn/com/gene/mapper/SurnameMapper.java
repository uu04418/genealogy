package cn.com.gene.mapper;

import cn.com.gene.pojo.Surname;
import cn.com.gene.pojo.SurnameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurnameMapper {
    int countByExample(SurnameExample example);

    int deleteByExample(SurnameExample example);

    int deleteByPrimaryKey(Long surnameid);

    int insert(Surname record);

    int insertSelective(Surname record);

    List<Surname> selectByExample(SurnameExample example);

    Surname selectByPrimaryKey(Long surnameid);

    int updateByExampleSelective(@Param("record") Surname record, @Param("example") SurnameExample example);

    int updateByExample(@Param("record") Surname record, @Param("example") SurnameExample example);

    int updateByPrimaryKeySelective(Surname record);

    int updateByPrimaryKey(Surname record);
}