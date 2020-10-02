package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.constant.Constants;
import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;
import cn.edu.aqtc.im.mapper.ClassScheduleMapper;
import cn.edu.aqtc.im.service.inter.IClassScheduleService;
import cn.edu.aqtc.im.util.CommonUtils;
import cn.edu.aqtc.im.util.DateUtils;
import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @ClassName: ClassScheduleService
 * @Author: zhangjj
 * @Date: 2020-09-30
 */
@Service
@Slf4j
public class ClassScheduleService implements IClassScheduleService {

    @Autowired
    private ClassScheduleMapper classScheduleMapper;


    /**
     * 添加课程
     *
     * @param classScheduleWithBLOBs
     * @throws
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-09-30
     */
    @Override
    public void addSchedule(ClassScheduleWithBLOBs classScheduleWithBLOBs) {
        classScheduleWithBLOBs.setClassScheduleOwner(Constants.CLASS_SCHEDULE_DEFAULT_OWNER);
        classScheduleWithBLOBs.setUpdateTime(DateUtils.getCurrentTime().toDate());
        if (CommonUtils.objectIsNull(classScheduleMapper.selectByOwner(classScheduleWithBLOBs))) {
            classScheduleWithBLOBs.setClassScheduleId(SnowflakeIdWorker.getSequenceId());
            classScheduleMapper.insertSelective(classScheduleWithBLOBs);
        } else {
            classScheduleMapper.updateByOwnerWithBLOBs(classScheduleWithBLOBs);
        }
    }

    /**
     * 获取课程
     *
     * @param Owner
     * @throws
     * @return: cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs
     * @Author: zhangjj
     * @Date: 2020-09-30
     */
    @Override
    public ClassScheduleWithBLOBs getSchedule(String Owner) {
        ClassScheduleWithBLOBs classScheduleWithBLOBs = new ClassScheduleWithBLOBs();
        classScheduleWithBLOBs.setClassScheduleOwner(Owner);
        return classScheduleMapper.selectByOwner(classScheduleWithBLOBs);
    }
}
