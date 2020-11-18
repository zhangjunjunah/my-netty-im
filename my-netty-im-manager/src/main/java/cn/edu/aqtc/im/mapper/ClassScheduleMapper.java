package cn.edu.aqtc.im.mapper;

import cn.edu.aqtc.im.entity.ClassSchedule;
import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ClassScheduleMapper {
    int deleteByPrimaryKey(Long classScheduleId);

    int insert(ClassScheduleWithBLOBs record);

    int insertSelective(ClassScheduleWithBLOBs record);

    ClassScheduleWithBLOBs selectByOwner(ClassScheduleWithBLOBs classScheduleWithBLOBs);

    List<ClassScheduleWithBLOBs> select(ClassScheduleWithBLOBs classScheduleWithBLOBs);

    int updateByPrimaryKeySelective(ClassScheduleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClassScheduleWithBLOBs record);


    int updateByOwnerWithBLOBs(ClassScheduleWithBLOBs record);

    int updateByPrimaryKey(ClassSchedule record);
}