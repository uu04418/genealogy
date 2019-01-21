package cn.com.gene.mapper;

import cn.com.gene.pojo.Messagesreply;
import cn.com.gene.pojo.MessagesreplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessagesreplyMapper {
    int countByExample(MessagesreplyExample example);

    int deleteByExample(MessagesreplyExample example);

    int deleteByPrimaryKey(Long messagesreplyid);

    int insert(Messagesreply record);

    int insertSelective(Messagesreply record);

    List<Messagesreply> selectByExample(MessagesreplyExample example);

    Messagesreply selectByPrimaryKey(Long messagesreplyid);

    int updateByExampleSelective(@Param("record") Messagesreply record, @Param("example") MessagesreplyExample example);

    int updateByExample(@Param("record") Messagesreply record, @Param("example") MessagesreplyExample example);

    int updateByPrimaryKeySelective(Messagesreply record);

    int updateByPrimaryKey(Messagesreply record);
}