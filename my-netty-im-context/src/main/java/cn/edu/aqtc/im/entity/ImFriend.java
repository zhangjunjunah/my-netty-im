package cn.edu.aqtc.im.entity;

import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.Data;

import java.util.Date;

@Data
public class ImFriend {
    private Long seqId;

    private Long friendId;

    private String friendName;

    private String remarkName;

    private Long userId;

    private Date createTime;

    public static ImFriend getDefaultFriend(Long userId) {
        ImFriend imFriend = new ImFriend();
        imFriend.setCreateTime(new Date());
        imFriend.setRemarkName("IM系统团队");
        imFriend.setFriendId(100000000000000000L);
        imFriend.setFriendName("机锋小摩托");
        imFriend.setUserId(userId);
        imFriend.setSeqId(SnowflakeIdWorker.getSequenceId());
        return imFriend;
    }

}