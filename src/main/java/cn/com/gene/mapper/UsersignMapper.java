package cn.com.gene.mapper;

import cn.com.gene.pojo.Usersign;
import cn.com.gene.pojo.UsersignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersignMapper {
    int countByExample(UsersignExample example);

    int deleteByExample(UsersignExample example);

    int deleteByPrimaryKey(Long signid);

    int insert(Usersign record);

    int insertSelective(Usersign record);

    List<Usersign> selectByExample(UsersignExample example);

    Usersign selectByPrimaryKey(Long signid);

    int updateByExampleSelective(@Param("record") Usersign record, @Param("example") UsersignExample example);

    int updateByExample(@Param("record") Usersign record, @Param("example") UsersignExample example);

    int updateByPrimaryKeySelective(Usersign record);

    int updateByPrimaryKey(Usersign record);
}