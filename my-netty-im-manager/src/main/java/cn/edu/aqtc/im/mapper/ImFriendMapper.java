package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ImFriend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImFriendMapper {
    int deleteByPrimaryKey(Long seqId);

    int insert(ImFriend record);

    int insertSelective(ImFriend record);

    ImFriend selectByPrimaryKey(Long seqId);

    int updateByPrimaryKeySelective(ImFriend record);

    int updateByPrimaryKey(ImFriend record);
}