package cn.com.gene.mapper;

import cn.com.gene.pojo.Lucre;
import cn.com.gene.pojo.LucreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LucreMapper {
    int countByExample(LucreExample example);

    int deleteByExample(LucreExample example);

    int deleteByPrimaryKey(Long lucreid);

    int insert(Lucre record);

    int insertSelective(Lucre record);

    List<Lucre> selectByExample(LucreExample example);

    Lucre selectByPrimaryKey(Long lucreid);

    int updateByExampleSelective(@Param("record") Lucre record, @Param("example") LucreExample example);

    int updateByExample(@Param("record") Lucre record, @Param("example") LucreExample example);

    int updateByPrimaryKeySelective(Lucre record);

    int updateByPrimaryKey(Lucre record);
}