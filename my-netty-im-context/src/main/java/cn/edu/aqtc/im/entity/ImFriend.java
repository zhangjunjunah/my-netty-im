package cn.edu.aqtc.im.entity;

import cn.edu.aqtc.im.constant.Constants;
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
        imFriend.setRemarkName(Constants.ADMIN_REMARK_NAME);
        imFriend.setFriendId(Constants.ADMIN_USER_ID);
        imFriend.setFriendName(Constants.ADMIN_USERNAME);
        imFriend.setUserId(userId);
        imFriend.setSeqId(SnowflakeIdWorker.getSequenceId());
        return imFriend;
    }

}