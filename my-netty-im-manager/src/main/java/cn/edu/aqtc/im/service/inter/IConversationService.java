package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.protocol.MessagePayload;
import io.netty.channel.Channel;

/**
 * @Description: 会话服务
 * @ClassName: IConversationService
 * @Author: zhangjj
 * @Date: 2020-04-15
 */
public interface IConversationService {

    /**
     * @Description: 发送私信
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-15
     */
    void pushPrivateMsg(MessagePayload messagePayload);

    /**
     * @Description: 客户端连接，注册用户
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-15
     */
    void connect(MessagePayload messagePayload);

    /**
     * @Description: 获取历史消息
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-16
     */
    void getHisMsg(MessagePayload messagePayload);

    /**
     * @param messagePayload
     * @return void
     * @Description 服务端pong
     * @Author zhangjj
     * @Date 2020-04-21
     **/
    void pong(MessagePayload messagePayload);

    /**
     * @param channel
     * @return void
     * @Description 用户下线
     * @Author zhangjj
     * @Date 2020-04-26
     **/
    void userOffline(Channel channel);
}
