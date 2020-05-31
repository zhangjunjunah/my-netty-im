package cn.edu.aqtc.im.constant;

/**
 * @Description: 消息信号
 * @ClassName: MessageSign
 * @Author: zhangjj
 * @Date: 2020-04-15
 */
public enum MessageSign {

    /**
     * 连接
     */
    CONNECT,
    /**
     * 断开连接
     */
    DISCONNECT,
    CONNECT_ACK,
    /**
     * 获取消息(历史)
     */
    GET_HIS_MSG,
    /**
     * 客户端ping
     */
    PING,
    /**
     * 服务端pong
     */
    PONG,
    /**
     * 广播朋友状态信息
     */
    PUBLISH_FRIEND,
    /**
     * 刷新会话
     */
    FLUSH_CONVERSION,
    /**
     * 发送私信
     */
    PUBLISH_PRIVATE;

}
