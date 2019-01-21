package cn.com.gene.mapper;

import cn.com.gene.pojo.Userconn;
import cn.com.gene.pojo.UserconnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserconnMapper {
    int countByExample(UserconnExample example);

    int deleteByExample(UserconnExample example);

    int deleteByPrimaryKey(Long profileid);

    int insert(Userconn record);

    int insertSelective(Userconn record);

    List<Userconn> selectByExample(UserconnExample example);

    Userconn selectByPrimaryKey(Long profileid);

    int updateByExampleSelective(@Param("record") Userconn record, @Param("example") UserconnExample example);

    int updateByExample(@Param("record") Userconn record, @Param("example") UserconnExample example);

    int updateByPrimaryKeySelective(Userconn record);

    int updateByPrimaryKey(Userconn record);
}