package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ClassSchedule;
import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClassScheduleMapper {
    int deleteByPrimaryKey(Long classScheduleId);

    int insert(ClassScheduleWithBLOBs record);

    int insertSelective(ClassScheduleWithBLOBs record);

    ClassScheduleWithBLOBs selectByPrimaryKey(Long classScheduleId);

    int updateByPrimaryKeySelective(ClassScheduleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClassScheduleWithBLOBs record);

    int updateByPrimaryKey(ClassSchedule record);
}