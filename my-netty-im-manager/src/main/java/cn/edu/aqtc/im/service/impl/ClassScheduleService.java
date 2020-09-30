package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;
import cn.edu.aqtc.im.mapper.ClassScheduleMapper;
import cn.edu.aqtc.im.service.inter.IClassScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @ClassName: ClassScheduleService
 * @Author: zhangjj
 * @Date: 2020-09-30
 */
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

    }

    /**
     * 获取课程
     *
     * @param classScheduleWithBLOBs
     * @throws
     * @return: cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs
     * @Author: zhangjj
     * @Date: 2020-09-30
     */
    @Override
    public ClassScheduleWithBLOBs getSchedule(ClassScheduleWithBLOBs classScheduleWithBLOBs) {
        return null;
    }
}
