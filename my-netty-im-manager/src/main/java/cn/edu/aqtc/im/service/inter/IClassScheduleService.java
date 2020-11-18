package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;

/**
 * @Description:
 * @ClassName: IClassScheduleService
 * @Author: zhangjj
 * @Date: 2020-09-30
 */
public interface IClassScheduleService {

    /**
     * 添加课程
     *
     * @param classScheduleWithBLOBs
     * @throws
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-09-30
     */
    void addSchedule(ClassScheduleWithBLOBs classScheduleWithBLOBs);


    /**
     * 获取课程
     *
     * @param Owner
     * @throws
     * @return: cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs
     * @Author: zhangjj
     * @Date: 2020-09-30
     */
    ClassScheduleWithBLOBs getSchedule(String Owner);

}
