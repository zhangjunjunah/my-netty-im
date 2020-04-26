package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.cache.PersonalMsgCache;
import cn.edu.aqtc.im.cache.UserChannelCache;
import cn.edu.aqtc.im.constant.MessageSign;
import cn.edu.aqtc.im.constant.UserConstants;
import cn.edu.aqtc.im.constant.UserStatusEnum;
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
        //将用户 通道放到缓存中
        ChatUser chatUser = JSONObject.parseObject(messagePayload.getBody(), ChatUser.class);
        //保存用户通道关系
        setUserChannelRel(chatUser, messagePayload.getChannel());
        //调整用户状态为上线状态
        chatUser = adjustChatUserStatus(chatUser, UserStatusEnum.ONLINE);
        //广播所有用户，更新好友列表状态
        broadcastFriendList(chatUser);
    }

    private void setUserChannelRel(ChatUser chatUser, Channel channel) {
        userChannelCache.putUserChannel2Cache(chatUser.getUserId(), channel);
        userChannelCache.putChannelUser2Cache(channel.id().toString(), chatUser);
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

    /**
     * @param messagePayload
     * @return void
     * @Description 服务端pong
     * @Author zhangjj
     * @Date 2020-04-21
     **/
    @Override
    public void pong(MessagePayload messagePayload) {
        messagePayload.setSign(MessageSign.PONG);
        messagePayload.getChannel().writeAndFlush(new TextWebSocketFrame(CommonUtils.toJSONString(messagePayload)));
    }

    /**
     * @param channel
     * @return void
     * @Description 用户下线
     * @Author zhangjj
     * @Date 2020-04-26
     **/
    @Override
    public void userOffline(Channel channel) {
        ChatUser chatUser = userChannelCache.getUserByChannelId(channel.id().toString());
        chatUser.setUserStatus(UserStatusEnum.OFFLINE);
        UserConstants.updateFriendCache(chatUser);
        broadcastFriendList(chatUser);
        userChannelCache.removeChannelUser(channel.id().toString());
        userChannelCache.removeUserChannel(chatUser.getUserId());
    }

    /**
     * @Description: 获取接收者的channel
     * @Param: [friendMessage]
     * @return: io.netty.channel.Channel
     * @Author: zhangjj
     * @Date: 2020-04-22
     */
    private Channel getReceiverChannel(FriendMessage friendMessage) {
        return userChannelCache.getChannelByUserId(friendMessage.getReceiver());
    }

    /**
     * @Description: 调整用户状态
     * @Param: [chatUser, online]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-22
     */
    private ChatUser adjustChatUserStatus(ChatUser chatUser, UserStatusEnum userStatusEnum) {
        ChatUser search = UserConstants.getFriendCache().getIfPresent(chatUser.getUserId());
        if (CommonUtils.objectIsNull(search)) {
            return null;
        }
        search.setUserStatus(userStatusEnum);
        UserConstants.updateFriendCache(search);
        return search;
    }

    /**
     * @param chatUser
     * @Description: 广播朋友列表
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-22
     */
    private void broadcastFriendList(ChatUser chatUser) {
        if (CommonUtils.objectIsNull(chatUser)) {
            return;
        }
        MessagePayload messagePayload = new MessagePayload();
        messagePayload.setSign(MessageSign.PUBLISH_FRIEND);
        messagePayload.setBody(CommonUtils.toJSONString(chatUser));
        UserChannelCache.channelGroup.writeAndFlush(new TextWebSocketFrame(CommonUtils.toJSONString(messagePayload)));
    }
}
