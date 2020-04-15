package cn.edu.aqtc.im.protocol;

import cn.edu.aqtc.im.constant.MessageSign;
import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 消息载体
 * @ClassName: MessagePayload
 * @Author: zhangjj
 * @Date: 2020-04-15
 */
@Data
public class MessagePayload implements Serializable {

    /**
     * 消息标识
     */
    private MessageSign sign;

    /**
     * 消息内容(JSON格式)
     */
    private String body;


    /**
     * 消息接收通道
     */
    private Channel channel;

}
