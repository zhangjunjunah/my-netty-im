package cn.edu.aqtc.im.protocol;

import cn.edu.aqtc.im.util.CommonUtils;
import cn.edu.aqtc.im.util.DateUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 会话消息，与前端消息格式保持一致
 * @ClassName: ConversationMessage
 * @Author: zhangjj
 * @Date: 2020-04-16
 */
@Data
@Accessors(chain = true)
public class ConversationMessage {

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 消息发送人是否是自己
     */
    private boolean self;

    /**
     * 消息发送人
     */
    private String sender;


    /**
     * 消息发送时间字符串格式(2020-04-16 10:00:00)
     */
    private String timeStr;

    /**
     * @Description: 好友消息转换成会话消息
     * @Param: [friendMessages]
     * @return: java.util.List<cn.edu.aqtc.im.protocol.ConversationMessage>
     * @Author: zhangjj
     * @Date: 2020-04-16
     */
    public static List<ConversationMessage> convert(List<FriendMessage> friendMessages, String currentUser) {
        List<ConversationMessage> conversationMessages = new ArrayList<>(friendMessages.size());
        for (FriendMessage friendMessage : friendMessages) {
            ConversationMessage conversationMessage = new ConversationMessage()
                    .setContent(friendMessage.getContent())
                    .setSender(friendMessage.getSender())
                    .setSelf(currentUser)
                    .setTimeStr(DateUtils.getDateToString(friendMessage.getSendDate()));
            conversationMessages.add(conversationMessage);
        }
        return conversationMessages;

    }

    public ConversationMessage setSelf(String currentUser) {
        if (CommonUtils.objectIsNull(currentUser)) {
            throw new IllegalArgumentException("currentUser is illegal");
        }
        this.self = currentUser.equals(this.sender) ? true : false;
        return this;
    }

}
