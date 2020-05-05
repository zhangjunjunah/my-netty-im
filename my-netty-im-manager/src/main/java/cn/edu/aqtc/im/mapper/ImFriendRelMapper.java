package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ImFriendRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImFriendRelMapper {
    int deleteByPrimaryKey(Long friendRel);

    int insert(ImFriendRel record);

    int insertSelective(ImFriendRel record);

    ImFriendRel selectByPrimaryKey(Long friendRel);

    int updateByPrimaryKeySelective(ImFriendRel record);

    int updateByPrimaryKey(ImFriendRel record);
}