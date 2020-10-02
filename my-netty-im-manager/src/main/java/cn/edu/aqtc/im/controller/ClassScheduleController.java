package cn.edu.aqtc.im.controller;

import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.entity.ClassSchedule;
import cn.edu.aqtc.im.entity.ClassScheduleWithBLOBs;
import cn.edu.aqtc.im.service.impl.ClassScheduleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : ClassScheduleController
 * @Description : 课程表控制器
 * @Author : zhangjj
 * @Date: 2020-10-02
 */
@RestController
@RequestMapping(value = "/api/classSchedule")
@Api(value = "课程表接口", tags = {"课程表接口"})
@Slf4j
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService classScheduleService;

    @RequestMapping(value = "/getClassSchedule/{owner}", method = RequestMethod.GET)
    public RestResult<ClassSchedule> getClassSchedule(@PathVariable String owner) {
        return RestResult.getSuccessRestResult(classScheduleService.getSchedule(owner));
    }

    @RequestMapping(value = "/saveClassSchedule", method = RequestMethod.POST)
    public RestResult saveClassSchedule(@RequestBody ClassScheduleWithBLOBs classScheduleWithBLOBs) {
        classScheduleService.addSchedule(classScheduleWithBLOBs);
        return RestResult.getSuccessRestResult();
    }
}
