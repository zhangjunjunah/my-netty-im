package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.VO.ImFriendRelVO;
import cn.edu.aqtc.im.entity.ImFriendRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImFriendRelMapper {
    int deleteByPrimaryKey(Long friendRel);

    int insert(ImFriendRel record);

    int insertSelective(ImFriendRel record);

    ImFriendRel selectByPrimaryKey(Long friendRel);

    List<ImFriendRelVO> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(ImFriendRel record);

    int updateByPrimaryKey(ImFriendRel record);
}