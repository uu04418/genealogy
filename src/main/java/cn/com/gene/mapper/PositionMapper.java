package cn.com.gene.mapper;

import cn.com.gene.pojo.Position;
import cn.com.gene.pojo.PositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositionMapper {
    int countByExample(PositionExample example);

    int deleteByExample(PositionExample example);

    int deleteByPrimaryKey(Long positionid);

    int insert(Position record);

    int insertSelective(Position record);

    List<Position> selectByExample(PositionExample example);

    Position selectByPrimaryKey(Long positionid);

    int updateByExampleSelective(@Param("record") Position record, @Param("example") PositionExample example);

    int updateByExample(@Param("record") Position record, @Param("example") PositionExample example);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}