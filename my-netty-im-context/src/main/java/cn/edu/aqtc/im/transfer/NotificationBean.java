package cn.edu.aqtc.im.transfer;

import cn.edu.aqtc.im.entity.ImMessage;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : NotifitionBean
 * @Description : 通知消息Bean
 * @Author : zhangjj
 * @Date: 2020-07-06
 */
@Data
public class NotificationBean {

    private String userId;

    private List<ImMessage> imMessageList;


}
