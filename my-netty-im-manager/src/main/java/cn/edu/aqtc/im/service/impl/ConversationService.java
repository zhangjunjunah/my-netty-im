package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.cache.ConversationCache;
import cn.edu.aqtc.im.cache.PersonalMsgCache;
import cn.edu.aqtc.im.cache.UserChannelCache;
import cn.edu.aqtc.im.constant.MessageSign;
import cn.edu.aqtc.im.constant.UserConstants;
import cn.edu.aqtc.im.constant.UserStatusEnum;
import cn.edu.aqtc.im.entity.ImFriendRel;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.mapper.ImFriendRelMapper;
import cn.edu.aqtc.im.protocol.ConversationMessage;
import cn.edu.aqtc.im.protocol.FriendMessage;
import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.service.inter.IConversationService;
import cn.edu.aqtc.im.transfer.FriendBean;
import cn.edu.aqtc.im.transfer.StartConversationBean;
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

    @Autowired
    private ConversationCache conversationCache;

    @Autowired
    private ImFriendRelMapper imFriendRelMapper;

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
        FriendMessage friendMessage = ((JSONObject) messagePayload.getBody()).toJavaObject(FriendMessage.class);
        Channel channel = getReceiverChannel(friendMessage);
        personalMsgCache.pushMsg(friendMessage.getReceiver(), friendMessage.getSender(), friendMessage);
        personalMsgCache.pushMsg(friendMessage.getSender(), friendMessage.getReceiver(), friendMessage);
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
        ChatUser chatUser = ((JSONObject) messagePayload.getBody()).toJavaObject(ChatUser.class);
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
        FriendMessage friendMessage = ((JSONObject) messagePayload.getBody()).toJavaObject(FriendMessage.class);
        List<FriendMessage> friendMessages = personalMsgCache.getMsg(friendMessage.getSender(), friendMessage.getReceiver());
        List<ConversationMessage> conversationMessages = null;
        if (CommonUtils.objectIsNull(friendMessages)) {
            conversationMessages = new ArrayList<>(0);
        } else {
            conversationMessages = ConversationMessage.convert(friendMessages, friendMessage.getReceiver());
        }
        messagePayload.setBody(conversationMessages);
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
     * @param userId
     * @return java.util.List<cn.edu.aqtc.im.transfer.FriendBean>
     * @Description 获取会话列表
     * @Author zhangjj
     * @Date 2020-05-26
     **/
    @Override
    public List<FriendBean> getConversionList(String userId) {
        return conversationCache.getConversation(userId);
    }

    /**
     * @param userId
     * @param imUser
     * @param friendBean
     * @return void
     * @Description 添加会话到列表
     * @Author zhangjj
     * @Date 2020-05-26
     **/
    @Override
    public void addConversion2List(String userId, ImUser imUser, FriendBean friendBean) {
        //将好友添加到会话列表
        conversationCache.addConversation(userId, friendBean);
        //在好友的会话列表添加自己
        ImFriendRel imFriendRel = imFriendRelMapper.selectByUserAndFriendId(new ImFriendRel().setUserId(Long.parseLong(friendBean.getFriendId())).setFriendId(imUser.getUserId()));
        FriendBean myBean = FriendBean.toFriendBean(imUser);
        if (!CommonUtils.objectIsNull(imFriendRel)) {
            myBean.setRemarkName(imFriendRel.getRemarkName());
        }
        conversationCache.addConversation(friendBean.getFriendId(), myBean);
        //发送websocket消息
        sendConversationMessage(imUser, friendBean);

    }

    /**
     * @param messagePayload
     * @return void
     * @Description 刷新会话
     * @Author zhangjj
     * @Date 2020-05-28
     **/
    @Override
    public void flushConversion(MessagePayload messagePayload) {
        StartConversationBean startConversationBean = ((JSONObject) messagePayload.getBody()).toJavaObject(StartConversationBean.class);
        addConversion2List(startConversationBean.getUserId(), startConversationBean.getMyBean(), startConversationBean.getFriendBean());
    }

    private void sendConversationMessage(ImUser myBean, FriendBean friendBean) {
        Channel channel = getChannelByUserId(Long.parseLong(friendBean.getFriendId()));
        MessagePayload<List<FriendBean>> messagePayload = new MessagePayload();
        messagePayload.setSign(MessageSign.FLUSH_CONVERSION);
        messagePayload.setBody(conversationCache.getConversation(friendBean.getFriendId()));
        if (!CommonUtils.objectIsNull(channel)) {
            channel.writeAndFlush(new TextWebSocketFrame(CommonUtils.toJSONString(messagePayload)));
        }

        channel = getChannelByUserId(myBean.getUserId());
        messagePayload.setBody(conversationCache.getConversation(myBean.getUserId().toString()));
        if (!CommonUtils.objectIsNull(channel)) {
            channel.writeAndFlush(new TextWebSocketFrame(CommonUtils.toJSONString(messagePayload)));
        }
    }

    /**
     * @Description: 获取接收者的channel
     * @Param: [friendMessage]
     * @return: io.netty.channel.Channel
     * @Author: zhangjj
     * @Date: 2020-04-22
     */
    private Channel getReceiverChannel(FriendMessage friendMessage) {
        return userChannelCache.getChannelByUserId(Long.parseLong(friendMessage.getReceiver()));
    }

    /**
     * @param userId
     * @return io.netty.channel.Channel
     * @Description 根据UserId获取通道
     * @Author zhangjj
     * @Date 2020-05-26
     **/
    private Channel getChannelByUserId(Long userId) {
        return userChannelCache.getChannelByUserId(userId);
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
