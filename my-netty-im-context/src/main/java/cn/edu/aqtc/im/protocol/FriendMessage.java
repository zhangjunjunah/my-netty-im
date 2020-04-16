package cn.edu.aqtc.im.protocol;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 发送好友信息
 * @ClassName: FriendMessage
 * @Author: zhangjj
 * @Date: 2020-04-10
 */
@Data
public class FriendMessage implements Serializable {

    private String receiver;

    private String sender;

    private String content;

    private Date sendDate;

}
