package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ImMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImMessageMapper {
    int deleteByPrimaryKey(Long messageId);

    int insert(ImMessage record);

    int insertSelective(ImMessage record);

    ImMessage selectByPrimaryKey(Long messageId);


    List<ImMessage> selectByUserId(Long messageId);

    int updateByPrimaryKeySelective(ImMessage record);

    int updateByPrimaryKey(ImMessage record);
}