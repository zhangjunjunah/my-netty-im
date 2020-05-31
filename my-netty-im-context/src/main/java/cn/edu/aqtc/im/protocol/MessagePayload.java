package cn.edu.aqtc.im.protocol;

import cn.edu.aqtc.im.constant.MessageSign;
import com.alibaba.fastjson.annotation.JSONField;
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
public class MessagePayload<T> implements Serializable {

    /**
     * 消息标识
     */
    private MessageSign sign;

    /**
     * 消息内容(JSON格式)
     */
    private T body;


    /**
     * 消息接收通道
     */
    @JSONField(serialize = false)
    private Channel channel;

}
