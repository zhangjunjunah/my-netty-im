package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.protocol.MessagePayload;

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
}
