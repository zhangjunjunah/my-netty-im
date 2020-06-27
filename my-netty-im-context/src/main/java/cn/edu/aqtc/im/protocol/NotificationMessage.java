package cn.edu.aqtc.im.protocol;

import cn.edu.aqtc.im.constant.NotificationMessageEnum;
import lombok.Data;

/**
 * @ClassName : NotificationMessage
 * @Description : 通知消息
 * @Author : zhangjj
 * @Date: 2020-06-27
 */
@Data
public class NotificationMessage {

    private NotificationMessageEnum notificationType;

    private String receiver;

    private String sender;
}
