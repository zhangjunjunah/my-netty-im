package cn.edu.aqtc.im.serivce;

import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.service.inter.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息分发器
 * @ClassName: MessageDispatcher
 * @Author: zhangjj
 * @Date: 2020-04-15
 */
@Component
public class MessageDispatcher {

    @Autowired
    private IConversationService conversationService;

    /**
     * @Description: 消息分发
     * @Param: []
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-15
     */
    public void dispatch(MessagePayload messagePayload) {

        switch (messagePayload.getSign()) {
            case PING:
                conversationService.pong(messagePayload);
                break;
            case CONNECT:
                conversationService.connect(messagePayload);
                break;
            case PUBLISH_PRIVATE:
                conversationService.pushPrivateMsg(messagePayload);
                break;
            case GET_HIS_MSG:
                conversationService.getHisMsg(messagePayload);
                break;
            case FLUSH_CONVERSION:
                conversationService.flushConversion(messagePayload);
                break;
            default:
                break;
        }
    }

}
