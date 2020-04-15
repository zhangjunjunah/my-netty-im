package cn.edu.aqtc.im.protocol;

import lombok.Data;

/**
 * @ClassName : MessageRecord
 * @Description : 消息记录
 * @Author : zhangjj
 * @Date: 2020-04-15
 */
@Data
public class MessageRecord {

    private String content;

    private boolean self;

    private String timeStr;

}
