package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.FriendMessage;
import cn.edu.aqtc.im.cache.UserChannelCache;
import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.service.inter.IConversationService;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 会话服务
 * @ClassName: ConversationService
 * @Author: zhangjj
 * @Date: 2020-04-15
 */
@Service
public class ConversationService implements IConversationService {

    @Autowired
    private UserChannelCache userChannelCache;

    /**
     * @param messagePayload
     * @Description: 发送私信
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-15
     */
    @Override
    public void pushPrivateMsg(MessagePayload messagePayload) {
        FriendMessage friendMessage = JSONObject.parseObject(messagePayload.getBody(), FriendMessage.class);
        Channel channel = getReceiverChannel(friendMessage);
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(messagePayload)));
        }
    }

    /**
     * @param messagePayload
     * @Description: 客户端连接，注册用户
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-15
     */
    @Override
    public void connect(MessagePayload messagePayload) {
        ChatUser chatUser = JSONObject.parseObject(messagePayload.getBody(), ChatUser.class);
        userChannelCache.putUserChannel2Cache(chatUser.getUserId(), messagePayload.getChannel());

    }

    private Channel getReceiverChannel(FriendMessage friendMessage) {
        return userChannelCache.getChannelByUserId(friendMessage.getReceiver());
    }
}
