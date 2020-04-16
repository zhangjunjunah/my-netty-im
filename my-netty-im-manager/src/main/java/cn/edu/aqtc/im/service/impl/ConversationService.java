package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.cache.PersonalMsgCache;
import cn.edu.aqtc.im.cache.UserChannelCache;
import cn.edu.aqtc.im.protocol.ConversationMessage;
import cn.edu.aqtc.im.protocol.FriendMessage;
import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.service.inter.IConversationService;
import cn.edu.aqtc.im.util.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private PersonalMsgCache personalMsgCache;

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
        personalMsgCache.pushMsg(friendMessage.getReceiver(), friendMessage.getSender(), friendMessage);
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

    /**
     * @param messagePayload
     * @Description: 获取历史消息
     * @Param: [messagePayload]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-16
     */
    @Override
    public void getHisMsg(MessagePayload messagePayload) {
        FriendMessage friendMessage = JSONObject.parseObject(messagePayload.getBody(), FriendMessage.class);
        List<FriendMessage> friendMessages = personalMsgCache.getMsg(friendMessage.getReceiver(), friendMessage.getSender());
        List<ConversationMessage> conversationMessages = null;
        if (CommonUtils.objectIsNull(friendMessages)) {
            conversationMessages = new ArrayList<>(0);
        } else {
            conversationMessages = ConversationMessage.convert(friendMessages, friendMessage.getReceiver());
        }
        messagePayload.setBody(CommonUtils.toJSONString(conversationMessages));
        messagePayload.getChannel().writeAndFlush(new TextWebSocketFrame(CommonUtils.toJSONString(messagePayload)));
    }

    private Channel getReceiverChannel(FriendMessage friendMessage) {
        return userChannelCache.getChannelByUserId(friendMessage.getReceiver());
    }
}
