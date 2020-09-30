package cn.edu.aqtc.im.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClassSchedule {
    private Long classScheduleId;

    private String classScheduleOwner;

    private Date updateTime;

}