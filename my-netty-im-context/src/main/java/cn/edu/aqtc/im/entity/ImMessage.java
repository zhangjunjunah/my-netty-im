package cn.edu.aqtc.im.entity;

import cn.edu.aqtc.im.constant.MessageSendStatusEnum;
import cn.edu.aqtc.im.util.CommonUtils;
import cn.edu.aqtc.im.util.DateUtils;
import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.Data;

import java.util.Date;

@Data
public class ImMessage {
    private Long messageId;

    private String messageType;

    private String messageContent;

    private Long messageSender;

    private Long messageReceiver;

    private Date sendTime;

    private String sendStatus;

    public ImMessage() {
        this.messageId = SnowflakeIdWorker.getSequenceId();

        this.sendStatus = MessageSendStatusEnum.unprocessed.toString();

        this.sendTime = DateUtils.getCurrentTime().toDate();
    }


    public ImMessage(String messageSender, String messageReceiver) {
        this();
        if (CommonUtils.objectIsNull(messageSender) || CommonUtils.objectIsNull(messageReceiver)) {
            throw new IllegalArgumentException("messageSender or messageReceiver is null");
        }
        this.messageSender = Long.parseLong(messageSender);
        this.messageReceiver = Long.parseLong(messageReceiver);

    }
}