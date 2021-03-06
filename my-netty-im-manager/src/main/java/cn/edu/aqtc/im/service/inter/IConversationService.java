package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.entity.ImMessage;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.transfer.FriendBean;
import io.netty.channel.Channel;

import java.util.List;

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

    /**
     * @param userId
     * @return java.util.List<cn.edu.aqtc.im.transfer.FriendBean>
     * @Description 获取会话列表
     * @Author zhangjj
     * @Date 2020-05-26
     **/
    List<FriendBean> getConversionList(String userId);

    /**
     * @param userId
     * @param myBean
     * @param friendBean
     * @return void
     * @Description 添加会话到列表
     * @Author zhangjj
     * @Date 2020-05-26
     **/
    void addConversion2List(String userId, ImUser myBean, FriendBean friendBean);

    /**
     * @param messagePayload
     * @return void
     * @Description 刷新会话
     * @Author zhangjj
     * @Date 2020-05-28
     **/
    void flushConversion(MessagePayload messagePayload);

    /**
     * @param imMessage
     * @return void
     * @Description 添加好友
     * @Author zhangjj
     * @Date 2020-06-27
     **/
    void addFriends(ImMessage imMessage);

    /**
     * @param messagePayload
     * @return void
     * @Description 消息通知
     * @Author zhangjj
     * @Date 2020-06-27
     **/
    void notification(MessagePayload messagePayload);

    /**
     * @param messagePayload
     * @return void
     * @Description 获取通知
     * @Author zhangjj
     * @Date 2020-07-06
     **/
    void getNotification(MessagePayload messagePayload);
}
