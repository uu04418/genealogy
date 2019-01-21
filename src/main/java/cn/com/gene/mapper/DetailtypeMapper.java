package cn.com.gene.mapper;

import cn.com.gene.pojo.Detailtype;
import cn.com.gene.pojo.DetailtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetailtypeMapper {
    int countByExample(DetailtypeExample example);

    int deleteByExample(DetailtypeExample example);

    int deleteByPrimaryKey(Integer detailtypeid);

    int insert(Detailtype record);

    int insertSelective(Detailtype record);

    List<Detailtype> selectByExample(DetailtypeExample example);

    Detailtype selectByPrimaryKey(Integer detailtypeid);

    int updateByExampleSelective(@Param("record") Detailtype record, @Param("example") DetailtypeExample example);

    int updateByExample(@Param("record") Detailtype record, @Param("example") DetailtypeExample example);

    int updateByPrimaryKeySelective(Detailtype record);

    int updateByPrimaryKey(Detailtype record);
}