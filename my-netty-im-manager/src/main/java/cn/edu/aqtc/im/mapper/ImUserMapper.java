package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ImUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ImUser record);

    int insertSelective(ImUser record);

    ImUser selectByPrimaryKey(Long userId);

    ImUser selectByName(String userName);


    ImUser select(ImUser imUser);


    int updateByPrimaryKeySelective(ImUser record);

    int updateByPrimaryKey(ImUser record);

    List<ImUser> selectFuzzy(ImUser imUser);
}